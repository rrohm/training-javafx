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
package org.meins.javafx.architektur.converter;

import javafx.util.StringConverter;
import org.meins.javafx.architektur.model.Baustein;
import org.meins.javafx.architektur.viewmodel.BausteinAuswahlViewModel;

/**
 * String-Converter für die ViewModel-Klasse Baustein.
 * @author robert rohm
 */
public class BausteinStringConverter extends StringConverter<Baustein> {
  
  public BausteinStringConverter() {
  }

  @Override
  public String toString(Baustein baustein) {
    return (baustein != null) ? baustein.getName() : "";
  }

  @Override
  public Baustein fromString(String string) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
}
