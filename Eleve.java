
public class Eleve extends Regroupement {
    String nom;
    int indice;

	public Eleve(String nom, int indice){
        this.nom = nom;
        this.indice = indice;
    }

    public Eleve[] getMembers(){
    	Eleve[] eleves = { this };
        return eleves;
    }
	
    public int getIndice(){ return this.indice; }

    public String toString() {
        return nom;
    }
}
