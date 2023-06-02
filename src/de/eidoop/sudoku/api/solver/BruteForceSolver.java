package de.eidoop.sudoku.api.solver;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuState;

public class BruteForceSolver extends Solver {

    private final Sudoku sudoku;

    public BruteForceSolver(Sudoku sudoku) {
        super(sudoku);
        this.sudoku = sudoku;
    }

    /**
     * Solves the Sudoku by BruteForce
     */
    @Override
    public void solve() {
        sudoku.setState(SudokuState.SOLVING);
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                sudoku.forceValue(y + 1, x + 1, 0);
            }
        }
        if (solveRecursive(0, sudoku)) {
            sudoku.setState(SudokuState.SOLVED);
        } else sudoku.setState(SudokuState.UNSOLVABLE);
    }

    private boolean solveRecursive(int fieldPos, Sudoku sudoku) {
        if (fieldPos == 9 * 9)
            return true;

        int row = fieldPos / 9;
        int column = fieldPos % 9;

        if (sudoku.getField(column, row).getIsFixed())
            return solveRecursive(fieldPos + 1, sudoku);

        boolean isValueValid = false;
        for (int i = 1; i <= 9; i++) {
            boolean result = sudoku.forceValue(row + 1, column + 1, i);
            if (result) {
                isValueValid = solveRecursive(fieldPos + 1, sudoku);
                if (isValueValid)
                    break;
            }
        }

        if (!isValueValid)
            sudoku.forceValue(row + 1, column + 1, 0);

        return isValueValid;
    }
}
