
public class Station
{
    private int x;   // map x cooredinate
    private int y;   // map y coordinate   
    
    // constructor
    public Station(int x, int y)
    {
        // initialise instance variables
        this.x = x;
        this.y = y;
    }

    // calculates the distanc between given station an dcurrent one
    public double distance(Station s)
    {
        
        return Math.sqrt(Math.pow(this.x - s.x, 2)  + Math.pow(this.y - s.y, 2));
    }
}
