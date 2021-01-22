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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;

/**
 * View-Baustein für die Darstellung der Element-Model-Liste.
 * 
 * @author robert rohm
 */
public class ElementListe extends TitledPane implements Initializable {

  @FXML
  private TitledPane pane;

  @FXML
  private TableView<?> tvElemente;

  @FXML
  private TableColumn<?, ?> colName;

  @FXML
  private TableColumn<?, ?> colBaustein1;

  @FXML
  private TableColumn<?, ?> colBaustein2;

  @FXML
  private TableColumn<?, ?> colBaustein3;

  @SuppressWarnings("LeakingThisInConstructor")
  public ElementListe() {
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
    System.out.println(this.getClass().getName() + ".initialize");
  }
}
