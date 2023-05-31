package de.eidoop.sudoku.api.loeser;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuZustand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZufallSudoku extends Loeser {

    private final Sudoku sudoku;
    public ZufallSudoku(Sudoku sudoku) {
        super(sudoku);
        this.sudoku=sudoku;
    }

    /**
     * Löst das Sudoku durch BruteForce in zufälliger Reihenfolge
     */
    @Override
    public void loesen() {
        List<Integer> randFeld = new ArrayList<>();
        for (Integer i = 0; i < 9*9; i++) {
            randFeld.add(i);
        }
        Collections.shuffle(randFeld);
        if(solveRecursive(0,randFeld)){
            sudoku.setZustand(SudokuZustand.GELOEST);
        }  else sudoku.setZustand(SudokuZustand.UNLOESBAR);
    }

    private boolean solveRecursive(int index, List<Integer> randFeld) {
        if (index == 9 * 9)
            return true;
        int fieldPos=randFeld.get(index);
        int row = fieldPos / 9;
        int column = fieldPos % 9;

        if (sudoku.getFeld(column,row).getIsFixed())
            return solveRecursive(index + 1,randFeld);

        boolean isValueValid = false;
        for (int i = 1; i <= 9; i++) {
            boolean result = sudoku.forceWert(row + 1, column + 1, i);
            if (result) {
                isValueValid = solveRecursive(index + 1,randFeld);
                if (isValueValid)
                    break;
            }
        }

        if (!isValueValid)
            sudoku.forceWert(row + 1, column + 1, 0);

        return isValueValid;
    }
}