public class trinome extends Regroupement {
    Eleve eleve1;
    Eleve eleve2;
    Eleve eleve3;
    int indice;

    void Eleve(Eleve e1, Eleve e2, Eleve3 e3, int indice){
        eleve3 = e3;
        eleve2 = e2;
        eleve1 = e1;
        this.indice = indice;
    }

    Eleve[] getMembers(){
        return [eleve1, eleve2, eleve3];
    }

    String toString() {
        return eleve1.toString() + ", " + eleve2.toString() + ", " + eleve3.toString();
    }
}
