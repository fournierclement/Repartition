
public class Eleve extends Regroupement {
    String nom;
    int indice;

    void Eleve(String nom, int indice){
        this.nom = nom;
        this.indice = indice;
    }

    Eleve[] getMembers(){
        return [this];
    }

    String toString() {
        return nom;
    }
}
