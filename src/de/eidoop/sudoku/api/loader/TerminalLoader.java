package de.eidoop.sudoku.api.loader;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.exceptions.*;

import java.util.Scanner;
@Deprecated(since ="1.1", forRemoval = true)
public class TerminalLoader extends SudokuLoader {

    /**
     * Processes a String entered on Terminal
     * and loads it
     *
     * @param sudoku Sudoku
     */
    @Override
    @Deprecated(since ="1.1", forRemoval = true)
    public void loadSudoku(Sudoku sudoku) {
            sudoku.reset();
            Scanner scanner = new Scanner(System.in);
            String[] input = new String[9];
            System.out.println("Enter a Sudoku:");
            for (int i = 0; i < 9; i++) {
                input[i] = scanner.nextLine();
            }
            int error = 0;
            for (int j = 0; j < 9; j++) {
                int k=0;
                for (int l = 0; l < input[j].length(); l++) {
                    if(input[j].charAt(l)=='_'){
                        if(!sudoku.setEmpty(j,k))error++;
                    }
                    else if(input[j].charAt(l)==' ')k++;
                    else if(Character.isDigit(input[j].charAt(l))){
                        int value = Character.getNumericValue(input[j].charAt(l));
                        try{
                            sudoku.setFixedValue(j+1,k+1,value);
                        } catch (QuadrantOccupiedException | FieldFixedException |
                                 ValueOutOfRangeException | RowOccupiedException |
                                 ColumnOccupiedException | CoordinatesOutOfRangeException e) {
                            error++;
                        }
                    } else error++;
                }
            }
            if(error!=0)System.out.println(error + " Errors!");
    }
}
