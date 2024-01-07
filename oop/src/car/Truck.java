package car;

public class Truck extends Vehicle
{

    protected double maxWeight; // max weight to carry                                    
    protected double length;    // truck length                      
    protected double weight;    // truck weight                                      

    // constructors
    public Truck(String  manufacturer,String model, int wheelsNum,String color,  
                    double maxWeight, double length, double weight)
    {
             super(  manufacturer, model,  wheelsNum,color);
            this.maxWeight=maxWeight;
            this.length=length;
            this.weight=weight;
     }
    public Truck(Vehicle v,  double maxWeight, double length, double weight)
    {
             super(  v.manufacturer, v.model,  v.wheelsNum,v.color);
            this.maxWeight=maxWeight;
            this.length=length;
            this.weight=weight;
    }
    
    // getters   
    public double getMaxWeight()
    {
       return this.maxWeight;
    }
    
    public double getLength()
    {
        return this.length;
    }
    
    public double getWeight()
    {
        return this.weight;
    }
    
    // setters   
    public void setMaxWeight( double maxWeight)
    {
        this.maxWeight=maxWeight;
    }
    
    public void setLength( double length)
    {
        this.length=length;
    }
    
    public void setWeight(double weight)
    {
        this.weight=weight;
    }
    // toString
    public String toString()
    {
       return "  Truck:  "  + super.toString()+  " Maximum Weight: "+this.maxWeight+"     length:    "  +this.length+ "       weight:   "+this.weight;
    }
}