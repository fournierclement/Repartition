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
			System.out.println("Acquisition du csv");
			Csv csv = new Csv(args[0], nombreGroupes);
			// Définir nombre de trinome/binome;
			nombreTrinomes = csv.trinome;
			System.out.println("il faut " + nombreTrinomes + " trinomes sur " + nombreGroupes + " groupes");
			// Créer une matrice de préfs
			MatricePreferences prefs = new MatricePreferences(csv.getEleves(), csv.getPrefs());
			System.out.println("matrice des preferences acquise");
			// Convertir en matrice de rangs
			MatriceRangs rangs = prefs.preferencesARangs();
			System.out.println("matrice des rangs acquise");
			// Convertir en matrice de distance;
			MatriceDistances distances = rangs.rangsADistances();
			System.out.println("matrice des distances acquise :");
			System.out.println(distances.toString());
			Regroupement[] futursGroupes;
			Trinome nouveauTrinome = null;
			while( trinomesFaits < nombreTrinomes){
				System.out.println("Recherche des distances minimales :");
				futursGroupes = distances.distancesMin();
//				for(Regroupement groupe : futursGroupes){ System.out.println(groupe.toString()); }
				while( nouveauTrinome == null ){
					System.out.println("Vérification d'un trinome de distance minimal.");
					for( Regroupement groupe : futursGroupes){
						if( nouveauTrinome == null && groupe instanceof Trinome ){
							System.out.println("Trinome créé :" + groupe);
							nouveauTrinome = (Trinome) groupe;
						}
					}
					if( nouveauTrinome == null){
						System.out.println("pas de nouveau trinome, ajout des binomes potentiels :");
						for( Regroupement groupe : futursGroupes){
							System.out.println("nouveau binome potentiel :" + groupe );
							distances.ajoutMembre(groupe);
						}
					}
				}
				System.out.println("nouveau trinome : " + nouveauTrinome.toString());
				elevesTries.add(nouveauTrinome);
				distances.retireMembre(nouveauTrinome);
				nouveauTrinome = null;
				groupesFaits++;
				trinomesFaits++;
			}
			System.out.println(trinomesFaits);
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
			System.out.println(elevesTries.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
