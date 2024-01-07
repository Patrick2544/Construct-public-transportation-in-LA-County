import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/******************************************************/
//Kruskal Algorithm according to this Pseudocode
// MST-KRUSKAL(G, w)
// 1 A = ∅ ;
// 2 for each vertex v ∈ G.V
// 3    MAKE-SET(v)
// 4 sort the edges of G.E into nondecreasing order by weight w
// 5 for each edge(u, v) ∈ G.E, taken in nondecreasing order by weight
// 6 if FIND-SET(u) != FIND-SET(v)
// 7    A = A ∪ {(u, v)}
// 8    UNION(u,v)
// 9 return A
/*******************************************************/

class KruskalMST {
    public List<Edge> findMinimumSpanningTree(List<Edge> edges, List<String> vertices) 
    {
        // Record the heap memory usage before running the algorithm
        long heapMemoryBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        //Initialize MST (represent as A in the pseudocode above: A = ∅)
        List<Edge> minimumSpanningTree = new ArrayList<>();

        //Hashmap(key, value) while key is the vertex, and value is its representative
        Map<String, String> disjointSets = new HashMap<>();

        //Initialize disjoint sets with each vertex as its own representative
         for (String vertex : vertices) 
        {
            disjointSets.put(vertex, vertex);
        }

        //sort the edges of G.E into nondecreasing order by weight w
        Collections.sort(edges, (a, b) -> Double.compare(a.weight, b.weight));

        //for each edge in every (list) edges
        for (Edge edge : edges) {
            String u = edge.u;
            String v = edge.v;
            //check if the MST does not contain a cycle 
            if (!findSet(disjointSets, u).equals(findSet(disjointSets, v))) 
            {
                minimumSpanningTree.add(edge); // A = A ∪ {(u, v)}
                union(disjointSets, u, v);     // UNION(u,v)
            }
        }

        //Check run-time
        long startTime = System.nanoTime(); // Record the start time in nanoseconds
        long endTime = System.nanoTime(); // Record the end time in nanoseconds
        long executionTime = endTime - startTime;

        System.out.println("Kruskal's Algorithm");
        System.out.println("Execution time: " + executionTime + " nanoseconds");

        //Check memory
        // Record the heap memory usage after running the algorithm
        long heapMemoryAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long memoryUsed = heapMemoryAfter - heapMemoryBefore;
        System.out.println("Kruskal's Algorithm");
        System.out.println("Memory used: " + memoryUsed + " bytes");

        return minimumSpanningTree;
    }

    //Function to find its representative vertex for each tree
    private String findSet(Map<String, String> disjointSets, String vertex) 
    {
        //Base case: when the representative is the vertex itself
        if (disjointSets.get(vertex).equals(vertex)) 
        {
            return vertex;
        }
        //recursively track back to its parent 
        return findSet(disjointSets, disjointSets.get(vertex));
    }

    //fuction that merges 2 tree into one tree (that is MST tree)
    private void union(Map<String, String> disjointSets, String u, String v) 
    {
        String uRoot = findSet(disjointSets, u);
        String vRoot = findSet(disjointSets, v);
        disjointSets.put(uRoot, vRoot); //update the representative of the target vertex
    }
}
