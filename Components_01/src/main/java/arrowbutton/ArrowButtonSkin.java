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

import arrowbutton.ArrowButtonAPI.Direction;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 *
 * @author robert
 */
class ArrowButtonSkin implements Skin<ArrowButton> {

  private static final double ARROW_TIP_WIDTH = 5;
  private ArrowButton control;
  private final Group rootNode = new Group();
  private Label lbl = null;
  private Direction direction;
  private EventHandler clientEH = null;

  public ArrowButtonSkin(ArrowButton control) {
    this.direction = ArrowButtonAPI.Direction.RIGHT;
    this.control = control;
    draw();
  }

  @Override
  public ArrowButton getSkinnable() {
    return this.control;
  }

  public ArrowButton getControl() {
    return control;
  }

  @Override
  public Node getNode() {
    return rootNode;
  }

  @Override
  public void dispose() {
  }

//  @Override
  public void setOnMouseClicked(EventHandler eh) {
    clientEH = eh;
  }


  public void draw() {
    if (lbl == null) {
      lbl = new Label();
      lbl.textProperty().bind(this.control.textProperty());
    }

    double labelWidth = lbl.getBoundsInLocal().getWidth();
    double labelHeight = lbl.getHeight();
    lbl.setTranslateX(40);
    lbl.setTranslateY(2);

    Path path = new Path();
    path.getElements().add(new MoveTo(10, 0));
    path.getElements().add(new LineTo(0, 10));
    path.getElements().add(new LineTo(10, 20));
    path.getElements().add(new MoveTo(0, 10));
    path.getElements().add(new LineTo(30, 10));
    path.setStroke(Color.BLACK);
    path.setStrokeWidth(2);

    rootNode.getChildren().setAll(path, lbl);

    rootNode.setOnMouseClicked((MouseEvent me) -> {
      // Pass along to client if an event handler was provided
      if (clientEH != null) {
        clientEH.handle(me);
      }
    });
  }
}
