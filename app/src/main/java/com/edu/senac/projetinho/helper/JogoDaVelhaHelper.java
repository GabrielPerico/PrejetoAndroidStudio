package com.edu.senac.projetinho.helper;

import android.widget.ImageView;

public class JogoDaVelhaHelper {

    public String MesageHelper(int i, int[] tabuleiro) {

        String vencedor = "";
        boolean end = false;
        int[][] estadoFinal = new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6},
        };

        for (int x = 0; x <= 7; x++) {
            int s1 = tabuleiro[estadoFinal[x][0]];
            int s2 = tabuleiro[estadoFinal[x][1]];
            int s3 = tabuleiro[estadoFinal[x][2]];
            if (s1 != 0) {
                if ((s1 == s2) && (s1 == s3)) {
                    end = true;
                    vencedor = (s1 == 1) ? "x" : "o";
                    i = 0;
                }
            }
        }
        if (end || i == 9) {
            if (i < 9) {
                if (vencedor.equals("x")) {
                    return "Vencedor foi X!";
                } else {
                    return "Vencedor foi O!";
                }
            } else {
                return "Deu Velha!";
            }
        }
        return "";
    }
}
