package queue;

import java.util.*;

import junit.framework.*;

public class TestQueueMethods extends TestCase
{
    
    private <T> Queue<T> generateQueue(T [] values)
    {
        Queue<T> q = new Queue<T>();
        for (int i = 0; i < values.length; i++)
            q.insert(values[i]);
        return q;
    }
    
    // Q 1 sumEven
    public void test_sumEvenStd()
    {
        Integer [] arr = {1, 2, 3, 4};
        Queue<Integer> q = generateQueue(arr);
        int expected = 6;
        int res = QueueMethods.sumEven(q);
        
        assertTrue("sumEven([1, 2, 3, 4]) expected: " + expected +
                   " result: " + res, expected == res);
    }

    public void test_sumAllEven()
    {
        Integer [] arr = {8, 2, 4, 4};
        Queue<Integer> q = generateQueue(arr);
        int expected = 18;
        int res = QueueMethods.sumEven(q);
        
        assertTrue("sumEven([8, 2, 4, 4]) expected: " + expected +
                   " result: " + res, expected == res);
    }
    
    public void test_sumNoEven()
    {
        Integer [] arr = {1, 3, 5, 7};
        Queue<Integer> q = generateQueue(arr);
        int expected = 0;
        int res = QueueMethods.sumEven(q);
        
        assertTrue("sumEven([1, 3, 5, 7]) expected: " + expected +
                   " result: " + res, expected == res);
    }
    
    public void test_sumEmpty()
    {
        Integer [] arr = {};
        Queue<Integer> q = generateQueue(arr);
        int expected = 0;
        int res = QueueMethods.sumEven(q);
        
        assertTrue("sumEven([]) expected: " + expected +
                   " result: " + res, expected == res);
    }
    
    // Q 2 noRepeat
    private static boolean isInandRemove(Queue<Integer> q, int num)
    {
        boolean in = false;
        q.insert(null);
        
        while(q.head() != null)
        {
            int x = q.remove();
            if (x == num && !in)
                in = true;   
            else
                q.insert(x);
        }
        q.remove();
        return in;
    }
    public void test_noRepeatStd() 
    {
        Integer [] arr = {1,1,2,1,3,2,1};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Integer [] expectedNoOrder = {1, 2, 3};
        Queue<Integer> expectedNoOrderQ = generateQueue(expectedNoOrder);  
        Queue<Integer> res = QueueMethods.noRepeat(q);
        
        
        while (!expectedNoOrderQ.isEmpty())
        {
            int head = expectedNoOrderQ.remove();
            assertTrue("noRepeat([1,1,2,1,3,2,1]) " + head + 
                       " should be in returned Q but is not", 
                       isInandRemove(res, head));
        }
        
        
        int expectedCount = 3;
        assertTrue("noRepeat([1,1,2,1,3,2,1]): " +
                  "return value should contain " +  
                  expectedCount + " values " +
                  " but it contains more", 
                  res.isEmpty());
                  
        assertEquals("noRepeat([1,1,2,1,3,2,1]): q corrupted",
                     expectedQ, q.toString());
   }

    public void test_noRepeatNoRepeat() 
    {
        Integer [] arr = {1, 2, 3};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Integer [] expectedNoOrder = {1, 2, 3};
        Queue<Integer> expectedNoOrderQ = generateQueue(expectedNoOrder);          
        Queue<Integer> res = QueueMethods.noRepeat(q);
        
        
        while (!expectedNoOrderQ.isEmpty())
        {
            int head = expectedNoOrderQ.remove();
            assertTrue("noRepeat([1,2,3]) " + head + 
                       " should be in returned Q but is not", 
                       isInandRemove(res, head));
        }
        
        int expectedCount = 3;
        assertTrue("noRepeat([1,2,3]): " +
                  "return value should contain " +  
                  expectedCount + " values " +
                  " but it contains more", 
                  res.isEmpty()); 
                  
        assertEquals("noRepeat([1,2,3]): q corrupted",
                     expectedQ, q.toString());
   }
   
