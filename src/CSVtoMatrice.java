import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class CSVtoMatrice {
	public static void main(String arg[]) throws Exception
    {
        String[] colonne = new String[50];
        int nbrLigne = 0;
        String delimiter = ",";
        String[][] MatricePrefEleve = new String[50][50];

        Scanner sc = new Scanner(new File(arg[0]));

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

  System.out.println("");
  System.out.println("*****Matrice de Preference Eleve en int*****");
  //On enleve les lignes d'etudiant( e1,e2,e3,....)
        for (int x=1; x<nbrLigne; x++)
        {
            for (int y=1; y<colonne.length; y++)
            {
            	if(MatricePrefEleve[x][y].equals("Tres Bien") )
            	{
            		MatricePrefEleveInt[x][y]= 6;
            	}
            	else if(MatricePrefEleve[x][y].equals("Bien"))
            	{
            		MatricePrefEleveInt[x][y]= 5;
            	}
            	else if(MatricePrefEleve[x][y].equals("Assez Bien"))
            	{
            		MatricePrefEleveInt[x][y]= 4;
            	}
            	else if(MatricePrefEleve[x][y].equals("Passable"))
            	{
            		MatricePrefEleveInt[x][y]= 3;
            	}
            	else if(MatricePrefEleve[x][y].equals("Insuffisant"))
            	{
            		MatricePrefEleveInt[x][y]= 2;
            	}
            	else if(MatricePrefEleve[x][y].equals("A Rejeter"))
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

   //Ici pour sortir un csv

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(arg[1]), "utf-8"))) {
        	   for (int x=1; x<nbrLigne; x++)
               {
                   for (int y=1; y<colonne.length; y++)
                   {
                	   writer.write(MatricePrefEleveInt[x][y] +",");
                   }
                   writer.write(System.getProperty("line.separator"));
               }

  }

    } //main()

}
