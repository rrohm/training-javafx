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

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 * High level binding for property objects, also with InvalidationListener.
 * Inspect the behaviour with and without an attached change listener!
 * <br>
 * Hint: Invalidation listeners are meant to support lazy computation of binding
 * values. A change listener enforces eager computation instead.
 *
 * @author robert rohm
 */
public class Binding_03_BindingInvalidation {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    IntegerProperty n1 = new SimpleIntegerProperty(100);
    IntegerProperty n2 = new SimpleIntegerProperty(1);

    NumberBinding sumBinding = n1.add(n2);

    sumBinding.addListener(new InvalidationListener() {
      @Override
      public void invalidated(Observable observable) {
        System.out.println("Binding invalid! " + observable);
      }
    });

//        sumBinding.addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("Binding changed!");
//            }
//        });

    System.out.println("Summe: " + sumBinding.getValue());

    System.out.println("1. Set new value ... ");
    n2.set(100);
    System.out.println("2. Set new value ... ");
    n2.set(110);
    System.out.println("3. Set new value ... ");
    n2.set(120);

    System.out.println("Summe: " + sumBinding.getValue());

  }
}
