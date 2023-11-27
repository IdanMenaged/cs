package stackT.summary;

import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import stackT.Stack;


import junit.framework.*;
public class TestSummary extends TestCase
{
    private <T> Stack<T> generateStack(T [] values)
    {
        Stack<T> s = new Stack<T>();
        for (int i = 0; i < values.length; i++)
            s.push(values[i]);
        return s;
    }
    
    public void test_ChangeHalfStd()
    {
        Character [] arr = {'a', 'b', 'c', 'd'};
        Stack<Character> s = generateStack(arr);
        String expected = "[c,d,a,b]";
        
        Summary.ChangeHalf(s);
        assertEquals("ChangeHalf(['a', 'b', 'c', 'd'])",
                        expected, s.toString());
    }
    
    public void test_ChangeHalfEmpty()
    {
        Character [] arr = {};
        Stack<Character> s = generateStack(arr);
        String expected = "[]";
        
        Summary.ChangeHalf(s);
        assertEquals("ChangeHalf([])",
                        expected, s.toString());
    }
    
    public void test_ChangeHalf2Items()
    {
        Character [] arr = {'1','2'};
        Stack<Character> s = generateStack(arr);
        String expected = "[2,1]";
        
        Summary.ChangeHalf(s);
        assertEquals("ChangeHalf(['1','2'])",
                        expected, s.toString());
    }  
   
    // Q 2 - stackPick
    public void test_stackPickStd()
    {
        Integer [] arr = {1,2, 3, 4, 2 };
        Stack<Integer> s = generateStack(arr);
        int expected = 4;
        
        int res = Summary.stackPick(s);
        assertTrue("stackPick([1,2, 3, 4, 2]) expected is " + expected + 
                    " while actual is " + res ,
                        expected == res);
    }  
    
    public void test_stackPickEmpty()
    {
        Integer [] arr = {};
        Stack<Integer> s = generateStack(arr);
        int expected = -1;
        
        int res = Summary.stackPick(s);
        assertTrue("stackPick([]) expected is " + expected + 
                    " while actual is " + res ,
                        expected == res);
    } 
    
    public void test_stackPickOnlyDown()
    {
        Integer [] arr = {4, 3, 1};
        Stack<Integer> s = generateStack(arr);
        int expected = 4;
        
        int res = Summary.stackPick(s);
        assertTrue("stackPick([4, 3, 1]) expected is " + expected + 
                    " while actual is " + res ,
                        expected == res);
    } 
    
     public void test_stackPickOnlyUp()
    {
        Integer [] arr = {4, 5, 6};
        Stack<Integer> s = generateStack(arr);
        int expected = 6;
        
        int res = Summary.stackPick(s);
        assertTrue("stackPick([4, 5, 6]) expected is " + expected + 
                    " while actual is " + res ,
                        expected == res);
    } 
    
    // Q 3 putInPlace
    public void test_putInPlaceStd()
    {
        Double [] arr = {4.2, 5.2, 6.7};
        Stack<Double> s = generateStack(arr);
        String expected = "[4.2,5.2,5.5,6.7]";
        
        Summary.putInPlace(s, 5.5);
        assertEquals("putInPlace([4.2, 5.2, 6.7], 5.5)",
                        expected, s.toString());
    } 
    
     public void test_putInPlaceTop()
    {
        Double [] arr = {4.2, 5.2, 6.7};
        Stack<Double> s = generateStack(arr);
        String expected = "[4.2,5.2,6.7,11.1]";
        
        Summary.putInPlace(s, 11.1);
        assertEquals("putInPlace([4.2, 5.2, 6.7], 11.1)",
                        expected, s.toString());
    } 
    
     public void test_putInPlaceBottom()
    {
        Double [] arr = {4.2, 5.2, 6.7};
        Stack<Double> s = generateStack(arr);
        String expected = "[1.1,4.2,5.2,6.7]";
        
        Summary.putInPlace(s, 1.1);
        assertEquals("putInPlace([1.1,4.2, 5.2, 6.7], 1.1)",
                        expected, s.toString());
    } 
    
     public void test_putInPlaceEmpty()
    {
        Double [] arr = {};
        Stack<Double> s = generateStack(arr);
        String expected = "[1.0]";
        
        Summary.putInPlace(s, 1);
        assertEquals("putInPlace([], 1)",
                        expected, s.toString());
    } 
    
