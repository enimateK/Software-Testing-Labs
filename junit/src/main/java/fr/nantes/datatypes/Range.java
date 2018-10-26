package fr.nantes.datatypes;


public abstract class Range {

    public static Range ALL = new AllValues();
    private static String SEPARATOR = "-";

    public abstract boolean includes(int i);

    /**
     * Creates an instance of Range, from a given string.
     * If the string contains a SEPARATOR, then it is considered an interval.
     * Otherwise, it is a SingleValue.
     *
     * @param str
     * @return
     */
    public static Range fromString(String str) {
        if (str.equals("*")) {
            return ALL;
        }
        String[] values = str.split(SEPARATOR);
        if (values.length == 1) {
            return new SingleValue(Integer.parseInt(values[0]));
        }
        if (values.length == 2) {
            int v1 = Integer.parseInt(values[0]);
            int v2 = Integer.parseInt(values[1]);
            return v1 > v2 ? new Interval(v2, v1) : new Interval(v1, v2);
        }

        return null;
    }
}

