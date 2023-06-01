package de.eidoop.sudoku.api.solver;

import de.eidoop.sudoku.api.entities.Sudoku;

public abstract class Solver {

    public static final int length = 9;

    public Solver(Sudoku sudoku) {
    }

    public abstract void solve();
}
