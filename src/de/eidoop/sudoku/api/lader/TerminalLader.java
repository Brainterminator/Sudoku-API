package de.eidoop.sudoku.api.lader;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.exceptions.*;

import java.util.Scanner;

public class TerminalLader extends SudokuLader {

    /**
     * Gibt das eingegebene Sudoku geladen wieder zurück
     * @param sudoku Sudoku
     * @return Sudoku
     */
    @Override
    public void ladeSudoku(Sudoku sudoku) {
            Scanner scanner = new Scanner(System.in);
            String[] input = new String[9];
            System.out.println("Geben sie ein Sudoku ein:");
            for (int i = 0; i < 9; i++) {
                input[i] = scanner.nextLine();
            }
            int fehler = 0;
            for (int j = 0; j < 9; j++) {
                int k=0;
                for (int l = 0; l < input[j].length(); l++) {
                    if(input[j].charAt(l)=='_'){
                        if(!sudoku.setEmpty(j,k))fehler++;
                    }
                    else if(input[j].charAt(l)==' ')k++;
                    else if(Character.isDigit(input[j].charAt(l))){
                        int wert = Character.getNumericValue(input[j].charAt(l));
                        try{
                            sudoku.setFixedValue(j+1,k+1,wert);
                        } catch (WertInQuadrantVorhandenException | FeldBelegtException |
                                 WertebereichUngueltigException | WertInZeileVorhandenException |
                                 WertInSpalteVorhandenException e) {
                            fehler++;
                        }
                    } else fehler++;
                }
            }
            if(fehler!=0)System.out.println(fehler + " Fehler!");
    }
}
