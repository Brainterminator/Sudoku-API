package de.eidoop.sudoku.api.enums;

/**
 * Describes the current state of the Sudoku
 */
public enum SudokuState {
    EMPTY("Empty"),
    LOADED("Loaded"),
    SOLVING("Solving"),
    SOLVED("Solved"),
    UNSOLVABLE("Unsolvable");

    private final String stateName;

    SudokuState(String stateName){
        this.stateName=stateName;
    }

    public String getStateName() {
        return stateName;
    }
}