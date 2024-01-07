import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimMST {

    public static List<Edge> prim(List<Edge> edges, List<String> vertices) {

        long heapMemoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        List<Edge> minimumSpanningTree = new ArrayList<>();
        Set<String> visitedVertices = new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(e -> e.weight));

        // Choose a starting vertex arbitrarily or based on some criteria.
        String startVertex = vertices.get(0); //Pick index 0 for this case

        // Add the starting vertex to the visited set.
        visitedVertices.add(startVertex);

        while (visitedVertices.size() < vertices.size()) {
            // Find all edges connecting visited and unvisited vertices.
            for (Edge edge : edges) {
                if ((visitedVertices.contains(edge.u) && !visitedVertices.contains(edge.v))
                        || (visitedVertices.contains(edge.v) && !visitedVertices.contains(edge.u))) {
                    priorityQueue.add(edge);
                }
            }

            // Select the minimum weight edge.
            Edge minEdge = priorityQueue.poll();
            if (minEdge == null) {
                // This can happen if the graph is not connected.
                break;
            }

            // Check if adding the edge would create a cycle.
            if (!(visitedVertices.contains(minEdge.u) && visitedVertices.contains(minEdge.v))) {
                // Add the selected vertex to the visited set.
                visitedVertices.add(minEdge.u);
                visitedVertices.add(minEdge.v);

                // Add the edge to the minimum spanning tree.
                minimumSpanningTree.add(minEdge);
            }
        }

        //Check run-time
        long startTime = System.nanoTime(); // Record the start time in nanoseconds
        long endTime = System.nanoTime(); // Record the end time in nanoseconds
        long executionTime = endTime - startTime;

        System.out.println("Prim's Algorithm");
        System.out.println("Execution time: " + executionTime + " nanoseconds");
         
        // Record the heap memory usage after running the algorithm
        long heapMemoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long memoryUsed = heapMemoryAfter - heapMemoryBefore;
        System.out.println("Prim's Algorithm");
        System.out.println("Memory used: " + memoryUsed + " bytes");
        
        return minimumSpanningTree;
}

}