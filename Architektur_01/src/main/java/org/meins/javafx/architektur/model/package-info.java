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
 * Das Model - in klassischer Form:
 * <ul>
 *     <li>Es hat keine Kenntnisse von View, und auch nicht vom ViewModel.</li>
 *     <li>Es wird teilweise diskutiert, ob in JavaFX-MVVM-Anwendungen das Model nicht auch die Business Logik
 *        enthält, oder so etwas wie Business-Facades. V.a. für Anwendungen, die auf RESTful Services aufbauen,
 *        ist Verständnis des Models im Sinne eines reinen Daten-Modells sinnvoll.
 *     </li>
 * </ul>
 */
package org.meins.javafx.architektur.model;