import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Csv {
    String[][] preferences;
    ArrayList<Regroupement> eleves;
    int trinome;

    public Csv(String filePath, int nombreGroupes) throws FileNotFoundException {
    	Scanner sc = new Scanner(new File(filePath));
    	
    	String spliter = ",";
    	String line = sc.nextLine();
    	String[] noms = line.split(spliter);
    	
    	// Initialise le tableau des préférences en taille.
    	this.preferences = new String[noms.length-1][noms.length-1];
    	this.eleves = new ArrayList<Regroupement>(noms.length-1);
    	// Créé les nouveaux Eleves.
    	for(int i = 1; i < noms.length; i++ ){
    		eleves.add(new Eleve(noms[i], i));
    	}
    	
    	String[] prefs;
    	int currentLine = 0;
    	while (sc.hasNextLine()) {
    		line = sc.nextLine();
    		prefs = line.split(spliter);
    		for(int i= 1; i < prefs.length; i++)
    		{
    			this.preferences[currentLine][i-1] = prefs[i];
    		}
    		currentLine++;
    	}
    	if (eleves.size() % nombreGroupes == 0) {
    		if (eleves.size() > 2*nombreGroupes ) { trinome = nombreGroupes; }
    		else { trinome = 0; }
    	} else if (eleves.size() % nombreGroupes != 0) { trinome = eleves.size() % nombreGroupes; }
	}
    	
    ArrayList<Regroupement> getEleves(){ return this.eleves; }
    String[][] getPrefs(){ return this.preferences; }
}
