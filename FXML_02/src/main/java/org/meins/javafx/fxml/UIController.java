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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.meins.javafx.fxml.converter.BausteinStringConverter;
import org.meins.javafx.fxml.viewmodel.Baustein;
import org.meins.javafx.fxml.viewmodel.ViewModel;

/**
 * FXML-Controller des UIs - er ist in JavaFX-UIs nach MVVM-Konzept der View
 * zuzurechnen. Die Felder des Controllers für die @FXML-Injektion stellen damit
 * lediglich die API zu den View-Elementen dar.
 * <p>
 * Der Controller als Element der View-Schicht sollte keine eigene
 * Interaktions-Logik implementieren, das ist Sache des ViewModels. Da der
 * Controller vom JavaFX-Framework automatisch instanziiert wird, macht hier nur
 * die "View first"-Interpretation von MVVM Sinn. Demnach erzeugt und verdrahtet
 * hier die View (d.h., der Controller!) das ViewModel – mehr aber auch nicht.
 * </p>
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
  private ComboBox<Baustein> comboBox1;

  /**
   * Achtung: Namenskonvention für "embedded controllers:" [fx:id]["Controller"]
   * .
   */
  @FXML
  private UIFragmentController fragmentController;
  /**
   * ViewModel kapselt ViewModel-Daten, und wird in diesem Beispiel nach dem
   * "View first"-Ansatz von der View erzeugt.
   */
  private final ViewModel viewModel;

  public UIController() {
    this.viewModel = new ViewModel();
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Bindings der UI-Controls
    this.tfName.textProperty().bindBidirectional(this.viewModel.getElement().nameProperty());
    this.tfBeschreibung.textProperty().bindBidirectional(this.viewModel.getElement().beschreibungProperty());
    // Custom String-Bindings:
    this.lbBaustein1.textProperty().bind(viewModel.createBaustein1Binding());
    this.lbBaustein2.textProperty().bind(viewModel.createBaustein2Binding());

    // Initialisierung des ComboBox-ViewModels
    // Achtung: Befüllung der Listen ist auch ViewModel, muss noch entsprechend abgebildet werden.
    comboBox1.setConverter(new BausteinStringConverter());
    comboBox1.setItems(this.viewModel.getBaustein1AuswahlListe());
    viewModel.selektierterBaustein1Property().bind(comboBox1.valueProperty());

    // Initialisierung des (view-)Models des eingebetten Controllers!
    this.fragmentController.titleProperty().set("Baustein 2");
    this.fragmentController.getBausteinListe().setAll(this.viewModel.getBaustein2AuswahlListe());
    viewModel.selektierterBaustein2Property().bind(this.fragmentController.getComboBox().valueProperty());
  }

}
