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

import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Low Level Bindings for JavaFX Properties
 * @author robert rohm
 */
public class Binding_04_LowLevel {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    final IntegerProperty n1 = new SimpleIntegerProperty(1);
    final IntegerProperty n2 = new SimpleIntegerProperty(2);
    final IntegerProperty n3 = new SimpleIntegerProperty(3);
    final IntegerProperty n4 = new SimpleIntegerProperty(4);

    IntegerBinding binding = new IntegerBinding() {

      // Bind to dependend observable values: 
      {
//        super.bind(n1, n2, n3, n4); // super ist optional
        // Hier werden die zu überwachenden ObservableValues übergeben
        bind(n1, n2, n3, n4);
      }

      /**
       * Here we implement our custom binding algorithm.
       *
       * @return the bound value
       */
      @Override
      protected int computeValue() {
        return n1.get() * n2.get() * n3.get() * n4.get();
      }
    };

    System.out.println("Binding value: " + binding.get());

    n1.set(2);
    System.out.println("Binding value: " + binding.get());
  }
}
