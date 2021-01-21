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
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;

/**
 * In diesem Beispiel werden Thread-Lebenszyklus-Ereignisse durch
 * WorkerStateEvent-Handler verarbeitet, die aus der ausführenden Anwendung
 * heraus an die Task angefügt werden.
 *
 * @author robert rohm
 */
public class Concurrency_03_TaskController implements Initializable {

    private Task task;

    @FXML
    private Label label;

    @FXML
    private Text text;

    @FXML
    private ProgressBar progressBar;

    
    private WorkerStateEventEventHandler onWorkerStateEventEventHandler = new WorkerStateEventEventHandler();
    
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
                        updateMessage("Task abgebrochen.");
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
                updateMessage("Task beendet.");
                return null;
            }
        };
        // Wenn hier nicht "hardcoded" verdrahtet wird: 
//        this.label.textProperty().bind(task.messageProperty());
//        this.progressBar.progressProperty().bind(task.progressProperty());

        // ... statt dessen, ein EventHandler, er alle Ausführungszyklus-Erreignisse behandelt:
        this.task.setOnScheduled(onWorkerStateEventEventHandler);
        this.task.setOnRunning(onWorkerStateEventEventHandler);
        this.task.setOnCancelled(onWorkerStateEventEventHandler);
        this.task.setOnSucceeded(onWorkerStateEventEventHandler);
        this.task.setOnFailed(onWorkerStateEventEventHandler);
        
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

    private class WorkerStateEventEventHandler implements EventHandler<WorkerStateEvent> {

        public WorkerStateEventEventHandler() {
        }

        @Override
        public void handle(WorkerStateEvent event) {
            
            if (WorkerStateEvent.WORKER_STATE_SCHEDULED.equals(event.getEventType())) {
                // Bevor neu gebunden wird - geht auch ohne Abfrage ".isBound()"): 
                label.textProperty().unbind();
                progressBar.progressProperty().unbind();

                // Hier könnte z.B. auch das Binden an die Feedback-Controls erfolgen
                label.textProperty().bind(event.getSource().messageProperty());
                progressBar.progressProperty().bind(event.getSource().progressProperty());

                System.out.println("WorkerStateEvent SCHEDULED - "
                        + Thread.currentThread().getName());
                
            } else if (WorkerStateEvent.WORKER_STATE_RUNNING.equals(event.getEventType())) {
                
                System.out.println("WorkerStateEvent RUNNING");
                
            } else if (WorkerStateEvent.WORKER_STATE_SUCCEEDED.equals(event.getEventType())) {
                label.textProperty().unbind();
                label.setText("Fertig!");
                progressBar.progressProperty().unbind();
                
                System.out.println("WorkerStateEvent SUCCEEDED");
                
            } else if (WorkerStateEvent.WORKER_STATE_CANCELLED.equals(event.getEventType())) {
                label.textProperty().unbind();
                label.setText("Abgebrochen!");
                progressBar.progressProperty().unbind();
                
                System.out.println("WorkerStateEvent CANCELLED");
                
            } else if (WorkerStateEvent.WORKER_STATE_FAILED.equals(event.getEventType())) {
                label.textProperty().unbind();
                label.setText("FEHLER!");
                progressBar.progressProperty().unbind();
                
                System.out.println("WorkerStateEvent FAILED");
            }
            
        }
    }
}
