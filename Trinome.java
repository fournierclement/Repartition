class Trinome extends Regroupement {
    Eleve eleve1;
    Eleve eleve2;
    Eleve eleve3;
    int indice;

    Trinome(Regroupement g1, Regroupement g2, int indice){
        if (g1 instanceof Binome) {
            eleve1 = g1.getMembers()[0];
            eleve2 = g1.getMembers()[1];
            eleve3 = g2.getMembers()[0];
        } else {
            eleve1 = g1.getMembers()[0];
            eleve2 = g2.getMembers()[0];
            eleve3 = g2.getMembers()[1];
        }
        this.indice = indice;
    }

    Trinome(Regroupement g1, Regroupement g2){
        if (g1 instanceof Binome) {
            eleve1 = g1.getMembers()[0];
            eleve2 = g1.getMembers()[1];
            eleve3 = g2.getMembers()[0];
        } else {
            eleve1 = g1.getMembers()[0];
            eleve2 = g2.getMembers()[0];
            eleve3 = g2.getMembers()[1];
        }
    }

    Trinome(Trinome t, int indice){
        eleve1 = t.getMembers()[0];
        eleve2 = t.getMembers()[1];
        eleve3 = t.getMembers()[2];
        this.indice = indice;
    }

    public Eleve[] getMembers(){
    	Eleve[] eleves = {
    			eleve1,
    			eleve2,
    			eleve3
    	};
        return eleves;
    }
    
    public int getIndice(){ return this.indice; }

    public String toString() {
        return eleve1.toString() + " & " + eleve2.toString() + " & " + eleve3.toString();
    }
}
