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
package org.meins.javafx.threads;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * „Zweite Evolutionsstufe:“ Der nebenläufige auszuführende Code wird jetzt in
 * ein Task-Objekt gekapselt. Für die Rückmeldung an der Oberfläche werden die
 * Update-Methoden der Task-Klasse eingesetzt. Dies ist Standard-Infrastruktur
 * des JavaFX-Frameworks.
 *
 * @author robert rohm
 */
public class Concurrency_01_TaskController implements Initializable {

  @FXML
  private Label label;

  @FXML
  private ProgressBar progressBar;

  @FXML
  private void handleButtonAction(ActionEvent event) {
    System.out.println("You clicked me!");

    // Task ist die Standard-Klasse für die Implementierung nebenläufiger Logik.
    // (Vgl. Callable!)
    // Zudem stellt Task einfach Wege für Feedback in die GUI bereit.
    Task<Void> task = new Task<Void>() {
      static final int max = 100000000;

      @Override
      protected Void call() throws Exception {
        for (int i = 1; i <= max; i++) {
          if (isCancelled()) {
            break;
          }
          // "Threadsicheres" Aktualisieren von z.B. ProgressProperty
          this.updateProgress(i, max);
        }
        return null;
      }
    };

    this.progressBar.progressProperty().bind(task.progressProperty());

    final Thread thread = new Thread(task);
    // Achtung! Standardmäßig soll die JVM warten, bis jeder Thread seine
    // run/call()-Methode verlassen hat.
//    new Thread(task).start();
    // Besser: Thread als "Dämon-Thread" ausführen - die JVM muss dann nicht
    // warten, bis dieser Thread von sich aus "zu Ende" gelaufen ist.
    thread.setDaemon(true);
    thread.setPriority(Thread.MIN_PRIORITY);
    thread.start();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }
}
