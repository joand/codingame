package fr.joand.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EdgeTest {
    @Test
    public void equals() {
        Edge a = new Edge(1, 2, 5);
        Edge b = new Edge(2, 1, 5);
        Edge c = new Edge(2, 2, 5);

        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }
}
