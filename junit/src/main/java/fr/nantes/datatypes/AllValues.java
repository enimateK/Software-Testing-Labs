package fr.nantes.datatypes;

/**
 * Created on 10/01/2018.
 *
 * @author AtlanMod team.
 */
public class AllValues extends Range {

    @Override
    public boolean includes(int i) {
        return i >= 0;
    }
}