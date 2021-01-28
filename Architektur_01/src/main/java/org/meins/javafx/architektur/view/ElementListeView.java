/*
 *   This code is released under Creative Commons Attribution 4.0 International
 *   (CC BY 4.0) license, http://creativecommons.org/licenses/by/4.0/legalcode .
 *   That means:
 *
 *   You are free to:
 *
 *       Share — copy and redistribute the material in any medium or format
 *       Adapt — remix, transform, and build upon the material
 *                for any purpose, even commercially.
 *
 *       The licensor cannot revoke these freedoms as long as you follow the
 *       license terms.
 *
 *   Under the following terms:
 *
 *       Attribution — You must give appropriate credit, provide a link to the
 *       license, and indicate if changes were made. You may do so in any
 *       reasonable manner, but not in any way that suggests the licensor endorses
 *       you or your use.
 *
 *   No additional restrictions — You may not apply legal terms or technological
 *   measures that legally restrict others from doing anything the license
 *   permits.
 *
 *
 *   2021 aeonium software systems UG (haftungsbeschränkt), Robert Rohm.
 */
package org.meins.javafx.architektur.view;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import org.meins.javafx.architektur.conf.GuiceModule;
import org.meins.javafx.architektur.model.Baustein;
import org.meins.javafx.architektur.model.Element;
import org.meins.javafx.architektur.viewmodel.ElementListeViewModel;

/**
 * View-Baustein für die Darstellung der Element-Model-Liste, nach "View
 * first"-Ansatz implementiert, d.h., diese View steuert die Erzeugung und
 * Verdrahtung des eigenen ViewModels selbst (und nicht umgekehrt).
 * <p>
 * Die Implementierung als "View First" macht u.a. dann Sinn, wenn diese View
 * deklarativ in FXML verwendet wird, da in diesem Fall die Konstruktion der 
 * Instanz durch den FXMLLoader erfolgt. </p>
 *
 * @author robert rohm
 */
public class ElementListeView extends TitledPane implements Initializable {

  @FXML
  private TitledPane pane;

  @FXML
  private TableView<Element> tvElemente;

  @FXML
  private TableColumn<Element, String> colName;

  @FXML
  private TableColumn<Element, String> colBaustein1;

  @FXML
  private TableColumn<Element, String> colBaustein2;

  @FXML
  private TableColumn<Element, Baustein> colBaustein3;
  @FXML
  private BausteinAuswahlView pnBausteinAuswahl1;
  @FXML
  private BausteinAuswahlView pnBausteinAuswahl2;
  @FXML
  private BausteinAuswahlView pnBausteinAuswahl3;

  private ElementListeViewModel viewModel;

  @SuppressWarnings("LeakingThisInConstructor")
  public ElementListeView() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/meins/javafx/architektur/app/ElementListe.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);

    try {
      fxmlLoader.load();

    } catch (IOException ex) {
      // TODO: Einheitliche Fehlerbehandlung
      ex.printStackTrace();
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Injector injector = Guice.createInjector(new GuiceModule());

    this.viewModel = injector.getInstance(ElementListeViewModel.class);
    
    this.tvElemente.disableProperty().bind(viewModel.getDisabledProperty());
    this.pnBausteinAuswahl1.disableProperty().bind(viewModel.selektiertesElementProperty().isNull());
    this.pnBausteinAuswahl2.disableProperty().bind(viewModel.selektiertesElementProperty().isNull());
    this.pnBausteinAuswahl3.disableProperty().bind(viewModel.selektiertesElementProperty().isNull());
    this.tvElemente.itemsProperty().set(viewModel.getElementListe());

    this.viewModel.setBausteinViewModel1(this.pnBausteinAuswahl1.getViewModel());
    this.viewModel.setBausteinViewModel2(this.pnBausteinAuswahl2.getViewModel());
    this.viewModel.setBausteinViewModel3(this.pnBausteinAuswahl3.getViewModel());
    this.viewModel.aktualisiereElementListe();
    this.viewModel.selektiertesElementProperty().bind(this.tvElemente.getSelectionModel().selectedItemProperty());
    this.viewModel.setOnElementBearbeitet(() -> this.tvElemente.refresh());
    this.initialisiereTableColumns();
  }

  private void initialisiereTableColumns() {
    // Spalten-Konfiguration mit POJO-Model:
    // a) Im einfachsten Fall kann bei komplexen Werten die Umformung in den Anzeige-String in einer CellValueFactory erfolgen.
    //    Achtung: der null-Fall ist in diesem Beispiel ausgespart und müsste ggf. berücksichtigt werden!
    this.colName.setCellValueFactory((TableColumn.CellDataFeatures<Element, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getName()));
    this.colBaustein1.setCellValueFactory((TableColumn.CellDataFeatures<Element, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getBaustein1().getName()));
    this.colBaustein2.setCellValueFactory((TableColumn.CellDataFeatures<Element, String> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getBaustein2().getName()));
    // b) Korrekter wäre dagegen bei komplexen Werten die Verwendung einer CellValueFactory sowie einer zum Typ passenden CellFactory:
    this.colBaustein3.setCellValueFactory((TableColumn.CellDataFeatures<Element, Baustein> param) -> new ReadOnlyObjectWrapper<>(param.getValue().getBaustein3()));
    this.colBaustein3.setCellFactory((param) -> {
      return new TableCell<>() {
        @Override
        protected void updateItem(Baustein item, boolean empty) {
          super.updateItem(item, empty);
          // Achtung: hier sollten immer *alle* Fälle abgedeckt werden, wegen. 
          // interner Wiederverwendung der TableCells kann es sonst u.U. Fehler
          // in der Darstellung geben.
          if (!empty && item != null) {
            this.setText(item.getName());
          } else {
            this.setText(null);
          }
        }
      };
    });
  }
}