    // Q 4 - mergeStacks
    public void test_mergeStacksStd()
    {
        Integer [] arr1 = {1, 3, 5, 7};
        Integer [] arr2 = {2, 4, 9};
        Stack<Integer> s1 = generateStack(arr1);
        Stack<Integer> s2 = generateStack(arr2);
        String expected = "[9,7,5,4,3,2,1]";
        String expectedS1 = s1.toString();
        String expectedS2 = s2.toString();
        
        Stack<Integer> s = Summary.mergeStacks(s1, s2);
        assertEquals("mergeStacks([1, 3, 5, 7], [2, 4, 9])",
                        expected, s.toString());
        assertEquals("mergeStacks([1, 3, 5, 7], [2, 4, 9]) - test s1",
                        expectedS1, s1.toString());
        assertEquals("mergeStacks([1, 3, 5, 7], [2, 4, 9]) - test s2",
                expectedS2, s2.toString());
    }  
    
    public void test_mergeStacksS1beforS2()
    {
        Integer [] arr1 = {1, 3, 5};
        Integer [] arr2 = {6, 7, 8};
        Stack<Integer> s1 = generateStack(arr1);
        Stack<Integer> s2 = generateStack(arr2);
        String expected = "[8,7,6,5,3,1]";
        String expectedS1 = s1.toString();
        String expectedS2 = s2.toString();
        
        Stack<Integer> s = Summary.mergeStacks(s1, s2);
        assertEquals("mergeStacks([1, 3, 5], [6, 7, 8])",
                        expected, s.toString());
        assertEquals("mergeStacks([1, 3, 5], [6, 7, 8]) - test s1",
                        expectedS1, s1.toString());
        assertEquals("mergeStacks([1, 3, 5], [6, 7, 8]]) - test s2",
                expectedS2, s2.toString());
    } 
    
    public void test_mergeStacksS2beforS1()
    {
        Integer [] arr1 = {6, 7, 8};
        Integer [] arr2 = {1, 3, 5};
        
        Stack<Integer> s1 = generateStack(arr1);
        Stack<Integer> s2 = generateStack(arr2);
        String expected = "[8,7,6,5,3,1]";
        String expectedS1 = s1.toString();
        String expectedS2 = s2.toString();
        
        Stack<Integer> s = Summary.mergeStacks(s1, s2);
        assertEquals("mergeStacks([6, 7, 8], [1, 3, 5])",
                        expected, s.toString());
        assertEquals("mergeStacks([6, 7, 8], [1, 3, 5]) - test s1",
                        expectedS1, s1.toString());
        assertEquals("mergeStacks([6, 7, 8], [1, 3, 5]) - test s2",
                expectedS2, s2.toString());
    } 
    
     public void test_mergeStacksS1Empty()
    {
        Integer [] arr1 = {};
        Integer [] arr2 = {1, 3, 5};
        
        Stack<Integer> s1 = generateStack(arr1);
        Stack<Integer> s2 = generateStack(arr2);
        String expected = "[5,3,1]";
        String expectedS1 = s1.toString();
        String expectedS2 = s2.toString();
        
        Stack<Integer> s = Summary.mergeStacks(s1, s2);
        assertEquals("mergeStacks([],[1, 3, 5])",
                        expected, s.toString());
        assertEquals("mergeStacks([],[1, 3, 5]) - test s1",
                        expectedS1, s1.toString());
        assertEquals("mergeStacks([],[1, 3, 5]) - test s2",
                expectedS2, s2.toString());
    } 
    
     public void test_mergeStacksS2Empty()
    {
        Integer [] arr1 = {1, 3, 5};
        Integer [] arr2 = {};
        
        Stack<Integer> s1 = generateStack(arr1);
        Stack<Integer> s2 = generateStack(arr2);
        String expected = "[5,3,1]";
        String expectedS1 = s1.toString();
        String expectedS2 = s2.toString();
        
        Stack<Integer> s = Summary.mergeStacks(s1, s2);
        assertEquals("mergeStacks([1, 3, 5],[])",
                        expected, s.toString());
        assertEquals("mergeStacks([1, 3, 5],[]) - test s1",
                        expectedS1, s1.toString());
        assertEquals("mergeStacks([1, 3, 5],[]) - test s2",
                expectedS2, s2.toString());
    }
 
