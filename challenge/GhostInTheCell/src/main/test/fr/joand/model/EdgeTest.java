package fr.joand.model;

import fr.joand.App;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void getEdge(){
        Edge a = new Edge(1, 2, 5);
        Edge a_clone = new Edge(2, 1);

        Edge c = new Edge(2, 3, 6);

        List<Edge> edges = new ArrayList<>();
        edges.add(a);
        edges.add(c);

        Edge edge = App.getEdge(edges, a_clone);
        assertTrue(edge.equals(a));
        assertFalse(edge.equals(c));
    }
}
