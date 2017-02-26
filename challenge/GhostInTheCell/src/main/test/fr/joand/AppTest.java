package fr.joand;

import fr.joand.model.Edge;
import fr.joand.model.Factory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest {
    private static List<Factory> factories;
    private static List<Edge> edges;

    @BeforeClass
    public static void init() {
        factories = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Test
    public void calculateOportunityScore() {
        App.calculateOpportunityScore(factories, edges);
    }


    @Test
    public void calculateDangerScore() {
        App.calculateDangerScore(factories, edges);
    }

    @Test
    public void takeADecision(){
        String action = App.takeADecision(factories, edges);
    }

}
