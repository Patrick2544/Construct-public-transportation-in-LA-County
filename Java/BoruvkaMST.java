import java.util.*;

class BoruvkaMST {

    public static List<Edge> boruvka(List<Edge> edges, List<String> vertices) {
        //memory shows 0 byte - Java cleared up the memory
        long heapMemoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        List<Edge> minimumSpanningTree = new ArrayList<>();
        int numVertices = vertices.size();

        // Initialize an array to keep track of the cheapest edge for each component
        Edge[] cheapestEdge = new Edge[numVertices];

        // Create components for each vertex
        int[] component = new int[numVertices];
        Arrays.fill(component, -1); // initializing all elements of the component array to -1

        while (true) {
            Arrays.fill(cheapestEdge, null);

            // Find the cheapest edge for each component
            for (Edge edge : edges) {
                int componentSrc = findComponent(component, vertices.indexOf(edge.u));
                int componentDest = findComponent(component, vertices.indexOf(edge.v));

                //checking for cycle
                if (componentSrc != componentDest) {
                    if (cheapestEdge[componentSrc] == null || edge.weight < cheapestEdge[componentSrc].weight) {
                        cheapestEdge[componentSrc] = edge;
                    }
                    if (cheapestEdge[componentDest] == null || edge.weight < cheapestEdge[componentDest].weight) {
                        cheapestEdge[componentDest] = edge;
                    }
                }
            }

            // Add the cheapest edges to the minimum spanning tree
            for (int i = 0; i < numVertices; i++) {
                Edge edge = cheapestEdge[i];
                if (edge != null) {
                    int componentSrc = findComponent(component, vertices.indexOf(edge.u));
                    int componentDest = findComponent(component, vertices.indexOf(edge.v));

                    if (componentSrc != componentDest) {
                        minimumSpanningTree.add(edge);
                        unionComponents(component, componentSrc, componentDest);
                    }
                }
            }

            // Count the number of components
            int numComponents = 0;
            for (int i = 0; i < numVertices; i++) {
                if (component[i] == -1) {
                    numComponents++;
                }
            }

            // If there is only one component, the minimum spanning tree is complete
            if (numComponents == 1) {
                break;
            }

        }

        //Check run-time
        long startTime = System.nanoTime(); // Record the start time in nanoseconds
        long endTime = System.nanoTime(); // Record the end time in nanoseconds
        long executionTime = endTime - startTime;

        System.out.println("Boruvka's Algorithm");
        System.out.println("Execution time: " + executionTime + " nanoseconds");

        // Record the heap memory usage after running the algorithm
        long heapMemoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long memoryUsed = heapMemoryAfter - heapMemoryBefore;
        System.out.println("Boruvka's Algorithm");
        System.out.println("Memory used: " + memoryUsed + " bytes");

        return minimumSpanningTree;
    }

    //Union-find data structure
    private static int findComponent(int[] component, int vertex) {
        if (component[vertex] == -1) {
            return vertex;
        }
        return findComponent(component, component[vertex]);
    }

    private static void unionComponents(int[] component, int src, int dest) {
        int srcComponent = findComponent(component, src);
        int destComponent = findComponent(component, dest);
        component[srcComponent] = destComponent;
    }

}