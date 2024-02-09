
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;
public class TestListRecursion extends TestCase 
{
    private static <T> Node<T> generateList(T [] values)
    {
        Node<T> lst = new Node<T>(values[0]);
        Node<T> n = lst;
        Node<T> last = lst;
        for (int i = 1; i < values.length; i++)
        {
            n = new Node<T>(values[i]);
            last.setNext(n);
            last = n;
        }
        return lst;
    }

    // Q 1
    public static void test_sumEvenStd()
    {
        Integer [] arr = {1, 2, 4, 3};
        Node<Integer> lst = generateList(arr);
        
        int expected = 6;
        int result = ListRecursion.sumEven(lst);
        
        assertEquals("sumEven([1, 2, 4, 3])", expected, result);   
    }
    
    public static void test_sumEvenAllEven()
    {
        Integer [] arr = {2, 2, 4, 6};
        Node<Integer> lst = generateList(arr);
        
        int expected = 14;
        int result = ListRecursion.sumEven(lst);
        
        assertEquals("sumEven([2, 2, 4, 6])", expected, result);   
    }

    public static void test_sumEvenNoEven()
    {
        Integer [] arr = {1, 455, 11};
        Node<Integer> lst = generateList(arr);
        
        int expected = 0;
        int result = ListRecursion.sumEven(lst);
        
        assertEquals("sumEven([1, 455, 11])", expected, result);   
    }

    public static void test_sumEvenSingleItemEven()
    {
        Integer [] arr = {2};
        Node<Integer> lst = generateList(arr);
        
        int expected = 2;
        int result = ListRecursion.sumEven(lst);
        
        assertEquals("sumEven([2])", expected, result);   
    }
    
    public static void test_sumEvenSingleItemOdd()
    {
        Integer [] arr = {1};
        Node<Integer> lst = generateList(arr);
        
        int expected = 0;
        int result = ListRecursion.sumEven(lst);
        
        assertEquals("sumEven([1])", expected, result);   
    }
    
    public static void test_sumEvenSingleItemNull()
    {       
        int expected = 0;
        int result = ListRecursion.sumEven(null);
        
        assertEquals("sumEven(null)", expected, result);   
    }
    
    // Q 2
    public static void test_evenAfterNodeStd()
    {
        Integer [] arr = {1, 2, 4, 3, 8};
        Node<Integer> lst = generateList(arr);
        
        int expected = 2;
        int result = ListRecursion.evenAfterNode(lst, lst.getNext().getNext());
        
        assertEquals("evenAfter([1, 2, 4, 3, 8])", expected, result);   
    }
    
    public static void test_evenAfterNodeNoEven()
    {
        Integer [] arr = {1, 2, 5, 3, 7};
        Node<Integer> lst = generateList(arr);
        
        int expected = 0;
        int result = ListRecursion.evenAfterNode(lst, lst.getNext().getNext());
        
        assertEquals("evenAfter([1, 2, 5, 3, 7])", expected, result);   
    }
    
    public static void test_evenAfterNodeAllEven()
    {
        Integer [] arr = {1, 2, 4, 6, 8};
        Node<Integer> lst = generateList(arr);
        
        int expected = 3;
        int result = ListRecursion.evenAfterNode(lst, lst.getNext().getNext());
        
        assertEquals("evenAfter([1, 2, 4, 6, 8])", expected, result);   
    }
    
    public static void test_evenAfterNodeLastEven()
    {
        Integer [] arr = {1, 3, 5, 8};
        Node<Integer> lst = generateList(arr);
        
        int expected = 1;
        int result = ListRecursion.evenAfterNode(lst, lst.getNext().getNext());
        
        assertEquals("evenAfter([1, 3, 5, 8])", expected, result);   
    }
    
    public static void test_evenAfterNodeFirstEven()
    {
        Integer [] arr = {1, 3, 2, 7};
        Node<Integer> lst = generateList(arr);
        
        int expected = 1;
        int result = ListRecursion.evenAfterNode(lst, lst.getNext().getNext());
        
        assertEquals("evenAfter([1, 3, 2, 7])", expected, result);   
    }
    
    public static void test_evenAfterNodeNull()
    {
        Integer [] arr = {1, 3, 5, 8};
        Node<Integer> lst = generateList(arr);
        
        int expected = 0;
        int result = ListRecursion.evenAfterNode(null, lst.getNext().getNext());
        
        assertEquals("evenAfter(null)", expected, result);   
    }
    //' Q 3
    public static void test_printEvenIndexStd()
    {
        Integer [] arr = {1, 2, 5, 4};
        Node<Integer> lst = generateList(arr);
        
        String expected = "2\r\n4\r\n";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ListRecursion.printEvenIndex(lst);
        
        String result = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("printEvenIndex([1, 2, 5, 4])", expected, result);  
    }
    
