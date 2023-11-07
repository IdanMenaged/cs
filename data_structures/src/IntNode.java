public class IntNode
 {
     
    // Properties
    private int value;
    private IntNode next;
    
    // Constructors
    public IntNode(int value)
    {
        this.value = value;
        this.next = null;
    }
    public IntNode()
    {
        this.next = null;
    }
    
    public IntNode(int value, IntNode next)
    {
        this.value = value;
        this.next = next;
    }
    
    // getters
    public int getValue()
    {
        return this.value;
    }
    
    public IntNode getNext()
    {
        return this.next;
    }
    
    // setters            
    public void setValue(int value)
    {
        this.value = value;
    }
    
    public void setNext(IntNode next)
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
