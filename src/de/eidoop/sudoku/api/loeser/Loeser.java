package de.eidoop.sudoku.api.loeser;

import de.eidoop.sudoku.api.entities.Sudoku;

public abstract class Loeser {

    public static final int length = 9;

    public Loeser(Sudoku sudoku) {
    }

    public abstract void loesen();
}
