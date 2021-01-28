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
import org.meins.javafx.architektur.model.Element;
import org.meins.javafx.architektur.services.AlleElementeService;

/**
 * ViewModel-Element, mit Unterstruktur
 *
 * @author robert rohm
 */
public class ElementListeViewModel {

  private final ObservableList<Element> elementListe;
  private final ObjectProperty<Element> selektiertesElement;

  /**
   * Referenz auf ein Runnable, das bei Aktualisierung des selektierten Elements
   * ausgeführt wird - im Prinzip als Listener. Statt einem Runnable hätte hier
   * auch ein eigener Listener-Typ eingeführt werden können.
   */
  private Runnable onElementBearbeitet;
  /**
   * Bindbare Property für View-Enablement
   */
  private final BooleanProperty disabled;

  /**
   * Referenz auf untergeordnete ViewModels - müssen von außen zuweisbar sein,
   * da dieses ViewModel (in diesem Fall, als Beispiel) nach dem "View
   * first"-Ansatz verwaltet wird.
   */
  private BausteinAuswahlViewModel bausteinViewModel1;
  private BausteinAuswahlViewModel bausteinViewModel2;
  private BausteinAuswahlViewModel bausteinViewModel3;

  private AlleElementeService alleElementeService;

  public ElementListeViewModel() {
    this.disabled = new SimpleBooleanProperty(Boolean.FALSE);
    this.elementListe = FXCollections.observableArrayList();

    this.selektiertesElement = new SimpleObjectProperty<>();
    this.selektiertesElement.addListener((observable, oldValue, newValue) -> {
      this.bausteinViewModel1.setSelectedBaustein((newValue != null) ? newValue.getBaustein1() : null);
      this.bausteinViewModel2.setSelectedBaustein((newValue != null) ? newValue.getBaustein2() : null);
      this.bausteinViewModel3.setSelectedBaustein((newValue != null) ? newValue.getBaustein3() : null);
    });
  }

  /**
   * Lädt die Daten mit Hilfe des injizierten Services neu. Durch Verwendung von
   * Service#restart() werden evtl. laufende Tasks abgebrochen und der Service
   * ohne Probleme neu gestartet.
   */
  public void aktualisiereElementListe() {
    this.alleElementeService.restart();
  }

  /**
   * Setter-Injektion: wird genutzt, um Event-Handler für den Service zu binden.
   *
   * @param alleElementeService Service-Instanz, soll hier injiziert werden.
   */
  @Inject
  public void setAlleElementeService(AlleElementeService alleElementeService) {
    this.alleElementeService = alleElementeService;
    this.alleElementeService.setOnSucceeded(this::onAlleElementeServiceSucceeded);
    this.disabled.bind(this.alleElementeService.runningProperty());
  }

  /**
   * Listener für den Service, ersetzt die bisherige Datenmenge mit den
   * erfolgreich geladenen Daten vom Service.
   *
   * @param event nicht relevant
   */
  private void onAlleElementeServiceSucceeded(WorkerStateEvent event) {
    this.elementListe.setAll(this.alleElementeService.getValue());
  }
  
  /**
   * Falls "Listener" für Bearbeitung des Elements zugewiesen: ausführen.
   */
  private void sendeOnElementBearbeitet() {
    if (this.onElementBearbeitet != null) {
      this.onElementBearbeitet.run();
    }
  }

  public void setBausteinViewModel1(BausteinAuswahlViewModel bausteinViewModel1) {
    this.bausteinViewModel1 = bausteinViewModel1;
    this.bausteinViewModel1.selectedBausteinProperty().addListener((observable, oldValue, newValue) -> {
      if (selektiertesElement.get() != null) {
        selektiertesElement.get().setBaustein1(newValue);
        sendeOnElementBearbeitet();
      } else {
        // TODO selektiertesElement ist null? Falls unerlaubter, aber möglicher Zustand, dann Situation behandeln.
      }
    });
  }

  public void setBausteinViewModel2(BausteinAuswahlViewModel bausteinViewModel2) {
    this.bausteinViewModel2 = bausteinViewModel2;
    this.bausteinViewModel2.selectedBausteinProperty().addListener((observable, oldValue, newValue) -> {
      if (selektiertesElement.get() != null) {
        selektiertesElement.get().setBaustein2(newValue);
        sendeOnElementBearbeitet();
      } else {
        // TODO selektiertesElement ist null? Falls unerlaubter, aber möglicher Zustand, dann Situation behandeln.
      }
    });
  }

  public void setBausteinViewModel3(BausteinAuswahlViewModel bausteinViewModel3) {
    this.bausteinViewModel3 = bausteinViewModel3;
    this.bausteinViewModel3.selectedBausteinProperty().addListener((observable, oldValue, newValue) -> {
      if (selektiertesElement.get() != null) {
        selektiertesElement.get().setBaustein3(newValue);
        sendeOnElementBearbeitet();
      } else {
        // TODO selektiertesElement ist null? Falls unerlaubter, aber möglicher Zustand, dann Situation behandeln.
      }
    });
  }

  public BausteinAuswahlViewModel getBausteinViewModel1() {
    return bausteinViewModel1;
  }

  public BausteinAuswahlViewModel getBausteinViewModel2() {
    return bausteinViewModel2;
  }

  public BausteinAuswahlViewModel getBausteinViewModel3() {
    return bausteinViewModel3;
  }

  public BooleanProperty getDisabledProperty() {
    return disabled;
  }

  public ObservableList<Element> getElementListe() {
    return elementListe;
  }

  public AlleElementeService getAlleElementeService() {
    return alleElementeService;
  }

  public ObjectProperty<Element> selektiertesElementProperty() {
    return selektiertesElement;
  }

  public Runnable getOnElementBearbeitet() {
    return onElementBearbeitet;
  }

  public void setOnElementBearbeitet(Runnable onElementBearbeitet) {
    this.onElementBearbeitet = onElementBearbeitet;
  }

}
