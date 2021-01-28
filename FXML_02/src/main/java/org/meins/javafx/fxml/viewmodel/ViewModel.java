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
package org.meins.javafx.fxml.viewmodel;

import java.util.Arrays;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Diese Klasse bildet das (eigentliche) ViewModel ab. Sie dient der klaren
 * Abgrenzung von der Controller-Logik.
 *
 * @author robert rohm
 */
public class ViewModel {

  private final ObjectProperty<Element> elementProperty;

  private final ObservableList<Baustein> baustein1AuswahlListe;
  private final ObservableList<Baustein> baustein2AuswahlListe;
  private final ObjectProperty<Baustein> selektierterBaustein1;
  private final ObjectProperty<Baustein> selektierterBaustein2;

  public ViewModel() {
    this.selektierterBaustein1 = new SimpleObjectProperty<>();
    this.selektierterBaustein2 = new SimpleObjectProperty<>();
    this.baustein1AuswahlListe = FXCollections.observableArrayList();
    this.baustein1AuswahlListe.addAll(Arrays.asList(
            new Baustein("Baustein 1.1"),
            new Baustein("Baustein 1.2"),
            new Baustein("Baustein 1.3")
    ));
    this.baustein2AuswahlListe = FXCollections.observableArrayList();
    this.baustein2AuswahlListe.addAll(Arrays.asList(
            new Baustein("Baustein 2.1"),
            new Baustein("Baustein 2.2"),
            new Baustein("Baustein 2.3")
    ));
    this.elementProperty = new SimpleObjectProperty<>();

    // Bindungen für das ELement-Modell einrichten - in diesem Fall nur einmal 
    // nach Erzeugung. Wird das Element dem ViewModel von außen zugewiesen, 
    // dann müsste dafür z.B. eine Property bereitgestellt werden, die in einem 
    // ChangeListener für ein sauberes unbind() des alten Element-Modells sorgt,
    // bevor das neue nach gleichem Muster wie hier neu gebunden wird:
    final Element element = new Element();
    this.elementProperty.set(element);
    element.baustein1Property().bind(selektierterBaustein1);
    element.baustein2Property().bind(selektierterBaustein2);
  }

  public Element getElement() {
    return elementProperty.get();
  }

  public void setElement(Element element) {
    this.elementProperty.set(element);
  }

  public ObjectProperty<Element> elementProperty() {
    return elementProperty;
  }

  public ObservableList<Baustein> getBaustein1AuswahlListe() {
    return baustein1AuswahlListe;
  }

  public ObservableList<Baustein> getBaustein2AuswahlListe() {
    return baustein2AuswahlListe;
  }

  /**
   * Fabrik-Methode für spezifisches Binding - könnte auch private sein, wenn
   * das ViewModel seine View selbst erzeugen würde.
   *
   * @return Binding für View-Element-Property
   */
  public StringBinding createBaustein1Binding() {
    return new StringBinding() {
      {
        this.bind(getElement().baustein1Property());
      }

      @Override
      protected String computeValue() {
        if (getElement().baustein1Property().get() != null) {
          return getElement().baustein1Property().get().getBezeichnung();
        } else {
          return "(Bitte Baustein 1 wählen)";
        }
      }
    };
  }

  /**
   * Fabrik-Methode für spezifisches Binding - könnte auch private sein, wenn
   * das ViewModel seine View selbst erzeugen würde.
   *
   * @return Binding für View-Element-Property
   */
  public StringBinding createBaustein2Binding() {
    return new StringBinding() {
      {
        this.bind(getElement().baustein2Property());
      }

      @Override
      protected String computeValue() {
        if (getElement().baustein2Property().get() != null) {
          return getElement().baustein2Property().get().getBezeichnung();
        } else {
          return "(Bitte Baustein 2 wählen)";
        }
      }
    };
  }

  public ObjectProperty<Baustein> selektierterBaustein1Property() {
    return selektierterBaustein1;
  }

  public ObjectProperty<Baustein> selektierterBaustein2Property() {
    return selektierterBaustein2;
  }
}
