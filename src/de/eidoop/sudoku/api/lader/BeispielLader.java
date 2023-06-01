package de.eidoop.sudoku.api.lader;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuZustand;
import de.eidoop.sudoku.api.exceptions.*;

public class BeispielLader extends SudokuLader {

    /**
     * Gibt das eingegebene Sudoku geladen wieder zurück
     *
     * @param sudoku Sudoku
     * @return Sudoku
     */
    @Override
    public void ladeSudoku(Sudoku sudoku){
        sudoku.reset();
        try {
            sudoku.setFixedValue(1, 2, 3);
            sudoku.setFixedValue(2, 4, 1);
            sudoku.setFixedValue(2, 5, 9);
            sudoku.setFixedValue(2, 6, 5);
            sudoku.setFixedValue(3, 3, 8);
            sudoku.setFixedValue(3, 8, 6);
            sudoku.setFixedValue(4, 1, 8);
            sudoku.setFixedValue(4, 5, 6);
            sudoku.setFixedValue(5, 1, 4);
            sudoku.setFixedValue(5, 4, 8);
            sudoku.setFixedValue(5, 9, 1);
            sudoku.setFixedValue(6, 5, 2);
            sudoku.setFixedValue(7, 2, 6);
            sudoku.setFixedValue(7, 7, 2);
            sudoku.setFixedValue(7, 8, 8);
            sudoku.setFixedValue(8, 4, 4);
            sudoku.setFixedValue(8, 5, 1);
            sudoku.setFixedValue(8, 6, 9);
            sudoku.setFixedValue(8, 9, 5);
            sudoku.setFixedValue(9, 8, 7);
            sudoku.setZustand(SudokuZustand.GELADEN);
        } catch (WertInQuadrantVorhandenException | FeldBelegtException | WertebereichUngueltigException |
                 WertInZeileVorhandenException | WertInSpalteVorhandenException e) {
            sudoku.reset();
        }
    }
}
