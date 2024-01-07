package car;

public class Vehicle
{

       protected  String  manufacturer; // vehicle manufacturer
        protected String model;         // vehicle model
        protected int wheelsNum;        // number of wheels
         protected String color;        // veicle color

    // constructors
        public Vehicle()
    {
        // initialise instance variables
        this.manufacturer="  ";
        this.model="  ";
        this.wheelsNum=4;
        this.color="red";
    }
    public Vehicle(String  manufacturer,String model, int wheelsNum,String color)
    {
        // initialise instance variables
        this.manufacturer=manufacturer;
        this.model=model;
        this.wheelsNum=wheelsNum;
        this.color=color;
    }
    
    // getters
    public String getManufacturer()
    {
       
        return this.manufacturer;
    }
    
    public String getModel()
    {
       
        return this.model;
    }
    
    public int getWheelsNum()
    {
       
        return this.wheelsNum;
    }
    
    public String getColor()
    {
       
        return this.color;
    }

    // setters
        public void setManufacturer(String manufacturer)
    {
       
         this.manufacturer=manufacturer;
    }
    
    public void setModel(String model )
    {
       
         this.model=model;
    }
    
    public void setWheelsNum(int wheelsNum)
    {
       
         this.wheelsNum=wheelsNum;
    }
    
    public void  setColor(String color)
    {
       
         this.color=color;
    }

    // to string
    public String toString()
    {
        return "Vehicle Details :  manufacturer:    " +this.manufacturer+"      model   :   "+this.model+"  Number of wheels:   "+this.wheelsNum+"   color:  "+this.color;
    
   }
}
