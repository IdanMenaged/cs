package list;

public class Node<T>
 {
     
    // Properties
    private T value;
    private Node<T> next;
    
    // Constructors
    public Node(T value)
    {
        this.value = value;
        this.next = null;
    }
    public Node()
    {
        this.next = null;
    }
    
    public Node(T value, Node<T> next)
    {
        this.value = value;
        this.next = next;
    }
    
    // getters
    public T getValue()
    {
        return this.value;
    }
    
    public Node<T>getNext()
    {
        return this.next;
    }
    
    // setters            
    public void setValue(T value)
    {
        this.value = value;
    }
    
    public void setNext(Node<T> next)
    {
        this.next = next;
    }
    
    
    // print method
    public  String toString()
    {
        if (this.next == null)
            return this.value + "-->Null";
        return this.value + "-->" + this.next ;
         
    
    }
    
    // is there a "next node?
    public boolean hasNext()
    {
        return (this.next != null);
    }

}

