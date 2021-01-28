package org.meins.javafx.architektur.data;

import org.meins.javafx.architektur.model.Baustein;
import org.meins.javafx.architektur.model.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Dummy-Datenzugriffsklasse für Element-Model-Daten, ersetzt z.B. einen
 * REST-Service-Consumer oder Spring-Repository.
 *
 * @author robert
 */
public class ElementRepository {


    public List<Element> leseAlle() {
        List<Element> daten = new ArrayList<>();
        daten.addAll(Arrays.asList(
                new Element(1, "Element 1", "Das erste Element",
                        new Baustein(1, "Baustein 1"),
                        new Baustein(3, "Baustein 3"),
                        new Baustein(2, "Baustein 2")),
                new Element(2, "Element 2", "Das zweite Element",
                        new Baustein(3, "Baustein 3"),
                        new Baustein(4, "Baustein 4"),
                        null),
                new Element(3, "Element 3", "Das dritte Element",
                        new Baustein(2, "Baustein 2"),
                        new Baustein(1, "Baustein 1"),
                        new Baustein(3, "Baustein 3"))
        ));

        // Unser Datenzugriff dauert etwas ....
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return daten;
    }
}
