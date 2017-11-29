import java.util.ArrayList;

public class MatriceRangs extends Matrice {

	private int[][] rangs;
	private ArrayList<Regroupement> eleves;

	public MatriceRangs(ArrayList<Regroupement> eleves, int[][] rangs) {
		this.rangs = rangs;
		this.eleves = eleves;
	}

	public String toString() {
    	int rows = this.rangs.length;
        int columns = this.rangs[0].length;
        String str = "|\t";
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){ str += this.rangs[i][j] + "\t"; }
            str += "|\n|\t";
        }
        return str;
    }

	public MatriceDistances rangsADistances() {
		double [][] distances = new double[rangs.length][rangs.length];
		int indice = rangs.length;
		int i = 0; int j = 0;
		double distance = 0;
        while( i <= j && j < indice ){
            distance = (rangs[i][j] + rangs[j][i]) / 2;
            distances[i][j] = distance;
            distances[j][i] = distance;
            if( i == j ){ i = 0; j++; }
            else { i++; }
        }
		return new MatriceDistances(eleves, distances);
	}

}
