package queue.classes;

public class Runner
{
    private String name;    // name of runner
    private double speed;   // runner's speed km per hour
    
    // constructor
    public Runner(String name, double speed)
    {
        this.name = name;
        this.speed = speed;
    }
    
    // getters
    public String getName()
    {
        return this.name;       
    }
    
    public double getSpeed()
    {
        return this.speed;       
    }  
    
    // setters
    public void setName(String name)
    {
        this.name = name;       
    }
    
    public void setCount(double count)
    {
         this.speed = speed;       
    } 
    
    // toString
    public String toString()
    {
        return "(" + this.name + ": " + this.speed + ")";
    }
}
