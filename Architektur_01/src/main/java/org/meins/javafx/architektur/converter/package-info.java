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
 * Converter, zwecks Wiederverwendung in eigenständigen Klassen implementiert.
 * <p>
 * Je nach MVVM-Ansatz ("View first" oder "ViewModel first") wäre es u.U.
 * entweder Sache der View <i>oder</i> des ViewModels, die Definition von
 * Convertern zu übernehmen. So gesehen schafft das Zusammenfassen in einem
 * eigenen eigenen Package mehr Klarheit und erübrigt u.U. nicht eindeutig zu
 * treffende Entscheidungen.</p>
 */
package org.meins.javafx.architektur.converter;
