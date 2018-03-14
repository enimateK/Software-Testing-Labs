package fr.univnantes.cta.impl;

import fr.univnantes.cta.Airport;

public class AirportImpl implements Airport {

    private String name;

    public AirportImpl(String str) {
        name = str;
    }

    public String getName() {
        return name;
    }
}
