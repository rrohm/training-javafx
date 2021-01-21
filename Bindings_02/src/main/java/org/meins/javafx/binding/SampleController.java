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
package org.meins.javafx.binding;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * Simple example demonstrating the use of bidirectional bindings.
 *
 * @author robert rohm
 */
public class SampleController implements Initializable {

  @FXML
  private TextField textField1;
  @FXML
  private TextField textField2;
  @FXML
  private Text text;
  @FXML
  private Text text2;
  @FXML
  private Text text3;

  /**
   * In real-world applications we would need to take care of properly releasing
   * the bound properties with unbind(), in order to avoid memory leaks! This is
   * not necessary here, but would apply to data objects that we may bind to UI
   * components.
   *
   * @param url
   * @param rb
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    textField1.textProperty().bindBidirectional(textField2.textProperty());
    
//    text.textProperty().bindBidirectional(textField1.textProperty());
////    text.textProperty().bindBidirectional(textField2.textProperty());
//    text2.textProperty().bind(text.textProperty());
//    // Siehe u.U. auch Bindings.concat()
//    text3.textProperty().bind(
//            Bindings.concat(
//                    textField1.textProperty(), 
//                    ", ", 
//                    textField2.textProperty()));
  }
}
