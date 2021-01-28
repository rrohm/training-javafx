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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import org.meins.javafx.architektur.conf.GuiceModule;
import org.meins.javafx.architektur.converter.BausteinStringConverter;
import org.meins.javafx.architektur.model.Baustein;
import org.meins.javafx.architektur.viewmodel.BausteinAuswahlViewModel;

/**
 *
 * @author robert rohm
 */
public class BausteinAuswahlView extends TitledPane implements Initializable {
  
  @FXML
  private TitledPane pane;
  @FXML
  private ComboBox<Baustein> comboBoxBausteine;

  private BausteinAuswahlViewModel viewModel;
  
  public BausteinAuswahlView() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/meins/javafx/architektur/app/BausteinAuswahl.fxml"));
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
    System.out.println(this.getClass().getName() + ".initialize");
    Injector injector = Guice.createInjector(new GuiceModule());
    
    this.viewModel = injector.getInstance(BausteinAuswahlViewModel.class);
    this.viewModel.selectedBausteinProperty().bindBidirectional(this.comboBoxBausteine.valueProperty());
    this.comboBoxBausteine.itemsProperty().set(this.viewModel.getAlleBausteine());
    this.comboBoxBausteine.setConverter(new BausteinStringConverter());
    
    this.viewModel.ladeAlleBausteine();
  }

  public BausteinAuswahlViewModel getViewModel() {
    return viewModel;
  }
  
}
