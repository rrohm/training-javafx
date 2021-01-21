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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author robert rohm
 */
public class Concurrency_02_TaskController implements Initializable {

  private Task task;

  @FXML
  private Label label;

  @FXML
  private Button buttonCancel;
 
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
        updateMessage("Task gestartet ...");
        for (int i = 1; i <= max; i++) {
          if (isCancelled()) {
            updateMessage("Task abgebrochen.");
            break;
          }
          updateMessage("Task bearbeitet ".concat(
                  Integer.toString(i).concat(" von ").concat(
                  Integer.toString(max))));
          updateProgress(i, max);
        }
        updateMessage("Task beendet.");
        return null;
      }
    };
    this.label.textProperty().bind(task.messageProperty());
    this.progressBar.progressProperty().bind(task.progressProperty());
    // automatisches Enablement: 
    this.buttonCancel.disableProperty().bind(task.runningProperty().not());
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
    // Ausgangszustand, solange noch kein Task existiert: 
      this.buttonCancel.disableProperty().set(true);
  }
}
