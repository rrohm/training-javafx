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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel-Element, mit Unterstruktur
 *
 * @author robert rohm
 */
public class Element {
  
  private final StringProperty name = new SimpleStringProperty("Bitte Name ändern ...");
  private final StringProperty beschreibung = new SimpleStringProperty("Bitte Beschreibung eingeben ...");

  private final ObjectProperty<Baustein> baustein1;
  private final ObjectProperty<Baustein> baustein2;
  private final ObjectProperty<Baustein> baustein3;

  public Element() {
    this.baustein3 = new SimpleObjectProperty<>();
    this.baustein2 = new SimpleObjectProperty<>();
    this.baustein1 = new SimpleObjectProperty<>();
  }
  
  public StringProperty nameProperty(){
    return this.name;
  }
  
  public String getName(){
    return this.name.get();
  }
  
  public void setName(String name){
    this.name.set(name);
  }
  
  public StringProperty beschreibungProperty(){
    return this.beschreibung;
  }
  
  public String getBeschreibung(){
    return this.beschreibung.get();
  }

  public void setBeschreibung(String beschreibung){
    this.beschreibung.set(beschreibung);
  }
  
  public ObjectProperty<Baustein> baustein1Property() {
    return baustein1;
  }

  public Baustein getBaustein1() {
    return baustein1.get();
  }

  public void setBaustein1(Baustein baustein1) {
    this.baustein1.set(baustein1);
  }

  public ObjectProperty<Baustein> baustein2Property() {
    return baustein2;
  }

  public Baustein getBaustein2() {
    return baustein2.get();
  }

  public void setBaustein2(Baustein baustein2) {
    this.baustein2.set(baustein2);
  }

  public ObjectProperty<Baustein> baustein3Property() {
    return baustein3;
  }

  public Baustein getBaustein3() {
    return baustein3.get();
  }

  public void setBaustein3(Baustein baustein3) {
    this.baustein3.set(baustein3);
  }

}
