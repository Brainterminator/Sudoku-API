package de.eidoop.sudoku.api.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Diese Klasse verwaltet Zufallszahlen statisch,
 * die in Methoden verwendet werden
 */
public class ZufallsGenerator {
    /**
     *  Gibt eine zufällige ganze Zahl zwischen 1 und 9 zurück
     * @return int
     */
    public static int randInt() {
        int min = 1;
        int max = 9;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
