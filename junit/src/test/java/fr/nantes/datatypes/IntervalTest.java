package fr.nantes.datatypes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class IntervalTest {
    Interval tester;

    @BeforeEach
    void setUp() {
         tester = new Interval(1, 5);
    }
    @Test
    void includesMustReturnTrueWhenIn() {
        assertTrue(tester.includes(4));
    }
    @Test
    void includesMustReturnFalseWhenOut() {
        assertFalse(tester.includes(30));
    }
    @Test
    void fromString() {
        assertEquals(Interval.fromString("1-5").getClass(), Interval.class);
    }
    @Test
    void interval() {
        assertEquals(tester.getClass(), Interval.class);
        assertEquals(tester.getFrom(), 1);
        assertEquals(tester.getTo(), 5);
    }
    @Test
    void intervalError() {
        assertThrows(Error.class, ()->{
            Interval i = new Interval(10, 8);
        });
    }
}