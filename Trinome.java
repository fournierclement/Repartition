class Trinome extends Regroupement {
    Eleve eleve1;
    Eleve eleve2;
    Eleve eleve3;
    int indice;

    void Eleve(Eleve e1, Eleve e2, Eleve e3, int indice){
        eleve3 = e3;
        eleve2 = e2;
        eleve1 = e1;
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

    public String toString() {
        return eleve1.toString() + ", " + eleve2.toString() + ", " + eleve3.toString();
    }
}
