package queue.classes;

public class Pair
{
    private int num;   // a number
    private int count; // number of occurances
    
    // constructor
    public Pair(int num, int count)
    {
        this.num = num;
        this.count = count;
    }
    
    // getters
    public int getNum()
    {
        return this.num;       
    }
    
    public int getCount()
    {
        return this.count;       
    }  
    
    // setters
    public void setNum(int num)
    {
        this.num = num;       
    }
    
    public void setCount(int count)
    {
         this.count = count;       
    } 
    
    // toString
    public String toString()
    {
        return "(" + this.num + ", " + this.count + ")";
    }
}
