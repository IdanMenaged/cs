
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

import java.lang.reflect.*;

public class TestNewDataStructuresNoTree extends TestCase
{
    private static String LS = System.lineSeparator();
    /*******************************   Access Properties  ******************************/
    private int getIntValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        int value = fld.getInt(o);  
        
        return value;
    }
    
    private double getDoubleValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        double value = fld.getDouble(o);  
        
        return value;
    }
    
    private char getCharValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        char value = fld.getChar(o);  
        
        return value;
    }

    private boolean getBoolValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        boolean value = fld.getBoolean(o);  
        
        return value;
    }
    
    
     private Object getObjectValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        Object value = fld.get(o);  
        
        return value;
    }
    
    private int getStaticIntValue(Class cls, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        int value = fld.getInt(null);  
        
        return value;        
    }

    private double getStaticDoubleValue(Class cls, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        double value = fld.getDouble(null);  
        
        return value;        
    }
    
    private boolean getStaticBoolValue(Class cls, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        boolean value = fld.getBoolean(null);  
        
        return value;        
    }
    
    private void setIntValue(Object o, String fldName, int val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    }

     private void setStaticIntValue(Class cls, String fldName, int val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(null, val);
     
    }
    private void setStringValue(Object o, String fldName, String val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    }
    
    private void setDoubleValue(Object o, String fldName, double val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    } 
    
    private void setStaticDoubleValue(Class cls, String fldName, double val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(null, val);
     
    } 
    
    private String removeMessage (String st, String message)
    {
        return st.replace(message, "");
    }
    
   /***********************************************************/
  
    
    /************************* Group *************************/
    public void test_Group_identicalGroupsTrue()
    {
        Node<Integer> l1 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4))));
                           
        Node<Integer> l2 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4))));
                           
        Group g1 = new Group(l1);
        Group g2 = new Group(l2); 
        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();
        
        boolean expected = true;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups([1, 2, 3, 4], [1, 2, 3, 4])", 
                      expected, actual);
                      
        assertEquals("identicalGroups([1, 2, 3, 4], [1, 2, 3, 4])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups([1, 2, 3, 4], [1, 2, 3, 4])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());                      
                      
    }
    
    public void test_Group_identicalGroupsDifferentOrderTrue()
    {
        Node<Integer> l1 = new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4,
                           new Node<Integer>(1))));
                           
        Node<Integer> l2 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4))));
                           
        Group g1 = new Group(l1);
        Group g2 = new Group(l2); 
        
        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();        
        
        boolean expected = true;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups([2, 3, 4, 1], [1, 2, 3, 4])", 
                      expected, actual);
                      
        assertEquals("identicalGroups([2, 3, 4, 1], [1, 2, 3, 4])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups([2, 3, 4, 1], [1, 2, 3, 4])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());                       
    }
    
    public void test_Group_identicalGroupsSingleItemTrue()
    {
        Node<Integer> l1 = new Node<Integer>(1);
                           
        Node<Integer> l2 = new Node<Integer>(1);
                           
        Group g1 = new Group(l1);
        Group g2 = new Group(l2); 
        
        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();        
        
        boolean expected = true;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups([1], [1])",expected, actual);
        
        assertEquals("identicalGroups([1], [])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups([1], [1])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());               
    }    
    
    public void test_Group_identicalGroupsEmptyTrue()
    {             
        Group g1 = new Group();
        Group g2 = new Group(); 

        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();
        
        boolean expected = true;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups([], [])", expected, actual);
        
        assertEquals("identicalGroups([], [])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups(], [])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());               
    }    
    
    public void test_Group_identicalGroupsSameSizeFalse()
    {             
        Node<Integer> l1 = new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(9,
                           new Node<Integer>(1))));
                           
        Node<Integer> l2 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4))));
                           
        Group g1 = new Group(l1);
        Group g2 = new Group(l2);   
        
        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();        
        
        boolean expected = false;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups([2. 3, 9, 1], [1, 2, 3, 4])", 
                     expected, actual);
                     
        assertEquals("identicalGroups([2, 3, 9, 1], [1, 2, 3, 4])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups([2, 3, 9, 1], [1, 2, 3, 4])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());                            
    }  
    
    public void test_Group_identicalGroupsDifferentSizeFalse()
    {             
        Node<Integer> l1 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3)));
                           
        Node<Integer> l2 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4))));
                           
        Group g1 = new Group(l1);
        Group g2 = new Group(l2);
        
        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();        
        
        boolean expected = false;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups([1. 2, 3], [1, 2, 3, 4])", 
                     expected, actual);
                     
        assertEquals("identicalGroups([1, 2, 3], [1, 2, 3, 4])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups([1, 2, 3], [1, 2, 3, 4])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());                            
    }     

    public void test_Group_identicalGroupsFirsDifferentFalse()
    {             
        Node<Integer> l1 = new Node<Integer>(9,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4))));
                           
        Node<Integer> l2 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4))));
                           
        Group g1 = new Group(l1);
        Group g2 = new Group(l2);
        
        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();        
        
        boolean expected = false;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups([9, 2, 3, 4], [1, 2, 3, 4])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups([9, 2, 3, 4], [1, 2, 3, 4])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());       
        
        assertEquals("identicalGroups(9. 2, 3, 4], [1, 2, 3, 4])", 
                     expected, actual);
    }   
    
    public void test_Group_identicalGroupsLastDifferentFalse()
    {             
        Node<Integer> l1 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4))));
                           
        Node<Integer> l2 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(9))));
                           
        Group g1 = new Group(l1);
        Group g2 = new Group(l2);  
        
        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();        
        
        boolean expected = false;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups(1. 2, 3, 4], [1, 2, 3, 9])", 
                     expected, actual);
                     
        assertEquals("identicalGroups([1, 2, 3, 9], [1, 2, 3, 4])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups([1, 2, 3, 9], [1, 2, 3, 4])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());                            
    }  
    
    public void test_Group_identicalGroupsMiddleDifferentFalse()
    {             
        Node<Integer> l1 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(9,
                           new Node<Integer>(4))));
                           
        Node<Integer> l2 = new Node<Integer>(1,
                           new Node<Integer>(2,
                           new Node<Integer>(3,
                           new Node<Integer>(4))));
                           
        Group g1 = new Group(l1);
        Group g2 = new Group(l2);  
        
        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();        
        
        boolean expected = false;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups(1. 2, 9, 4], [1, 2, 3, 4])", 
                     expected, actual);
                     
        assertEquals("identicalGroups([1, 2, 9, 4], [1, 2, 3, 4])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups([1, 2, 9, 4], [1, 2, 3, 4])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());                            
    }
    
    
    public void test_Group_identicalGroupsSingleItemFalse()
    {             
        Node<Integer> l1 = new Node<Integer>(1);
                           
        Node<Integer> l2 = new Node<Integer>(9);
                           
        Group g1 = new Group(l1);
        Group g2 = new Group(l2); 
        
        String origGroup1 = g1.toString();
        String origGroup2 = g2.toString();        
        
        boolean expected = false;
        boolean actual = GroupExt.identicalGroups(g1, g2);
        
        assertEquals("identicalGroups(1], [9])", 
                     expected, actual);
                     
        assertEquals("identicalGroups([1], [9])" +
                     "Group should not change - first group", 
                      origGroup1, g1.toString());
                      
        assertEquals("identicalGroups([1], [9])" +
                     "Group should not change - second group", 
                      origGroup2, g2.toString());                            
    }
    
    public void test_Group_medianEven()
    {             
        Node<Integer> l = new Node<Integer>(1,
                          new Node<Integer>(2,
                          new Node<Integer>(3,
                          new Node<Integer>(4))));
                                                     
        Group g = new Group(l); 
        
        String origGroup = g.toString();        
        
        double expected = 2.5;
        double actual = GroupExt.median(g);
        
        assertEquals("median([1, 2, 3, 4])",   expected, actual);
        
        assertEquals("median([1, 2, 3, 4])" +
                     "Group should not change", 
                      origGroup, g.toString());          
    }    
    
    public void test_Group_medianOdd()
    {             
        Node<Integer> l = new Node<Integer>(1,
                          new Node<Integer>(2,
                          new Node<Integer>(3)));
                                                     
        Group g = new Group(l); 
        
        String origGroup = g.toString();         
        
        double expected = 2.0;
        double actual = GroupExt.median(g);
        
        assertEquals("median([1, 2, 3])",   expected, actual);

        assertEquals("median([1, 2, 3])" +
                     "Group should not change", 
                      origGroup, g.toString());         
    }
    
    public void test_Group_median2items()
    {             
        Node<Integer> l = new Node<Integer>(1,
                          new Node<Integer>(2));
                                                     
        Group g = new Group(l);   
        
        String origGroup = g.toString();         
        
        double expected = 1.5;
        double actual = GroupExt.median(g);
        
        assertEquals("median([1, 2])",   expected, actual);
        
        assertEquals("median([1, 2])" +
                     "Group should not change", 
                      origGroup, g.toString());         
    }
    
    public void test_Group_meduanSingleItems()
    {             
        Node<Integer> l = new Node<Integer>(1);
                                                     
        Group g = new Group(l); 
        
        String origGroup = g.toString();         
        
        double expected = 1.0;
        double actual = GroupExt.median(g);
        
        assertEquals("median([1])",   expected, actual);
        
        assertEquals("median([1])" +
                     "Group should not change", 
                      origGroup, g.toString());         
    } 
    
    public void test_Group_mdianEmpty()
    {                                          
        Group g = new Group();  
        
        String origGroup = g.toString();         
        
        double expected = 0.0;
        double actual = GroupExt.median(g);
        
        assertEquals("median([])",   expected, actual);
        
        assertEquals("median([])" +
                     "Group should not change", 
                      origGroup, g.toString());         
    }  
    
    /*********************** RealSetExt **********************/  
    
    private boolean cmprSets(RealSet rs1, RealSet rs2)
    {
        boolean res = true;
        RealSet tmp1 = new RealSet();
        RealSet tmp2 = new RealSet();
        
        while (rs1.size() > 0 && rs2.size() > 0)
        {
            double d1 = rs1.findBiggest();
            tmp1.insert(d1);
            double d2 = rs1.findBiggest();
            tmp2.insert(d2);
            
            if (d1 != d2)
                res = false;
            
            rs1.remove(d1);
            rs2.remove(d2);
        }
        
        if (res)
            res = rs1.size() == 0 && rs2.size() == 0;
            
        while (tmp1.size() > 0) {
            double d = tmp1.findBiggest();
            tmp1.remove(d);
            rs1.insert(d);
        }
        
        while (tmp2.size() > 0) {
            double d = tmp2.findBiggest();
            tmp2.remove(d);
            rs2.insert(d);
        }        
        return res;
    }
    
    public void test_clone()
    {
        RealSet rs = new RealSet();
        RealSet same = new RealSet();
        
        rs.insert(1);
        rs.insert(2);
        rs.insert(3);
        rs.insert(4);
        
        same.insert(1);
        same.insert(2);
        same.insert(3);
        same.insert(4);
        
        RealSet copy = RealSetExt.clone(rs);
        
        boolean cloneOK = cmprSets(same, copy);
        boolean rsOK = cmprSets(same, rs);
        
        assertTrue("clone([1, 2, 3, 4]) not cloned porperly: " + 
                    copy.toString(), cloneOK);
                            
        assertTrue("clone([1, 2, 3, 4]) set should stay the same: " + 
                            rs.toString(), rsOK);
    }
     

    public void test_cloneSingleItem()
    {
        RealSet rs = new RealSet();
        RealSet same = new RealSet();
        
        rs.insert(1);
        
        same.insert(1);
        
        RealSet copy = RealSetExt.clone(rs);
        
        boolean cloneOK = cmprSets(same, copy);
        boolean rsOK = cmprSets(same, rs);
        
        assertTrue("clone([1]) not cloned porperly: " + 
                    copy.toString(), cloneOK);
                            
        assertTrue("clone([1]) set should stay the same: " + 
                     rs.toString(), rsOK);
    }    
 
    
    public void test_cloneEmpty()
    {
        RealSet rs = new RealSet();
        RealSet same = new RealSet();
        

        RealSet copy = RealSetExt.clone(rs);
        
        boolean cloneOK = cmprSets(same, copy);
        boolean rsOK = cmprSets(same, rs);
        
        assertTrue("clone([]) not cloned porperly: " + 
                    copy.toString(), cloneOK);
                            
        assertTrue("clone([]) set should stay the same: " + 
                     rs.toString(), rsOK);
    }       
    /*********************** TriTreeExt **********************/

    /************************* Pole ************************/
    public void test_Poll_sortFewItems()
    {
        Ring r1 = new Ring("L", 1);
        Ring r2 = new Ring("S", 2);
        Ring r3 = new Ring("L", 3);
        Ring r4 = new Ring("S", 4);
        
        Pole p = new Pole();
        p.add(r1);
        p.add(r2);
        p.add(r3);
        p.add(r4);
        p.sort();
        
        String expected1 = "[(L, 1),(L, 3),(S, 2),(S, 4)]";
        String expected2 = "[(L, 3),(L, 1),(S, 2),(S, 4)]";
        String expected3 = "[(L, 3),(L, 3),(S, 4),(S, 2)]";
        String expected4 = "[(L, 1),(L, 3),(S, 4),(S, 2)]";
        
        String actual = p.toString();
        
        boolean sorted = actual.equals(expected1) ||
                         actual.equals(expected2) ||
                         actual.equals(expected3) ||
                         actual.equals(expected4);
        
        assertTrue("sorted pole should be " +
                    expected1 + " or " +
                    expected2 + " or " +
                    expected3 + " or " +
                    expected4 + 
                    " while it is: " + actual, 
                    sorted);
    }
    
    public void test_Poll_sort1LItem()
    {
        Ring r = new Ring("L", 1);
        
        Pole p = new Pole();
        p.add(r);

        p.sort();
        
        String expected = "[(L, 1)]";
        
        String actual = p.toString();
        
        assertEquals("p.sort([L, 1)])", expected, actual);
    } 
    
    public void test_Poll_sort1SItem()
    {
        Ring r = new Ring("S", 1);
        
        Pole p = new Pole();
        p.add(r);

        p.sort();
        
        String expected = "[(S, 1)]";
        
        String actual = p.toString();
        
        assertEquals("p.sort([(S, 1)])", expected, actual);
    } 
    
    public void test_Poll_sortEmpty()
    {    
        Pole p = new Pole();
        
        p.sort();
        
        String expected = "[]";
        
        String actual = p.toString();
        
        assertEquals("p.sort([])", expected, actual);
    }        
        
}   
  

    


