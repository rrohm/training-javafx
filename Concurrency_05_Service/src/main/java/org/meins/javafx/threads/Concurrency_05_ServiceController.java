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
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * Dieses Beispiel illustriert die grundlegende Handhabung von JavaFX-Services.
 *
 * @author robert rohm
 */
public class Concurrency_05_ServiceController implements Initializable {

  @FXML
  private Label label;

  @FXML
  private Button buttonCancel;

  @FXML
  private ProgressBar progressBar;

  private MeinService meinService = new MeinService();

  @FXML
  private void handleButtonAction(ActionEvent event) {
    System.out.println("You clicked 'Start'!");

    this.label.textProperty().bind(meinService.messageProperty());
    this.progressBar.progressProperty().bind(meinService.progressProperty());
    // automatisches Enablement: 
    this.buttonCancel.disableProperty().bind(meinService.runningProperty().not());
    
//    if (!meinService.getState().equals(WorkerStateEvent.WORKER_STATE_SCHEDULED)) {
//      meinService.reset(); // ... Exception! May only be called while in one of the finish states!
//    }
    /// Ggf. Vor Start des Services: entsprechend parametrisieren, z.B._
    meinService.setId(10001); /// TODO: Übergabe weiter ausillustrieren
    meinService.start();
  }

  @FXML
  private void handleButtonCancelAction(ActionEvent event) {
    if (meinService != null) {
      if (this.meinService.isRunning()) {
        this.meinService.cancel();
      }
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // Ausgangszustand, solange noch kein Task existiert: 
    this.buttonCancel.disableProperty().set(true);
  }

  private static class MeinService extends Service<Void> {

    private int id;

    public void setId(int id){
      this.id = id;
    }

    public MeinService() {
    }

    @Override
    protected Task<Void> createTask() {
      
      return new Task<Void>() {
        static final int max = 10000000;

        {
          updateMessage("Service erzeugt Task.");
        }

        @Override
        protected Void call() throws Exception {
          updateMessage("Service-Task gestartet ...");
          for (int i = 1; i <= max; i++) {
            if (isCancelled()) {
              updateMessage("Service-Task abgebrochen.");
              break;
            }
            updateMessage("Service-Task bearbeitet ".concat(
                    Integer.toString(i).concat(" von ").concat(
                            Integer.toString(max))));
            updateProgress(i, max);
          }
          updateMessage("Service-Task beendet.");
          return null;
        }
      };
    }
  }
}
