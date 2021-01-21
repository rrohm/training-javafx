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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

/**
 * Bidirectional Binding with a Converter.
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


  private DoubleProperty doubleProperty = new SimpleDoubleProperty(0);


  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    final StringConverter doubleStringConverter = new DoubleStringConverter();
    Bindings.bindBidirectional(textField1.textProperty(), doubleProperty, doubleStringConverter);
    Bindings.bindBidirectional(textField2.textProperty(), doubleProperty, doubleStringConverter);

    text.textProperty().bind(doubleProperty.asString());


    text2.textProperty().bind(text.textProperty());
  }
}
