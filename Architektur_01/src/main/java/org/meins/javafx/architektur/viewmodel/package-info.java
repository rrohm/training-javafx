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
/**
 * <p>Die ViewModel-Bausteine der Anwendung – in diesem Beispiel-Projekt zur Gegenüberstellung
 * teils nach <b>"View first"</b>-Ansatz, teils nach <b>"ViewModel first"</b>-Ansatz implementiert.</p>
 * 
 * <p>Das ViewModel ist das Bindglied zwischen Model und View.</p>
 * <ul>
 *   <li>Es hält die Model-Information für die View bereit - ggf. in für die View geeigneter Form</li>
 *   <li>Es stellt ggf. zusätzliche Properties für die UI-Steuerung bereit.</li>
 *   <li>Es kennt jedoch keine spezifischen UI-Elemente.</li>
 *   <li>Statt dessen werden UI-Elemente an ViewModel-Elemente gebunden.</li>
 *   <li>Eine Implementierung des ViewModels als eigenständige Struktur macht es möglich, ViewModel-Verhalten
 *       völlig unabhängig vom UI zu testen.
 *   </li>
 *   <li>Nach MVVM-Pattern ist das ViewModel auch zuständig für die Aktualisierung des Models. Allerdings
 *       macht das Pattern und auch das JavaFX-Framework keine Aussage darüber, <i>wie</i> dies im Detail zu lösen ist.
 *       Dies bleibe als "Herausforderung" für die Entwicklung.
 *   </li>
 * </ul>
 */
package org.meins.javafx.architektur.viewmodel;