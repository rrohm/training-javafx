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
package org.meins.fxml.di;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * This example illustrates the use of dependency injection with the FXML
 * controllers. The optional ControllerFactory for the FXMLLoader is a good
 * starting point. It is a mere callback that must provide the controller class
 * instance. You have to create it yourself – so, you are free to do any kind of
 * dependency injection here. This may be simple constructor injection or a
 * full-blown DI framework like Google Guice, Dagger etc.
 *
 * @author Robert Rohm&lt;r.rohm@aeonium-systems.de&gt;
 */
public class FXMLApp_06_DI extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    
    // The anonymous callback could be refactored into an inner or even outer class!
    Parent root = FXMLLoader.load(getClass().getResource("Form.fxml"), null, null, new Callback<Class<?>, Object>() {

      private final Injector injector = Guice.createInjector(new GuiceDIModule());

      /**
       * This method must return the new instance of the given class. 
       * @param param
       * @return 
       */
      @Override
      public Object call(Class<?> param) {
        // No. not the brute-force, hardwired way: 
//        FormController instance = injector.getInstance(FormController.class);
        
        // Stay generic, return an object
        Object instance = injector.getInstance(param);
        return instance;
      }
    });

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

}
