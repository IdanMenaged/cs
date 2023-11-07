
public class StackInt {

		private IntNode head;
		public StackInt()
		{
			this.head=null;
		}

		public void push(int x)
		{
			IntNode temp=new IntNode (x);
			temp.setNext(head);
			head=temp;
		}

		public int pop()
		{
			int x=head.getValue();
			head=head.getNext();
			return x;
		}

		public int top()
		{
			return head.getValue();
		}

		public boolean isEmpty()
		{
			return head==null;
		}

		public String toString()
		{
    		 if(this.isEmpty())
    			 return "[]";
    		 StackInt  temp=new StackInt ();
    		 while(!this.isEmpty())
    			 temp.push(this.pop());
    		 String s="[";
    		 while (!temp.isEmpty())
    		 {
    			 s=s+temp.top()+",";
    			 this.push(temp.pop());
    		 }
    		 s=s.substring(0, s.length()-1)+"]";
    		 return s;
		}
		 		
		}

