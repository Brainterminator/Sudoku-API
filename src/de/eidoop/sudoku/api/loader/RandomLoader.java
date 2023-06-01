package de.eidoop.sudoku.api.loader;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuState;
import de.eidoop.sudoku.api.exceptions.*;
import de.eidoop.sudoku.api.util.RandomGenerator;

public class RandomLoader extends SudokuLoader {
    private int toFill;

    public RandomLoader(){
        this.toFill=12;
    }

    public RandomLoader(int toFill){
        this.toFill=toFill;
    }

    /**
     * Loads a randomized Sudoku
     * @param sudoku Sudoku
     */
    @Override
    public void loadSudoku(Sudoku sudoku) {
        sudoku.reset();
        int i = 0;
        if(toFill>32){
            toFill=32;
        }
        while ( i < toFill) {
            int row = RandomGenerator.randInt();
            int column = RandomGenerator.randInt();
            int value = RandomGenerator.randInt();
            try{
                sudoku.setFixedValue(row, column, value);
            } catch (QuadrantOccupiedException | FieldFixedException | ValueOutOfRangeException |
                     RowOccupiedException | ColumnOccupiedException | CoordinatesOutOfRangeException e) {
                i++;
            }
        }
        sudoku.setState(SudokuState.LOADED);
    }

}
