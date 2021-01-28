/*
 * This code is released under Creative Commons Attribution 4.0 International
 * (CC BY 4.0) license, http://creativecommons.org/licenses/by/4.0/legalcode .
 * That means:
 *
 * You are free to:
 *
 *     Share — copy and redistribute the material in any medium or format
 *     Adapt — remix, transform, and build upon the material
 *              for any purpose, even commercially.
 *
 *     The licensor cannot revoke these freedoms as long as you follow the
 *     license terms.
 *
 * Under the following terms:
 *
 *     Attribution — You must give appropriate credit, provide a link to the
 *     license, and indicate if changes were made. You may do so in any
 *     reasonable manner, but not in any way that suggests the licensor endorses
 *     you or your use.
 *
 * No additional restrictions — You may not apply legal terms or technological
 * measures that legally restrict others from doing anything the license
 * permits.
 */
package org.meins.javafx.fxml.threadedloading;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * This is a test app for comparing performance of loading subordinate FXML UIs
 * on the application thread or parallel and asynchronously in separate threads.
 * <p>
 * This is possible with any node that may be instantiated and initialized
 * independently from the scene graph. There are only a few exceptions from
 * this, e.g., Tooltip.
 * </p>
 *
 * @author Robert Rohm &lt;r.rohm@aeonium-systems.de&gt;
 */
public class FXMLDocumentController implements Initializable {

  @FXML
  private Label label;

  @FXML
  private VBox vBox;

  /**
   * This method loads the UI fragments on the FX Application Thread – looks
   * quite standard, but makes the UI "chocke". And, it is not necessary, for
   * most UI nodes can be instantiated independently from the FX Application
   * Thread. Only putting them in the scene graph is required to happen on the
   * FX Application Thread.
   *
   * @param event not relevant
   */
  @FXML
  private void handleButtonAction(ActionEvent event) {
    System.out.println("Loading FXML UIs on FX Application thread ...");
    label.setText("Loading FXML UIs on FX Application thread ...");

    long start = System.currentTimeMillis();
    int rounds = 20;

    for (int i = 0; i < rounds; i++) {
      try {
        Parent load = this.load();
        vBox.getChildren().add(load);

      } catch (IOException ex) {
        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    long end = System.currentTimeMillis();

    label.setText("Time: " + (end - start) + " ms");
  }

  /**
   * This method loads UI fragments, each in a new thread – so, extending the UI
   * happens <b>asynchronously and parallel</b>.
   *
   * @param event not relevant
   */
  @FXML
  private void handleAsyncLoad(ActionEvent event) {
    System.out.println("Loading FXML UIs parallel and asynchronously ...");
    label.setText("Loading FXML UIs parallel and asynchronously ...");

    long start = System.currentTimeMillis();
    int rounds = 20;

    for (int i = 0; i < rounds; i++) {
      new Thread(() -> {
        try {
          Parent load = this.load();

          Platform.runLater(() -> {
            vBox.getChildren().add(load);
          });

        } catch (IOException ex) {
          ex.printStackTrace();
          Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
      }).start();
    }

    long end = System.currentTimeMillis();

    label.setText("Time: " + (end - start) + " ms");
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // no op
  }

  /**
   * Standard procedure for loading.
   *
   * @return The parent node of the loaded UI.
   * @throws IOException
   */
  private Parent load() throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setResources(null);
    loader.setLocation(this.getClass().getResource("FXML.fxml"));
    Parent parent = (Parent) loader.load(this.getClass().getResource("FXML.fxml").openStream());

    return parent;
  }

}
