package fr.nantes.datatypes;

/**
 *
 */
public class SingleValue extends Range {

    private final int value;

    protected SingleValue(int i) {
        assert i >= 0;

        value = i;
    }

    @Override
    public boolean includes(int i) {
        return i == value;
    }
}