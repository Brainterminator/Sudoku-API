package de.eidoop.sudoku.api.loader;

import de.eidoop.sudoku.api.entities.Sudoku;

/**
 * Loads values into a Sudoku
 */
public abstract class SudokuLoader {
    /**
     * Loads values to the given Sudoku in a certain way
     *
     * @param sudoku Sudoku
     */
    public abstract void loadSudoku(Sudoku sudoku);
}
