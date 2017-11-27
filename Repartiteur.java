import java.io.FileNotFoundException;

public class Repartiteur {
	public static void main( String[] args){
		try {
			new Matrice(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
