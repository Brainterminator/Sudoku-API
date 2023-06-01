package de.eidoop.sudoku.api.entities;

import de.eidoop.sudoku.api.enums.SudokuState;
import de.eidoop.sudoku.api.exceptions.*;

public class Sudoku {

    protected SudokuState state = SudokuState.EMPTY;
    protected FieldGroup[] quadrants = new FieldGroup[9];
    protected FieldGroup[] rows = new FieldGroup[9];
    protected FieldGroup[] columns = new FieldGroup[9];

    public Sudoku() {
        for (int i = 0; i < 9; i++) {
            rows[i] = new FieldGroup();
            rows[i].setNr(i);
            columns[i] = new FieldGroup();
            columns[i].setNr(i);
            quadrants[i] = new FieldGroup();
            quadrants[i].setNr(i);
        }

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                FieldGroup quadrant = getQuadrant(y, x);
                int quadrantPos = ((y % 3) * 3) + (x % 3);
                Field field = new Field(rows[y], columns[x], quadrant);
                rows[y].setField(x, field);
                columns[x].setField(y, field);
                quadrant.setField(quadrantPos, field);
            }
        }
    }

    /**
     * Checks if the Sudoku is solved
     *
     * @return boolean
     */
    public boolean isSolved() {
        for (FieldGroup fieldGroup : rows) {
            for (int x = 0; x < columns.length; x++) {
                if (fieldGroup.getField(x).getValue() == 0)
                    return false;
            }
        }
        this.setState(SudokuState.SOLVED);
        return true;
    }

    /**
     * Sets a value
     *
     * @param row int
     * @param column int
     * @param value int
     * @throws FieldFixedException            for fixed fields
     * @throws ValueOutOfRangeException       for forbidden coordinates
     * @throws RowOccupiedException           if the row is occupied
     * @throws ColumnOccupiedException        if the column is occupied
     * @throws QuadrantOccupiedException      if the quadrant is occupied
     * @throws CoordinatesOutOfRangeException if the coordinates are not allowed
     */
    public void setValue(int row, int column, int value) throws CoordinatesOutOfRangeException, QuadrantOccupiedException, ColumnOccupiedException, RowOccupiedException, ValueOutOfRangeException, FieldFixedException {
        if (isInRange(row - 1, column - 1)) {
            rows[row - 1].getField(column - 1).setValue(value);
        } else throw new CoordinatesOutOfRangeException();
    }

    public boolean forceValue(int row, int column, int value) {
        if (isInRange(row - 1, column - 1))
            return rows[row - 1].getField(column - 1).forceValue(value);

        return false;
    }

    /**
     * Sets a fixed value
     *
     * @param row int
     * @param column int
     * @param value int
     * @throws FieldFixedException            for fixed fields
     * @throws ValueOutOfRangeException       for forbidden coordinates
     * @throws RowOccupiedException           if the row is occupied
     * @throws ColumnOccupiedException        if the column is occupied
     * @throws QuadrantOccupiedException      if the quadrant is occupied
     * @throws CoordinatesOutOfRangeException if the coordinates are not allowed
     */
    public void setFixedValue(int row, int column, int value) throws QuadrantOccupiedException, ColumnOccupiedException, RowOccupiedException, ValueOutOfRangeException, FieldFixedException, CoordinatesOutOfRangeException {
        setValue(row, column, value);
        rows[row - 1].getField(column - 1).setFixed(true);
    }


    public boolean setEmpty(int row, int column) {
        if (isInRange(row, column)) {
            rows[row].getField(column).reset();
            return true;
        }
        return false;
    }

    /**
     * @param row    int
     * @param column int
     * @return boolean
     */
    private boolean isInRange(int row, int column) {
        return row >= 0 && row < 9 && column >= 0 && column < 9;
    }

    public SudokuState getState() {
        return state;
    }

    public void setState(SudokuState state) {
        this.state = state;
    }

    public FieldGroup getQuadrant(int row, int column) {
        return quadrants[((row / 3) * 3) + (column / 3)];
    }

    public Field getField(int x, int y) {
        return this.rows[y].getField(x);
    }

    public void reset() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.setEmpty(i, j);
            }
            this.setState(SudokuState.EMPTY);
        }
        this.setState(SudokuState.LOADED);
    }
}
