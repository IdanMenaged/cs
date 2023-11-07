import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;


import junit.framework.*;
public class TestStackIntMethods extends TestCase
{
    private StackInt generateStack(int [] values)
    {
        StackInt s = new StackInt();
        for (int i = 0; i < values.length; i++)
            s.push(values[i]);
        return s;
    }

    // Q 3 - buildStack
    @Test
    public void test_buildStackstd()
    {
    
        String str = "1\r\n-2\r\n-999\r\n";
        InputStream std = System.in;
        InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
            
        
        System.setIn(is);
        StackIntMethods.reader = new Scanner(System.in);
            StackInt s= StackIntMethods.buildStack();
        String expected = "[1,-2]";
        System.setIn(std);
        assertEquals("buildStack([1, -2]): ", expected, s.toString());
    }
    
    @Test
    public void test_buildStackEmpty()
    {
    
        String str = "-999\r\n";
        InputStream std = System.in;
        InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
            
        
        System.setIn(is);
        StackIntMethods.reader = new Scanner(System.in);
         StackInt s= StackIntMethods.buildStack();
        String expected = "[]";
        System.setIn(std);
        assertEquals("buildStack([]): ", expected, s.toString());
    }
    
    @Test
    public void test_buildStack1Element()
    {
    
        String str = "-1\r\n-999\r\n";
        InputStream std = System.in;
        InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8));
            
        
        System.setIn(is);
        StackIntMethods.reader = new Scanner(System.in);
        StackInt s= StackIntMethods.buildStack();
        String expected = "[-1]";
        System.setIn(std);
        assertEquals("buildStack([]): ", expected, s.toString());
    }    
    
    // Q2 naxStack
    @Test
    public void test_maxStackStd()
    {  
        int [] values = {1, 3, -6, 10, 2};
        StackInt s = generateStack(values);
        
        int mx = StackIntMethods.maxStack(s);
        assertEquals("maxStack([1, 3, -6, 10, 2]): ", 10, mx);
    }
    
    @Test
    public void test_maxStackEmpty()    
    {  
        int [] values = {};
        StackInt s = generateStack(values);
        
        int mx = StackIntMethods.maxStack(s);
        assertEquals("maxStack([]): ", 0, mx);
    }
    
    @Test
    public void test_maxStack1Element()    
    {  
        int [] values = {7};
        StackInt s = generateStack(values);
        
        int mx = StackIntMethods.maxStack(s);
        assertEquals("maxStack([]): ", 7, mx);
    } 
    
    @Test
    public void test_maxStackFirstMax()    
    {  
        int [] values = {7, 4, 6, 2};
        StackInt s = generateStack(values);
        
        int mx = StackIntMethods.maxStack(s);
        assertEquals("maxStack([]): ", 7, mx);
    } 
    
    @Test
    public void test_maxStackLastMax()    
    {  
        int [] values = {-19, 4, 6, 2, 7};
        StackInt s = generateStack(values);
        
        int mx = StackIntMethods.maxStack(s);
        assertEquals("maxStack([]): ", 7, mx);
    } 
    
    @Test
    public void test_maxStackAllEqual()    
    {  
        int [] values = {1, 1, 1, 1};
        StackInt s = generateStack(values);
        
        int mx = StackIntMethods.maxStack(s);
        assertEquals("maxStack([1, 1, 1, 1]): ", 1, mx);
    } 
    
    // Q 2 - minStack - keep stack
    @Test
    public void test_minStackStd()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {2, 1, -3, 4};
        StackInt s = generateStack(values);
        
        String origStack = s.toString();
        
        StackIntMethods.minStack(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("minStack([2, 1, 3, 4]): - output","-3\r\n", outSt);        
        assertEquals("minStack([2, 1, 3, 4]): - keep stack",origStack, s.toString());  
    } 
    
    @Test
    public void test_minStackEmpty()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {};
        StackInt s = generateStack(values);
        
        String origStack = s.toString();
        
        StackIntMethods.minStack(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("minStack([]): - output","empty\r\n", outSt);        
        assertEquals("minStack([]): - keep stack",origStack, s.toString());  
    }
    
    @Test
    public void test_minStack1Element()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {2};
        StackInt s = generateStack(values);
        
        String origStack = s.toString();
        
        StackIntMethods.minStack(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("minStack([2]): - output","2\r\n", outSt);        
        assertEquals("minStack([2]): - keep stack",origStack, s.toString());  
    } 
    
    @Test
    public void test_minStackFirst()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {-2, 0, 1, 3};
        StackInt s = generateStack(values);
        
        String origStack = s.toString();
        
        StackIntMethods.minStack(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("minStack([-2, 0, 1, 3]): - output","-2\r\n", outSt);        
        assertEquals("minStack([-2, 0, 1, 3]): - keep stack",origStack, s.toString());  
    } 
    
    @Test
    public void test_minStackLast()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {0, 1, 3, -4};
        StackInt s = generateStack(values);
        
        String origStack = s.toString();
        
        StackIntMethods.minStack(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("minStack([0, 1, 3, -4]): - output","-4\r\n", outSt);        
        assertEquals("minStack([0, 1, 3, -4]): - keep stack",origStack, s.toString());  
    }
    
    @Test
    public void test_minStackAllEqual()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {1, 1, 1};
        StackInt s = generateStack(values);
        
        String origStack = s.toString();
        
        StackIntMethods.minStack(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("minStack([1, 1, 1]): - output","1\r\n", outSt);
        assertEquals("minStack([1, 1, 1]): - keep stack",origStack, s.toString());  
    } 
    
    @Test
    public void test_plusMinusMimus()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {-11, -1, 22, -3};
        StackInt s = generateStack(values);

        StackIntMethods.plusMinus(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("plusMinus([-11, -1, 22, -3]): ","Minus\r\n", outSt);
    } 
    
    @Test
    public void test_plusMinusPlus()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {-11, 1, 22, 3};
        StackInt s = generateStack(values);

        StackIntMethods.plusMinus(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("plusMinus([-11, 1, 22, 3]): ","Plus\r\n", outSt);
    } 
    
    @Test
    public void test_plusMinusEqual()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {-11, 1, 22, -3};
        StackInt s = generateStack(values);

        StackIntMethods.plusMinus(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("plusMinus([-11, 1, 22, -3]): ","Equal\r\n", outSt);
    } 
    
    @Test
    public void test_plusMinusEmpty()    
    { 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
                
        int [] values = {};
        StackInt s = generateStack(values);

        StackIntMethods.plusMinus(s);
        String outSt = baos.toString();
       
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        assertEquals("plusMinus([]): ","Equal\r\n", outSt);
    } 
    
    @Test
    public void test_isSortedSorted()    
    {  
        int [] values = {1, 2, 3, 4};
        StackInt s = generateStack(values);

        assertTrue("isSorted([1, 2, 3, 4]): ", StackIntMethods.isSorted(s));
        
    } 
    
    @Test
    public void test_isSortedNotSorted()    
    { 
        int [] values = {1, -66, 3, 4};
        StackInt s = generateStack(values);

        assertFalse("isSorted([1, -66, 3, 4]): ", StackIntMethods.isSorted(s));
        
    }
    
    @Test
    public void test_isSortedEmpty()    
    { 
        int [] values = {};
        StackInt s = generateStack(values);

        assertTrue("isSorted([]): ", StackIntMethods.isSorted(s));
        
    } 
    
    @Test
    public void test_isSorted1Item()    
    { 
        int [] values = {7};
        StackInt s = generateStack(values);

        assertTrue("isSorted([7]): ", StackIntMethods.isSorted(s));
        
    } 
}
