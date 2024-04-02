
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
        Node<Ring> pole = this.head;
        Node<Ring> largeRings = new Node<>(), smallRings = new Node<>();
        Node<Ring> lastLarge = largeRings, lastSmall = smallRings;

        // go over pole and add to the right list
        while (pole != null) {
            if (pole.getValue().getSize().equals("L")) {
                lastLarge.setNext(new Node<>(pole.getValue()));
                lastLarge = lastLarge.getNext();
            }
            else {
                lastSmall.setNext(new Node<>(pole.getValue()));
                lastSmall = lastSmall.getNext();
            }

            pole = pole.getNext();
        }

        // construct joint list
        lastLarge.setNext(smallRings.getNext());
        this.head = largeRings.getNext();
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
