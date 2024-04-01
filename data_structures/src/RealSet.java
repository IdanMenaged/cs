
public class RealSet
{
    Node<Double> lst;
    
    // Constructor
    public RealSet()
    {
        lst = null;
    }
    
    // toString
    public String toString()
    {
        if (this.lst == null)
            return "[]";
        return this.lst.toString();
    }    
    
    // mumber of items in the set
    public int size()
    {
        Node<Double> pos = this.lst;
        int count = 0;
        
        while (pos != null) 
        {
            count++;
            pos = pos.getNext();
        }
        
        return count;
    }
        
    // is a number in set
    private boolean isIn(double d)
    {
        Node<Double> p = this.lst;
        
        while(p != null)
        {
            if (p.getValue() == d)
                return true;
            p = p.getNext();
        }
        return false;
    }
    
    
    // add a number to set
    public void insert(double d)
    {
        if (isIn(d))
            return;
        Node<Double> n = new Node<Double> (d);
        n.setNext(this.lst);
        this.lst = n;
    }
    
    
    // remove a number from set
    public void remove(double d)
    {
        Node<Double> p = this.lst, q = p;
        if (this.lst == null)  // set is empty
            return;
        
        // check first item
        if (this.lst.getValue() == d)
        {
            this.lst = this.lst.getNext();
            p.setNext(null);
            return;
        }
        
        p = p.getNext();
        while (p != null)  // scan list
        {  // item found
            if (p.getValue() == d){
                q.setNext(p.getNext());
                p.setNext(null);
                return;
            }
            q = p;
            p = p.getNext();
        }
    }
    
    // returns the value of maximal g\set number
    public double findBiggest()
    {
        if (this.lst == null)  // empty list
            return 0;
        Node<Double> pos = this.lst;
        
        // max keeper
        double max = pos.getValue();
        
        while (pos != null)
        {   // biger number found
            if (pos.getValue() > max)
                max = pos.getValue();
            pos = pos.getNext();
        } 
        return max;
    }
        
}
