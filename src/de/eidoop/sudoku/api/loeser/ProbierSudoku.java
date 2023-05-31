package de.eidoop.sudoku.api.loeser;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuZustand;

public class ProbierSudoku extends Loeser {

    private final Sudoku sudoku;
    public ProbierSudoku(Sudoku sudoku) {
        super(sudoku);
        this.sudoku = sudoku;
    }

    /**
     * Löst das Sudoku durch BruteForce in statischer Reihenfolge
     */
    @Override
    public void loesen() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                sudoku.forceWert(y + 1, x + 1, 0);
            }
        }
        if(solveRecursive(0, sudoku)){
            sudoku.setZustand(SudokuZustand.GELOEST);
        }  else sudoku.setZustand(SudokuZustand.UNLOESBAR);
    }

    private boolean solveRecursive(int fieldPos, Sudoku sudoku) {
        if (fieldPos == 9 * 9)
            return true;

        int row = fieldPos / 9;
        int column = fieldPos % 9;

        if (sudoku.getFeld(column,row).getIsFixed())
            return solveRecursive(fieldPos + 1, sudoku);

        boolean isValueValid = false;
        for (int i = 1; i <= 9; i++) {
            boolean result = sudoku.forceWert(row + 1, column + 1, i);
            if (result) {
                isValueValid = solveRecursive(fieldPos + 1, sudoku);
                if (isValueValid)
                    break;
            }
        }

        if (!isValueValid)
            sudoku.forceWert(row + 1, column + 1, 0);

        return isValueValid;
    }
}
