package fr.univnantes.cta.impl;

import fr.univnantes.cta.*;
import fr.univnantes.cta.impl.*;

public class CoordinateImpl extends AngleImpl implements Coordinate {

    private int angle;
    private int minutes;
    private int seconds;
    private CompassDirection dir;

    public CoordinateImpl(int a, int m, int s, CompassDirection cd) {
        super(a, m, s);
        angle = a;
        minutes = m;
        seconds = s;
        dir = cd;
    }

    public CompassDirection direction() {
        return dir;
    }

    public double Cos() {
        if (dir == CompassDirection.SOUTH || dir == CompassDirection.WEST) {
            return Math.cos(this.Radians());
        } else {
            return cos();
        }
    }

    public double Degrees() {
        int sign = 1;
        if (dir == CompassDirection.SOUTH || dir == CompassDirection.WEST) {
            sign = -1;
        }
        return sign * (angle + (minutes + seconds / 60.) / 60.);
    }

    public double Radians() {
        return Math.toRadians(this.Degrees());
    }

    public double Sin() {
        if (dir == CompassDirection.SOUTH || dir == CompassDirection.WEST) {
            return Math.sin(this.Radians());
        } else {
            return sin();
        }

    }
}
