
public class Pole
{
    // head of Pole
    private Node<Ring>head;
    
    // constructor
    public Pole()
    {
        this.head=null;
    }
    
    // add a ring
    public void add(Ring r)
    {
        Node<Ring>temp = new Node<Ring>(r);
        temp.setNext(this.head);
        this.head=temp;
    }
    
    // remove a ring
    public Ring remove()
    {
        Ring r = this.head.getValue();
        head=head.getNext();
        return r;
    }
    
    // is Pole Empty
    public boolean isEmpty()
    {
        return this.head == null;
    }
    
    // toString
    public String toString()
    {
         if(this.isEmpty())
             return "[]";
         Pole temp = new Pole();
         while(!this.isEmpty())
             temp.add(this.remove());
         String s="[";
         while (!temp.isEmpty())
         {
             Ring r = temp.remove();
             s=s+r+",";
             this.add(r);
         }
         s=s.substring(0, s.length()-1)+"]";
         return s;
    }       
}
