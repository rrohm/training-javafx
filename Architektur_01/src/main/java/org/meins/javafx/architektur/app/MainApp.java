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
package org.meins.javafx.architektur.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Startklasse der Anwendung – idealerweise enthält sie keine
 * Anwenungs-spezifische Logik und hat auch keine Detail-Kenntnisse über zu
 * eventuell nötige Initialisierungs-Logik der Anwendung. Dies sollte besser so 
 * gekapselt werden dass Anwendung und Initialisierung der Anwendung unabhängig
 * von dieser Klasse getestet werden können. Wichtig ist das v.a. für UI-Tests, 
 * in denen das Fenster (die Stage) u.U. unter anderen Rahmenbedingungen erzeugt 
 * werden muss. 
 *
 * @author robert rohm
 */
public class MainApp extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Anwendung anwendung = new Anwendung();

    Scene scene = new Scene(anwendung.erzeugeHauptfenster());

    stage.setScene(scene);
    stage.show();
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
