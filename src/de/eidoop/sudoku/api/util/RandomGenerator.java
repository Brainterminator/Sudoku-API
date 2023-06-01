package de.eidoop.sudoku.api.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    /**
     * Returns a random number between 1 and 9
     *
     * @return int
     */
    public static int randInt() {
        int min = 1;
        int max = 9;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
