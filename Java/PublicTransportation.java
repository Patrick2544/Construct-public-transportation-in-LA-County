import java.util.*;

public class PublicTransportation{

    public static void main(String[] args){

        //Input files
        String firstcities = "DistanceInMilesInit30Cities.txt";
        String firstAcron = "30cities.txt";
        
        //FUTURE WORKS 
        String LCTCities = "LancasterCities.txt";
        String LCTAcron = "LancasterAcron.txt";
        String SFDAcron = "SFDAcron.txt";
        String SFDCities = "SFDCities.txt";
        String BVHAcron = "BVHAcron.txt";
        String BVHCities = "BVHCities.txt";
        String PSDCities = "PSDCities.txt";
        String PSDAcron = "PSDAcron.txt";
        
       Scanner scanner = new Scanner(System.in);
       int choice;

       do
       {
        System.out.println("\n\nWelcome to our project: constructing a public transportation system in LA County using Krsukal's MST algorithm");
        System.out.println("Menu: ");
        System.out.println("Choice 1: Preliminary result");
        System.out.println("Choice 2: Comparing the result with other similar algorithms");
        System.out.println("Choice 3: Result by each region");
        System.out.println("Choice 4: Result by color lines");
        System.out.println("Enter 0 for exit");
        System.out.print("Enter a choice (integer only): ");
        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("\n\nYou chose 1");
                //Result of the first initial 30 cities 
                ResultKruskalMST(firstAcron, firstcities);
                break;
            case 2:
                System.out.println("\n\nYou chose 2");
                //compare Kruskal with Prim and Boruvka
                ComparingAlgo(firstAcron, firstcities);
                break;
            case 3:
                System.out.println("\n\nYou chose 3");
                //result by each region
                Scanner subScanner = new Scanner(System.in);
                char subChoice;
                do
                {
                    System.out.println("\nThe Transportation system is divided into four regions");
                    System.out.println("Choice A: North East LA");
                    System.out.println("Choice B: East LA");
                    System.out.println("Choice C: West LA (Upper Part)");
                    System.out.println("Choice D: West LA (Lower Part)");
                    System.out.println("Choice X: Back to main menu");
                    subChoice = subScanner.next().toUpperCase().charAt(0);

                    switch (subChoice)
                    {
                        case 'A':
                            ResultKruskalMST(LCTAcron, LCTCities);
                            break;
                        case 'B':
                            ResultKruskalMST(PSDAcron, PSDCities);
                            break;
                        case 'C':
                            ResultKruskalMST(SFDAcron, SFDCities);
                            break;
                        case 'D':
                            ResultKruskalMST(BVHAcron, BVHCities);
                            break;
                        case 'X':
                            System.out.println("Back to main menu");
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                            break;
                    }

                } while(subChoice != 'X');
                break;
            case 4:
                System.out.println("\n\nYou chose 4");
                // result by color lines
                Scanner subScannerCase4 = new Scanner(System.in);
                char subChoice2;
                do
                {
                    System.out.println("\nThe program can display the stations by each color line according to the map");
                    System.out.println("There are a total of 9 color lines:");
                    System.out.println("Choice A: Orange Line");
                    System.out.println("Choice B: Navy Blue Line");
                    System.out.println("Choice C: Light Blue Line");
                    System.out.println("Choice D: Pink Line");
                    System.out.println("Choice E: Purple Line");
                    System.out.println("Choice F: Red Line");
                    System.out.println("Choice G: Yellow Line");
                    System.out.println("Choice H: Light Green Line");
                    System.out.println("Choice I: Dark Green Line");
                    System.out.println("Choice X: Back to main menu");
                    subChoice2 = subScannerCase4.next().toUpperCase().charAt(0);

                    switch (subChoice2)
                    {
                        case 'A':
                            PrintOrangeStations();
                            break;
                        case 'B':
                            PrintNavyBlueStations();
                            break;
                        case 'C':
                            PrintLightBlueStations();
                            break;
                        case 'D':
                            PrintPinkStations();
                            break;
                        case 'E':
                            PrintPurpleStations();
                            break;
                        case 'F':
                            PrintRedStations();
                            break;
                        case 'G':
                            PrintYellowStations();
                            break;
                        case 'H':
                            PrintLightGreenStations();
                            break;
                        case 'I':
                            PrintDarkGreenStations();
                            break;
                        case 'X':
                            System.out.println("Back to main menu");
                            break;
                        default:
                            System.out.println("Invalid choice. Try again.");
                            break;
                    }
                } while (subChoice2 != 'X');
                

                break;
            case 0:
                System.out.println("Exiting the program.");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
                break;
        }

       } while(choice != 0);
       scanner.close();
    }

    public static void ResultKruskalMST(String acron, String cities)
    {
        int numVertices;
        
        // Array of edges 
        List<Edge> edges = new ArrayList<>();
        // Creating acronym and city names hashmap
        HashMap<String, String> acronMap  = new HashMap<String, String>();

        // these functions can be found in readFiles.java
        List<String> vertices = new ArrayList<>();
        readFiles.modifyHashMap(acronMap, vertices, acron);
        
        // will put nodes in an array list
        readFiles.createEdges(edges, cities);

        numVertices = acronMap.size();

        KruskalMST kruskal = new KruskalMST();
        List<Edge> minimumSpanningTree = kruskal.findMinimumSpanningTree(edges, vertices);

        System.out.println("The minimum spanning tree for this dataset can be achieved by: ");
        //for each edge in minimumSpanningTree
        
        for(Edge edge : minimumSpanningTree){
            if(acronMap.containsKey(edge.u) && acronMap.containsKey(edge.v)){
                System.out.print("Connect " + acronMap.get(edge.u) + " to " + acronMap.get(edge.v));
                System.out.println(". Approximated distance: " + edge.weight + " miles");
            }
        }
    }

    public static void ComparingAlgo(String firstAcron, String firstcities)
    {
        System.out.println("The project compares Kruskal's MST algorithm with Prim's and Boruvka's MST algorithms");

        int numVertices;
        
        // Array of edges 
        List<Edge> edges = new ArrayList<>();
        // Creating acronym and city names hashmap
        HashMap<String, String> acronMap  = new HashMap<String, String>();

        // these functions can be found in readFiles.java
        List<String> vertices = new ArrayList<>();
        readFiles.modifyHashMap(acronMap, vertices, firstAcron);
        
        // will put nodes in an array list
        readFiles.createEdges(edges, firstcities);

        numVertices = acronMap.size();

        ResultKruskalMST(firstAcron, firstcities);
        
        List<Edge> primMST = PrimMST.prim(edges, vertices);
    
        System.out.println("\nPrim's algorithm:");

        for (Edge edge : primMST) {
            System.out.print("Connect " + acronMap.get(edge.u) + " to " + acronMap.get(edge.v));
            System.out.println(". Approximate distance: " + edge.weight + " miles");
        }


        List<Edge> boruvkaMST = BoruvkaMST.boruvka(edges, vertices);

        System.out.println("\nBoruvka's algorithm:");

        for (Edge edge : boruvkaMST) {
        System.out.print("Connect " + acronMap.get(edge.u) + " to " + acronMap.get(edge.v));
        System.out.println(". Approximated distance: " + edge.weight + " miles");
        }
    }

    public static void PrintOrangeStations()
    {
        System.out.println("The Orange Line consists of 16 stations:");
        System.out.println("Lancaster\nPalmdale (transit to Light Blue Line or Navy Blue Line)");
        System.out.println("Pasadena (transit to Pink Line)\nClaremont");
        System.out.println("Glendale (transit to Light green Line)\nDowntown LA");
        System.out.println("Huntington Park (transit tp Yellow Line)\nBeverly Hills (transit to Red Line)");
        System.out.println("Commerce\nDowney\nWhittier\nEl Monte");
        System.out.println("West Covina (transit to Purple Line)\nCompton (transit to Orange Line)");
        System.out.println("Lakewood\nLong Beach");
    }

    public static void PrintNavyBlueStations()
    {
        System.out.println("The Navy Blue Line consists of 7 stations:");
        System.out.println("Antelope Acres\nDel Sur\nQuart Hill");
        System.out.println("Lancaster (transit to Orange Line)\nOban");
        System.out.println("Deser View Highlands\nPalmdale (transit to Orange Line or Light Blue Line)");
    }

    public static void PrintLightBlueStations()
    {
        System.out.println("The Light Blue Line consists of 8 stations:");
        System.out.println("Lakeview\nPalmdale (transit to Orange Line or Nevy Blue Line)");
        System.out.println("Palmdale Regional Airport\nRoosevelt\nRedman");
        System.out.println("Antelope Center\nSun Village\nLittlerock");
    }

    public static void PrintPinkStations()
    {
        System.out.println("The Pink Line consists of 12 stations:");
        System.out.println("Pasadena (transit to Orange Line)\nS Pasadena\nAlhambra");
        System.out.println("Monterey Park\nSan Marino\nKinneloa Mesa\nAltadena");
        System.out.println("Rosemead\nTemple City\nEl Monte (transity to Orange Line)");
        System.out.println("Baldwin Park\nWest Coviona (transit to Purple Line or Orange Line)");
    }

    public static void PrintPurpleStations()
    {
        System.out.println("The Purple Line consists of 10 stations:");
        System.out.println("West Covina (transit to Pink Line or Orange Line)");
        System.out.println("Covina\nAzusa\nDuarte\nMonrovia");
        System.out.println("Valida\nCity of Industry\nSouth San Jose hills\nWalnut\nHacienda Heights");
    }

    public static void PrintRedStations()
    {
        System.out.println("The Red Line consists of 18 stations:");
        System.out.println("Hidden Hills (transit to Dark Green Line)\nMalibu\nSanta Monica");
        System.out.println("Marina Del Rey\nLos Angeles International Airport");
        System.out.println("Brentwood\nWestwood\nWest Los Angeles\nCulver City");
        System.out.println("Beverly Hills (transit to Orange Line)\nWest Hollywood\nHollywood");
        System.out.println("Central LA\nKoreantown\nMidcity\nCrenshaw\nLeimert Park\nLadera Heights");
    }

    public static void PrintYellowStations()
    {
        System.out.println("The Yellow Line consists of 8 stations:");
        System.out.println("Ingle Wood\nHuntington Park (transit to Orange Line)\nCommerce");
        System.out.println("Downey\nCompton (transit to Orange Line)\nTorrace\nRancho Palos Verdes\nAvalon");
    }

    public static void PrintLightGreenStations()
    {
        System.out.println("The Light Green Line consists of 11 stations:");
        System.out.println("Glendale (transit to Orange line\nBurbank\nNorth Hollywood\nSun valley");
        System.out.println("Van Nuys\nSherman Oaks\nPanorama City\nSan Fernando\nGranada Hills");
        System.out.println("Northridge\nReseda (transit to Dark Green Line)");
    }

    public static void PrintDarkGreenStations()
    {
        System.out.println("Tthe Dark Green Line consists of 10 stations:");
        System.out.println("Reseda (transit to Light Green Line)\nEncino");
        System.out.println("Canoga Park\nChatworth\nTwin Lakes\nWest Hills");
        System.out.println("Hidden Hills (transit to Red Line)\nWestlake Villege\nCalabasas\nWoodland Hills");
    }
} 