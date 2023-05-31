package de.eidoop.sudoku.api.entities;

/**
 * Die Feldgruppen repräsentieren im Sudoku die Zeilen, Spalten und Quadranten
 */
public class Feldgruppe {
    private int nr;
    private final Feld[] felder;

    /**
     * Konstruktor der Feldgruppe
     */
    public Feldgruppe(){
        felder = new Feld[9];
    }

    /**
     * Setzt die Nummer der Feldgruppe
     * @param nr int
     */
    public void setNr(int nr) {
        this.nr = nr;
    }

    /**
     * Gibt die Nummer der Feldgruppe aus
     * @return int
     */
    public int getNr() {
        return nr;
    }

    /**
     * Gibt das Feld an dem jeweiligen Index aus
     * @param index int
     * @return Feld
     */
    public Feld getFeld(int index) {
        if (index >= 0 && index < 9)
            return felder[index];

        return null;
    }

    /**
     * Setzt das Feld an dem Index
     * @param index int
     * @param feld Feld
     */
    public void setFeld(int index, Feld feld) {
        if(index >= 0 && index < 9)
            felder[index] = feld;
    }

    /**
     * Gibt zurück ob der Wert in der Feldgruppe vorhanden ist
     * @param wert int
     * @return boolean
     */
    public boolean istVorhanden(int wert){
        for (Feld feld : felder) {
            if (feld.getWert() == wert)
                return true;
        }

        return false;
    }
}
