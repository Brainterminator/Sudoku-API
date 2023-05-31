package de.eidoop.sudoku.api;


import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuZustand;
import de.eidoop.sudoku.api.lader.BeispielLader;
import de.eidoop.sudoku.api.lader.SudokuLader;
import de.eidoop.sudoku.api.lader.TerminalLader;
import de.eidoop.sudoku.api.lader.ZufallLader;
import de.eidoop.sudoku.api.ui.TerminalAnzeige;
import de.eidoop.sudoku.api.loeser.Loeser;
import de.eidoop.sudoku.api.loeser.ProbierSudoku;
import de.eidoop.sudoku.api.loeser.StrategieSudoku;
import de.eidoop.sudoku.api.loeser.ZufallSudoku;

import java.util.Scanner;

public class SudokuApp {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean shouldRun = true;
        Sudoku sudoku = new Sudoku();
        while(shouldRun){
        sudoku.reset();
        System.out.println("Wählen sie aus, wie sie ihr Sudoku lösen möchten:\n(1:Ausprobieren 2:Zufällig 3:Strategisch)\n");

        int input = scanner.nextInt();
        Loeser loeser;
            switch (input) {
                case 2 -> loeser = new ZufallSudoku(sudoku);
                case 3 -> loeser = new StrategieSudoku(sudoku);
                default -> loeser = new ProbierSudoku(sudoku);
            }
            System.out.println("\nWählen sie aus, wie sie ihr Sudoku laden möchten:\n(1:Beispiel 2:Manuell 3:Randomisiert)\n");

        input = scanner.nextInt();

        SudokuLader sudokuLader;
            switch (input) {
                case 2 -> sudokuLader = new TerminalLader();
                case 3 -> {
                    System.out.println("\nWieviele Felder sollen vorbelegt sein?\n");
                    sudokuLader = new ZufallLader(scanner.nextInt());
                }
                default -> sudokuLader = new BeispielLader();
            }
        sudokuLader.ladeSudoku(sudoku);
        sudoku.ausgeben(new TerminalAnzeige());

        System.out.println();
        System.out.println("Eingabe wird verarbeitet...");
        sudoku.setZustand(SudokuZustand.LOESUNGSVERSUCH);
        loeser.loesen();

        System.out.println();

        sudoku.ausgeben(new TerminalAnzeige());
        TerminalAnzeige.druckeFazit(sudoku.getZustand());

        System.out.println("\n\n(1:fortfahren 2:abbrechen)\n");

        input = scanner.nextInt();

        if(input==2)shouldRun=false;
        }
    }
}