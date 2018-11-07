package fr.univnantes.cta.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.univnantes.cta.FlightPlan;
import fr.univnantes.cta.TakenAirway;

public class FlightPlanImpl implements FlightPlan {

    private List<TakenAirway> path = new LinkedList<TakenAirway>();
    private List<TakenAirway> airwayListNonModifiable;

    public FlightPlanImpl() {
        airwayListNonModifiable = Collections.unmodifiableList(path);
    }
    
    // à noter que cette méthode compare des références sur des objets, or 
    // sans spécification il ne nous est pas possible de savoir si ce 
    // comportement est normal ou non
    public void addAirway(TakenAirway a) {

        if (path.isEmpty()) {
            path.add(a);
        } else {
            int dernier = (path.size() - 1);
            if (((AirwayImpl) (path.get(dernier).getAirway())).GetVORArrive()
                    != ((AirwayImpl) (a.getAirway())).GetVORDepart()) {

                throw new IllegalArgumentException("Wrong Flight Plan");
            }
            path.add(a);
        }
    }

    public double distance() {
        double total = 0;
        Iterator<TakenAirway> it = path.iterator();
        while (it.hasNext())
        	total = total + it.next().distance();
        return total;
    }

    public List<TakenAirway> getPath() {
        return airwayListNonModifiable;
    }
    
    /*
     * Ajout d'un getter sur takenAirways pour permettre le
     * test de l'ajout
     */
    public List<TakenAirway> getTakenAirways() {
    	return this.path;
    }
}
