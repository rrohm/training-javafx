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
package org.meins.javafx.architektur.viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javax.inject.Inject;
import org.meins.javafx.architektur.model.Baustein;
import org.meins.javafx.architektur.services.AlleBausteineService;

/**
 * Untergeordnetes ViewModel-Elements
 *
 * @author robert rohm
 */
public class BausteinAuswahlViewModel {

  /**
   * ViewModel-Daten - hier eine ObservableList, da nur die Items als Model für
   * die ComboBox relevant sind. ObservableListProperty wäre Overhead, ein
   * unnötiger "Wrapper".
   */
  private final ObservableList<Baustein> alleBausteine = FXCollections.observableArrayList();

  private final ObjectProperty<Baustein> selectedBaustein = new SimpleObjectProperty<>();
  /**
   * Bindbare Property für View-Enablement
   */
  private final BooleanProperty disabled = new SimpleBooleanProperty(Boolean.TRUE);

  private AlleBausteineService alleBausteineService;

  public BausteinAuswahlViewModel() {
    // no op 
  }

  /**
   * @see ElementListeViewModel#aktualisiereElementListe()
   */
  public void ladeAlleBausteine() {
    this.alleBausteineService.restart();
  }

  @Inject
  public void setAlleBausteineService(AlleBausteineService alleBausteineService) {
    this.alleBausteineService = alleBausteineService;
    this.alleBausteineService.setOnSucceeded(this::onAlleBausteineServiceSucceeded);
    /// ... wäre auch per Lambda lösbar, ist so u.U. aber schöner lesbar.

    this.disabled.bind(this.alleBausteineService.runningProperty());
  }

  private void onAlleBausteineServiceSucceeded(WorkerStateEvent event) {
    this.alleBausteine.setAll(this.alleBausteineService.getValue());
  }

  public AlleBausteineService getAlleBausteineService() {
    return alleBausteineService;
  }

  /**
   * Zugriff auf das Datenmodell des Viewmodels.
   *
   * @return Überwachbare Liste aller Bausteine.
   */
  public ObservableList<Baustein> getAlleBausteine() {
    return alleBausteine;
  }

  /**
   * Zugriff auf das selektierte Baustein-Element.
   *
   * @return Property, die den selektierten Baustein kapselt.
   */
  public ObjectProperty<Baustein> selectedBausteinProperty() {
    return selectedBaustein;
  }

  /**
   * Setzen der Auswahl von außen, z.B., um den im übergeordneten Model
   * gegebenen Wert eizustellen. Hierbei ist zweierlei zu berücksichtigen:
   * <ol>
   * <li>Die Property ist eigentlich gebunden, und kann daher nicht gebunden
   * werden.</li>
   * <li>Wenn der einzustellende Wert von außen kommt, kann nicht gewährleistet
   * werden, dass es sich um dieselbe Instanz handelt, wie der passende, in der
   * Auswahl verfügbare Wert. Dies muss mit einer geeigneten
   * equals()-Implementierung geregelt werden, will man nicht durch die
   * Items-Menge iterieren.</li>
   * </ol>
   *
   * @param baustein Das zu selektierende Objekt.
   */
  public void setSelectedBaustein(Baustein baustein) {
    this.selectedBaustein.set(baustein);
  }

  public BooleanProperty getDisabledProperty() {
    return disabled;
  }

}
