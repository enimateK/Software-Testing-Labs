/*
 * Created on 22 oct. 06
 *
 */
package fr.univnantes.cta;

/**
 * @author sunye
 *
 * Exception thrown during a {@link Flight Flight} instantiation.
 */
public class IncompatibleAirway extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 102869751064547652L;

    public IncompatibleAirway() {
        super("Incompatible Airway");
    }

    public IncompatibleAirway(double autonomie, double distance) {
        super("Airway Distance: " + distance + " Airplane Autonomy: " 
                + autonomie + "\n Too Long Distance");
    }
}
