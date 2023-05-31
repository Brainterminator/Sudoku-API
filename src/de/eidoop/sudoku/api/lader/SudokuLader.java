package de.eidoop.sudoku.api.lader;

import de.eidoop.sudoku.api.entities.Sudoku;

/**
 * Lädt das Sudoku
 */
public abstract class SudokuLader {
    /**
     * Gibt das eingegebene Sudoku geladen wieder zurück
     * @param sudoku Sudoku
     * @return Sudoku
     */
    public abstract void ladeSudoku(Sudoku sudoku);
}
