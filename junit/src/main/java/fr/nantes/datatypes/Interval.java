package fr.nantes.datatypes;

/**
 *
 */
public class Interval extends Range {

    private int from;
    private int to;

    /**
     * Creates an interval between two values.
     *
     * @param from the minimal value
     * @param to the maximal value
     */
    protected Interval(int from, int to) {
        assert to > from;

        this.from = from;
        this.to = to;
    }

    /**
     * Checks whether the parameter is conainted by this internal
     *
     * @param i an int value.
     * @return true, if i is between from and to.
     */
    @Override
    public boolean includes(int i) {
        return i >= from && i <= to;
    }
}