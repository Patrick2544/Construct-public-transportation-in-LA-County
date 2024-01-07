class Node{

    // This is our edges
    public Node next;
    public String curLoc; 
    //String locA, locB;
    //double weight; // distance
    //comment

    public Node(String curLoc)
    {
        this.curLoc = curLoc;
    }

    public Node(String curLoc, Node next)
    {
        this.curLoc = curLoc;
        this.next = next;
    }
    // u = a v = b ex: (u, v)
    /* 
    public Node(String u, String v, double distance){
        
        this.locA = u;
        this.locB = v;
        this.weight = distance;

    }

    public String getlocA(){
        return this.locA;
    }

    public String getlocB(){
        return this.locB;
    }

    public double getdistance(){
        return this.weight;
    }
    */
}


