package list;

import java.util.*;

public class ListExample
{
    static Scanner reader = new Scanner(System.in);
    
  //  יצירת רשימה חדשה על ידי יצירת התא הראשון 
   public static Node<Integer> newList(int x)
   {
        Node<Integer> n = new Node<Integer>(x, null);
        return n;
   }
   
   // הוספת איבר שערכו x כאיבר ראשון ברשימה
   // ערך חזרה ה - Node החדש
   public static Node<Integer> addFirstNode(int x,  Node<Integer>lst)
   {
       Node<Integer> newNode = new Node<Integer> (x);
       newNode.setNext(lst);
       lst = newNode;
       
       return newNode;
   }
   
   // הוספת איבר שערכו x כאיבר אחרון ברשימה
   // ערך חזרה ה - Node החדש
   public static Node<Integer> addLastNode(int x,  Node<Integer>lst)
   {
       Node<Integer> p = lst;
       
       // יצירת חולייה חדשה
       Node<Integer> newNode = new Node<Integer> (x);
       
       // מציאת האיבר האחרון ברשימה
       while (p.hasNext())
        p = p.getNext();
       
        // השמת האיבר בסוף הרשימה - אחרי p
       p.setNext(newNode);
       return newNode;
   }
   
   // הוספת איבר שערכו x לרשימה אחרי איבר n
   // ערך חזרה ה - Node החדש
   public static Node<Integer> addNode(int x, Node<Integer> n)
   {
       Node<Integer> newNode = new Node<Integer> (x);
       newNode.setNext(n.getNext());
       n.setNext(newNode);
       return newNode;
    }
    
    
    // בניית רשימה הפוכה על יד input מהמשתמש
    // ערך חזרה: ראש הרשימה שנוצרה
    public static Node<Integer> buildReverseList()
    {
        int input = 0;
        Node<Integer> lst = null, n = null;
        
        System.out.println("please enter value:");
        input = reader.nextInt();    
        n = new Node<Integer>(input);
        while (input != -999)
        {
           n = new Node<Integer>(input);
           n.setNext(lst);
           lst = n;
           System.out.println("please enter value:");
           input = reader.nextInt();
        }
        return lst;
    }
    
    // בניית רשימה הפוכה על יד input מהמשתמש
    // ערך חזרה: ראש הרשימה שנוצרה 
    public static Node<Integer> buildList()
    {
        int input = 0;
        Node<Integer> list = null, n = null, last = null;
        
        // יצירת ראש הרשימה
        System.out.println("please enter value (-999 to exit):");
        input = reader.nextInt();
        
        if (input != -999)
        {
           n = new Node<Integer>(input);
           last = n;
           list = n;
        }
        
        while (input != -999)
        {
           System.out.println("please enter value(-999 to exit:");
           input = reader.nextInt();
           
           if (input != -999)
           {    // הוסםת האיבר האחרון
               n = new Node<Integer>(input);
               last.setNext(n);
               last = n;
           }
        }
        return list;
    }
    
    
    // מחיקת האיבר p מרשימה
    public static void deleteFromList(Node<Integer> lst, Node<Integer> p)
    {
      // p מציאת האיבר שלפני 
       Node<Integer> q = lst;
       while (q.hasNext() && q.getNext()!= p)
            q = q.getNext();
       
       // מחיקה
      q.setNext(p.getNext());
      p.setNext(null);
    }

    // מחיקת האיבר שערכו x מהרשימה
    public static Node<Integer> deleteByValueFromList(Node<Integer> lst, int x)
    {
      // p - הפנייה לחולייה הראשונה
      // q - הפנייה לחולייה העוקבת
       Node<Integer> p = lst.getNext(), q = lst;
       
       
       // טיפול באיבר הראשון
       if (lst.getValue() == x) 
       {
          lst = lst.getNext();
          return lst;
        }
          
       // מעבר על הרימה עם הפניות ל 2 חוליות עוקבות
       while (p.hasNext() && p.getValue()!= x)
       {
            q = p;
            p = p.getNext();
       }
   
       // מחיקת החולייה באמצעות הפניית החוליה שלפניה לזו שאחריה
       if (p.getValue() == x)
       {
           q.setNext(p.getNext());
           p.setNext(null);
       }
      return lst; 
    }
    
   // הדפסת ערכי רשימה
   public static void printListValues(Node<Integer> lst)
   {
       while (lst != null)
       {
           System.out.println(lst.getValue());
           lst = lst.getNext();
        }
    }
       
   public static void main(String args[])
   {
        Node<Integer> lst1 = buildList();
       
        // קריאת ערך למחיקה מהרשימה
       System.out.println("which value would you like to remov?");
       int y = reader.nextInt();
       System.out.println(deleteByValueFromList(lst1, y));
       
       
       // יציר רשימה חדשה שהערך באיבר בראשון שלה הוא 1
       Node<Integer> n = newList(1);
       
       // הדפסת כל הרשימה
       System.out.println(n);
       
       // הוספת איבר שערכו 19 אחרי האיבר שייצרנו
       Node<Integer> secondNode = addNode(19, n);
       
        // הדפסת כל הרשימה
       System.out.println(n);
       
       
       Node<Integer> lst = buildList();
       
       // הדפסת כל הרשימה
       System.out.println("Generated list: " + lst);
       
       // מחיקת האיבר השני מהרשימה
       deleteFromList(lst, lst.getNext());
       
       // הדפסת כל הרשימה
       System.out.println("Second item deleted: " + lst);
       
       // הוספת הערך 19 בסוף הרשימה
       addLastNode(19, lst);
       
       // הדפסת כל הרשימה
       System.out.println("Adding 19 to end of list:" + lst);
       
       // הוספת המספר 100 בתחילת הרשימה
       lst = addFirstNode(100, lst);
      
       // הדפסת כל הרשימה
       System.out.println("Adding 100 to head of list: " +lst);
       
       // קריאת ערך למחיקה מהרשימה
       System.out.println("which value would you like to remov?");
       int x = reader.nextInt();
       deleteByValueFromList(lst, x);
       
       
       // הדפסת כל הרשימה
       System.out.println("Item removed: " + lst);
       
       // הדפסת ערכי הרשימה
       printListValues(lst);
       
   }
}
