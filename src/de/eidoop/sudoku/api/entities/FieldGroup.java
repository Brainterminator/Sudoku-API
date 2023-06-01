package de.eidoop.sudoku.api.entities;

/**
 * The Field Groups are representing rows, columns and quadrants
 */
public class FieldGroup {
    private int nr;
    private final Field[] fields;

    public FieldGroup(){
        fields = new Field[9];
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public int getNr() {
        return nr;
    }

    public Field getField(int index) {
        if (index >= 0 && index < 9)
            return fields[index];

        return null;
    }

    public void setField(int index, Field field) {
        if(index >= 0 && index < 9)
            fields[index] = field;
    }

    /**
     * Checks if any of the Field Groups of this Field are occupied
     * @param value int
     * @return boolean
     */
    public boolean isOccupied(int value){
        for (Field field : fields) {
            if (field.getValue() == value)
                return true;
        }

        return false;
    }
}
