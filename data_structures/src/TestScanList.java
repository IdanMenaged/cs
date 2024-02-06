import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

public class TestScanList extends TestCase 
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
        Integer [] arr = {1, 2, 3, 4};
        Node<Integer> lst = generateList(arr);
        
        int expected = 6;
        int result = ScanList.sumEven(lst);
        
        assertEquals("sumEven([1, 2, 3, 4])", expected, result);
    }
    
    public static void test_sumEvenAllEven()
    {
        Integer [] arr = {8, 2, 6, 4};
        Node<Integer> lst = generateList(arr);
        
        int expected = 20;
        int result = ScanList.sumEven(lst);
        
        assertEquals("sumEven([8, 2, 6, 4])", expected, result);
    }
    
    public static void test_sumEvenAllOdd()
    {
        Integer [] arr = {1, 5, 3, 7};
        Node<Integer> lst = generateList(arr);
        
        int expected = 0;
        int result = ScanList.sumEven(lst);
        
        assertEquals("sumEven([1, 5, 3, 7])", expected, result);
    }
    
    public static void test_sumEvenNull()
    {

        int expected = 0;
        int result = ScanList.sumEven(null);
        
        assertEquals("sumEven([1, 5, 3, 7])", expected, result);
    }
    
    // Q 2
    public static void test_maxListStd()
    {
       
        Integer [] arr = {1, 2, 5, 4};
        Node<Integer> lst = generateList(arr);
        
        String expected = "5\r\n";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ScanList.maxList(lst);
        
        String result = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("maxList([1, 2, 5, 4}])", expected, result);
    }   
    
    public static void test_maxListNegative()
    {
       
        Integer [] arr = {-2, -1, -5, -4};
        Node<Integer> lst = generateList(arr);
        
        String expected = "-1\r\n";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ScanList.maxList(lst);
        
        String result = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("maxList([-2, -1, -5, -4}])", expected, result);
    } 
    
    public static void test_maxListStart()
    {
       
        Integer [] arr = {9, 8, 7, 6};
        Node<Integer> lst = generateList(arr);
        
        String expected = "9\r\n";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ScanList.maxList(lst);
        
        String result = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("maxList([9, 8, 7, 6}])", expected, result);
    }  
    
    public static void test_maxListEnd()
    {
       
        Integer [] arr = {9, 8, 7, 11};
        Node<Integer> lst = generateList(arr);
        
        String expected = "11\r\n";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ScanList.maxList(lst);
        
        String result = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("maxList([9, 8, 7, 11}])", expected, result);
    }  
    
    public static void test_maxListEmpty()
    { 
        String expected = "null\r\n";
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ScanList.maxList(null);
        
        String result = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertEquals("maxList(null)", expected, result);
    }
    
    public static void test_allPosStd()
    { 
        Integer [] arr = {1, -2, 3, -4};
        Node<Integer> lst = generateList(arr);
        
        String expected = "1-->2-->3-->4-->Null";

        ScanList.allPos(lst);
        
        String result = lst.toString();

        assertEquals("allPos([1, -2, 3, -4])", expected, result);
    } 
    
    public static void test_allPosNoNgative()
    { 
        Integer [] arr = {1, 2, 3,4};
        Node<Integer> lst = generateList(arr);
        
        String expected = "1-->2-->3-->4-->Null";

        ScanList.allPos(lst);
        
        String result = lst.toString();

        assertEquals("allPos([1, 2, 3, 4])", expected, result);
    }
    
    public static void test_allPosAllNgative()
    { 
        Integer [] arr = {-1, -2, -3,-4};
        Node<Integer> lst = generateList(arr);
        
        String expected = "1-->2-->3-->4-->Null";

        ScanList.allPos(lst);
        
        String result = lst.toString();

        assertEquals("allPos([-1, -2, -3, -4])", expected, result);
    }
    
    public static void test_allPosA1Node()
    { 
        Integer [] arr = {-1};
        Node<Integer> lst = generateList(arr);
        
        String expected = "1-->Null";

        ScanList.allPos(lst);
        
        String result = lst.toString();

        assertEquals("allPos([-1])", expected, result);
    }
    
     public static void test_zugiEzugiZ()
    { 
        Integer [] arr = {1, 2, 4, 6};
        Node<Integer> lst = generateList(arr);
        
        char expected = 'z';

        ScanList.allPos(lst);
        
        char result = ScanList.zugiEzugi(lst);

        assertEquals("zugiEzugiStd()([1, 2, 4, 6])", expected, result);
    }
    
     public static void test_zugiEzugiE()
    { 
        Integer [] arr = {1, 3, 4, 5};
        Node<Integer> lst = generateList(arr);
        
        char expected = 'e';

        ScanList.allPos(lst);
        
        char result = ScanList.zugiEzugi(lst);

        assertEquals("zugiEzugiStd()([1, 3, 4, 5])", expected, result);
    }
    
    public static void test_zugiEzugiS()
    { 
        Integer [] arr = {1, 3, 4, 6};
        Node<Integer> lst = generateList(arr);
        
        char expected = 's';

        ScanList.allPos(lst);
        
        char result = ScanList.zugiEzugi(lst);

        assertEquals("zugiEzugiStd()([1, 3, 4, 6])", expected, result);
    }
}
