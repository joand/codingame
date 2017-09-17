package fr.joand.root;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.Assert.assertEquals;


/**
 * Robert C. Martin
 * http://blog.cleancoder.com/uncle-bob/2016/10/26/DijkstrasAlg.html
 */
public class PathFinderTest {

    private void assertMinPath(String actualGraph,
                               int expectedLength, String expectedPath) {
        String debut = "A";
        String fin = "Z";
        PathFinder pf = makePathFinder(actualGraph, debut, fin);

        assertEquals(expectedLength, pf.getLength());
        if (expectedPath != null) {
            assertEquals(expectedPath, pf.getPath().toString());
        }
    }

    // todo : to customize
    private PathFinder makePathFinder(String graph, String debut, String fin) {
        PathFinder pf = new PathFinder();
        Pattern edgePattern =
                Pattern.compile("(\\D+)(\\d+)(\\D+)");
        String[] edges = graph.split(",");
        for (String edge : edges) {
            Matcher matcher = edgePattern.matcher(edge);
            if (matcher.matches()) {
                String start = matcher.group(1);
                int length = Integer.parseInt(matcher.group(2));
                String end = matcher.group(3);
                pf.addEdge(start, end, length);
            }
        }
        pf.findPath(debut, fin);
        return pf;
    }

    @Test
    public void degenerateCases() throws Exception {
        assertMinPath("", 0, "[]");   //empty graph
        assertMinPath("A", 0, "[]");  //one node
        assertMinPath("B1C", 0, "[]");//no start or end
        assertMinPath("A1C", 0, "[]");//no end
        assertMinPath("B1Z", 0, "[]");//no start
    }

    @Test
    public void oneEdge() throws Exception {
        assertMinPath("A1Z", 1, "[A, Z]");
        assertMinPath("A2Z", 2, "[A, Z]");
    }

    @Test
    public void twoEdges() throws Exception {
        assertMinPath("A1B,B1Z", 2, "[A, B, Z]");
        assertMinPath("B1Z,A1B", 2, "[A, B, Z]");
        assertMinPath("A1X,Y1Z", 0, "[]");
    }

    @Test
    public void threeEdges() throws Exception {
        assertMinPath("A2B,B3C,C4Z", 9, "[A, B, C, Z]");
        assertMinPath("B3C,C4Z,A2B", 9, "[A, B, C, Z]");
    }

    @Test
    public void OnlyOnePath() throws Exception {
        assertMinPath("A1B,B2C,C3Z,B4D,D6E", 6, "[A, B, C, Z]");
        assertMinPath("A1B,B2C,C3D,C3Z", 6, "[A, B, C, Z]");
    }

    @Test
    public void parallelPaths() throws Exception {
        assertMinPath("A1B,B2Z,A1Z", 1, "[A, Z]");
        assertMinPath("A1B,A1C,A2D,C5E,B8E,C1F,D3F,F2G,G3Z,E2G",
                7, "[A, C, F, G, Z]");
    }

    /**
     * http://www.geeksforgeeks.org/greedy-algorithms-set-6-dijkstras-shortest-path-algorithm/
     */
    @Test
    public void geeksforgeeks() {
        String actualGraph = "A4B,A8H,B11H,B8C,H7I,H1G,C2I,I6G,C7D,C4F,G2F,D14F,F10Z,D9Z";
        int expectedLength = 21;
        String expectedPath = "[A, H, G, F, Z]";

        assertMinPath(actualGraph, expectedLength, expectedPath);
    }
}
