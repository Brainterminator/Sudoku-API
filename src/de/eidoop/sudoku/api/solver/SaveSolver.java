package de.eidoop.sudoku.api.solver;

import de.eidoop.sudoku.api.entities.FieldGroup;
import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuState;

import java.util.ArrayList;

public class SaveSolver extends Solver {

    private final Sudoku sudoku;

    public SaveSolver(Sudoku sudoku){
        super(sudoku);
        this.sudoku= sudoku;
    }

    /**
     * Solves the next Sudoku step safely
     */
    @Override
    public void solve() {
        sudoku.setState(SudokuState.ATTEMPTING_SOLVE);
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                solveRecursive(y, x);
            }
        }

        if (sudoku.isSolved())
            sudoku.setState(SudokuState.SOLVED);
        else
            sudoku.setState(SudokuState.LOADED);
    }

    private void solveRecursive(int row, int column) {
        ArrayList<Integer> values = sudoku.getField(column,row).getPossibleValues();
        if (values.size() != 1)
            return;

        sudoku.forceValue(row + 1, column + 1, values.get(0));
        for (int i = 0; i < 9; i++) {
            solveRecursive(row, i);
            solveRecursive(i, column);
            FieldGroup quadrant = sudoku.getQuadrant(row, column);
            solveRecursive(((quadrant.getNr() / 3) * 3) + (i / 3), ((quadrant.getNr() % 3) * 3) + (i % 3));
        }
    }
}