    public static void test_printEvenIndexSingleItem()
    {
        Integer [] arr = {1};
        Node<Integer> lst = generateList(arr);
        
        String expected = "";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ListRecursion.printEvenIndex(lst);
        
        String result = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("printEvenIndex([1])", expected, result);  
    }
    
    public static void test_printEvenIndexPair()
    {
        Integer [] arr = {1, 7};
        Node<Integer> lst = generateList(arr);
        
        String expected = "7\r\n";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ListRecursion.printEvenIndex(lst);
        
        String result = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("printEvenIndex([1])", expected, result);  
    }
    
    public static void test_printEvenIndexNull()
    {
        Integer [] arr = {1, 7};
        Node<Integer> lst = generateList(arr);
        
        String expected = "";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ListRecursion.printEvenIndex(null);
        
        String result = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("printEvenIndex(null)", expected, result);  
    }
    
    // Q 4
    public static void test_p2qStd()
    {
        Integer [] arr = {1, 2, 4, 3, 8};
        Node<Integer> lst = generateList(arr);
        
        int expected = 9;
        int result = ListRecursion.p2q(lst,
                        lst.getNext(),
                        lst.getNext().getNext().getNext());
        
        assertEquals("p2q([1, 2, 4, 3, 8], 2-3)", expected, result);   
    } 
    
    public static void test_p2q1Item()
    {
        Integer [] arr = {1, 2, 4, 3, 8};
        Node<Integer> lst = generateList(arr);
        
        int expected = 2;
        int result = ListRecursion.p2q(lst,
                        lst.getNext(),
                        lst.getNext());
        
        assertEquals("p2q([1, 2, 4, 3, 8], 2-2)", expected, result);   
    } 
    
    public static void test_p2q2Items()
    {
        Integer [] arr = {1, 2, 4, 3, 8};
        Node<Integer> lst = generateList(arr);
        
        int expected = 6;
        int result = ListRecursion.p2q(lst,
                        lst.getNext(),
                        lst.getNext().getNext());
        
        assertEquals("p2q([1, 2, 4, 3, 8], 2-4)", expected, result);   
    }
    
    public static void test_p2qUpToLast()
    {
        Integer [] arr = {1, 2, 4, 3};
        Node<Integer> lst = generateList(arr);
        
        int expected = 9;
        int result = ListRecursion.p2q(lst,
                        lst.getNext(),
                        lst.getNext().getNext().getNext());
        
        assertEquals("p2q([1, 2, 4, 3], 2-3)", expected, result); 
    }
    
    // Q 5
    public static void test_listDiff1Longer()
    {
        Integer [] arr1 = {1, 2, 4, 3};
        Node<Integer> lst1 = generateList(arr1);

        Integer [] arr2 = {4, 3};
        Node<Integer> lst2 = generateList(arr2);
        int expected = 2;
        int result = ListRecursion.listDiff(lst1, lst2);
        
        assertEquals("listDiff([1, 2, 4, 3], [4, 3])", expected, result); 
    }   

    public static void test_listDiff2Longer()
    {
        Integer [] arr1 = {1, 2, 4, 3};
        Node<Integer> lst1 = generateList(arr1);

        Integer [] arr2 = {4, 3};
        Node<Integer> lst2 = generateList(arr2);
        int expected = 2;
        int result = ListRecursion.listDiff(lst2, lst1);
        
        assertEquals("listDiff([4, 3], [1, 2, 4, 3])", expected, result); 
    } 

    public static void test_listDiffEqual()
    {
        Integer [] arr1 = {1, 2, 4, 3};
        Node<Integer> lst1 = generateList(arr1);

        Integer [] arr2 = {4, 3, 5, 6};
        Node<Integer> lst2 = generateList(arr2);
        int expected = 0;
        int result = ListRecursion.listDiff(lst2, lst1);
        
        assertEquals("listDiff([4, 3, 5, 6], [1, 2, 4, 3])", expected, result); 
    } 
    
    public static void test_listDiffFitstNull()
    {
        Integer [] arr1 = {1, 2, 4, 3};
        Node<Integer> lst1 = generateList(arr1);
 
        int expected = 4;
        int result = ListRecursion.listDiff(null, lst1);
        
        assertEquals("listDiff([null, [1, 2, 4, 3])", expected, result); 
    }
    
    public static void test_listDiffSecondNull()
    {
        Integer [] arr2 = {4, 3, 5, 6};
        Node<Integer> lst2 = generateList(arr2);
        int expected = 4;
        int result = ListRecursion.listDiff(lst2, null);
        
        assertEquals("listDiff([4, 3, 5, 6], null)", expected, result); 
    } 
}
