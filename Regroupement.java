
public abstract class Regroupement {
    public abstract Eleve[] getMembers();
    public abstract String toString();

    public static Regroupement regrouper(Regroupement g1, Regroupement g2){
        if(
            (g1 instanceof Trinome || g2 instanceof Trinome) ||
            (g1 instanceof Binome && g2 instanceof Binome)
        ) {
            System.out.println("Regroupement impossible : " + g1 + " et " + g2);
            System.out.println("Exit très sale");
            System.exit(-1);
        } else if (g1 instanceof Binome){
            return new Trinome((Binome) g1, (Eleve) g2);
        } else if (g2 instanceof Binome){
            return new Trinome((Binome) g2, (Eleve) g1);
        } else {
            return new Binome((Eleve) g1, (Eleve) g2);
        }
    }

    public static Regroupement regrouper(Regroupement g, int indice){
        if( g instanceof Trinome ) { return new Trinome((Trinome) g, indice); }
        else { return new Binome((Binome) g, indice); }
    }

    boolean regroupementImpossible(Regroupement g){
        if( this instanceof Trinome || g instanceof Trinome ) { return true; }
        else if( this instanceof Binome && g instanceof Binome ) { return true; }
        else {
            boolean test = false;
            for( Eleve eleve1 : this.getMembers()){
                for( Eleve eleve2 : g.getMembers()){
                    test = test || (eleve1.equals(eleve2));
                }
            }
            return test;
        }
    }
}
