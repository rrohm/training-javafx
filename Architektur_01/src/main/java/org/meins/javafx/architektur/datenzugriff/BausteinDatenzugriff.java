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
package org.meins.javafx.architektur.datenzugriff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.meins.javafx.architektur.model.Baustein;

/**
 * Dummy-Datenzugriffsklasse für Baustein-Model-Daten, ersetzt z.B. einen
 * REST-Service-Consumer.
 *
 * @author robert
 */
public class BausteinDatenzugriff {

  public List<Baustein> leseAlle() {
    List<Baustein> daten = new ArrayList<>();
    daten.addAll(Arrays.asList(
            new Baustein(1, "Baustein 1"),
            new Baustein(2, "Baustein 2"),
            new Baustein(3, "Baustein 3"),
            new Baustein(4, "Baustein 4"),
            new Baustein(5, "Baustein 5"),
            new Baustein(6, "Baustein 6"),
            new Baustein(7, "Baustein 7")
    ));

    // Unser Datenzugriff dauert etwas ....
    try {
      Thread.sleep(2000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
    return daten;
  }
}
