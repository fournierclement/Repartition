import java.util.ArrayList;

public class MatriceDistances {
	double [][] distances;
	ArrayList<Regroupement> eleves;

	// Ajoute le groupe à la matrice et en calcule les distances
	public void ajoutMembre(Regroupement groupe){
        int indice = eleves.size();
        double[][] nouvellesDistances = new double[indice+1][indice+1];
        int i = 0; int j = 0;
        double distance;
        while( i <= j && j < indice ){
            distance = distances[i][j];
            nouvellesDistances[i][j] = distance;
            nouvellesDistances[j][i] = distance;
            if( i == j ){ i = 0; j++; }
            else { i++; }
        }
        eleves.add(Regroupement.regrouper(groupe, indice ));
        for ( i = 0; i < indice; i++){
            distance = calculeDistance(groupe, eleves.get(i));
            distances[i][indice] = distance;
            distances[indice][i] = distance;
        }
        this.distances = nouvellesDistances;
	}

	// Met les lignes et colones des membres du groupes à 0
	public void retireMembre(Regroupement groupe){
        int indice = groupe.getIndice();
        for (int i = 0; i < indice; i++){
            distances[i][indice] = calculeDistance(groupe, eleves.get(i));
            distances[indice][i] = calculeDistance(groupe, eleves.get(i));
        }
	}

	// Trouve les distances minimales mais > 0 de la matrice (0 étant les cases "vides" ou "vidées").
	public Regroupement[] distancesMin(){
        int i = 0; int j = 0;
        double min = 0; int count = 0;
        while( i < j && j < distances.length ){
            if( distances[i][j] > 0 && distances[i][j] < min || min == 0 ) {
                min = distances[i][j];
                count = 0;
            }
            if( i+1 >= j ){ i = 0; j++; count++; }
            else { i++; }
        }
        Regroupement[] futursGroupes = new Regroupement[count];
        count = 0;
        while( i < j && j < distances.length ){
            if( distances[i][j] == min ){
                futursGroupes[count] = Regroupement.regrouper(eleves.get(i), eleves.get(j));
                count++;
            }
            if( i+1 >= j ){ i = 0; j++; }
            else { i++; }
        }
        return futursGroupes;
	}

	// 0 si groupe impossible, ou la moyenne des rangs inter groupe.
	public double calculeDistance(Regroupement groupe1, Regroupement groupe2){
        if( groupe1.regroupementImpossible(groupe2)) {
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
        } else { return 0; };

	}

	// Renvoie le premier binome de la matrice ou null.
	public Binome premierBinome(){
        int i = 0;
        Regroupement groupe;
        do {
            groupe = eleves.get(i);
            i++;
        } while (!(groupe instanceof Binome))
        eleves.remove(groupe);
        return groupe;
	}

    public String matriceToString() {
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
