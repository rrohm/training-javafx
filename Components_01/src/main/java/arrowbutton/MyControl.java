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

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Control;

/**
 * Grundstruktur der eigenen Komponente - diese Klasse definiert nur die
 * logischen Aspekte, nicht aber die Präsentation im UI, d.h., den SceneGraph
 * der Komponenten.
 *
 * @author robert rohm
 */
public class MyControl extends Control {

  /**
   * Wichtig: FX-Controls brauchen unbedingt einen parameterlosen
   * Standard-Konstruktor, z.B. für die Verwendung in FXML!
   */
  public MyControl() {
    this.setSkin(new MyControlSkin(this));
  }
  
  
  /**
   * Eigenschaft von MyControl: IMMER FX-Properties, 
   * ist zumindest for die Verwendung in FXML Voraussetzung
   */
  private final StringProperty myText = new SimpleStringProperty("MyControl ist da!");

  public String getMyText() {
    return myText.get();
  }

  public void setMyText(String value) {
    myText.set(value);
  }

  public StringProperty myTextProperty() {
    return myText;
  }
  
  
  

}
