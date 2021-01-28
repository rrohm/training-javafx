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
package org.meins.javafx.architektur.model;

/**
 * Datenmodell-Klasse eines "Elements" - sie dient als einfaches Beispiel einer
 * Datenklasse mit (etwas) komplexeren Unterstrukturen.
 *
 * @author robert rohm
 */
public class Element {

  private int id;

  private String name;
  private String bezeichnung;

  private Baustein baustein1;
  private Baustein baustein2;
  private Baustein baustein3;

  public Element() {
    // no op
  }

  public Element(int id, String name, String bezeichnung, Baustein baustein1, Baustein baustein2, Baustein baustein3) {
    this.id = id;
    this.name = name;
    this.bezeichnung = bezeichnung;
    this.baustein1 = baustein1;
    this.baustein2 = baustein2;
    this.baustein3 = baustein3;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBezeichnung() {
    return bezeichnung;
  }

  public void setBezeichnung(String bezeichnung) {
    this.bezeichnung = bezeichnung;
  }

  public Baustein getBaustein1() {
    return baustein1;
  }

  public void setBaustein1(Baustein baustein1) {
    this.baustein1 = baustein1;
  }

  public Baustein getBaustein2() {
    return baustein2;
  }

  public void setBaustein2(Baustein baustein2) {
    this.baustein2 = baustein2;
  }

  public Baustein getBaustein3() {
    return baustein3;
  }

  public void setBaustein3(Baustein baustein3) {
    this.baustein3 = baustein3;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
