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
			System.out.println("matrice des preferences acquise :");
			System.out.println(prefs);
			// Convertir en matrice de rangs
			MatriceRangs rangs = prefs.preferencesARangs();
			System.out.println("matrice des rangs calculée :");
			System.out.println(rangs);
			// Convertir en matrice de distance;
			MatriceDistances distances = rangs.rangsADistances();
			System.out.println("matrice des distances calculée :");
			System.out.println("(La distance est la moyenne des rangs que les eleves s'attribuent)");
			System.out.println(distances.toString());
			Regroupement[] futursGroupes;
			Trinome nouveauTrinome = null;
			while( trinomesFaits < nombreTrinomes){
				System.out.println("Recherche des distances minimales :");				
				while( nouveauTrinome == null ){
					futursGroupes = distances.distancesMin();
					System.out.println("Vérification d'un trinome de distance minimal.");
					for( Regroupement groupe : futursGroupes){
						if( nouveauTrinome == null && groupe instanceof Trinome ){
							System.out.println("Trinome créé :" + groupe);
							nouveauTrinome = (Trinome) groupe;
						}
					}
					if( nouveauTrinome == null){
						System.out.println("pas de nouveau trinome, ajout des binomes potentiels :");
						for(Regroupement groupe : futursGroupes){ System.out.println(groupe.toString()); }
						distances.ajoutMembres(futursGroupes);
					}
//					System.out.println("nouvelles distances: ");
//					System.out.println(distances.toString());
				}
				System.out.println("nouveau trinome : " + nouveauTrinome.toString());
				elevesTries.add(nouveauTrinome);
				System.out.println("Evacutation des groupes et eleves du trinome de la matrice.");
				distances.retireMembre(nouveauTrinome);
				nouveauTrinome = null;
				trinomesFaits++;
				groupesFaits++;
				System.out.println();
			}
			System.out.println("Tous les " + nombreTrinomes + " trinomes sont construits.");
			System.out.println("Ajout & évacuation des binomes potentiels déjà créés : ");
			Binome nouveauBinome = distances.premierBinome();
			while (nouveauBinome != null ){
				System.out.println(nouveauBinome);
				elevesTries.add(nouveauBinome);
				distances.retireMembre(nouveauBinome);
				groupesFaits++;
				nouveauBinome = distances.premierBinome();
			}
			System.out.println("Recherche des binômes restants :");
			while( groupesFaits < nombreGroupes){
				futursGroupes = distances.distancesMin();
				System.out.println(futursGroupes[0]);
				elevesTries.add(futursGroupes[0]);
				distances.retireMembre(futursGroupes[0]);
				groupesFaits++;
			}
			System.out.println("Répartition finie :");
			for(Regroupement groupe : elevesTries) {
				System.out.println(groupe);
			}

		// print
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
