/*
 *  This code is released under Creative Commons Attribution 4.0 International
 *  (CC BY 4.0) license, http://creativecommons.org/licenses/by/4.0/legalcode .
 *  That means:
 * 
 *  You are free to:
 * 
 *      Share — copy and redistribute the material in any medium or format
 *      Adapt — remix, transform, and build upon the material
 *               for any purpose, even commercially.
 * 
 *      The licensor cannot revoke these freedoms as long as you follow the
 *      license terms.
 * 
 *  Under the following terms:
 * 
 *      Attribution — You must give appropriate credit, provide a link to the
 *      license, and indicate if changes were made. You may do so in any
 *      reasonable manner, but not in any way that suggests the licensor endorses
 *      you or your use.
 * 
 *  No additional restrictions — You may not apply legal terms or technological
 *  measures that legally restrict others from doing anything the license
 *  permits.
 * 
 *
 *  2021 aeonium software systems UG (haftungsb.), Robert Rohm.
 */
package org.meins.javafx.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * Main controller class
 *
 * @author robert rohm
 */
public class UIController implements Initializable {

  @FXML
  private ComboBox<String> comboBox1;
  @FXML
  private ComboBox<String> comboBox2;
  /**
   * Achtung: Namenskonvention für "embedded controllers:"
   * [fx:id]["Controller"]
   */
  @FXML
  private UIFragmentController fragmentController;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    this.comboBox2.getItems().addAll("Erster Eintrag", "Zweiter Eintrag", "Dritter Eintrag");
    System.out.println("this.fragmentController: " + this.fragmentController);

    // Initialisierung des (View-)Models des eingebetten Controllers!
    this.fragmentController.getComboboxModel().setAll("Erster Eintrag", "Zweiter Eintrag", "Dritter Eintrag");
  }
}
