package recursion;

import Queue;

import junit.framework.*;
public class TestQueueRec extends TestCase
{
    private <T> Queue<T> generateQueue(T [] values)
    {
        Queue<T> q = new Queue<T>();
        for (int i = 0; i < values.length; i++)
            q.insert(values[i]);
        return q;
    } 
    
    // Q 1 - sumEven
   public void test_sumEven_std()
   {
        Integer [] arr = {1, 2, 3, 4};
       
        
        Queue<Integer> q = generateQueue(arr);
        
        int expected = 6;
       
        int res = QueueRec.sumEven(q);
        
        assertEquals("sumEven([1, 2, 3, 4]", expected, res);
                                   
   } 
   
    // Q 1 - sumEven
   public void test_sumEven_Empty()
   {
       
        
        Queue<Integer> q = new Queue<Integer>();
        
        int expected = 0;
       
        int res = QueueRec.sumEven(q);
        
        assertEquals("sumEven([]", expected, res);
                                   
   }
   
   public void test_sumEven_noEven()
   {
        Integer [] arr = {1, 5, 3, 9};
       
        
        Queue<Integer> q = generateQueue(arr);
        
        int expected = 0;
       
        int res = QueueRec.sumEven(q);
        
        assertEquals("sumEven([1, 5, 3, 9]", expected, res);                             
   }  
   
   public void test_sumEven_onlyEven()
   {
        Integer [] arr = {2, 4, 6};
       
        
        Queue<Integer> q = generateQueue(arr);
        
        int expected = 12;
       
        int res = QueueRec.sumEven(q);
        
        assertEquals("sumEven([2, 4, 6]", expected, res);                             
   }
   
   public void test_sumEven_singleItemEven()
   {
        Integer [] arr = {2};
       
        Queue<Integer> q = generateQueue(arr);
        
        int expected = 2;
       
        int res = QueueRec.sumEven(q);
        
        assertEquals("sumEven([2]", expected, res);                             
   }
   
   public void test_sumEven_singleItemOdd()
   {
        Integer [] arr = {1};
       
        Queue<Integer> q = generateQueue(arr);
        
        int expected = 0;
       
        int res = QueueRec.sumEven(q);
        
        assertEquals("sumEven([1]", expected, res);                             
   } 
   
   public void test_sumEven_bottomEven()
   {
        Integer [] arr = {1, 3, 4};
       
        Queue<Integer> q = generateQueue(arr);
        
        int expected = 4;
       
        int res = QueueRec.sumEven(q);
        
        assertEquals("sumEven([1, 3, 4]", expected, res);                             
   }
   
   public void test_sumEven_topEven()
   {
        Integer [] arr = {2, 3, 5};
       
        Queue<Integer> q = generateQueue(arr);
        
        int expected = 2;
       
        int res = QueueRec.sumEven(q);
        
        assertEquals("sumEven([2, 3, 5]", expected, res);                             
   }
   
   
   // q2 - upsideDown
   public void test_upsideDown_std()
   {
       String [] arr = {"a", "bb", "ccc", "dddd"};
       
       Queue<String> q = generateQueue(arr);
       
       String [] arrExpected = {"dddd", "ccc", "bb", "a"};
       Queue<String> qExpected = generateQueue(arrExpected);
       
       String expected = qExpected.toString();
       
       QueueRec.upsideDown(q);
       
       assertEquals("upsideDown([a, bb, ccc,dddd]", expected, q.toString());
    }
       
   public void test_upsideDown_Empty()
   {      
       Queue<String> q = new Queue<String>();
            
       String expected = "[]";
       
       QueueRec.upsideDown(q);
       
       assertEquals("upsideDown([a, bb, ccc,dddd]", expected, q.toString());
    } 
    
   
   public void test_upsideDown_singleItem()
   {      
       String [] arr = {"hello"};
       
       Queue<String> q = generateQueue(arr);
       
       String [] arrExpected = {"hello"};
       Queue<String> qExpected = generateQueue(arrExpected);
       
       String expected = qExpected.toString();
        
       QueueRec.upsideDown(q);
       
       assertEquals("upsideDown([hello]", expected, q.toString());
    } 
    
