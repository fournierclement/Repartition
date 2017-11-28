import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;


public class MatricePreferences extends Matrice {
    String[][] preferences;
    double[][] distance;
    ArrayList<Regroupement> eleves;

    public MatricePreferences(ArrayList<Regroupement> eleves, String[][] preferences) {
	}
    
    
    public MatriceRangs preferencesARangs(){
        int[][] rangs = new int[preferences.length][preferences.length];
        // Parcourir tout le tableau (AJUSTER L'INDICE I)
        for( int i = 0; i < preferences.length; i++){
            int length = preferences[i].length;
            int[] compteur = {0, 0, 0, 0, 0, 0};
            // Parcourir toute la ligne, compter le nombre de TB, B, etc.
            for( int j = 0; j < length; j++ ){
                switch (preferences[i][j]) {
                    case "TB": compteur[0]++; break;
                    case "B": compteur[1]++; break;
                    case "AB": compteur[2]++; break;
                    case "P": compteur[3]++; break;
                    case "I": compteur[4]++; break;
                    case "AR": compteur[5]++; break;
                }
            }
            // Assigner les rangs à notre nouvelle matrice
            for (int j = 0; j < length; j++) {
                // à init avant
                switch (preferences[i][j]) {
                    case "TB": rangs[i][j] = 1; break;
                    case "B": rangs[i][j] = compteur[0] + 1; break;
                    case "AB": rangs[i][j] = compteur[1] + 1; break;
                    case "P": rangs[i][j] = compteur[2] + 1; break;
                    case "I": rangs[i][j] = compteur[3] + 1; break;
                    case "AR": rangs[i][j] = compteur[4] + 1; break;
                    case "-1": rangs[i][j] = 0; break;
                }
            }
        }
        return new MatriceRangs(this.eleves, rangs) ;
    }
    
    
    public String MatriceToString() {
    	int rows = this.preferences.length;
        int columns = this.preferences[0].length;
        String str = "|\t";
        for(int i=0;i<rows;i++){
        	str += "|\n|\t";
            for(int j=0;j<columns;j++){ str += this.preferences[i][j] + "\t"; }
        }
        return str;
    }
}
