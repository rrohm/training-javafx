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
 * Datenmodell eines Bausteins.
 *
 * @author robert rohm
 */
public class Baustein {
  
  private int id;

  private String name;

  public Baustein() {
    // no op
  }

  public Baustein(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  /**
   * Wichtig: Diese Klasse muss anhand der ID verglichen werden können.
   * Daher müssen Hashcode und Equality neu implementiert werden.
   * Dies ist u.a. für das Setzen der Selektion in Choicebox oder Combobox
   * wichtig.
   *
   * @return Hashcode
   */
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 23 * hash + this.id;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Baustein other = (Baustein) obj;
    if (this.id != other.id) {
      return false;
    }
    return true;
  }
  
  
}
