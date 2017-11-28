
public class Binome extends Regroupement {
    Eleve eleve1;
    Eleve eleve2;
    int indice;

    public Binome(Regroupement e1, Regroupement e2, int indice){
        eleve2 = e2.getMembers()[0];
        eleve1 = e1.getMembers()[0];
        this.indice = indice;
    }

    public Binome(Regroupement e1, Regroupement e2){
        eleve2 = e2.getMembers()[0];
        eleve1 = e1.getMembers()[0];
    }

    public Binome(Binome b, int indice){
        eleve2 = b.getMembers()[0];
        eleve1 = b.getMembers()[1];
        this.indice = indice;
    }

    public Eleve[] getMembers(){
        Eleve[] eleves = {eleve1, eleve2};
        return eleves;
    }

    public int getIndice(){ return this.indice; }
    
    public String toString() {
        return eleve1.toString() + " & " + eleve2.toString();
    }
}
