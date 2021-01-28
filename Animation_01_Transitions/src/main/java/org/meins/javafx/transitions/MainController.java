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
 *  2016 Aeonium Software Systems, Robert Rohm.
 */
package org.meins.javafx.transitions;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * FXML Controller class - it uses <b>one</b> FadeTransition for two nodes,
 * start the application, click the two buttons and observe the behaviour of the
 * animation!
 * <p>
 * This example demonstrates that a transition needs to get stopped, before it
 * may be restarted, if it is running.</p>
 * <p>
 * You may extend this example by extending the onBt2Click handler so that it at
 * first queries the transition status with getStatus(), and, if the transition
 * is still playing, stop it, before you reassign and restart it.</p>
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class MainController implements Initializable {

  @FXML
  private Button bt1;

  @FXML
  private Button bt2;

  @FXML
  private Rectangle rect1;

  @FXML
  private Rectangle rect2;

  private final FadeTransition fadeTransition;

  public MainController() {
    this.fadeTransition = new FadeTransition(Duration.seconds(2));
    this.fadeTransition.setOnFinished((event) -> {
      System.out.println("Transition finished.");
    });
  }

  @FXML
  void onBt1Click(ActionEvent event) {
    try {
      fadeTransition.setNode(rect1);
      fadeTransition.play();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  void onBt2Click(ActionEvent event) {
    try {
      fadeTransition.setNode(rect2);
      fadeTransition.play();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Initializes the controller class.
   */
  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // This could have been done also in a constructor:
    fadeTransition.setFromValue(1.0);
    fadeTransition.setToValue(0.0);
  }

}