    public void test_noRepeatOneRepeatItem() 
    {
        Integer [] arr = {1, 1, 1, 1};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Integer [] expectedNoOrder = {1};
        Queue<Integer> expectedNoOrderQ = generateQueue(expectedNoOrder);        
        
         
        Queue<Integer> res = QueueMethods.noRepeat(q);
        
        int count = 0;
        
        while (!expectedNoOrderQ.isEmpty())
        {
            int head = expectedNoOrderQ.remove();
            assertTrue("noRepeat([1]) " + head + 
                       " should be in returned Q but is not", 
                       isInandRemove(res, head));

        }
        
        int expectedCount = 1;
        assertTrue("noRepeat([1]): " +
                  "return value should contain " +  
                  expectedCount + " values " +
                  " but it contains more", 
                  res.isEmpty());
                  
        assertEquals("noRepeat([1]): q corrupted",
                     expectedQ, q.toString());                  
   }
   
    public void test_noRepeatLastSingle() 
    {
        Integer [] arr = {1, 2, 1, 2, 3};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Integer [] expectedNoOrder = {1, 2, 3};
        Queue<Integer> expectedNoOrderQ = generateQueue(expectedNoOrder);         
        Queue<Integer> res = QueueMethods.noRepeat(q);
        
        while (!expectedNoOrderQ.isEmpty())
        {
            int head = expectedNoOrderQ.remove();
            assertTrue("noRepeat([1, 2, 1, 2, 3]) " + head + 
                       " should be in returned Q but is not", 
                       isInandRemove(res, head));

        }
        
        int expectedCount = 3;
        assertTrue("noRepeat([1, 2, 1, 2, 3]): " +
                  "return value should contain " +  
                  expectedCount + " values " +
                  " but it contains more", 
                  res.isEmpty());
                  
        assertEquals("noRepeat([1, 2, 1, 2, 3]): q corrupted",
                     expectedQ, q.toString());
   }
   
   public void test_noRepeatFirstSingle() 
   {
        Integer [] arr = {1, 2, 3, 2, 3};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Integer [] expectedNoOrder = {1, 2, 3};
        Queue<Integer> expectedNoOrderQ = generateQueue(expectedNoOrder);         
         
        Queue<Integer> res = QueueMethods.noRepeat(q);
        
        int count = 0;
        
        while (!expectedNoOrderQ.isEmpty())
        {
            int head = expectedNoOrderQ.remove();
            assertTrue("noRepeat([1, 2, 3, 2, 3]) " + head + 
                       " should be in returned Q but is not", 
                       isInandRemove(res, head));
        }
        
        int expectedCount = 3;
        assertTrue("noRepeat([1, 2, 3, 2, 3]): " +
                  "return value should contain " +  
                  expectedCount + " values " +
                  " but it contains more", 
                  res.isEmpty());
                  
        assertEquals("noRepeat([1, 2, 3, 2, 3]): q corrupted",
                     expectedQ, q.toString());
   }
   
    public void test_noRepeatEmpty() 
    {
        Integer [] arr = {};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Integer [] expectedNoOrder = {};
        Queue<Integer> expectedNoOrderQ = generateQueue(expectedNoOrder);         
         
        Queue<Integer> res = QueueMethods.noRepeat(q);
        
        
        while (!expectedNoOrderQ.isEmpty())
        {
            int head = expectedNoOrderQ.remove();
            assertTrue("noRepeat([]) " + head + 
                       " should be in returned Q but is not", 
                       isInandRemove(res, head));
        }
        
        int expectedCount = 0;
        assertTrue("noRepeat([]): " +
                  "return value should contain " +  
                  expectedCount + " values " +
                  " but it contains more", 
                  res.isEmpty());
                  
        assertEquals("noRepeat([]): q corrupted",
                     expectedQ, q.toString());
   }
   
   
   // Q 3 oddEven
   public void test_oddEvenStd()
   {
       Integer [] arr = {1, 2,3,4,5};
       Queue<Integer> q = generateQueue(arr);
       
       String expected = "[2,4,1,3,5]";
       
       QueueMethods.oddEven(q);
       
       assertEquals("oddEven([1, 2,3,4,5]): ", expected, q.toString());
    }
    
   public void test_oddEvenOnlyOdd()
   {
       Integer [] arr = {1, 3, 5};
       Queue<Integer> q = generateQueue(arr);
       
       String expected = "[1,3,5]";
       
       QueueMethods.oddEven(q);
       
       assertEquals("oddEven([1, 3, 5]): ", expected, q.toString());
    } 
   
   public void test_oddEvenOnlyEven()
   {
       Integer [] arr = {2, 4, 6};
       Queue<Integer> q = generateQueue(arr);
       
       String expected = "[2,4,6]";
       
       QueueMethods.oddEven(q);
       
       assertEquals("oddEven([2, 4,6]): ", expected, q.toString());
    } 
    
