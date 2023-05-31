package de.eidoop.sudoku.api.loeser;

import de.eidoop.sudoku.api.entities.Feldgruppe;
import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuZustand;

import java.util.ArrayList;

public class StrategieSudoku extends Loeser {

    private final Sudoku sudoku;

    public StrategieSudoku(Sudoku sudoku){
        super(sudoku);
        this.sudoku= sudoku;
    }

    /**
     * Löst das Sudoku durch BruteForce in strategischer Reihenfolge
     * @TODO Schritt 2 und 3 müssen noch implementiert werden
     */
    @Override
    public void loesen() {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                solveStepOne(y, x);
            }
        }

        if (sudoku.isGeloest())
            sudoku.setZustand(SudokuZustand.GELOEST);
        else
            sudoku.setZustand(SudokuZustand.UNLOESBAR);
    }

    private void solveStepOne(int zeile, int spalte) {
        ArrayList<Integer> values = sudoku.getFeld(spalte,zeile).getPossibleValues();
        if (values.size() != 1)
            return;

        sudoku.forceWert(zeile + 1, spalte + 1, values.get(0));
        for (int i = 0; i < 9; i++) {
            solveStepOne(zeile, i);
            solveStepOne(i, spalte);
            Feldgruppe quadrant = sudoku.getQuadrant(zeile, spalte);
            solveStepOne(((quadrant.getNr() / 3) * 3) + (i / 3), ((quadrant.getNr() % 3) * 3) + (i % 3));
        }

        solveStepTwo(zeile, spalte);
    }

    private void solveStepTwo(int zeile, int spalte) {

    }
}
