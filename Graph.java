class Graph {

    // This is our edges 
    Node locA, locB;
    double weight; // distance

    // u = a v = b ex: (u, v)
    public Graph(Node u, Node v, double distance){
        
        this.locA = u;
        this.locB = v;
        this.weight = distance;

    }

    public Node getlocA(){
        return this.locA;
    }

    public Node getlocB(){
        return this.locB;
    }

    public double getdistance(){
        return this.weight;
    }
}
