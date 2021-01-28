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
package org.meins.javafx.css;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * CSS-Attribute -fx-shape, for defining the background shape of any region and
 * descendants. In combination with StageStyle.TRANSPARENT, the whole UI may
 * take any shape we like ...
 *
 * @author robert rohm
 */
public class SVGRegionShape extends Application {

  @Override
  public void start(Stage primaryStage) {

    Region region = new Region();
    region.setPrefSize(50, 50);
    region.setId("my-region");
    
    // Caveat! Hit testing uses the rectangular area of the region - not the shape!
    region.setOnMouseClicked((event) -> Platform.exit());

    StackPane root = new StackPane();
    root.getChildren().add(region);
    root.getStylesheets().add("org/meins/javafx/css/styles.css");

    Scene scene = new Scene(root, 300, 250);

    // With a transparent stage, the whole application gets shaped:
    scene.setFill(null);
    primaryStage.initStyle(StageStyle.TRANSPARENT);

    primaryStage.setTitle("Region with SVG");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * The main() method is ignored in correctly deployed JavaFX application.
   * main() serves only as fallback in case the application can not be launched
   * through deployment artifacts, e.g., in IDEs with limited FX support.
   * NetBeans ignores main().
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
