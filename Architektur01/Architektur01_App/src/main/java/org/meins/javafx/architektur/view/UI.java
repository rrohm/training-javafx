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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Das Hauptfenster der Anwendung.
 * 
 * @author robert rohm
 */
public class UI extends VBox implements Initializable {

  @FXML
  private Button btElemente;

  @FXML
  private TabPane tabPane;

  @FXML
  private HBox pnStatus;

  @FXML
  private Label lbStatus;

  public UI() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/meins/javafx/architektur/app/UI.fxml"));
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
    
    this.btElemente.setOnAction((event) -> {
      Tab tab = new Tab("Element-Auswahl");
      ElementListe elementListe = new ElementListe();
      tab.setContent(elementListe);
      this.tabPane.getTabs().add(tab);
      this.tabPane.getSelectionModel().select(tab);
    });
  }

}
