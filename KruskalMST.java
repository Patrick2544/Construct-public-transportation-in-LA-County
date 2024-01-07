import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class KruskalMST {
    
    public static List<Graph> KruskalAlgo(List<Graph> edges, int numVertices)
    {
        //1. Initialize an empty list to represent the Minimum Spanning Tree (MST).
        List<Graph> mst = new ArrayList<>();

        //2. Sort all edges of the graph G in non-decreasing order of their weights.
        Collections.sort(edges, new Comparator<Graph>()
        {
            //Comparator (inner class) compares Node objects based on their weight field.
            public int compare(Graph node1, Graph node2) 
            {
                // Compare based on the 'weight' field
                return Double.compare(node1.weight, node2.weight);
            }
        });

         // uncommented to check for a list of non-decrasing order edges
         /* 
        for(int i = 0; i < edges.size(); i++)
        {
            Graph edge = edges.get(i);
            System.out.println("Edge " + (i+1) + " : " + edge.locA.curLoc + " to " + edge.locB.curLoc + ", " + edge.weight);
        }
        */

       //3. Initialize an empty list to represent the set of vertices already included in the MST.
       List<String> nodeMST = new ArrayList<>();

       //4. Iterate through the sorted edges one by one:
       for(int i = 0; i < edges.size(); i++)
       {
            Graph edge = edges.get(i);

            //If both u and v are not in the set of vertices included in the MST:
            if(!nodeMST.contains(edge.locA.curLoc) && !nodeMST.contains(edge.locB.curLoc))
            {
                //4.1 Add u and v to the set of vertices included in the MST.
                nodeMST.add(edge.locA.curLoc);
                nodeMST.add(edge.locB.curLoc);


                //4.2 Add the edge (u, v) to the MST.
                mst.add(edge);
            }
            //Otherwise, if either u or v (but not both) is in the set of vertices included in the MST
            //Skip this edge to avoid creating a cycle
            if(mst.size() == numVertices - 1)
            {
                break;
            }
       }
       return mst;
    }
}
