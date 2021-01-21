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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Simple example of a custom control. 
 * 
 * See https://wiki.openjdk.java.net/display/OpenJFX/UI+Controls+Architecture
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class ArrowButton extends Control {

  private final StringProperty text = new SimpleStringProperty();
  private final ObjectProperty<ArrowButtonAPI.Direction> direction;

  public ArrowButton() {
    this.direction = new SimpleObjectProperty<>();
    // A default skin may be set here: 
    // this.setSkin(new ArrowButtonSkin(this));
    // BUT: the internal default (see below) and the skin from the user agent 
    //      stylesheet get eveluated not before the node is placed in a 
    //      scene graph!!!
    this.getStyleClass().add("arrow-button");
  }

  public ArrowButton(String title) {
    this();
    this.text.set(title);
  }

  public void setText(String text) {
    this.text.set(text);
  }

  public StringProperty textProperty() {
    return this.text;
  }
  
  public String getText(){
    return this.text.get();
  }

  public void setDirection(ArrowButtonAPI.Direction direction) {
    this.direction.set(direction);
  }
  
  public ArrowButtonAPI.Direction getDirection(){
    return this.direction.get();
  }

  public ObjectProperty<ArrowButtonAPI.Direction> directionProperty() {
    return this.direction;
  }

  @Override
  protected Skin<ArrowButton> createDefaultSkin() {
    System.out.println("createDefaultSkin ");
    return new ArrowButtonSkin(this);
  }

  @Override
  public String getUserAgentStylesheet() {
    System.out.println("getUserAgentStylesheet: " + ArrowButton.class.getResource("arrowbutton.css").toExternalForm());
    return ArrowButton.class.getResource("arrowbutton.css").toExternalForm();
  }

}
