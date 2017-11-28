import java.util.ArrayList;

public class MatriceRangs extends Matrice {

	private int[][] rangs;
	private ArrayList<Regroupement> eleves;

	public MatriceRangs(ArrayList<Regroupement> eleves, int[][] rangs) {
		this.rangs = rangs;
		this.eleves = eleves;
	}

	public String matriceToString() {
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
		// TODO Auto-generated method stub
		return null;
	}

}
