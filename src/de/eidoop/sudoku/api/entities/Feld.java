package de.eidoop.sudoku.api.entities;

import de.eidoop.sudoku.api.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Diese Klasse stellt im Sudoku ein einzelnes Feld dar
 */
public class Feld {
    private int wert = 0;
    private boolean isFixed = false;
    private final Feldgruppe zeile;
    private final Feldgruppe spalte;
    private final Feldgruppe quadrant;
    private final ArrayList<Integer> possibleValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    /**
     * Konstruiert ein neues Feld und übergibt die Feldgruppen an das Feld
     * @param neueZeile Feldgruppe
     * @param neueSpalte Feldgruppe
     * @param neuerQuadrant Feldgruppe
     */
    public Feld(Feldgruppe neueZeile, Feldgruppe neueSpalte, Feldgruppe neuerQuadrant){
        zeile = neueZeile;
        spalte = neueSpalte;
        quadrant = neuerQuadrant;
    }

    /**
     * Gibt den Wert des jeweiligen Feldes zurück
     * @return int
     */
    public int getWert() {
        return wert;
    }

    /**
     *  Setzt im Feld den eingegebenen Wert ein
     *  Gibt bei Erfolg "true" und ansonsten "false" zurück
     * @param wert int
     * @return boolean
     */
    public boolean setWert(int wert) throws FeldBelegtException, WertebereichUngueltigException, WertInZeileVorhandenException, WertInSpalteVorhandenException, WertInQuadrantVorhandenException {
        if(isFixed) throw new FeldBelegtException();
        if(wert<1 || wert>9) throw new WertebereichUngueltigException();
        if(zeile.istVorhanden(wert)) throw new WertInZeileVorhandenException();
        if(spalte.istVorhanden(wert)) throw new WertInSpalteVorhandenException();
        if(quadrant.istVorhanden(wert))throw new WertInQuadrantVorhandenException();
        this.wert = wert;
        possibleValues.clear();
        for (int i = 0; i < 9; i++) {
            zeile.getFeld(i).getPossibleValues().remove((Integer)wert);
            spalte.getFeld(i).getPossibleValues().remove((Integer)wert);
            quadrant.getFeld(i).getPossibleValues().remove((Integer)wert);
        }
        return true;
    }

    public boolean forceWert(int wert) {
        if (this.isFixed||(wert != 0 && (zeile.istVorhanden(wert) || spalte.istVorhanden(wert) || quadrant.istVorhanden(wert))))
            return false;

        this.wert = wert;
        possibleValues.clear();
        for (int i = 0; i < 9; i++) {
            zeile.getFeld(i).getPossibleValues().remove((Integer)wert);
            spalte.getFeld(i).getPossibleValues().remove((Integer)wert);
            quadrant.getFeld(i).getPossibleValues().remove((Integer)wert);
        }
        return true;
    }

    /**
     * Gibt zurück ob das jeweilige Feld bereits fixiert ist
     * @return boolean
     */
    public boolean getIsFixed(){
        return isFixed;
    }

    /**
     * Setzt die Fixierung der Funktion
     * @param fixed boolean
     */
    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }

    /**
     * Gibt die möglichen Werte für das Feld zurück
     * @return ArrayList<Integer>
     */
    public ArrayList<Integer> getPossibleValues() {
        return possibleValues;
    }

    /**
     * Gibt den Quadranten des Feldes zurück
     * @return Feldgruppe
     */
    public Feldgruppe getQuadrant() {
        return quadrant;
    }
    /**
     * Gibt die Spalte des Feldes zurück
     * @return Feldgruppe
     */
    public Feldgruppe getSpalte() {
        return spalte;
    }
    /**
     * Gibt die Zeile des Feldes zurück
     * @return Feldgruppe
     */
    public Feldgruppe getZeile() {
        return zeile;
    }

    public void reset(){
        this.wert=0;
        this.isFixed=false;
    }
}