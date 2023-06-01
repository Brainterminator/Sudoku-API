package de.eidoop.sudoku.api.entities;

import de.eidoop.sudoku.api.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Single Field in a Sudoku
 */
public class Field {
    private int value = 0;
    private boolean isFixed = false;
    private final FieldGroup row;
    private final FieldGroup column;
    private final FieldGroup quadrant;
    private final ArrayList<Integer> possibleValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    /**
     * Constructs a new Field and provides the Field with the Field Groups
     *
     * @param row      FieldGroup
     * @param column   FieldGroup
     * @param quadrant FieldGroup
     */
    public Field(FieldGroup row, FieldGroup column, FieldGroup quadrant) {
        this.row = row;
        this.column = column;
        this.quadrant = quadrant;
    }

    /**
     * @param value int
     * @return bool depending on success
     * @throws FieldFixedException for fixed fields
     * @throws ValueOutOfRangeException for forbidden coordinates
     * @throws RowOccupiedException if the row is occupied
     * @throws ColumnOccupiedException if the column is occupied
     * @throws QuadrantOccupiedException if the quadrant is occupied
     */
    public boolean setValue(int value) throws FieldFixedException, ValueOutOfRangeException, RowOccupiedException, ColumnOccupiedException, QuadrantOccupiedException {
        if (isFixed) throw new FieldFixedException();
        if (value < 1 || value > 9) throw new ValueOutOfRangeException();
        if (row.isOccupied(value)) throw new RowOccupiedException();
        if (column.isOccupied(value)) throw new ColumnOccupiedException();
        if (quadrant.isOccupied(value)) throw new QuadrantOccupiedException();
        this.value = value;
        possibleValues.clear();
        for (int i = 0; i < 9; i++) {
            row.getField(i).getPossibleValues().remove((Integer) value);
            column.getField(i).getPossibleValues().remove((Integer) value);
            quadrant.getField(i).getPossibleValues().remove((Integer) value);
        }
        return true;
    }

    public boolean forceValue(int value) {
        if (this.isFixed || (value != 0 && (row.isOccupied(value) || column.isOccupied(value) || quadrant.isOccupied(value))))
            return false;

        this.value = value;
        possibleValues.clear();
        for (int i = 0; i < 9; i++) {
            row.getField(i).getPossibleValues().remove((Integer) value);
            column.getField(i).getPossibleValues().remove((Integer) value);
            quadrant.getField(i).getPossibleValues().remove((Integer) value);
        }
        return true;
    }

    public void reset() {
        this.value = 0;
        this.isFixed = false;
    }

    public int getValue() {
        return value;
    }

    public boolean getIsFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    public ArrayList<Integer> getPossibleValues() {
        return possibleValues;
    }

    public FieldGroup getQuadrant() {
        return quadrant;
    }

    public FieldGroup getColumn() {
        return column;
    }

    public FieldGroup getRow() {
        return row;
    }
}