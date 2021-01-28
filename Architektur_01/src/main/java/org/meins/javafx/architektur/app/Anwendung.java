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
package org.meins.javafx.architektur.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.scene.Parent;
import org.meins.javafx.architektur.conf.GuiceModule;
import org.meins.javafx.architektur.viewmodel.UiViewModel;

/**
 * Diese Klasse dient vor allem als Wrapper für die Initialisierung der
 * Anwendungslogik. Sie kann als Factory verstanden werden (s.u.).
 *
 * @author robert rohm
 */
public class Anwendung {

  public Anwendung() {
    // no op
  }

  /**
   * In diesem Beispiel sollen View und ViewModel im Sinne der MVVM-Architektur
   * nach dem "ViewModel-First"-Ansatz erzeugt und verdrahtet werden.
   * <p>
   * In diesem Fall werden Abhängigkeiten innerhalb des ViewModels via
   * Dependency Injection mittels Google Guice verwaltet.</p>
   *
   * @return Parent-Node des geladenen Hauptfensters
   */
  public Parent erzeugeHauptfenster() {

    Injector injector = Guice.createInjector(new GuiceModule());
    UiViewModel uiViewModel = injector.getInstance(UiViewModel.class);

    return uiViewModel.getView();
  }
}
