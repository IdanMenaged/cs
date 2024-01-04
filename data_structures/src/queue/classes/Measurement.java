package queue.classes;

public class Measurement
{ 
    private int month;  // number of month
    private double weight; // customer's weght
    
   // constructor
    public Measurement(int month, double weight)
    {
        this.month = month;
        this.weight = weight;
    }
    
    // getters
    public int getMonth()
    {
        return this.month;       
    }
    
    public double getWeight()
    {
        return this.weight;       
    }  
    
    // setters
    public void setMonth(int month)
    {
        this.month = month;       
    }
    
    public void setWeight(double weight)
    {
         this.weight = weight;       
    } 
    
    // toString
    public String toString()
    {
        return "(" + this.month + ": " + this.weight + ")";
    }    
}
