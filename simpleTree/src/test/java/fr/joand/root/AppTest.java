package fr.joand.root;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static fr.joand.root.IsoContest.getMaxHeight;
import static fr.joand.root.IsoContest.getNodeOrStoreIt;
import static junit.framework.Assert.assertEquals;

/**
 * Unit test for simple IsoContest.
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
        assertEquals(2, getMaxHeight(allNodes));

        String keyC = "C";
        Node nodeC = getNodeOrStoreIt(keyC, allNodes);
        Node nodeB_2 = getNodeOrStoreIt(keyB, allNodes);
        nodeB_2.addChild(nodeC);
        assertEquals(3, getMaxHeight(allNodes));

        String keyD = "D";
        Node nodeD = getNodeOrStoreIt(keyD, allNodes);
        Node nodeA_2 = getNodeOrStoreIt(keyA, allNodes);
        nodeA_2.addChild(nodeD);
        assertEquals(3, getMaxHeight(allNodes));

        String keyE = "E";
        Node nodeE = getNodeOrStoreIt(keyE, allNodes);
        Node nodeA_3 = getNodeOrStoreIt(keyA, allNodes);
        nodeE.addChild(nodeA_3);
        assertEquals(4, getMaxHeight(allNodes));

    }
}
