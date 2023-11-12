package stackT;

import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;


import junit.framework.*;

public class TestMethods extends TestCase
{
    
    private <T> Stack<T> generateStack(T [] values)
    {
        Stack<T> s = new Stack<T>();
        for (int i = 0; i < values.length; i++)
            s.push(values[i]);
        return s;
    }
    // Q1 - switchStacks2 
    public void test_switchStacks2Std()
    {   Character [] arr1 = {'a', 'b'};
        Character [] arr2 = {'2', '1', 'c'}; 
        Stack<Character> s1 = generateStack(arr1);
        Stack<Character> s2 = generateStack(arr2);
        
        String expected1 = s2.toString();
        String expected2 = s1.toString();
        Methods.switchStacks2(s1, s2);
        
        assertEquals("switchStacks2(s1, s2) s1 should be swtched to s2", 
                        expected1, s1.toString()); 
        assertEquals("switchStacks2(s1, s2) s2 should be swtched to s2", 
                        expected2, s2.toString());
        
    }
    
    public void test_switchStacks2S2Empty()
    {   Character [] arr1 = {'h', 'e', 'l', 'l', 'o'};
        Character [] arr2 = {}; 
        Stack<Character> s1 = generateStack(arr1);
        Stack<Character> s2 = generateStack(arr2);
        
        String expected1 = s2.toString();
        String expected2 = s1.toString();
        Methods.switchStacks2(s1, s2);
        
        assertEquals("switchStacks2(s1, s2) s1 should be swtched to s2", 
                        expected1, s1.toString()); 
        assertEquals("switchStacks2(s1, s2) s2 should be swtched to s2", 
                        expected2, s2.toString());
        
    }
    
    public void test_switchStacks2S1Empty()
    {   Character [] arr1 = {};
        Character [] arr2 = {'2', '1', 'c'}; 
        Stack<Character> s1 = generateStack(arr1);
        Stack<Character> s2 = generateStack(arr2);
        
        String expected1 = s2.toString();
        String expected2 = s1.toString();
        Methods.switchStacks2(s1, s2);
        
        assertEquals("switchStacks2(s1, s2) s1 should be swtched to s2", 
                        expected1, s1.toString()); 
        assertEquals("switchStacks2(s1, s2) s2 should be swtched to s2", 
                        expected2, s2.toString());
        
    }
        
    public void test_switchStacks2S1and2Empty()
    {   Character [] arr1 = {};
        Character [] arr2 = {}; 
        Stack<Character> s1 = generateStack(arr1);
        Stack<Character> s2 = generateStack(arr2);
        
        String expected1 = s2.toString();
        String expected2 = s1.toString();
        Methods.switchStacks2(s1, s2);
        
        assertEquals("switchStacks2(s1, s2) s1 should be swtched to s2", 
                        expected1, s1.toString()); 
        assertEquals("switchStacks2(s1, s2) s2 should be swtched to s2", 
                        expected2, s2.toString());
        
    }
    
    // Q2 - switchStacks1 
    public void test_switchStacks1Std()
    {   Character [] arr1 = {'a', 'b'};
        Character [] arr2 = {'2', '1', 'c'}; 
        Stack<Character> s1 = generateStack(arr1);
        Stack<Character> s2 = generateStack(arr2);
        
        String expected1 = s2.toString();
        String expected2 = s1.toString();
        Methods.switchStacks2(s1, s2);
        
        assertEquals("switchStacks1(s1, s2) s1 should be swtched to s2", 
                        expected1, s1.toString()); 
        assertEquals("switchStacks1(s1, s2) s2 should be swtched to s2", 
                        expected2, s2.toString());
        
    }
    
    public void test_switchStacks1S2Empty()
    {   Character [] arr1 = {'h', 'e', 'l', 'l', 'o'};
        Character [] arr2 = {}; 
        Stack<Character> s1 = generateStack(arr1);
        Stack<Character> s2 = generateStack(arr2);
        
        String expected1 = s2.toString();
        String expected2 = s1.toString();
        Methods.switchStacks2(s1, s2);
        
        assertEquals("switchStacks1(s1, s2) s1 should be swtched to s2", 
                        expected1, s1.toString()); 
        assertEquals("switchStacks1(s1, s2) s2 should be swtched to s2", 
                        expected2, s2.toString());
        
    }
    