   public void test_upsideDown_2Items()
   {      
       String [] arr = {"hello", "bye"};
       
       Queue<String> q = generateQueue(arr);
       
       String [] arrExpected = {"bye", "hello"};
       Queue<String> qExpected = generateQueue(arrExpected);
       
       String expected = qExpected.toString();
        
       QueueRec.upsideDown(q);
       
       assertEquals("upsideDown([hello, bye]", expected, q.toString());
    }      
   
   // Q 3 - posNeg
   public void test_posNeg_std()
   {
       Double [] arr = {-1.1, 2.2, -3.3, 4.4};
       Queue<Double> q = generateQueue(arr);       
       Double [] arrExpected = {-4.4, 3.3, -2.2, 1.1};
       Queue<Double> qExpected = generateQueue(arrExpected);
       
       String expected = qExpected.toString();
       
       QueueRec.posNeg(q);
       assertEquals("posNeg([-1.1, 2.2, -3.3, 4.4]", expected, q.toString());
    }
    
   public void test_posNeg_allPos()
   {
       Double [] arr = {1.1, 2.2, 3.3, 4.4};
       Queue<Double> q = generateQueue(arr);       
       Double [] arrExpected = {-4.4, -3.3, -2.2, -1.1};
       Queue<Double> qExpected = generateQueue(arrExpected);
       
       String expected = qExpected.toString();
       
       QueueRec.posNeg(q);
       assertEquals("posNeg([1.1, 2.2, 3.3, 4.4]", expected, q.toString());
    } 
    
   public void test_posNeg_allNeg()
   {
       Double [] arr = {-1.1, -2.2, -3.3, -4.4};
       Queue<Double> q = generateQueue(arr);       
       Double [] arrExpected = {4.4, 3.3, 2.2, 1.1};
       Queue<Double> qExpected = generateQueue(arrExpected);
       
       String expected = qExpected.toString();
       
       QueueRec.posNeg(q);
       assertEquals("posNeg([-1.1, -2.2, -3.3, -4.4]", expected, q.toString());
    }   
    
   public void test_posNeg_singlePos()
   {
       Double [] arr = {1.1};
       Queue<Double> q = generateQueue(arr);       
       Double [] arrExpected = {-1.1};
       Queue<Double> qExpected = generateQueue(arrExpected);
       
       String expected = qExpected.toString();
       
       QueueRec.posNeg(q);
       assertEquals("posNeg([1.1]", expected, q.toString());
    } 
    
   public void test_posNeg_singleNeg()
   {
       Double [] arr = {-1.1};
       Queue<Double> q = generateQueue(arr);       
       Double [] arrExpected = {1.1};
       Queue<Double> qExpected = generateQueue(arrExpected);
       
       String expected = qExpected.toString();
       
       QueueRec.posNeg(q);
       assertEquals("posNeg([-1.1]", expected, q.toString());
    } 
    
   public void test_posNeg_empty()
   {
       Queue<Double> q = new Queue<Double>();;       

       String expected = "[]";
       
       QueueRec.posNeg(q);
       assertEquals("posNeg([]", expected, q.toString());
    }  
    
   // Q4 - switchQueues
   public void test_switchQueues_std()
   {
       Integer [] arr1 = {1, 2, 3, 4};
       Queue<Integer> q1 = generateQueue(arr1); 
       
       Integer [] arr2 = {5, 6, 7};
       Queue<Integer> q2 = generateQueue(arr2); 
       
       Integer [] arrExpected1 = {7, 6, 5};
       Queue<Integer> qExpected1 = generateQueue(arrExpected1);
       String expected1 = qExpected1.toString();
       
       Integer [] arrExpected2 = {4, 3, 2, 1};
       Queue<Integer> qExpected2 = generateQueue(arrExpected2); 
       String expected2 = qExpected2.toString();       
       
       QueueRec.switchQueues(q1, q2);
       
       assertEquals("switchstacks([1, 2, 3, 4], [5, 6, 7]) - first queue", expected1, q1.toString());
       assertEquals("switchstacks([1, 2, 3, 4], [5, 6, 7]) - second queue", expected2, q2.toString());       
    }
    
