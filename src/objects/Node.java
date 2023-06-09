package Objects;


public class Node {
    public Vehicle data;
    public int ID;
    public Node next;
    public Node prev;
    Node (Vehicle n)
    {
        next = null;
        data = n;
    }


}

