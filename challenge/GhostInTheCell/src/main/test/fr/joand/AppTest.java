package fr.joand;

import fr.joand.model.Edge;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest {

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
