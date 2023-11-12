package stackT;

import java.util.*;

public class StackExample
{
    static Scanner reader = new Scanner(System.in);
    
 
    // פעולה הקולטת מספרים מהמשתמש ומכניסה אותם למחסנית
    public static Stack<Integer> buildStack()
    {
        Stack<Integer> s = new Stack<Integer>();
        System.out.println("please enter a number (-999 to finish)");
        int value = reader.nextInt();
        
        while (value != -999)
        {
            s.push(value);
            System.out.println("please enter a number (-999 to finish)");
            value = reader.nextInt();
        }
        
        return s;
    }
    
    // העתקת מחסנית - פעולה המקבלת מחסנית ומחזירה עותקשלה
    public static Stack<Integer> copyStack(Stack<Integer> s)
    {
        // מחסנית ביניים
        Stack<Integer> tmpStck = new Stack<Integer>();
        
        // מחסנית להחזרה
        Stack<Integer> stck = new Stack<Integer>();
        
        // מילוי מחסנית הביניים בסדר הפוך
        while (!s.isEmpty())
        {
           int num =  s.pop();
           tmpStck.push(num);
        }
        
       // מילוי המחסנית ממחסנית הביניים
        while (!tmpStck.isEmpty())
        {
           int num = tmpStck.pop();
           stck.push(num);
           // בנייה חדש של המחסנית המקור
           s.push(num);
        }
        
        return stck;
    }
    
    // סכימת הערכים במחסנית מבלי לפגוע במחסנית המקורית
    public static int sumStack(Stack<Integer> s)
    {
         // מחסנית ביניים
        Stack<Integer> tmpStck = new Stack<Integer>();
        
       int sum =0;
       // סכימת המספרים
        while (!s.isEmpty())
        {
            int num = s.pop();
            sum += num;
            tmpStck.push(num);
        }
        
        // החזרת המספרים ל stack המקורי
        while (!tmpStck.isEmpty())
        {
            int num = tmpStck.pop();
            s.push(num);
        }
        
        return sum;
    }
    
    // ריקון המחסנית
    public static void emptyStack(Stack<Integer> s)
    {
        while(!s.isEmpty())
        {
            s.pop();
        }
    }
            
    public static void main(String args[])
    {
        Stack<Integer> stck = buildStack();
        System.out.println(stck);
        
        Stack<Integer> stckCopy = copyStack(stck);
        System.out.println(stckCopy);
        System.out.println(sumStack(stck));
        System.out.println(stck);
        emptyStack(stckCopy);
        System.out.println(stckCopy);
       
        

    }
}
