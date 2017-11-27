import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class Matrice {
    String[][] preferences;
    int[][] rangs;
    double[][] distance;
    ArrayList<Regroupement> Eleves;

    public Matrice(String filePath) throws FileNotFoundException {
    	String[] colonne = new String[50];
        int nbrLigne = 0;
        String delimiter = ",";
        
        String[][] MatricePrefEleve = new String[50][50];

        Scanner sc = new Scanner(new File(filePath));

        while (sc.hasNextLine())
        {
            String line = sc.nextLine();
            colonne = line.split(delimiter);
            for(int i= 0; i <colonne.length;i++)
            {
            	MatricePrefEleve[nbrLigne][i]= colonne[i];
            }
            nbrLigne++;
        }
        System.out.println("*****Matrice de Preference Eleve en string*****");
        System.out.println("");

        for (int x=0; x<nbrLigne; x++)
        {
            for (int y=0; y<colonne.length; y++)
            {
                System.out.print(MatricePrefEleve[x][y] +" ");
              
            }
            System.out.println("");
        }
        int[][] MatricePrefEleveInt = new int[nbrLigne][colonne.length];

        System.out.println(MatricePrefEleve[1][1]);
        System.out.println("");
        System.out.println("*****Matrice de Preference Eleve en int*****");
        //On enleve les lignes d'etudiant( e1,e2,e3,....)
        	 
        	
              for (int x=1; x<nbrLigne; x++)
              {
            	  
                  for (int y=1; y<colonne.length; y++)
                  {
                	 
                  	if((MatricePrefEleve[x][y].equals("Tres Bien") || (MatricePrefEleve[x][y].equals("TB"))))
                  	{                             		
                  		MatricePrefEleveInt[x][y]= 6;
                  	}
                  	else if((MatricePrefEleve[x][y].equals("Bien") ||MatricePrefEleve[x][y].equals("B")))
                  	{
                  		MatricePrefEleveInt[x][y]= 5;
                  	}
                  	else if(MatricePrefEleve[x][y].equals("Assez Bien")||MatricePrefEleve[x][y].equals("AB"))
                  	{
                  		MatricePrefEleveInt[x][y]= 4;
                  	}
                  	else if(MatricePrefEleve[x][y].equals("Passable")||MatricePrefEleve[x][y].equals("P"))
                  	{
                  		MatricePrefEleveInt[x][y]= 3;
                  	}
                  	else if(MatricePrefEleve[x][y].equals("Insuffisant")||MatricePrefEleve[x][y].equals("I"))
                  	{
                  		MatricePrefEleveInt[x][y]= 2;
                  	}
                  	else if(MatricePrefEleve[x][y].equals("A Rejeter")||MatricePrefEleve[x][y].equals("AR"))
                  	{
                  		MatricePrefEleveInt[x][y]= 1;
                  	}
                  	else
                  	{
                  		MatricePrefEleveInt[x][y]= 0;
                  	}

                      System.out.print(MatricePrefEleveInt[x][y] +" ");
                  }
                  System.out.println("");
              }
              this.preferences = MatricePrefEleveInt;
	}
    
    
    private void preferencesARangs(){
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
                }
            }
        }
    }
    
    
}
