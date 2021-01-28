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

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import org.meins.javafx.architektur.view.ElementListeView;
import org.meins.javafx.architektur.view.UIView;

/**
 * ViewModel zum UIView (Hauptfenster) - hier wird der "ViewModel first"-Ansatz
 * verfolgt, d.h.: das ViewModel wird zuerst erzeugt und ist verantwortlich für
 * die Erzeugung, sowie für untergeordnete Views und ViewModels.
 *
 * @author robert rohm
 */
public class UiViewModel {

  /**
   * Referenz auf die View. Dependency Injection an dieser Stelle ist nicht
   * möglich, da unmittelbar nach der Konstruktion alle UI-Elemente zugreifbar
   * sein müssen. D.h.: Die Initialisierung der FXML-GUI muss zu diesem
   * Zeitpunkt abgeschlossen sein. Bei Injektion per DI (z.B. Google Guice) ist
   * das nicht der Fall, da die Injektion erst nach der Konstruktion erfolgt.
   * Weitere Abhängigkeiten, z.B. Services, könnten jedoch injiziert werden.
   */
  private final UIView view;

  public UiViewModel() {
    this.view = new UIView();
    this.view.getBtElemente().setOnAction(this::onElementlisteGeklickt);
  }

  public Parent getView() {
    return view;
  }

  /**
   * Ereignisbehandlung mit Erzeugung untegeordneter View-/ViewModel-Strukturen:
   * in diesem Fall wird das Einkapseln der ElementListeView in einen Tab hier
   * abgehandelt, da die ElementListeView generisch einfügbar bleiben soll.
   *
   * @param event nicht relevant
   */
  private void onElementlisteGeklickt(ActionEvent event) {
    final Tab tab = new Tab("Element-Auswahl");
    final ElementListeView elementListe = new ElementListeView();
    tab.setContent(elementListe);

    this.view.fuegeTabHinzu(tab);
  }

}