    public void test_mergeStacksBothEmpty()
    {
        Integer [] arr1 = {};
        Integer [] arr2 = {};
        
        Stack<Integer> s1 = generateStack(arr1);
        Stack<Integer> s2 = generateStack(arr2);
        String expected = "[]";
        String expectedS1 = s1.toString();
        String expectedS2 = s2.toString();
        
        Stack<Integer> s = Summary.mergeStacks(s1, s2);
        assertEquals("mergeStacks([],[])",
                        expected, s.toString());
        assertEquals("mergeStacks([],[]) - test s1",
                        expectedS1, s1.toString());
        assertEquals("mergeStacks([],[]) - test s2",
                expectedS2, s2.toString());
    }
    
    // Q 5 - isMirror
    public void test_isMirrorTrue()
    {
        String st = "abcba";
        
        boolean isMir = Summary.isMirror(st);
        
        assertTrue("isMirror (\"abcba\") - expected true actual " + isMir, isMir);
    } 
    
    public void test_isMirrorFalse()
    {
        String st = "abcb";
        
        boolean isMir = Summary.isMirror(st);
        
        assertFalse("isMirror (\"abcb\") - expected false actual " + isMir, isMir);
    }
    
    public void test_isMirrorEmpty()
    {
        String st = "";
        
        boolean isMir = Summary.isMirror(st);
        
        assertFalse("isMirror (\"\") - expected false actual " + isMir, isMir);
    }
    
    //Q -6 - zip
    public void test_zipStd()
    {
        Character [] arr = {'a', 'a', 'b', 'b', 'c', 'c'};
        
        Stack<Character> s = generateStack(arr);
        String expectedS = s.toString();
        String expected = "[a,b,c]";
        
        Stack<Character> res = Summary.zip(s);
        assertEquals("zip (['a', 'a', 'b', 'b', 'c', 'c']) ", 
                        expected, res.toString());
        assertEquals("zip (['a', 'a', 'b', 'b', 'c', 'c']) - check stack", 
                        expectedS, s.toString());
    }
    
    public void test_zipOnlySingle()
    {
        Character [] arr = {'a', 'b', 'c'};
        
        Stack<Character> s = generateStack(arr);
        String expectedS = s.toString();
        String expected = "[a,b,c]";
        
        Stack<Character> res = Summary.zip(s);
        assertEquals("zip (['a', 'b', 'c']) ", 
                        expected, res.toString());
        assertEquals("zip (['a', 'b', 'c']) - check stack", 
                        expectedS, s.toString());
    }
    
    public void test_zipSingleTop()
    {
        Character [] arr = {'a', 'a', 'b', 'b', 'c'};
        
        Stack<Character> s = generateStack(arr);
        String expectedS = s.toString();
        String expected = "[a,b,c]";
        
        Stack<Character> res = Summary.zip(s);
        assertEquals("zip (['a', 'a', 'b', 'b', 'c']) ", 
                        expected, res.toString());
        assertEquals("zip (['a', 'a', 'b', 'b', 'c']) - check stack", 
                        expectedS, s.toString());
    }
    
    public void test_zipSingleBottom()
    {
        Character [] arr = {'a', 'b', 'b', 'c', 'c'};
        
        Stack<Character> s = generateStack(arr);
        String expectedS = s.toString();
        String expected = "[a,b,c]";
        
        Stack<Character> res = Summary.zip(s);
        assertEquals("zip (['a', 'b', 'b', 'c', 'c']) ", 
                        expected, res.toString());
        assertEquals("zip (['a', 'b', 'b', 'c', 'c']) - check stack", 
                        expectedS, s.toString());
    }
    
     public void test_zipEmpty()
    {
        Character [] arr = {};
        
        Stack<Character> s = generateStack(arr);
        String expectedS = s.toString();
        String expected = "[]";
        
        Stack<Character> res = Summary.zip(s);
        assertEquals("zip ([]) ", 
                        expected, res.toString());
        assertEquals("zip ([]) - check stack", 
                        expectedS, s.toString());
    }
}
