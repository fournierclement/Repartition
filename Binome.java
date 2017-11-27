
public class Binome extends Regroupement {
    Eleve eleve1;
    Eleve eleve2;
    int indice;

    void Eleve(Eleve e1, Eleve e2, int indice){
        eleve2 = e2;
        eleve1 = e1;
        this.indice = indice;
    }

    public Eleve[] getMembers(){
        Eleve[] eleves = {eleve1, eleve2};
        return eleves;
    }

    public String toString() {
        return eleve1.toString() + ", " + eleve2.toString();
    }
}
