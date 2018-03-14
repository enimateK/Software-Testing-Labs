/*
 * Created on 18 oct. 06
 *
 */
package fr.univnantes.cta;

/**
 * @author sunye
 *
 */
public interface Airplane {
    
    /**
     * @return The weight of the airplane (empty). 
     */
    public double weight();

    /**
     * @return The estimated autonomy, in km.
     */
    public int getAutonomy();
    
    /**
     * @return the loading capacity of the airplane, in kg.
     */
    public int getLoading();
}