    public void test_switchStacks1S1Empty()
    {   Character [] arr1 = {};
        Character [] arr2 = {'2', '1', 'c'}; 
        Stack<Character> s1 = generateStack(arr1);
        Stack<Character> s2 = generateStack(arr2);
        
        String expected1 = s2.toString();
        String expected2 = s1.toString();
        Methods.switchStacks2(s1, s2);
        
        assertEquals("switchStacks1(s1, s2) s1 should be swtched to s2", 
                        expected1, s1.toString()); 
        assertEquals("switchStacks1(s1, s2) s2 should be swtched to s2", 
                        expected2, s2.toString());
        
    }
        
    public void test_switchStacks1S1and2Empty()
    {   Character [] arr1 = {};
        Character [] arr2 = {}; 
        Stack<Character> s1 = generateStack(arr1);
        Stack<Character> s2 = generateStack(arr2);
        
        String expected1 = s2.toString();
        String expected2 = s1.toString();
        Methods.switchStacks2(s1, s2);
        
        assertEquals("switchStacks1(s1, s2) s1 should be swtched to s2", 
                        expected1, s1.toString()); 
        assertEquals("switchStacks1(s1, s2) s2 should be swtched to s2", 
                        expected2, s2.toString());
        
    }    
    
    // Q 3 - getStringLen  
    
    public void test_getStringLentd()
    {
        String [] arr = {"abc","defg", ""};
        Stack<String> stString = generateStack(arr);
        
        String expectedStString = stString.toString();
        String expectedStLen = "[3,4,0]";
        
        Stack<Integer> stLen = Methods.getStringLen(stString);
        
        assertEquals("getStringLen([\"abc\",\"defg\", \"\"])",
                        expectedStLen, stLen.toString());
        assertEquals("getStringLen([\"abc\",\"defg\", \"\"]) orig stack corruptes",
                        expectedStString, stString.toString());
    }
    public void test_getStringLen1Item()
    {
        String [] arr = {"abc"};
        Stack<String> stString = generateStack(arr);
        
        String expectedStString = stString.toString();
        String expectedStLen = "[3]";
        
        Stack<Integer> stLen = Methods.getStringLen(stString);
        
        assertEquals("getStringLen([\"abc\"])",
                        expectedStLen, stLen.toString());
        assertEquals("getStringLen([\"abc\"]) orig stack corruptes",
                        expectedStString, stString.toString());
    }                        
 
    public void test_getStringLenEmpty()
    {
        String [] arr = {};
        Stack<String> stString = generateStack(arr);
        
        String expectedStString = stString.toString();
        String expectedStLen = "[]";
        
        Stack<Integer> stLen = Methods.getStringLen(stString);
        
        assertEquals("getStringLen([])",
                        expectedStLen, stLen.toString());
        assertEquals("getStringLen([]) orig stack corruptes",
                        expectedStString, stString.toString());
    } 
    
    // Q 4 -topMax 
    public void test_topMaxStd()
    {
        Integer [] arr = {8, 2, 3, 11, -1};
        Stack<Integer> s = generateStack(arr);
         
        String expected = "[8,2,3,-1,11]";
        
        Methods.topMax(s);
        
        assertEquals("topMax({8, 2, 3, 11, -1})", expected, s.toString());;
    } 
    public void test_topMaxFirs()
    {
        Integer [] arr = {8, 2, 3, 11, -1};
        Stack<Integer> s = generateStack(arr);
         
        String expected = "[8,2,3,-1,11]";
        
        Methods.topMax(s);
        
        assertEquals("topMax({8, 2, 3, 11, -1})", expected, s.toString());;
    } 
 
    public void test_topMaxBottomMax()
    {
        Integer [] arr = {11, 8, 2, 3};
        Stack<Integer> s = generateStack(arr);
         
        String expected = "[8,2,3,11]";
        
        Methods.topMax(s);
        
        assertEquals("topMax([11, 8, 2, 3])", expected, s.toString());;
    } 

    public void test_topMaxTopMax()
    {
        Integer [] arr = {8, 2, 3, 11};
        Stack<Integer> s = generateStack(arr);
         
        String expected = "[8,2,3,11]";
        
        Methods.topMax(s);
        
        assertEquals("topMax([8, 2, 3, 11])", expected, s.toString());;
    }
    public void test_topMaxAllEqual()
    {
        Integer [] arr = {5, 5, 5, 5};
        Stack<Integer> s = generateStack(arr);
         
        String expected = "[5,5,5,5]";
        
        Methods.topMax(s);
        
        assertEquals("topMax([5,5,5,5])", expected, s.toString());;
    } 
    
    public void test_topMaxAllEmpty()
    {
        Integer [] arr = {};
        Stack<Integer> s = generateStack(arr);
         
        String expected = "[]";
        
        Methods.topMax(s);
        
        assertEquals("topMax([])", expected, s.toString());;
    } 
}
