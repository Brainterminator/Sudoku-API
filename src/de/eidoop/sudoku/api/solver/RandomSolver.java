package de.eidoop.sudoku.api.solver;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Deprecated(since = "1.1", forRemoval = true)
public class RandomSolver extends Solver {

    private final Sudoku sudoku;
    public RandomSolver(Sudoku sudoku) {
        super(sudoku);
        this.sudoku=sudoku;
    }

    /**
     * Solves the Sudoku in random order <br>
     * which can take some time
     */
    @Override
    public void solve() {
        List<Integer> randomField = new ArrayList<>();
        for (Integer i = 0; i < 9*9; i++) {
            randomField.add(i);
        }
        Collections.shuffle(randomField);
        if(solveRecursive(0,randomField)){
            sudoku.setState(SudokuState.SOLVED);
        }  else sudoku.setState(SudokuState.UNSOLVABLE);
    }

    private boolean solveRecursive(int index, List<Integer> randomField) {
        if (index == 9 * 9)
            return true;
        int fieldPos=randomField.get(index);
        int row = fieldPos / 9;
        int column = fieldPos % 9;

        if (sudoku.getField(column,row).getIsFixed())
            return solveRecursive(index + 1,randomField);

        boolean isValueValid = false;
        for (int i = 1; i <= 9; i++) {
            boolean result = sudoku.forceValue(row + 1, column + 1, i);
            if (result) {
                isValueValid = solveRecursive(index + 1,randomField);
                if (isValueValid)
                    break;
            }
        }

        if (!isValueValid)
            sudoku.forceValue(row + 1, column + 1, 0);

        return isValueValid;
    }
}