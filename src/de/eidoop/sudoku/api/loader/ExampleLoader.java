package de.eidoop.sudoku.api.loader;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuState;
import de.eidoop.sudoku.api.exceptions.*;

public class ExampleLoader extends SudokuLoader {

    /**
     * Loads the default example Sudoku
     * @param sudoku Sudoku
     */
    @Override
    public void loadSudoku(Sudoku sudoku){
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
            sudoku.setState(SudokuState.LOADED);
        } catch (QuadrantOccupiedException | FieldFixedException | ValueOutOfRangeException |
                 RowOccupiedException | ColumnOccupiedException | CoordinatesOutOfRangeException e) {
            sudoku.reset();
        }
    }
}
