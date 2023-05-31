package de.eidoop.sudoku.api.ui;

import de.eidoop.sudoku.api.entities.Feldgruppe;
import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuZustand;
import de.eidoop.sudoku.api.loeser.Loeser;

/**
 * Statische Klasse die Ausgaben des Sudokus auf dem Terminal verwaltet
 */
public class TerminalAnzeige implements ISudokuAnzeige{
    /**
     * Benötigt das Sudoku um es auf dem Terminal auszugeben
     * @param sudoku Sudoku
     */
    public void druckeSudoku(Sudoku sudoku){
        for (int y = 0; y < Loeser.length; y++) {
            if (y % 3 == 0)
                druckeLinie();

            for (int x = 0; x < Loeser.length; x++) {
                if (x % 3 == 0)
                    System.out.print("| ");

                int wert = sudoku.getFeld(x,y).getWert();
                if (wert == 0)
                    System.out.print("  ");
                else
                    System.out.printf("%1d ", wert);
            }
            System.out.print("|\n");
        }
        druckeLinie();
    }

    private static void druckeLinie(){
        System.out.print("+ - - - + - - - + - - - +\n");
    }

    /**
     * Gibt im Falle, dass die Verarbeitung abgeschlossen wurde Auskunft darüber
     * ob das Sudoku gelöst werden konnte
     * @param zustand SudokuZustand
     */
    public static void druckeFazit(SudokuZustand zustand){
        switch (zustand) {
            case UNLOESBAR -> System.out.println("Das Sudoku ist unlösbar!");
            case GELOEST -> System.out.println("Das Sudoku wurde gelöst!");
        }
    }
}