    public void test_oddEvenSingleOdd()
   {
       Integer [] arr = {1};
       Queue<Integer> q = generateQueue(arr);
       
       String expected = "[1]";
       
       QueueMethods.oddEven(q);
       
       assertEquals("oddEven([1]): ", expected, q.toString());
    }
    
    public void test_oddEvenSingleEven()
   {
       Integer [] arr = {2};
       Queue<Integer> q = generateQueue(arr);
       
       String expected = "[2]";
       
       QueueMethods.oddEven(q);
       
       assertEquals("oddEven([2]): ", expected, q.toString());
    }
    
    public void test_oddEvenEmpty()
   {
       Integer [] arr = {};
       Queue<Integer> q = generateQueue(arr);
       
       String expected = "[]";
       
       QueueMethods.oddEven(q);
       
       assertEquals("oddEven([]): ", expected, q.toString());
    }
    
    
    // Q 4A reverseA
    public void test_reverseAStd()
    {
        Integer [] arr = {1, 2, 3};
        Queue<Integer> q = generateQueue(arr);
        
        String expected = "[3,2,1]";
        
        Queue<Integer> res = QueueMethods.reverseA(q);
        
        assertEquals("reversA([1,2,3]): ", expected,res.toString());
    }
    
    public void test_reverseASingle()
    {
        Integer [] arr = {1};
        Queue<Integer> q = generateQueue(arr);
        
        String expected = "[1]";
        
        Queue<Integer> res = QueueMethods.reverseA(q);
        
        assertEquals("reversA([1]): ", expected,res.toString());
    }
    
    public void test_reverseAEmpty()
    {
        Integer [] arr = {};
        Queue<Integer> q = generateQueue(arr);
        
        String expected = "[]";
        
        Queue<Integer> res = QueueMethods.reverseA(q);
        
        assertEquals("reversA([]): ", expected,res.toString());
    }
    
    // Q 4B reveresB
    public void test_reverseBStd()
    {
        Integer [] arr = {1, 2, 3};
        Queue<Integer> q = generateQueue(arr);
        
        String expected = "[3,2,1]";
        
        QueueMethods.reverseB(q);
        
        assertEquals("reversB([1,2,3]): ", expected,q.toString());
    }
    
    public void test_reverseBSingle()
    {
        Integer [] arr = {1};
        Queue<Integer> q = generateQueue(arr);
        
        String expected = "[1]";
        
        QueueMethods.reverseB(q);
        
        assertEquals("reversB([1]): ", expected,q.toString());
    }
    
    public void test_reverseBEmpty()
    {
        Integer [] arr = {};
        Queue<Integer> q = generateQueue(arr);
        
        String expected = "[]";
        
        QueueMethods.reverseB(q);
        
        assertEquals("reversB([]): ", expected,q.toString());
    }

    // Q 5 - upDown
    public void test_upDownStd()
    {
        Integer [] arr = {5,5,1,3,6,5};
        Queue<Integer> q = generateQueue(arr);
        Queue<Integer> res = QueueMethods.upDown(q);
        String expected = "[5,1,2,3,6,5]";
        assertEquals("upDown([5,5,1,3,6,5]): ", expected, res.toString());
    }
    
    public void test_upDownOnlyEqual()
    {
        Integer [] arr = {5,5};
        Queue<Integer> q = generateQueue(arr);
        Queue<Integer> res = QueueMethods.upDown(q);
        String expected = "[5]";
        assertEquals("upDown([5,5]): ", expected, res.toString());
    }
    
    public void test_upDownOnlyUp()
    {
        Integer [] arr = {1,3};
        Queue<Integer> q = generateQueue(arr);
        Queue<Integer> res = QueueMethods.upDown(q);
        String expected = "[1,2,3]";
        assertEquals("upDown([1,3]): ", expected, res.toString());
    }
    
    public void test_upDownOnlyDown()
    {
        Integer [] arr = {5,3};
        Queue<Integer> q = generateQueue(arr);
        Queue<Integer> res = QueueMethods.upDown(q);
        String expected = "[5,4,3]";
        assertEquals("upDown([5,3]): ", expected, res.toString());
    }
    
    public void test_upDownEmpty()
    {
        Integer [] arr = {};
        Queue<Integer> q = generateQueue(arr);
        Queue<Integer> res = QueueMethods.upDown(q);
        String expected = "[]";
        assertEquals("upDown([]): ", expected, res.toString());
    }
        
}
