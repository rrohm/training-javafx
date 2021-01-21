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
package org.meins.javafx.binding;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * High level binding for property objects.
 * @author robert rohm
 */
public class Binding_01_HighLevel {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    IntegerProperty n1 = new SimpleIntegerProperty(100);
    IntegerProperty n2 = new SimpleIntegerProperty(1);

    // Standard-Klasse für High-Level-Bindings:
    NumberBinding sumBinding = n1.add(n2);

    System.out.println("Summe: " + sumBinding.getValue());

    n2.set(100);

    System.out.println("Summe: " + sumBinding.getValue());
  }
}
