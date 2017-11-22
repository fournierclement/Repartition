import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Repartiteur {
	public void main( String[] arg){
		Eleves[] eleves;
		// Ce try/catch pourrait être encapsulé.
		try {
			Reader reader = Files.newBufferedReader(Paths.get(arg[0]));
			CSVReader csvReader = new CSVReader(reader);
			// Reading Records One by One in a String array
			String[] currentLine = csvReader.readNext();
			eleves = new Array(currentLine.length);
			for(int i = 1; i < currentLline.length; i++ ){
				eleves[i-1] = new Eleve(currentLine[i]);
			}
			while ((currentLine = csvReader.readNext()) != null) {
				//copier coller le java de San Wei pour transformer les quantitatif en qualitatif
			}
		} catch (IOException e) {
			System.err.println("Erreur: ce fichier n'existe pas ou ne respecte pas le former requis.");
			System.exit(-1);
		}
	}
}
