package passport;

import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

import java.lang.reflect.*;

public class TestTravelerPassport extends TestCase
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
    
    private boolean getBooleanValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        boolean value = fld.getBoolean(o);  
        
        return value;
    }
    
     private Object getObjectValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
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
    
    private String removeMessage (String st, String message)
    {
        return st.replace(message, "");
    }

    /*********************************** Date **************************/
    
    public void test_constructor() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        
        int day = getIntValue(d,"day");
        int month = getIntValue(d,"month");
        int year = getIntValue(d, "year");
        
        assertEquals("new Date(1, 2 , 22) - day", 1, day);
        assertEquals("new Date(1, 2 , 22) - month", 2, month);
        assertEquals("new Date(1, 2 , 22) - year", 22, year);
    }
    
    public void test_copyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        Date copy = new Date(d);
        
        int day = getIntValue(copy,"day");
        int month = getIntValue(copy,"month");
        int year = getIntValue(copy, "year");
        
        assertEquals("new Date(d(1, 2 , 22)) - day", 1, day);
        assertEquals("new Date(d(1, 2 , 22)) - month", 2, month);
        assertEquals("new Date(d(1, 2 , 22)) - year", 22, year);
    }
    
    /********************** Date setters ************************/
    public void test_setDay() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        d.setDay(11);
        int day = getIntValue(d,"day");
        assertEquals("setDay(11)", 11, day);
    }
    
    public void test_setMonth() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        d.setMonth(7);
        int month = getIntValue(d,"month");
        assertEquals("setMonth(7)", 7, month);
    }   
  
    public void test_setYear() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        d.setYear(20);
        int year = getIntValue(d,"year");
        assertEquals("setYear(20)", 20, year);
    } 
    
    /***************** Date getters ***********************/
    public void test_getDay() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        int day = d.getDay();
        int expected = getIntValue(d,"day");
        assertEquals("getDay()", expected, day);
    } 
    
    public void test_getMonth() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        int month = d.getMonth();
        int expected = getIntValue(d,"month");
        assertEquals("setMonth()", expected, month);
    }  
    
    public void test_getYear() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        int year = d.getYear();
        int expected = getIntValue(d,"year");
        assertEquals("getYear()", expected, year);
    } 
    
    public void test_toDateString()
    {
        Date d = new Date(1, 2 , 22);
        
        String expected = "1.2.22";
        String date  = d.toString();
        
        assertEquals("d.toString(1, 2 , 22)", expected, date);
    }
    
    public void test_isOlderYearTrue() 
    {
        Date d1 = new Date(1, 2 , 20);
        Date d2 = new Date(1, 2 , 22);
        
        boolean b = d2.isOlder(d1);
        
        assertEquals("isOlder() 1.2.20-1.2.22", true, b);
    }
    
    public void test_isOlderYearFalse() 
    {
        Date d1 = new Date(1, 2 , 20);
        Date d2 = new Date(1, 2 , 22);
        
        boolean b = d1.isOlder(d2);
        
        assertEquals("isOlder() 1.2.22-1.2.20", false, b);
    }
    
    public void test_isOlderMonthTrue() 
    {
        Date d1 = new Date(1, 1 , 22);
        Date d2 = new Date(1, 2 , 22);
        
        boolean b = d2.isOlder(d1);
        
        assertEquals("isOlder() 1.1.22-1.2.22", true, b);
    } 
    
    public void test_isOlderMonthFalse() 
    {
        Date d1 = new Date(1, 1 , 22);
        Date d2 = new Date(1, 2 , 22);
        
        boolean b = d1.isOlder(d2);
        
        assertEquals("isOlder() 1.2.22-1.1.22", false, b);
    } 
    
    public void test_isOlderDayTrue() 
    {
        Date d1 = new Date(1, 2 , 22);
        Date d2 = new Date(2, 2 , 22);
        
        boolean b = d2.isOlder(d1);
        
        assertEquals("isOlder() 1.2.22-2.1.22", true, b);
    } 
    
    public void test_isOlderDayFalse() 
    {
        Date d1 = new Date(1, 2 , 22);
        Date d2 = new Date(2, 2 , 22);
        
        boolean b = d1.isOlder(d2);
        
        assertEquals("isOlder() 2.1.22-1.2.22", false, b);
    } 
    
    /************************ Passport ****************************/
    public void testContructor() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Passport.class, "passCounter", 0);
        
        Date d = new Date(1,2,30);
        Passport p = new Passport("Dana",  "Israel", d);
        
        String name = (String)getObjectValue(p, "name");
        String country = (String)getObjectValue(p, "country");
        
        Date d1 = (Date)getObjectValue(p, "expiryDate");
        
        // check pass counter
        int passCounter = getStaticIntValue(Passport.class, "passCounter");
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - passCounter", 1, passCounter);
        
        /// check number
        int number = getIntValue(p, "number");
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - number", 1, number);
        
        // check name and country
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - name", "Dana", name);
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - country", "Israel", country);
        
        // check that date copied
        assertTrue("Passport(\"Dana\",  \"Israel\", d) - date copied", d != d1);
        
        // check date        
        int day = getIntValue(d1,"day");
        int month = getIntValue(d1,"month");
        int year = getIntValue(d1, "year");
      
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - date - day", 1, day);
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - date - month", 2, month);
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - date - month", 30, year);
    }

    public void test_CopyContructor() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Passport.class, "passCounter", 0);
        
        Date d = new Date(1,2,30);
        Passport p = new Passport("Dana",  "Israel", d);
        Passport pCopy = new Passport(p);
        String name = (String)getObjectValue(pCopy, "name");
        String country = (String)getObjectValue(pCopy, "country");
        
        Date dCopy = (Date)getObjectValue(pCopy, "expiryDate");
        Date dOrig = (Date)getObjectValue(p, "expiryDate");
        // check pass counter
        int passCounter = getStaticIntValue(Passport.class, "passCounter");
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - passCounter", 1, passCounter);
        
        /// check number
        int number = getIntValue(pCopy, "number");
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - number", 1, number);
        
        // check name and country
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - name", "Dana", name);
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - country", "Israel", country);
        
        // check that date copied
        assertTrue("Passport(\"Dana\",  \"Israel\", d) - date copied", d != dCopy);
        assertTrue("Passport(\"Dana\",  \"Israel\", d) - date copied", dOrig != dCopy);
        
        // check date        
        int day = getIntValue(dCopy,"day");
        int month = getIntValue(dCopy,"month");
        int year = getIntValue(dCopy, "year");
      
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - date - day", 1, day);
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - date - month", 2, month);
        assertEquals("Passport(\"Dana\",  \"Israel\", d) - date - month", 30, year);
    }    

    /*************************** Passport setters ****************/
    public void test_passSetName() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        Passport p = new Passport("Dana",  "Israel", d);
        p.setName("Dani");
        String name = (String)getObjectValue(p, "name");
        
        assertEquals("p.setName(\"Dani\")", "Dani", name);;
    } 
    
     public void test_passSetCountry() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        Passport p = new Passport("Dana",  "Israel", d);
        p.setCountry("USA");
        String country = (String)getObjectValue(p, "country");
        
        assertEquals("p.setCountry(\"USA\")", "USA", country);;
    } 
    
     public void test_passSetNumber() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        Passport p = new Passport("Dana",  "Israel", d);
        p.setNumber(72);
        int number = getIntValue(p, "number");
        
        assertEquals("p.setNumber(72)", 72, number);;
    }
    
     public void test_passSetPassCounter() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        Passport p = new Passport("Dana",  "Israel", d);
        p.setPassCounter(111);
        int passCounter = getStaticIntValue(Passport.class, "passCounter");
        
        assertEquals("p.setPassCounter(111)", 111, passCounter);;
    }
    
    public void test_passSetExpiryDate() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2 , 22);
        Date d1 = new Date(2, 3 , 25);
        Passport p = new Passport("Dana",  "Israel", d);
        p.setExpiryDate(d1);
        
        Date actualDate = (Date)getObjectValue(p, "expiryDate");
        
        assertTrue("p.setExpiryDate((2, 3 , 25)) - object copied", actualDate!= d1);
        // check date        
        int day = getIntValue(d1,"day");
        int month = getIntValue(d1,"month");
        int year = getIntValue(d1, "year");  
        
        assertEquals("p.setExpiryDate((2, 3 , 25)) - day", 2, day);
        assertEquals("p.setExpiryDate((2, 3 , 25)) - month", 3, month);        
        assertEquals("p.setExpiryDate((2, 3 , 25)) - year", 25, year);         
    }
    
    /********************* Passport getters ***************/
    
    public void test_passGetName()
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Moshe", "Germany", d);
        
        String name = p.getName();
        
        assertEquals("p.getName()", "Moshe", name);
    }
    
    public void test_passGetCountry()
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Moshe", "Germany", d);
        
        String country = p.getCountry();
        
        assertEquals("p.getCiuntry()", "Germany", country);
    }
    
    
    public void test_passGetNumber()  throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Moshe", "Germany", d);
        
        int expectedNumber = getIntValue(p,"number");
        int number = p.getNumber();
        
        assertEquals("p.getNumber()", expectedNumber, number);
    }
    
    public void test_passGetPassCounter() throws NoSuchFieldException, IllegalAccessException
    {
        
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Moshe", "Germany", d);
        
        int expectedPassCounter = getStaticIntValue(Passport.class,"passCounter");
        int passCounter = Passport.getPassCounter();
        
        assertEquals("p.getPassCounter()", expectedPassCounter, passCounter);
    }

    public void test_passGetExpiryDate() throws NoSuchFieldException, IllegalAccessException
    {
        
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Moshe", "Germany", d);
  
        Date actualDate = p.getExpiryDate();
        
        // check if date was copied
        assertTrue("p.getExpiryDate()", d != actualDate);
        
        // check date properties
        int day = getIntValue(actualDate,"day");
        int month = getIntValue(actualDate,"month");
        int year = getIntValue(actualDate, "year");  
        assertEquals("p.getExpiryDate((1, 2, 34)) - day", 1, day);
        assertEquals("p.getExpiryDate((1, 2, 34)) - month", 2, month);        
        assertEquals("p.getExpiryDate((1, 2, 34)) - year", 34, year);         
 
    }
  
    public void test_passToString() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Dana", "Israel", d);
        
        int number = getIntValue(p, "number");
        String expected = "Name: Dana Country: Israel number: " + number + " expiry: 1.2.34";
        
        assertEquals("p.toString(\"Dana\", \"Israel\", (1, 2, 34))",
                     expected, p.toString());
                     
    }

    public void tes_passIsActiveTrue()
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Dana", "Israel", d);
        
        Date now = new Date(23,11, 21);
        
        boolean b = p.isActive(now);
        
        assertEquals("p.isActive(1, 2, 34 - 23,11, 21)", true, b);
    }
    
    public void tes_passIsActiveFalse()
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Dana", "Israel", d);
        
        Date now = new Date(23,11, 36);
        
        boolean b = p.isActive(now);
        
        assertEquals("p.isActive(1, 2, 34 - 23,11, 36)", false, b);
    }
    
    public void tes_passSameCountryTrue()
    {
        Date d1 = new Date(1, 2, 34);
        Passport p1 = new Passport("Dana", "Israel", d1);

        Date d2 = new Date(5, 6, 78);
        Passport p2 = new Passport("Dani", "Israel", d2);
  
        boolean b = p1.sameCountry(p2);
        
        assertEquals("p1.sameCountry(p2)", true, b);
    }

    public void tes_passSameCountryFalse()
    {
        Date d1 = new Date(1, 2, 34);
        Passport p1 = new Passport("Dana", "Israel", d1);

        Date d2 = new Date(5, 6, 78);
        Passport p2 = new Passport("Dani", "IEngland", d2);
  
        boolean b = p1.sameCountry(p2);
        
        assertEquals("p1.sameCountry(p2)", false, b);
    }
    
    /************************** Traveler ******************/
    
    public void test_trvlrConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Passport.class, "passCounter", 0);
        Date d1 = new Date(1, 2, 34);
        Passport p1 = new Passport("Dana", "Israel", d1);
        Traveler t = new Traveler("Dana", p1, true);
        
        String name = (String)getObjectValue(t, "name");
        assertEquals("Traveler(\"Dana\", p, true) - name", "Dana", name);
        
        boolean isPaid = getBooleanValue(t,"isPaid");
        assertEquals("Traveler(\"Dana\", p, true) - isPaid", true, isPaid);
        
        // was passport copied
        Passport p = (Passport)getObjectValue(t, "pass");
        assertTrue("Traveler(\"Dana\", p, true) - passport copied", p != p1);
        
        // passport values copied
        Date dCopy = (Date)getObjectValue(p, "expiryDate");
        Date dOrig = (Date)getObjectValue(p1, "expiryDate");
        // check pass counter
        int passCounter = getStaticIntValue(Passport.class, "passCounter");
        assertEquals("Traveler(\"Dana\", p, true) - passCounter", 1, passCounter);
        
        /// check number
        int number = getIntValue(p, "number");
        assertEquals("Traveler(\"Dana\", p, true) - pass number ", 1, number);
        
        // check name and country
        String passName = (String)getObjectValue(p, "name");
        assertEquals("Traveler(\"Dana\", p, true) - pass  name", "Dana", passName);
        String country = (String)getObjectValue(p, "country");        assertEquals("Traveler(\"Dana\", p, true) - pass name", "Dana", name);
        assertEquals("Traveler(\"Dana\", p, true) - pass  country", "Israel", country);
        
        // check that date copied
        assertTrue("Traveler(\"Dana\", p, true) - pass - date copied", dOrig != dCopy);
        
        // check date        
        int day = getIntValue(dCopy,"day");
        int month = getIntValue(dCopy,"month");
        int year = getIntValue(dCopy, "year");
      
        assertEquals("Traveler(\"Dana\", p, true) - pass - date - day", 1, day);
        assertEquals("Traveler(\"Dana\", p, true) - pass - date - month", 2, month);
        assertEquals("Traveler(\"Dana\", p, true) - pass - date - year", 34, year);        
    }

    public void test_trvlrCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Passport.class, "passCounter", 0);
        Date dOrig = new Date(1, 2, 34);
        Passport pOrig = new Passport("Dana", "Israel", dOrig);
        Traveler tOrig = new Traveler("Dana", pOrig, true);
        Traveler tCopy = new Traveler(tOrig);
        
        String name = (String)getObjectValue(tCopy, "name");
        assertEquals("Traveler(t) - name", "Dana", name);
        
        boolean isPaid = getBooleanValue(tCopy,"isPaid");
        assertEquals("Traveler(t) - isPaid", true, isPaid);
        
        // was passport copied
        Passport pOrig1 = (Passport)getObjectValue(tOrig, "pass");
        Passport pCopy = (Passport)getObjectValue(tCopy, "pass");
        assertTrue("Traveler(t) - passport copied", pOrig1 != pCopy);
        
        // passport values copied
        Date dOrig1 = (Date)getObjectValue(pOrig, "expiryDate");
        Date dCopy = (Date)getObjectValue(pCopy, "expiryDate");
        // check pass counter
        int passCounter = getStaticIntValue(Passport.class, "passCounter");
        assertEquals("Traveler(t) - passCounter", 1, passCounter);
        
        /// check number
        int number = getIntValue(pCopy, "number");
        assertEquals("Traveler(t) - pass number ", 1, number);
        
        // check name and country
        String passName = (String)getObjectValue(pCopy, "name");
        assertEquals("Traveler(t) - pass  name", "Dana", passName);
        String country = (String)getObjectValue(pCopy, "country");        assertEquals("Traveler(\"Dana\", p, true) - pass name", "Dana", name);
        assertEquals("Traveler(t) - pass  country", "Israel", country);
        
        // check that date copied
        assertTrue("Traveler(\"Dana\", p, true) - pass - date copied", dOrig1 != dCopy);
        
        // check date        
        int day = getIntValue(dCopy,"day");
        int month = getIntValue(dCopy,"month");
        int year = getIntValue(dCopy, "year");
      
        assertEquals("Traveler(t) - pass - date - day", 1, day);
        assertEquals("Traveler(t) - pass - date - month", 2, month);
        assertEquals("Traveler(t) - pass - date - year", 34, year);        
    }  
    
    /******************** traveler setters ********************/
    
    public void test_trvlrSetName() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Dana", "Israel", d);
        Traveler t = new Traveler("Dana", p, true);
        
        t.setName("Dani");
        String name = (String)getObjectValue(t, "name");
        assertEquals("t.setName(\"Dani\")", "Dani", name);
    }
    
    public void test_trvlrSetIsPaid() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Dana", "Israel", d);
        Traveler t = new Traveler("Dana", p, true);
        
        t.setIsPaid(false);
        boolean b = getBooleanValue(t, "isPaid");
        assertEquals("t.setIsPaid(false)", false, b);
    }

    public void test_trvlrSetPassport() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Dana", "Israel", d);
        Traveler t = new Traveler("Dana", p, true);
        
        Date newD = new Date(2, 3, 45);
        Passport newP = new Passport("Dona", "England", newD);
        
        int passCounterOrig = getStaticIntValue(Passport.class, "passCounter");
        int passNumberNew = getIntValue(newP,"number"); 
        int passCounterNew = getStaticIntValue(Passport.class, "passCounter");
        t.setPass(newP);
        Passport actualP = (Passport)getObjectValue(t, "pass");
        
        // was passport copied?
        assertTrue("t.setPass(newP) - passport was not copied", newP != actualP);
        
        // were values copied
        String name = (String)getObjectValue(actualP, "name");
        String country = (String)getObjectValue(actualP, "country");
        Date expiryDate = (Date)getObjectValue(actualP, "expiryDate");
        int number = getIntValue(actualP, "number");
        int passCounter = getStaticIntValue(Passport.class, "passCounter");
        
        // check date        
        int day = getIntValue(expiryDate,"day");
        int month = getIntValue(expiryDate,"month");
        int year = getIntValue(expiryDate, "year");        
    
        assertEquals("t.setPass(newP) - pass name", "Dona", name);
        assertEquals("t.setPass(newP) - pass country", "England", country);
        assertEquals("t.setPass(newP) - pass number", passNumberNew, number);
        assertEquals("t.setPass(newP) - pass Counter", passCounterNew, passCounter);
        
        assertEquals("t.setPass(newP) - pass expDay - day", 2, day);
        assertEquals("t.setPass(newP) - pass expDay - month", 3, month);
        assertEquals("t.setPass(newP) - pass expDay - month", 45, year);
    }
    
    public void test_toSting() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Dana", "Israel", d);
        Traveler t = new Traveler("Dana", p, true);        
        int number = getIntValue(p, "number");
        
        String expected = "Name: Dana Paid: true *** Passport: *** Name: Dana Country: Israel number: " + number + " expiry: 1.2.34";
        
        assertEquals("t.toSting()", expected, t.toString());
    }
    
    public void test_canTravelFalseDate()
    {
        Date d = new Date(1, 2, 34);
        Passport p = new Passport("Dana", "Israel", d);
        Traveler t = new Traveler("Dana", p, true); 
        
        Date afterD = new Date(3, 3, 34);
        
        boolean b = t.canTravel(afterD);
        
        assertEquals("t(1, 2, 34).isValidPassport(3, 3, 34)", false, b);
    }

    public void test_canTravelFalsePaid()
    {
        Date d = new Date(1, 2, 42);
        Passport p = new Passport("Dana", "Israel", d);
        Traveler t = new Traveler("Dana", p, false); 
        
        Date afterD = new Date(3, 3, 34);
        
        boolean b = t.canTravel(afterD);
        
        assertEquals("t(1, 2, 34).isValidPassport(3, 3, 34)", false, b);
    }
    
    public void test_canTravelFalseAll()
    {
        Date d = new Date(1, 2, 32);
        Passport p = new Passport("Dana", "Israel", d);
        Traveler t = new Traveler("Dana", p, false); 
        
        Date afterD = new Date(3, 3, 34);
        
        boolean b = t.canTravel(afterD);
        
        assertEquals("t(1, 2, 34).isValidPassport(3, 3, 34)", false, b);
    }
    public void test_canTravelTrueAll()
    {
        Date d = new Date(2, 3, 34);
        Passport p = new Passport("Dana", "Israel", d);
        Traveler t = new Traveler("Dana", p, true); 
        
        Date beforeD = new Date(1, 3, 34);
        
        boolean b = t.canTravel(beforeD);
        
        assertEquals("t(2, 3, 34).isValidPassport(1, 3, 34)", true, b);
    }
    
    public void test_sameCountryTrue()
    {
        Date d1 = new Date(2, 3, 34);
        Passport p1 = new Passport("Dana", "Israel", d1);
        Traveler t1 = new Traveler("Dana", p1, true); 
        
        Date d2 = new Date(1, 2, 33);
        Passport p2 = new Passport("Dina", "Israel", d2);
        Traveler t2 = new Traveler("Dina", p2, false);
        
        boolean b = t1.sameCountry(t2);
        
        assertEquals("t.sameCountry()", true, b);
    }
    
    public void test_sameCountryFalse()
    {
        Date d1 = new Date(2, 3, 34);
        Passport p1 = new Passport("Dana", "Israel", d1);
        Traveler t1 = new Traveler("Dana", p1, true); 
        
        Date d2 = new Date(1, 2, 33);
        Passport p2 = new Passport("Daud", "Qatar", d2);
        Traveler t2 = new Traveler("Daud", p2, false);
        
        boolean b = t1.sameCountry(t2);
        
        assertEquals("t.sameCountry()", false, b);
    }
    
    /**************************** main **********************/
   public void test_mainSameCountry() throws NoSuchFieldException, IllegalAccessException
   {
        String stin = "1" + LS + "2" + LS + "17" + LS +
                        "Dani" + LS + "Israel" + LS + "true" + LS  +
                        "2" + LS + "3" + LS + "18" + LS +
                        "Dina" + LS + "Israel" + LS + "false" + LS +
                        "3" + LS + "2" + LS + "19" + LS +
                        "Dona" + LS + "Israel" + LS + "true" + LS +
                        "4" + LS + "2" + LS + "25" + LS +
                        "Dan" + LS + "Israel" + LS + "true" + LS +    
                        "5" + LS + "2" + LS + "24" + LS +
                        "Daud" + LS + "Israel" + LS + "true" + LS +                      
                        "6" + LS + "2" + LS + "19" + LS +
                        "Rina" + LS + "Israel" + LS + "false" + LS +                       
                        "7" + LS + "2" + LS + "20" + LS +
                        "Rona" + LS + "Israel" + LS + "true" + LS +
                        "8" + LS + "2" + LS + "16" + LS +
                        "Rony" + LS + "Israel" + LS + "false" + LS +                      
                        "9" + LS + "2" + LS + "30" + LS +
                        "Ron" + LS + "Israel" + LS + "true" + LS +
                        "10" + LS + "2" + LS + "25" + LS +
                        "Rinat" + LS + "Israel" + LS + "false" + LS;                        
        TravelerMain.reader = new Scanner (stin);
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        setStaticIntValue(Passport.class, "passCounter", 0);
        
        TravelerMain.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        
        String actual = removeMessage(st, "please enter day month year" + LS);
        actual = removeMessage(actual, "please enter name" + LS );
        actual = removeMessage(actual, "please enter country" + LS );
        actual = removeMessage(actual, "please enter paid" + LS );
 
        String expected = "Name: Dan Paid: true *** Passport: *** Name: Dan Country: Israel number: 4 expiry: 4.2.25" + LS  +
                          "Name: Daud Paid: true *** Passport: *** Name: Daud Country: Israel number: 5 expiry: 5.2.24" + LS  +
                          "Name: Ron Paid: true *** Passport: *** Name: Ron Country: Israel number: 9 expiry: 9.2.30" + LS ;
        
        expected += "all travelers from the same country" + LS ;
        assertEquals("Window Main(): ", expected, actual);        
    }

   public void test_mainFirstDifferentCountry() throws NoSuchFieldException, IllegalAccessException
   {
        String stin = "1" + LS  + "2" + LS  + "17" + LS  +
                        "Dani" + LS  + "Germany" + LS  + "true" + LS  +
                        "2" + LS  + "3" + LS  + "18" + LS  +
                        "Dina" + LS  + "Israel" + LS  + "false" + LS  +
                        "3" + LS  + "2" + LS  + "19" + LS  +
                        "Dona" + LS  + "Israel" + LS  + "true" + LS  +
                        "4" + LS  + "2" + LS  + "25" + LS  +
                        "Dan" + LS  + "Israel" + LS  + "true" + LS  +     
                        "5" + LS  + "2" + LS  + "24" + LS  +
                        "Daud" + LS  + "Israel" + LS  + "true" + LS  +                        
                        "6" + LS  + "2" + LS  + "19" + LS  +
                        "Rina" + LS  + "Israel" + LS  + "false" + LS  +                       
                        "7" + LS  + "2" + LS  + "20" + LS  +
                        "Rona" + LS  + "Israel" + LS  + "true" + LS  +
                        "8" + LS  + "2" + LS  + "16" + LS  +
                        "Rony" + LS  + "Israel" + LS  + "false" + LS  +                        
                        "9" + LS  + "2" + LS  + "30" + LS  +
                        "Ron" + LS  + "Israel" + LS  + "true" + LS  +
                        "10" + LS  + "2" + LS  + "25" + LS  +
                        "Rinat" + LS  + "Israel" + LS  + "false" + LS ;                        
        TravelerMain.reader = new Scanner (stin);
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        setStaticIntValue(Passport.class, "passCounter", 0);
        
        TravelerMain.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        
        String actual = removeMessage(st, "please enter day month year" + LS );
        actual = removeMessage(actual, "please enter name" + LS );
        actual = removeMessage(actual, "please enter country" + LS );
        actual = removeMessage(actual, "please enter paid" + LS );
 
        String expected = "Name: Dan Paid: true *** Passport: *** Name: Dan Country: Israel number: 4 expiry: 4.2.25" + LS  +
                          "Name: Daud Paid: true *** Passport: *** Name: Daud Country: Israel number: 5 expiry: 5.2.24" + LS  +
                          "Name: Ron Paid: true *** Passport: *** Name: Ron Country: Israel number: 9 expiry: 9.2.30" + LS ;
        
        assertEquals("Window Main(): ", expected, actual);        
    }
    
   public void test_mainLastDifferentCountry() throws NoSuchFieldException, IllegalAccessException
   {
        String stin = "1" + LS  + "2" + LS  + "17" + LS  +
                        "Dani" + LS  + "Israel" + LS  + "true" + LS  +
                        "2" + LS  + "3" + LS  + "18" + LS  +
                        "Dina" + LS  + "Israel" + LS  + "false" + LS  +
                        "3" + LS  + "2" + LS  + "19" + LS  +
                        "Dona" + LS  + "Israel" + LS  + "true" + LS  +
                        "4" + LS  + "2" + LS  + "25" + LS  +
                        "Dan" + LS  + "Israel" + LS  + "true" + LS  +     
                        "5" + LS  + "2" + LS  + "24" + LS  +
                        "Daud" + LS  + "Israel" + LS  + "true" + LS  +                        
                        "6" + LS  + "2" + LS  + "19" + LS  +
                        "Rina" + LS  + "Israel" + LS  + "false" + LS  +                       
                        "7" + LS  + "2" + LS  + "20" + LS  +
                        "Rona" + LS  + "Israel" + LS  + "true" + LS  +
                        "8" + LS  + "2" + LS  + "16" + LS  +
                        "Rony" + LS  + "Israel" + LS  + "false" + LS  +                        
                        "9" + LS  + "2" + LS  + "30" + LS  +
                        "Ron" + LS  + "Israel" + LS  + "true" + LS  +
                        "10" + LS  + "2" + LS  + "25" + LS  +
                        "Rinat" + LS  + "USA" + LS  + "false" + LS ;                        
        TravelerMain.reader = new Scanner (stin);
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        setStaticIntValue(Passport.class, "passCounter", 0);
        
        TravelerMain.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        
        String actual = removeMessage(st, "please enter day month year" + LS );
        actual = removeMessage(actual, "please enter name" + LS );
        actual = removeMessage(actual, "please enter country" + LS );
        actual = removeMessage(actual, "please enter paid" + LS );
 
        String expected = "Name: Dan Paid: true *** Passport: *** Name: Dan Country: Israel number: 4 expiry: 4.2.25" + LS  +
                          "Name: Daud Paid: true *** Passport: *** Name: Daud Country: Israel number: 5 expiry: 5.2.24" + LS  +
                          "Name: Ron Paid: true *** Passport: *** Name: Ron Country: Israel number: 9 expiry: 9.2.30" + LS ;
        
        assertEquals("Window Main(): ", expected, actual);        
    }
    
   public void test_mainMidDifferentCountry() throws NoSuchFieldException, IllegalAccessException
   {
        String stin = "1" + LS  + "2" + LS  + "17" + LS  +
                        "Dani" + LS  + "Israel" + LS  + "true" + LS  +
                        "2" + LS  + "3" + LS  + "18" + LS  +
                        "Dina" + LS  + "Israel" + LS  + "false" + LS  +
                        "3" + LS  + "2" + LS  + "19" + LS  +
                        "Dona" + LS  + "Israel" + LS  + "true" + LS  +
                        "4" + LS  + "2" + LS  + "25" + LS  +
                        "Dan" + LS  + "Jordan" + LS  + "true" + LS  +     
                        "5" + LS  + "2" + LS  + "24" + LS  +
                        "Daud" + LS  + "Israel" + LS  + "true" + LS  +                        
                        "6" + LS  + "2" + LS  + "19" + LS  +
                        "Rina" + LS  + "Israel" + LS  + "false" + LS  +                       
                        "7" + LS  + "2" + LS  + "20" + LS  +
                        "Rona" + LS  + "Israel" + LS  + "true" + LS  +
                        "8" + LS  + "2" + LS  + "16" + LS  +
                        "Rony" + LS  + "Israel" + LS  + "false" + LS  +                        
                        "9" + LS  + "2" + LS  + "30" + LS  +
                        "Ron" + LS  + "Israel" + LS  + "true" + LS  +
                        "10" + LS  + "2" + LS  + "25" + LS  +
                        "Rinat" + LS  + "USA" + LS  + "false" + LS ;                        
        TravelerMain.reader = new Scanner (stin);
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        setStaticIntValue(Passport.class, "passCounter", 0);
        
        TravelerMain.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        
        String actual = removeMessage(st, "please enter day month year" + LS );
        actual = removeMessage(actual, "please enter name" + LS );
        actual = removeMessage(actual, "please enter country" + LS );
        actual = removeMessage(actual, "please enter paid" + LS );
 
        String expected = "Name: Dan Paid: true *** Passport: *** Name: Dan Country: Jordan number: 4 expiry: 4.2.25" + LS  +
                          "Name: Daud Paid: true *** Passport: *** Name: Daud Country: Israel number: 5 expiry: 5.2.24" + LS  +
                          "Name: Ron Paid: true *** Passport: *** Name: Ron Country: Israel number: 9 expiry: 9.2.30" + LS ;
        
        assertEquals("Window Main(): ", expected, actual);        
    }
}
