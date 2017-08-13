package fr.joand.root;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static fr.joand.root.Root.getMaxHeight;
import static fr.joand.root.Root.getNodeOrStoreIt;
import static junit.framework.Assert.assertEquals;

/**
 * Unit test for simple Root.
 */
public class AppTest {
    @Test
    public void testApp() {
        Map<String, Node> allNodes = new HashMap<>();

        String keyA = "A";
        String keyB = "B";

        Node nodeA = getNodeOrStoreIt(keyA, allNodes);
        Node nodeB = getNodeOrStoreIt(keyB, allNodes);

        nodeA.addChild(nodeB);

        assertEquals(2, getMaxHeight(new ArrayList(allNodes.values())));

        String keyC = "C";
        Node nodeC = getNodeOrStoreIt(keyC, allNodes);
        nodeB.addChild(nodeC);

        assertEquals(3, getMaxHeight(new ArrayList(allNodes.values())));
    }
}
