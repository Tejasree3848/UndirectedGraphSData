import org.graph.com.Graph;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GraphTests {

    @Test
    public void testShortestPath1() {
        Graph graph = this.setUpGraph();
        List<String> result = graph.shortestPath("A","H");
        System.out.println(result);
        System.out.println(result.size());
        assertEquals(result.size(), 2);
    }
    @Test
    public void testShortestPath2() {
        Graph graph = this.setUpGraph();
        List<List<String>> output =graph.printAllPaths("A","H");
        assertEquals(output.size(), 3);
    }



    @Test
    public void testShortestPath3() {
        Graph graph = this.setUpGraph();
        List<String> result = graph.shortestPath("C", "F");
        System.out.println(result);
        assertEquals(result.size(), 3);
    }

    //construct the Graph
    private Graph setUpGraph() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "D");
        graph.addEdge("A", "H");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "F");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        graph.addEdge("E", "F");
        graph.addEdge("E", "H");
        graph.addEdge("F", "G");
        graph.addEdge("G", "H");

        return graph;
    }
}
