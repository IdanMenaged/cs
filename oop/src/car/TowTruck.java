package car;

public class TowTruck extends Truck
{

    private boolean tow;            // does truck hold tow
    private  double towWeight;      // tow weight     

    // constructor
    public TowTruck(String  manufacturer,String model, int wheelsNum,String color,  double maxWeight, double length, double weight,boolean tow,double towWeight)
    {
       super(manufacturer, model,  wheelsNum,color, maxWeight,  length,weight);
       this.tow=tow;
       this.towWeight=towWeight;
    }

    // getters
    public boolean getTow()
    {
        return this.tow;
    }
    
    public double getTowWeight()
    {
        return this.towWeight;
    }
    
    // setters
    public void  setTow(boolean tow)
    {
         this.tow=tow;
    }
    public void setTowWeight(double towWeight )
    {
         this.towWeight=towWeight;;
    }
    
    // toString
    public String toString()
    {
      return  "        TowTruck :  "  +super.toString() + " tow:   " +this.tow+ " tow maximum  Weight: " +this.towWeight;
    }
    
    // doe truck hold a tow and more then 4 wheels
    public boolean isHeavy()
    {
        return this.wheelsNum > 4 && this.tow;
    }
    
    // truck class
    public char derug ()
    {
        if (this.tow && this.maxWeight> 100)
            return 'A';
        if (!this.tow && this.maxWeight <= 1000 && this.maxWeight > 500)
            return 'B';
        if (!this.tow && this.maxWeight <= 500)
            return 'C';
        return 'F';
    }
        
}
