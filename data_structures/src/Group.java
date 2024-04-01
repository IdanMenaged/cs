
public class Group
{
    Node<Integer> lst;      // list of group items
    
    // Constructors
    public Group(Node<Integer> lst)
    {
        this.lst = lst;
    }
    
    public Group()
    {
        this.lst = null;
    }    
    
    // toString
    public String toString()
    {
        if (this.lst == null)
            return "[]";
        return this.lst.toString();
    }    
    
    // removes the maximal item from the Group
    public int removeMax()
    {
        if (this.lst == null)  // empty list
            return -1;
            
        Node<Integer> p = this.lst, q = p, qKeep = null;
        int max = p.getValue();  // keeps first value as maximak
        
        while (p != null)  // search list for maximal value
        {
            if (p.getValue() > max){  // a bigger item is found
                qKeep = q;
                max = p.getValue();
            }
             q = p;
             p = p.getNext();
         }
         
         if (qKeep == null)  // first item is the biggest
         {
            // remove first item
            p = this.lst;       
            lst = this.lst.getNext();
            p.setNext(null);
        }
        else
        {
            // remove maximal item
            p = qKeep.getNext();
            qKeep.setNext(p.getNext());
            p.setNext(null);
        }
        return max;
    }
    
    // removes the minimal item from the Group
    public int removeMin()
    {
        if (this.lst == null)  // empty list
            return -1;
            
        Node<Integer> p = this.lst, q = p, qKeep = null;
        int min = p.getValue();   // keeps first value as minimal
        
        while (p != null)  // search list for minimal value
        {
            if (p.getValue() < min){  // a smaller item is found
                qKeep = q;
                min = p.getValue();
            }
             q = p;
             p = p.getNext();
         }
         
         if (qKeep == null)
         {
            // remove first item
            p = this.lst;
            lst = this.lst.getNext();
            p.setNext(null);
        }
        else
        {
            // remove minimal item
            p = qKeep.getNext();
            qKeep.setNext(p.getNext());
            p.setNext(null);
        }
        return min;
    }
    
    // returns true if group is empty and false otherwise
    public boolean isEmpty()
    {
        return this.lst == null;
    }
   
    // constructs a new group (containing a new list) with the same values
    public Group  copyGroup()
    {
        Group g = new Group();  // construct an empty group
        
        if (this.lst == null)
        { // if current list is empty return empty group
            return g;
        }
        
        // construct first item in new group
        g.lst = new Node<Integer>(this.lst.getValue());
        
        Node<Integer> p_this = this.lst.getNext();  // a reference to 
        Node<Integer> last_g = g.lst;  // a reference to last item of new group
        
        while (p_this != null)
        { // copy current list items one by one
            Node<Integer> n = new Node<Integer> (p_this.getValue());
            last_g.setNext(n);
            last_g = last_g.getNext();
            p_this = p_this.getNext();
        }
        return g;
    }
            
        
        

   
}
