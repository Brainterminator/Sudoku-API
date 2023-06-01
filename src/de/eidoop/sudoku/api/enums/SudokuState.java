package de.eidoop.sudoku.api.enums;

/**
 * Describes the current state of the Sudoku
 */
public enum SudokuState {
    EMPTY,
    LOADED,
    ATTEMPTING_SOLVE,
    SOLVED,
    UNSOLVABLE,
}
