import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Repartiteur {
	final static int nombreGroupes = 18;
	static int nombreTrinomes;
	static int groupesFaits = 0;
	static int trinomesFaits = 0;
	static ArrayList<Regroupement> elevesTries = new ArrayList<Regroupement>(nombreGroupes);
	public static void main( String[] args){
		try {
			// récupérer le csv
			Csv csv = new Csv(args[0], nombreGroupes);
			// Définir nombre de trinome/binome;
			nombreTrinomes = csv.trinome;
			// Créer une matrice de préfs
			MatricePreferences prefs = new MatricePreferences(csv.getEleves(), csv.getPrefs());
			// Convertir en matrice de rangs
			MatriceRangs rangs = prefs.preferencesARangs();
			// Convertir en matrice de distance;
			MatriceDistances distances = rangs.rangsADistances();
			
			Regroupement[] futursGroupes;
			Trinome nouveauTrinome = null;
			while( trinomesFaits < trinomesFaits){
				futursGroupes = distances.distancesMin();
				while( nouveauTrinome == null ){
					for( Regroupement groupe : futursGroupes){
						if( nouveauTrinome == null && groupe instanceof Trinome ){
							nouveauTrinome = (Trinome) groupe;
						}
					}
					if( nouveauTrinome == null){
						for( Regroupement groupe : futursGroupes){
							distances.ajoutMembre(nouveauTrinome);
						}
					}
				}
				elevesTries.add(nouveauTrinome);
				distances.retireMembre(nouveauTrinome);
				nouveauTrinome = null;
				groupesFaits++;
				trinomesFaits++;
			}
			Binome nouveauBinome = distances.premierBinome();
			while (nouveauBinome != null ) {
				elevesTries.add(nouveauBinome);
				distances.retireMembre(nouveauBinome);
				groupesFaits++;
				nouveauBinome = null;
			}
			while( groupesFaits < nombreGroupes){
				futursGroupes = distances.distancesMin();
				elevesTries.add(futursGroupes[0]);
				distances.retireMembre(futursGroupes[0]);
				groupesFaits++;
			}
			
		// print
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
