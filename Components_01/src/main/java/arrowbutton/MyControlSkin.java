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
package arrowbutton;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;

/**
 * Grundstruktur der Skin-Klasse zur eigenen Komponente - sie definiert die
 * eigentliche Präsentation im UI. 
 *
 * @author robert rohm
 */
public class MyControlSkin implements Skin<MyControl> {

  private final MyControl control;
  Group rootNode = new Group();

  public MyControlSkin(MyControl control) {
    this.control = control;
    this.draw();
  }

  @Override
  public MyControl getSkinnable() {
    return this.control;
  }

  @Override
  public Node getNode() {
    return this.rootNode;
  }

  @Override
  public void dispose() {
    // nothing to do
  }

  public void draw() {
    // Wenn hier der Aufbau der GUI erfolgt, 
    // werden ggf. auch die Bindings zwischen Control-Properties
    // und SceneGraph-Nodes für die Präsentation in der View aufgebaut
    
//    Label label = new Label("Hallo Welt von der eigenen Komponente!");
    // statt dessen: 
    Label label = new Label();
    label.textProperty().bind(this.control.myTextProperty());
    
    this.rootNode.getChildren().add(label);
  }

}
