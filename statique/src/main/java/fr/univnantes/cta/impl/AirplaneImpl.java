package fr.univnantes.cta.impl;

import fr.univnantes.cta.Airplane;

public class AirplaneImpl implements Airplane {

    private double weight;
    private int loading, consumption, tank;

    public AirplaneImpl(double weight, int loading, int c, int t) {
        this.weight = weight;
        this.loading = loading;
        //this.consumption = t;
        //this.tank = c;
        // Correction grace au test de constructeur
        this.consumption = c;
        this.tank = t;

    }

    public int getAutonomy() {
        //return (tank * consumption);
    	// Correction grace au test autonomy
    	return (tank / consumption);
    }

    public int getLoading() {
        return loading;
    }
    
    /**
     * Ajout de getters sur consumption et tank pour permettre 
     * le test du constructeur
     */
    public int getConsumption() {
    	return consumption;
    }
    
    public int getTank() {
    	return tank;
    }

    public double weight() {
        return weight;
    }
}
