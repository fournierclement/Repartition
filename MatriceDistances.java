import java.util.ArrayList;

public class MatriceDistances {
	double [][] distances;
	ArrayList<Regroupement> eleves;

	public MatriceDistances(ArrayList<Regroupement> eleves, double[][] distances) {
		this.eleves = eleves;
		this.distances = distances;
	}

	// Ajoute le groupe à la matrice et en calcule les distances
	public void ajoutMembres(Regroupement[] groupes){
        int tailleInit = eleves.size();
        int i = tailleInit; int j = 0;
        double distance;
        
        // Ajouter les nouveaux eleves.
        for(Regroupement groupe: groupes){
        	eleves.add(Regroupement.regrouper(groupe, i));
        	i++;
        }
        i = 0;
        int tailleFin = eleves.size();
        double[][] nouvellesDistances = new double[tailleFin][tailleFin];
        
        // Remplir la nouvelle matrice avec l'ancienne.
        while( i <= j && j < tailleInit ){
            distance = distances[i][j];
            nouvellesDistances[i][j] = distance;
            nouvellesDistances[j][i] = distance;
            if( i == j ){ i = 0; j++; }
            else { i++; }
        }
        
        this.distances = nouvellesDistances;
        Regroupement groupe;
        // Pour chaque nouveau groupe.
        for ( i = tailleInit; i < tailleFin; i++ ) {
        	groupe = eleves.get(i);
        	// Pour chaque autre groupe
	        for ( j = 0; j < tailleFin; j++){
	            distance = calculeDistance(eleves.get(j), groupe);
	            distances[i][j] = distance;
	            distances[j][i] = distance;
	        }
	        // Vider les distances des groupes déjà contruits
	        for(Eleve eleve1 : groupe.getMembers()) {
	        	for(Eleve eleve2 : groupe.getMembers()) {
	        		distances[eleve1.getIndice()][eleve2.getIndice()] = 0;
	        		distances[eleve2.getIndice()][eleve1.getIndice()] = 0;
	            }
	        }
	        
        }
		System.out.println("Nouvelle matrice de distances calculée :");
		System.out.println("(La distance est la moyenne des rangs que les eleves s'attribuent)");
		System.out.println(this.toString());
	}

	// Met les lignes et colonnes des membres du groupes à 0
	public void retireMembre(Regroupement groupe){
        int indice;
        Eleve[] elevesSortants = groupe.getMembers();
        // Pour chacun de ses eleves.
        for ( Eleve eleve : elevesSortants){
        	indice = eleve.getIndice();
        	// Vider ses lignes/colonnes;
        	for (int i = 0; i < eleves.size(); i++){
        		distances[i][indice] = 0;
        		distances[indice][i] = 0;
        	}
        	// Vider les autres groupes contenants cet eleve.
        	for (Regroupement autreGroupe: eleves){
        		if( Regroupement.eleveCommun(autreGroupe, eleve)){
        			indice = autreGroupe.getIndice();
                	for (int i = 0; i < indice; i++){
                		distances[i][indice] = 0;
                		distances[indice][i] = 0;
                	}
        		}
        	}
        }
	}

	// Trouve les distances minimales mais > 0 de la matrice (0 étant les cases "vides" ou "vidées").
	public Regroupement[] distancesMin(){
        int i = 0; int j = 0;
        double distance;
        double min = 0; int count = 0;
        while( i <= j && j < distances.length ){
        	distance = distances[i][j];
            if(( distance > 0 && distance < min ) || !( min > 0 )) {
            	// Reassigner la distance min, reset le compteur;
                min = distance;
                count = 1;
            } else if( distance == min ) { count++; }
            if( i == j ){ i = 0; j++; }
            else { i++; }
        }
        System.out.println("distance min :" + min + "\t, occurrences : " + count);
        Regroupement[] futursGroupes = new Regroupement[count];
        count = 0; i = 0; j = 0;
        while( i <= j && j < distances.length ){
            if( distances[i][j] == min ){
                futursGroupes[count] = Regroupement.regrouper(eleves.get(i), eleves.get(j));
                count++;
            }
            if( i == j ){ i = 0; j++; }
            else { i++; }
        }
        return futursGroupes;
	}

	// 0 si groupe impossible, ou la moyenne des rangs inter groupe.
	public double calculeDistance(Regroupement groupe1, Regroupement groupe2){
        if( !groupe1.regroupementImpossible(groupe2)) {
            int sum = 0;
            int k = 0;
            for(Eleve eleve1 : groupe1.getMembers()) {
                for(Eleve eleve2 : groupe2.getMembers()) {
                    sum += distances[eleve1.indice][eleve2.indice];
                    sum += distances[eleve2.indice][eleve1.indice];
                    k += 2;
                }
            }
            return sum/k;
        } else { return 0; }

	}

	// Renvoie le premier binome de la matrice ou null.
	public Binome premierBinome(){
        int i = 0;
        int fin = eleves.size();
        int indice;
        double sum = 0;
        Regroupement groupe = null;
        Regroupement temp = null;
        while (groupe == null && (i < fin )){        	
        	temp = eleves.get(i);
        	sum = 0;
        	for(double distance : distances[i]){ sum += distance; }
        	i++;
        	if (sum != 0 && (temp instanceof Binome)){
        		groupe = temp;
        	}
        }
        return (Binome) groupe;
	}

    public String toString() {
        int rows = this.distances.length;
        int columns = this.distances[0].length;
        String str = "|\t";
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){ str += this.distances[i][j] + "\t"; }
            str += "|\n|\t";
        }
        return str;
    }

}
