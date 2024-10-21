package Models;

public class Node
{
    private Object Data;
    private Node Link;

    public Node(Object Data) 
    {
        this.Data = Data;
    }
    public Object getData()
    {
        return Data;
    }

    public void setData(Object Data)
    {
        this.Data = Data;
    }

    public Node getLink()
    {
        return Link;
    }

    public void setLink(Node Link)
    {
        this.Link = Link;
    }      
}