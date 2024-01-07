import java.util.*;

public class PublicTransportation{

    public static void main(String[] args){

        // Number of vertices (USE edges.length() - 1)
        int numVertices;
        List<Graph> firstMST = new ArrayList<>();

        // Array of edges 
        //List<Node> edges = new ArrayList<>();
        List<Graph> edges = new ArrayList<>();
        // Creating acronym and city names hashmap
        HashMap<String, String> acronMap  = new HashMap<String, String>();

        // these functions can be found in readFiles.java

        readFiles.modifyHashMap(acronMap);

        // will put nodes in an array list
        readFiles.createEdges(edges);

        numVertices = acronMap.size();

        System.out.println(numVertices);

        firstMST = KruskalMST.KruskalAlgo(edges, numVertices);
        int j = 0;

         
        for(int i = 0; i< firstMST.size(); i++){
            
            Graph mst = firstMST.get(i);

            if(acronMap.containsKey(mst.locA.curLoc) && acronMap.containsKey(mst.locB.curLoc)){

                System.out.print("Distance from " + acronMap.get(mst.locA.curLoc) + " to ");
                System.out.println(acronMap.get(mst.locB.curLoc) + " is " + mst.weight + " miles.");
                j++;
            }


        }
        System.out.println(j);
        

        /* 
        for(int i = 0; i < firstMST.size(); i++)
        {
            Graph mst = firstMST.get(i);
            
            System.out.println("from " + mst.locA.curLoc + " to " + mst.locB.curLoc + " - Distance: " + mst.weight);
            j++;
        }
        System.out.println(j);
        */
    
    }

}