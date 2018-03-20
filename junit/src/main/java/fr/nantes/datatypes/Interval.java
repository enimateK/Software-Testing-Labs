package fr.nantes.datatypes;

/**
 *
 */
public class Interval extends Range {

    private int from;
    private int to;

    protected Interval(int from, int to) {
        assert to > from;

        this.from = from;
        this.to = to;
    }

    @Override
    public boolean includes(int i) {
        return i >= from && i <= to;
    }
}