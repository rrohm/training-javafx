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
import javafx.scene.text.Text;

/**
 * Dieses Beispiel illustriert die Implementierung von Thread-
 * Lebenszyklusmethoden durch die Task-Klasse selbst. Außerdem ist der Task
 * (wie es sich gehört) unterbrechbar und verwendet dafür den
 * Standard-Mechanismus der Basisklasse.
 *
 * @author robert rohm
 */
public class Concurrency_04_TaskController implements Initializable {

  private Task task;

  @FXML
  private Label label;

  @FXML
  private Text text;

  @FXML
  private ProgressBar progressBar;

  @FXML
  private void handleButtonAction(ActionEvent event) {
    System.out.println("You clicked 'Start'!");

    this.task = new Task<Void>() {
      static final int max = 10000000;
      {
        updateMessage("Task erzeugt.");
      }

      @Override
      protected Void call() throws Exception {
        updateMessage("Task gestartet ...  ");
//        System.out.println("Task gestartet ... - " + Thread.currentThread().getName());

        for (int i = 1; i <= max; i++) {
          if (isCancelled()) {
            break;
          }
          if (i == max - 200000) {
            throw new Exception("Provoziere FAIL");
          }

          updateMessage("Task bearbeitet ".concat(
                  Integer.toString(i).concat(" von ").concat(
                  Integer.toString(max))));
          updateProgress(i, max);
        }
        return null;
      }

      @Override
      protected void scheduled() {
        super.scheduled(); // leer
        updateMessage("Task geplant.");
        System.out.println("TASK: Task geplant.");
      }

      @Override
      protected void done() {
        super.done(); // leer
        updateMessage("Task beendet.");
        System.out.println("TASK: Task beendet.");
      }

      @Override
      protected void cancelled() {
        super.cancelled(); // leer
        updateMessage("Task abgebrochen.");
        System.out.println("TASK: Task abgebrochen.");
      }

      @Override
      protected void failed() {
        super.failed(); // leer
        updateMessage("Task gestolpert!");
        System.out.println("TASK: Task gestolpert!");
      }
    };

    this.label.textProperty().bind(task.messageProperty());
    this.progressBar.progressProperty().bind(task.progressProperty());

    new Thread(this.task).start();
  }

  @FXML
  private void handleButtonCancelAction(ActionEvent event) {
    if (this.task != null) {
      if (this.task.isRunning()) {
        this.task.cancel();
      }
    }
  }
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
  }
}
