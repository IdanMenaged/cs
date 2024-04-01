
public class Ring
{
    private String size;  //גודל  הטבעת    
    private int color; //צבע  הטבעת    
    
    // Constructors
    public Ring() 
    {         
        this.size = "L";         
        this.color = 0;     
    }     
    
    public Ring(String str, int c) 
    {         
        this.size = str;
        this.color = c; 
    }     
    
    // setters and getters
    public String getSize() 
    {         
        return this.size; 
    }     
    public int getColor()
    {         
        return this.color;
    }
    
    // toString
    public String toString()
    {
        return "(" + this.size + ", " + this.color + ")";
    }

}
