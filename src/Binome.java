
public class binome extends Regroupement {
    Eleve eleve1;
    Eleve eleve2;
    int indice;

    void Eleve(Eleve e1, Eleve e2, int indice){
        eleve2 = e2;
        eleve1 = e1;
        this.indice = indice;
    }

    Eleve[] getMembers(){
        return [eleve1, eleve2];
    }

    String toString() {
        return eleve1.toString() + ", " + eleve2.toString();
    }
}
