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
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.meins.javafx.fxml.converter.BausteinStringConverter;
import org.meins.javafx.fxml.viewmodel.Baustein;
import org.meins.javafx.fxml.viewmodel.ViewModel;

/**
 * Main controller class
 *
 * @author robert rohm
 */
public class UIController implements Initializable {

  @FXML
  private TextField tfName;
  @FXML
  private TextField tfBeschreibung;
  @FXML
  private Label lbBaustein1;
  @FXML
  private Label lbBaustein2;
  @FXML
  private ComboBox<Baustein> comboBox2;

  /**
   * Achtung: Namenskonvention für "embedded controllers:" [fx:id]["Controller"]
   */
  @FXML
  private UIFragmentController fragmentController;
  
  private ViewModel viewModel = new ViewModel();

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Bindings der UI-Controls
    this.tfName.textProperty().bindBidirectional(this.viewModel.getElement().nameProperty());
    this.tfBeschreibung.textProperty().bindBidirectional(this.viewModel.getElement().beschreibungProperty());
    
    //this.lbBaustein1.textProperty().bind(this.viewModel.getElement().baustein1Property().asString());
    

    // Initialisierung des ComboBox-ViewModels
    comboBox2.setConverter(new BausteinStringConverter());
    comboBox2.getItems().setAll(Arrays.asList(
            new Baustein("Baustein 1.1"),
            new Baustein("Baustein 1.2"),
            new Baustein("Baustein 1.3")
    ));

    // Initialisierung des (view-)Models des eingebetten Controllers!
    this.fragmentController.titleProperty().set("Baustein 2");
    this.fragmentController.getViewModel().setAll(Arrays.asList(
            new Baustein("Baustein 2.1"),
            new Baustein("Baustein 2.2"),
            new Baustein("Baustein 2.3")
    ));
  }
}
