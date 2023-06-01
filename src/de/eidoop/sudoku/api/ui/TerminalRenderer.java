package de.eidoop.sudoku.api.ui;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.solver.Solver;

@Deprecated(since = "1.1", forRemoval = true)
public class TerminalRenderer implements ISudokuRenderer {
    public void print(Sudoku sudoku) {
        for (int y = 0; y < Solver.length; y++) {
            if (y % 3 == 0)
                printLine();

            for (int x = 0; x < Solver.length; x++) {
                if (x % 3 == 0)
                    System.out.print("| ");

                int value = sudoku.getField(x, y).getValue();
                if (value == 0)
                    System.out.print("  ");
                else
                    System.out.printf("%1d ", value);
            }
            System.out.print("|\n");
        }
        printLine();
    }

    private static void printLine() {
        System.out.print("+ - - - + - - - + - - - +\n");
    }
}
