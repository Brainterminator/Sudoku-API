package de.eidoop.sudoku.api.entities;

import de.eidoop.sudoku.api.enums.SudokuZustand;
import de.eidoop.sudoku.api.exceptions.*;
import de.eidoop.sudoku.api.ui.ISudokuAnzeige;
import de.eidoop.sudoku.api.ui.TerminalAnzeige;

/**
 * Diese Klasse repräsentiert ein gesamtes Sudoku Spielfeld
 */
public class Sudoku {

    protected SudokuZustand zustand = SudokuZustand.LEER;
    protected Feldgruppe[] quadranten = new Feldgruppe[9];
    protected Feldgruppe[] zeilen = new Feldgruppe[9];
    protected Feldgruppe[] spalten = new Feldgruppe[9];

    /**
     * Standard Konstruktor, initialisiert das Feld
     */
    public Sudoku() {
        for (int i = 0; i < 9; i++) {
            zeilen[i] = new Feldgruppe();
            zeilen[i].setNr(i);
            spalten[i] = new Feldgruppe();
            spalten[i].setNr(i);
            quadranten[i] = new Feldgruppe();
            quadranten[i].setNr(i);
        }

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Feldgruppe quadrant = getQuadrant(y, x);
                int quadrantenPos = ((y % 3) * 3) + (x % 3);
                Feld feld = new Feld(zeilen[y], spalten[x], quadrant);
                zeilen[y].setFeld(x, feld);
                spalten[x].setFeld(y, feld);
                quadrant.setFeld(quadrantenPos, feld);
            }
        }
    }

    /**
     * gibt das Sudoku aus
     */
    public void ausgeben(ISudokuAnzeige anzeige) {
        anzeige.druckeSudoku(this);
    }

    /**
     * Gibt an ob das Sudoku geloest ist
     *
     * @return boolean
     */
    public boolean isGeloest() {
        for (Feldgruppe feldgruppe : zeilen) {
            for (int x = 0; x < spalten.length; x++) {
                if (feldgruppe.getFeld(x).getWert() == 0)
                    return false;
            }
        }

        return true;
    }

    /**
     * Setzt den Wert in Speile und Zalte entsprechend und gibt zurück ob die Operation erfolgreich war
     *
     * @param zeile  int
     * @param spalte int
     * @param wert   int
     * @return boolean
     */
    public boolean setWert(int zeile, int spalte, int wert) throws UngueltigeKoordinatenException, WertInQuadrantVorhandenException, WertInSpalteVorhandenException, WertInZeileVorhandenException, WertebereichUngueltigException, FeldBelegtException {
        if (istKoordinateGueltig(zeile - 1, spalte - 1)) {
            return zeilen[zeile - 1].getFeld(spalte - 1).setWert(wert);
        } else throw new UngueltigeKoordinatenException();
    }

    public boolean forceWert(int zeile, int spalte, int wert) {
        if (istKoordinateGueltig(zeile - 1, spalte - 1))
            return zeilen[zeile - 1].getFeld(spalte - 1).forceWert(wert);

        return false;
    }

    /**
     * Setzt den Wert in Speile und Zalte entsprechend und gibt zurück ob die Operation erfolgreich war<br>
     * Darüber hinaus wird der Wert anschließend fixiert
     *
     * @param zeile  int
     * @param spalte int
     * @param wert   int
     * @return param
     */
    public boolean setFixedValue(int zeile, int spalte, int wert) throws WertInQuadrantVorhandenException, WertInSpalteVorhandenException, WertInZeileVorhandenException, WertebereichUngueltigException, FeldBelegtException {
        try {
            boolean result = setWert(zeile, spalte, wert);
            if (result)
                zeilen[zeile - 1].getFeld(spalte - 1).setFixed(true);

            return result;
        } catch (UngueltigeKoordinatenException e) {
            return false;
        }
    }

    /**
     * Setzt das Feld bei gültiger Koordinate auf Leer
     *
     * @param zeile  int
     * @param spalte int
     * @return boolean
     */
    public boolean setEmpty(int zeile, int spalte) {
        if (istKoordinateGueltig(zeile, spalte)) {
            zeilen[zeile].getFeld(spalte).reset();
            return true;
        }
        return false;
    }

    /**
     * Gibt an ob die eingegeben Koordinaten im Sudoku liegen
     *
     * @param zeile  int
     * @param spalte int
     * @return boolean
     */
    private boolean istKoordinateGueltig(int zeile, int spalte) {
        return zeile >= 0 && zeile < 9 && spalte >= 0 && spalte < 9;
    }

    /**
     * Gibt den Zustand des Sudokus aus
     *
     * @return SudokuZustand
     */
    public SudokuZustand getZustand() {
        return zustand;
    }

    /**
     * Setzt den Zustand des Sudokus
     *
     * @param zustand SudokuZustand
     */
    public void setZustand(SudokuZustand zustand) {
        this.zustand = zustand;
    }

    public Feldgruppe getQuadrant(int zeile, int spalte) {
        return quadranten[((zeile / 3) * 3) + (spalte / 3)];
    }

    public Feld getFeld(int x, int y) {
        return this.zeilen[y].getFeld(x);
    }

    public void reset(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.setEmpty(i, j);
            }
        }
        this.setZustand(SudokuZustand.GELADEN);
    }
}
