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
package org.meins.javafx.concurrency;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * Dieser Controller zeigt schon den ersten Schritt in die richtige Richtung:
 * der nebenläufige auszuführende Code wird in ein Runnable-Objekt verpackt und
 * auf einem neuen Thread ausgeführt, Die Manipulation der Oberfläche erfolgt
 * jedoch aus einem weiteren Runnable heraus, das auf den
 * JavaFX-Application-Thread gelegt wird.
 * 
 * @author robert
 */
public class Concurrency_00ControllerMitThread implements Initializable {

  @FXML
  private Label label;

  @FXML
  private void handleButtonAction(ActionEvent event) {
    System.out.println("You clicked me!");

    System.out.println("Aktueller Thread: "+ Thread.currentThread().getName());
    final int max = 10;

    Runnable r = () -> {
      for (int i = 0; i < max; i++) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ex) {
          Thread.currentThread().interrupt();
        }
        final int finalesI = i;
        Platform.runLater(() -> {
          label.setText("Hello World: " + finalesI);
        });
      }
    };
    new Thread(r).start();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }
}
