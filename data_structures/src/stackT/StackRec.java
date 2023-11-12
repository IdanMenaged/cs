package stackT;

import java.util.*;

public class StackRec
{
    
    // מציאצ איבר מקסימלי ברקורסיה
    // במחסנית של מספרים חוביים
    public static int maxStack(Stack<Integer> s)
    {
        if (s.isEmpty())
            return 0;
            
        int x = s.pop();
        return Math.max(x, maxStack(s));
    }
    
    // פעולה רקורסיבית המכפילה כל איבר במחסנית פי 2
    public static void mul2(Stack<Integer> s)
    {
        if (s.isEmpty())
            return;
        int x = s.pop();
        mul2(s);
        x *= 2;
        s.push(x);
    }
    
    
    
    public static void main(String args[])
    {
        Stack<Integer> stck = StackExample.buildStack();
        System.out.println(stck);
        
        System.out.println(maxStack(stck));
        stck = StackExample.buildStack();
        mul2(stck);
        System.out.println(stck);

    }
}
