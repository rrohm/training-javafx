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
package org.meins.javafx.fxml;

import org.meins.javafx.fxml.converter.BausteinStringConverter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TitledPane;
import org.meins.javafx.fxml.viewmodel.Baustein;

/**
 * Alternativer Ansatz: ViewModel-Elemente im FXML-Controller, die
 * @FXML-annotierten Felder stellen v.a. die API zur View dar.
 *
 * @author robert rohm
 */
public class UIFragmentController implements Initializable {

  /**
   * ViewModel-Element "Bausteinliste".
   */
  private final ObservableList<Baustein> bausteinListe;
  /**
   * ViewModel-Element "selektierter Baustein".
   */
  private final ObjectProperty<Baustein> selectedBaustein;

  @FXML
  private TitledPane pane;
  @FXML
  private ComboBox<Baustein> comboBox2;

  public UIFragmentController() {
    this.bausteinListe = FXCollections.observableArrayList();
    this.selectedBaustein = new SimpleObjectProperty<>();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    this.selectedBaustein.bind(this.comboBox2.getSelectionModel().selectedItemProperty());

    this.comboBox2.setItems(bausteinListe);
    this.comboBox2.setConverter(new BausteinStringConverter());
  }

  /**
   * ViewMode-Element "Titel", hier nur als "virtuelle" Property nach außen
   * durchgereicht. In der Klasse gibt es keine eigene Instanz von z.B.
   * StringProperty.
   *
   * @return Virtuelle Property "titleProperty"
   */
  public StringProperty titleProperty() {
    return this.pane.textProperty();
  }

  public ObservableList<Baustein> getBausteinListe() {
    return this.bausteinListe;
  }

  public ComboBox<Baustein> getComboBox() {
    return comboBox2;
  }

}