   public void test_switchQueues_singleItemQ1()
   {
       Integer [] arr1 = {1};
       Queue<Integer> q1 = generateQueue(arr1); 
       
       Integer [] arr2 = {5, 6, 7};
       Queue<Integer> q2 = generateQueue(arr2); 
       
       Integer [] arrExpected1 = {7, 6, 5};
       Queue<Integer> qExpected1 = generateQueue(arrExpected1);
       String expected1 = qExpected1.toString();
       
       Integer [] arrExpected2 = {1};
       Queue<Integer> qExpected2 = generateQueue(arrExpected2); 
       String expected2 = qExpected2.toString();       
       
       QueueRec.switchQueues(q1, q2);
       
       assertEquals("switchstacks([1], [5, 6, 7]) - first queue", expected1, q1.toString());
       assertEquals("switchstacks([1], [5, 6, 7]) - second queue", expected2, q2.toString());       
   } 
   
   public void test_switchQueues_singleItemQ2()
   {
       Integer [] arr1 = {1, 2, 3, 4};
       Queue<Integer> q1 = generateQueue(arr1); 
       
       Integer [] arr2 = {5};
       Queue<Integer> q2 = generateQueue(arr2); 
       
       Integer [] arrExpected1 = {5};
       Queue<Integer> qExpected1 = generateQueue(arrExpected1);
       String expected1 = qExpected1.toString();
       
       Integer [] arrExpected2 = {4, 3, 2, 1};
       Queue<Integer> qExpected2 = generateQueue(arrExpected2); 
       String expected2 = qExpected2.toString();       
       
       QueueRec.switchQueues(q1, q2);
       
       assertEquals("switchstacks([1, 2, 3, 4], [5]) - first queue", expected1, q1.toString());
       assertEquals("switchstacks([1, 2, 3, 4], [5]) - second queue", expected2, q2.toString());       
   } 
   
   public void test_switchQueues_singleItemBothQueues()
   {
       Integer [] arr1 = {1};
       Queue<Integer> q1 = generateQueue(arr1); 
       
       Integer [] arr2 = {5};
       Queue<Integer> q2 = generateQueue(arr2); 
       
       Integer [] arrExpected1 = {5};
       Queue<Integer> qExpected1 = generateQueue(arrExpected1);
       String expected1 = qExpected1.toString();
       
       Integer [] arrExpected2 = {1};
       Queue<Integer> qExpected2 = generateQueue(arrExpected2); 
       String expected2 = qExpected2.toString();       
       
       QueueRec.switchQueues(q1, q2);
       
       assertEquals("switchstacks([1], [5]) - first queue", expected1, q1.toString());
       assertEquals("switchstacks([1], [5]) - second queue", expected2, q2.toString());       
   }  
   
   public void test_switchQueues_emptyQ1()
   {
       
       Queue<Integer> q1 = new Queue<Integer>(); 
       
       Integer [] arr2 = {5, 6, 7};
       Queue<Integer> q2 = generateQueue(arr2); 
       
       Integer [] arrExpected1 = {7, 6, 5};
       Queue<Integer> qExpected1 = generateQueue(arrExpected1);
       String expected1 = qExpected1.toString();
       
       String expected2 = "[]";       
       
       QueueRec.switchQueues(q1, q2);
       
       assertEquals("switchstacks([], [5, 6, 7]) - first queue", expected1, q1.toString());
       assertEquals("switchstacks([], [5, 6, 7]) - second queue", expected2, q2.toString());       
    }

   public void test_switchQueues_emptyQ2()
   {
       Integer [] arr1 = {1, 2, 3, 4};
       Queue<Integer> q1 = generateQueue(arr1);        
       Queue<Integer> q2 = new Queue<Integer>(); 
       
       String expected1 = "[]"; 
       
       Integer [] arrExpected2 = {4, 3, 2, 1};
       
       Queue<Integer> qExpected2 = generateQueue(arrExpected2); 
       String expected2 = qExpected2.toString();        
       QueueRec.switchQueues(q1, q2);
       
       assertEquals("switchstacks([1, 2, 3, 4], []) - first queue", expected1, q1.toString());
       assertEquals("switchstacks([1, 2, 3, 4], []) - second queue", expected2, q2.toString());       
    }  
    
   public void test_switchQueues_emptyBothQueues()
   {       
       Queue<Integer> q1 = new Queue<Integer>(); 
       Queue<Integer> q2 = new Queue<Integer>(); 
       
       String expected1 = "[]"; 
       String expected2 = "[]"; 
       
       assertEquals("switchstacks([], []) - first queue", expected1, q1.toString());
       assertEquals("switchstacks([], []) - second queue", expected2, q2.toString());       
    } 
  }
