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
    public void line() {
        Map<String, Node> allNodes = new HashMap<>();

        String keyA = "A";
        String keyB = "B";

        Node nodeA = getNodeOrStoreIt(keyA, allNodes);
        Node nodeB = getNodeOrStoreIt(keyB, allNodes);

        nodeA.addChild(nodeB);
        IsoContest.computeAllHeights(allNodes);
        assertEquals(2, getMaxHeight(allNodes));

        String keyC = "C";
        Node nodeC = getNodeOrStoreIt(keyC, allNodes);
        nodeB.addChild(nodeC);
        IsoContest.computeAllHeights(allNodes);
        assertEquals(3, getMaxHeight(allNodes));
    }

    @Test
    public void fork() {
        Map<String, Node> allNodes = new HashMap<>();
        String keyA = "A";
        Node nodeA = getNodeOrStoreIt(keyA, allNodes);

        String keyD = "D";
        Node nodeD = getNodeOrStoreIt(keyD, allNodes);

        nodeA.addChild(nodeD);
        IsoContest.computeAllHeights(allNodes);
        assertEquals(2, getMaxHeight(allNodes));

        String keyE = "E";
        Node nodeE = getNodeOrStoreIt(keyE, allNodes);

        nodeA.addChild(nodeE);
        IsoContest.computeAllHeights(allNodes);
        assertEquals(2, getMaxHeight(allNodes));
    }


    @Test
    public void mixed() {
        Map<String, Node> allNodes = new HashMap<>();

        String keyA = "A";
        String keyB = "B";

        Node nodeA = getNodeOrStoreIt(keyA, allNodes);
        Node nodeB = getNodeOrStoreIt(keyB, allNodes);

        nodeA.addChild(nodeB);
        IsoContest.computeAllHeights(allNodes);
        assertEquals(2, getMaxHeight(allNodes));

        String keyC = "C";
        Node nodeC = getNodeOrStoreIt(keyC, allNodes);
        nodeB.addChild(nodeC);
        IsoContest.computeAllHeights(allNodes);
        assertEquals(3, getMaxHeight(allNodes));


        String keyD = "D";
        Node nodeD = getNodeOrStoreIt(keyD, allNodes);

        nodeA.addChild(nodeD);
        IsoContest.computeAllHeights(allNodes);
        assertEquals(3, getMaxHeight(allNodes));
    }
}
