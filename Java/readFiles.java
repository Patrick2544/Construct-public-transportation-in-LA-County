import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class readFiles {

public static void createEdges(List<Edge> edges, String fileName){

    BufferedReader read;
    try{
        read = new BufferedReader(new FileReader(fileName));
        String line = read.readLine();

        while(line != null){
            String city1, city2;
            Double distance;

            city1 = line.substring(0, 3);
            city2 = line.substring(4, 7);
            distance = Double.parseDouble(line.substring(8, line.length()));

            // adding edges
            edges.add(new Edge(city1, city2, distance));
            
            //read next line
            line = read.readLine();
        }

    }catch(IOException e){

        e.printStackTrace();

    }


}


public static void modifyHashMap(HashMap<String, String> map, List<String> vertices, String fileName) {

        BufferedReader read;
        try{
            read = new BufferedReader(new FileReader(fileName));
            String line = read.readLine();

            while(line != null){
            
                //parse and put in hash map
                String acronym;
                String cityName;
                
                acronym = line.substring(0, 3);
                cityName = line.substring(4, line.length());

                //sort all vertices in a list of String
                vertices.add(acronym);
                map.put(acronym, cityName);
                
                // read next line
                line = read.readLine();
                
            }

            for (Map.Entry<String, String> entry : map.entrySet()) 
                {
                    String key = entry.getKey();
                    String value = entry.getValue();
                   
                }
        
            read.close();
        } catch(IOException e){
            e.printStackTrace();
        }


    }
    
    
}
