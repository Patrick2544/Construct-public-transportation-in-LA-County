import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readFiles {

public static void createEdges(List<Graph> edges){

    BufferedReader read;
    try{
        read = new BufferedReader(new FileReader("DistanceInMilesInit30Cities.txt"));
        String line = read.readLine();

        while(line != null){
            String city1, city2;
            Double distance;

            city1 = line.substring(0, 3);
            city2 = line.substring(4, 7);
            distance = Double.parseDouble(line.substring(8, line.length()));

            // adding nodes in an array list NEED WORK
            //edges.add(new Node(city1, city2, distance));
            Node nodeA = new Node(city1);
            Node nodeB = new Node(city2);

            //edges.add(new Graph(city1, city2, distance));
            edges.add(new Graph(nodeA, nodeB, distance));

            // Print info after parsing
            /* 
            System.out.print("The distance from " + nodeA.curLoc + " to " + nodeB.curLoc + " is ");
            System.out.println(distance + " miles.");
            */

            //read next line
            line = read.readLine();
        }
        // IN THE WORKS
        //System.out.println(edges);

    }catch(IOException e){

        e.printStackTrace();

    }


}


public static void modifyHashMap(HashMap<String, String> map) {

        BufferedReader read;
        try{
            read = new BufferedReader(new FileReader("30cities.txt"));
            String line = read.readLine();

            while(line != null){
            
                //parse and put in hash map
                String acronym;
                String cityName;
                
                acronym = line.substring(0, 3);
                cityName = line.substring(4, line.length());
                
                map.put(acronym, cityName);

                // read next line
                line = read.readLine();
                
            }
            //Uncomment below to check if hashmap is working.
            //System.out.println("Hashmap: " + map + '\n');
            read.close();
        } catch(IOException e){
            e.printStackTrace();
        }


    }
    
    
}
