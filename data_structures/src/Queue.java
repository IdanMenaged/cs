import queue.Node;

public class Queue<T> {
	
		private Node<T> first;
	    private Node<T> last;
	    public Queue()
	    {
	        this.first = null;
	        this.last = null;
	    }
	    public void insert(T x)
	    {
	        Node<T> temp = new Node<T>(x);
	        if (first == null)
	        {
	            first = temp;
	            last = temp;
	        }
	        else
	        {
	            last.setNext(temp);
	            last = last.getNext();
	        }
	    }
	    public T remove()
	    {
	        T x = first.getValue();
	        first = first.getNext();
	        if (first == null)
	            last = null;
	        return x;
	    }

	    public T head()
	    {
	        return first.getValue();
	    }
	    public boolean isEmpty()
	    {
	        return first == null;
	    }
	    public  String toString()
	    {
	        
	         Node<T> r = this.first;
	        String str = "[";

	        while (r != null)
	        {
	            str = str + r.getValue().toString();

	            if (r.getNext() != null)
	            {
	                str = str + ",";
	            }
	                r = r.getNext();
	            
	        }
	        return (str + "]");
	    }
}
	  

