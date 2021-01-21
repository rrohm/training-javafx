/*
 *  ----- AEONIUM SOFTWARE SYSTEMS UG (HAFTUNGSB.) SOURCE CODE LICENSE -----
 *  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 *  Copyright 2013 AEONIUM SOFTWARE SYSTEMS UG (HAFTUNGSB.) - ALL RIGHTS RESERVED.
 *
 *  The contents of this file are intellectual property of
 *  aeonium software systems UG (haftungsb.), Robert Rohm. All rights reserved.
 *  You must NOT, especially:
 *  - redistribute this file in source form,
 *  - redistribute this file in binary form,
 *  - modify this file,
 *  - remove or modify this copyright information,
 *  - use this file for your own work
 *  WITHOUT WRITTEN PERMISSION.
 *
 *  Anyway, we appreciate any interest in our work and knowledge.
 *  So, if you wish to use this file for your own purposes,
 *  please contact us:
 *  mailto:info@aeonium-systems.de
 *
 *
 *  © 2013 aeonium software systems UG (haftungsb.), Robert Rohm.
 */
package arrowbutton;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author robert
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {

    ArrowButton ab = new ArrowButton("Wichtig!");
    ab.setOnMouseClicked(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        System.out.println("OK.");
      }
    });

    StackPane root = new StackPane();
    root.getChildren().add(ab);

    Scene scene = new Scene(root, 300, 250);

    primaryStage.setTitle("Komponenten");
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
