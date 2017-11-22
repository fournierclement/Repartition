

public class Repartiteur {
	public void main( String[] arg){
		Csv fichier = new Csv(arg[0]);
		ListeEleve eleves = fichier.ListeEleves();
		
	}
}
