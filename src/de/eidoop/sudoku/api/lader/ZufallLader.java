package de.eidoop.sudoku.api.lader;

import de.eidoop.sudoku.api.entities.Sudoku;
import de.eidoop.sudoku.api.enums.SudokuZustand;
import de.eidoop.sudoku.api.exceptions.*;
import de.eidoop.sudoku.api.util.ZufallsGenerator;

public class ZufallLader extends SudokuLader {
    private int zuBelegen;
    public ZufallLader(int zuBelegen){
        this.zuBelegen=zuBelegen;
    }

    /**
     * Gibt das eingegebene Sudoku geladen wieder zurück <br>
     * Fragt zusätzlich noch die Anzahl der zu ladenden Werte ab <br>
     * AUF MAXIMAL 32 ZUFALLSFELDER BESCHRÄNKT
     *
     * @param sudoku Sudoku
     */
    @Override
    public void ladeSudoku(Sudoku sudoku) {
        sudoku.reset();
        int i = 0;
        if(zuBelegen>32){
            zuBelegen=32;
            System.out.println("""

                    Höhere Werte führen zu exponentiell größeren Ladezeiten!
                    Die Maximal zulässige Menge an Feldern ist: 32
                    """);
        }
        System.out.println("Fülle " + zuBelegen + " Felder...\n");
        while ( i < zuBelegen) {
            int zeile = ZufallsGenerator.randInt();
            int spalte = ZufallsGenerator.randInt();
            int wert = ZufallsGenerator.randInt();
            try{
                sudoku.setFixedValue(zeile, spalte, wert);
            } catch (WertInQuadrantVorhandenException | FeldBelegtException | WertebereichUngueltigException |
                     WertInZeileVorhandenException | WertInSpalteVorhandenException e) {
                i++;
            }
        }
        sudoku.setZustand(SudokuZustand.GELADEN);
    }

}
