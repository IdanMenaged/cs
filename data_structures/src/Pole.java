
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

    /**
     * sorts the pole so that large rings are first
     */
    public void sort() {
        // divide into 2 poles
        Pole largeP = new Pole(), smallP = new Pole();
        while (!this.isEmpty()) {
            Ring r = this.remove();
            if (r.getSize().equals("L")) {
                largeP.add(r);
            }
            else {
                smallP.add(r);
            }
        }

        // join poles
        while (!largeP.isEmpty()) {
            this.add(largeP.remove());
        }
        while (!smallP.isEmpty()) {
            this.add(smallP.remove());
        }
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
