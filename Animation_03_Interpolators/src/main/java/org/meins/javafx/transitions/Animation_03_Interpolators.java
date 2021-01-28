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
 *  2021 Aeonium Software Systems, Robert Rohm.
 */
package org.meins.javafx.transitions;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This example illustrates the use of Intepolators with Transition animations.
 * <p>
 * Interpolators define the acceleration of transitions. This is especially
 * useful with TranslateTransitions.</p>
 * <p>
 * There are several built-in Interpolators. You also may implement custom
 * interpolators, if you need "something special".</p>
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class Animation_03_Interpolators extends Application {

  @Override
  public void start(Stage primaryStage) {

    Pane root = new Pane();

    Circle circle1 = createNodeWithLinearInterpolator(50, 50);
    Circle circle2 = createNodeWithDiscreteInterpolator(50, 75);
    Circle circle3 = createNodeWithEaseBothInterpolator(50, 100);
    Circle circle4 = createNodeWithSplineInterpolator(50, 125);
    Circle circle5 = createNodeWithCustomInterpolator(50, 150);

    root.getChildren().addAll(circle1, circle2, circle3, circle4, circle5);
    Scene scene = new Scene(root, 450, 250);

    primaryStage.setTitle(this.getClass().getSimpleName());
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private Circle createNodeWithLinearInterpolator(double x, double y) {
    Circle circle = new Circle(x, y, 10, Color.RED);
    TranslateTransition transition1 = new TranslateTransition(Duration.seconds(2), circle);
    transition1.setByX(350);
    transition1.setInterpolator(Interpolator.LINEAR);
    transition1.setCycleCount(1);
    transition1.play();
    return circle;
  }

  private Circle createNodeWithDiscreteInterpolator(double x, double y) {
    Circle circle = new Circle(x, y, 10, Color.RED);
    TranslateTransition transition1 = new TranslateTransition(Duration.seconds(2), circle);
    transition1.setByX(350);
    transition1.setInterpolator(Interpolator.DISCRETE);
    transition1.setCycleCount(1);
    transition1.play();
    return circle;
  }

  private Circle createNodeWithSplineInterpolator(double x, double y) {
    Circle circle = new Circle(x, y, 10, Color.RED);
    TranslateTransition transition1 = new TranslateTransition(Duration.seconds(2), circle);
    transition1.setByX(350);
    transition1.setInterpolator(Interpolator.SPLINE(0, 0, 0.5, 1));
    transition1.setCycleCount(1);
    transition1.play();
    return circle;
  }

  private Circle createNodeWithCustomInterpolator(double x, double y) {
    Circle circle = new Circle(x, y, 10, Color.RED);
    TranslateTransition transition1 = new TranslateTransition(Duration.seconds(2), circle);
    transition1.setByX(350);
    transition1.setInterpolator(new Interpolator() {
      @Override
      protected double curve(double t) {
        return t + t * Math.sin(3 * t * Math.PI * 2) / 10;
      }
    });
    transition1.setCycleCount(1);
    transition1.play();
    return circle;
  }

  private Circle createNodeWithEaseBothInterpolator(double x, double y) {
    Circle circle = new Circle(x, y, 10, Color.RED);
    TranslateTransition transition1 = new TranslateTransition(Duration.seconds(2), circle);
    transition1.setByX(350);
    transition1.setInterpolator(Interpolator.EASE_BOTH);
    transition1.setCycleCount(1);
    transition1.play();
    return circle;
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
