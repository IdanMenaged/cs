package news_paper;

import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

import java.lang.reflect.*;


public class TestNewsPaper extends TestCase
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
    
    public void test_DateCopyConstructor() throws NoSuchFieldException, IllegalAccessException
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
     /***************** Article ***********************/
     
     public void test_Constructor() throws NoSuchFieldException, IllegalAccessException
     {
         Article a = new Article("best writer", "news", 32);
         
        int num = getIntValue(a,"num");
        String headLine = (String)getObjectValue(a,"headLine");
        String writer = (String)getObjectValue(a,"writer");
        assertEquals("new Article(\"best writer\", \"news\", 32) - num", 32, num);        
        assertEquals("new Article(\"worst writer\", \"old news\" - headline", "news", headLine);         
        assertEquals("new Article(\"best writer\", \"news\", 32) - writer", "best writer", writer);         
        
    }
    
     public void test_ArticelCopyConstructor() throws NoSuchFieldException, IllegalAccessException
     {
        Article a1 = new Article("worst writer", "old news", 10);
        Article a2 = new Article(a1); 
        int num = getIntValue(a2,"num");
        String headLine = (String)getObjectValue(a2,"headLine");
        String writer = (String)getObjectValue(a2,"writer");
        assertEquals("new Article(\"worst writer\", \"old news\", 32) - num", 10, num);        
        assertEquals("new Article(\"worst writer\", \"old news\", 32) - headLine", "old news", headLine);         
        assertEquals("new Article(\"news\", \"best writer\", 32) - writer", "worst writer", writer);          
    }
   
    public void test_sameWriterTrue()
    {
       Article a1 = new Article("worst writer", "old news", 10);
       Article a2 = new Article("worst writer", "old news", 10);
    
       boolean b = a1.sameWriter(a2);
       
       assertEquals("sameWriter() - same", true, b);
    }
    
    public void test_sameWriterFalse()
    {
       Article a1 = new Article("worst writer", "old news", 10);
       Article a2 = new Article("best writer", "old news", 10);
    
       boolean b = a1.sameWriter(a2);
       
       assertEquals("sameWriter() -  not same", false, b);
    }
    
    /***************** News paper ***********************/
    
    private void assertArticle(Article a1, Article a2 , String title)throws NoSuchFieldException, IllegalAccessException
    {
       
        int num1 = getIntValue(a1,"num");
        String headLine1 = (String)getObjectValue(a1,"headLine");
        String writer1 = (String)getObjectValue(a1,"writer");
        
        int num2 = getIntValue(a2,"num");
        String headLine2 = (String)getObjectValue(a2,"headLine");
        String writer2 = (String)getObjectValue(a2,"writer");
        assertEquals(title + " - num", num1, num2);        
        assertEquals(title + "- headline", headLine1, headLine2);         
        assertEquals(title + " - writer", writer1, writer2);         

        assertTrue("NewsPaper (\"best paper\", 1.1.22, arts) - article copied", a1 != a2);
    }
        
    public void test_NewspaperConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1,1,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("e", "f", 3);
        
        Article [] arts = {a1, a2, a3};
        
        NewsPaper np = new NewsPaper ("best paper", d, arts);
        
        Date d1 = (Date) getObjectValue(np, "date");

       
        int day = getIntValue(d1,"day");
        int month = getIntValue(d1,"month");
        int year = getIntValue(d1, "year");
        
        assertEquals("NewsPaper (\"best paper\", 1.1.22, arts) - day", 1, day);
        assertEquals("NewsPaper (\"best paper\", 1.1.22, arts) - month", 1, month);
        assertEquals("NewsPaper (\"best paper\", 1.1.22, arts) - year", 22, year);
        
        assertTrue("NewsPaper (\"best paper\", 1.1.22, arts) - date copied", d != d1);
        
        String name = (String)getObjectValue(np, "name");
        assertEquals("NewsPaper (\"best paper\", 1.1.22, arts) - name", "best paper", name);
        
        
       Article [] arts1 = (Article [])getObjectValue(np, "articles");
       
       // compare articles
       for (int i = 0; i < arts.length; i++)
       {
           assertArticle(arts[i], arts1[i], "NewsPaper (\"best paper\", 1.1.22, arts) - article[" + i + "]" );
        }
        assertTrue("NewsPaper (\"best paper\", 1.1.22, arts) - array copied", arts != arts1);
        
        
    }

     public void test_NewspaperConstructorWithInput() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1,2,22);

        String stin = "writer1" + LS + "headline1" + LS + "111" + LS +
                      "writer2" + LS + "headline2" + LS + "222" + LS +
                      "writer3" + LS + "headline3" + LS + "333" + LS +
                      "writer4" + LS + "headline4" + LS + "444" + LS +
                      "writer5" + LS + "headline5" + LS + "555" + LS;
        NewsPaper.reader = new Scanner (stin);    
        NewsPaper np = new NewsPaper ("best paper", d, 5);
        
        Date d1 = (Date) getObjectValue(np, "date");

       
        int day = getIntValue(d1,"day");
        int month = getIntValue(d1,"month");
        int year = getIntValue(d1, "year");
        
        assertEquals("NewsPaper (\"best paper\", 1.2.22, 5) - day", 1, day);
        assertEquals("NewsPaper (\"best paper\", 1.2.22, 5) - month", 2, month);
        assertEquals("NewsPaper (\"best paper\", 1.2.22, 5) - year", 22, year);
        
        assertTrue("NewsPaper (\"best paper\", 1.2.22, arts) - date copied", d != d1);
        
        String name = (String)getObjectValue(np, "name");
        assertEquals("NewsPaper (\"best paper\", 1.1.22, arts) - name", "best paper", name);
        
        
       Article [] arts = (Article [])getObjectValue(np, "articles");
       
       // compare articles
       for (int i = 0; i < arts.length; i++)
       {
           int k = i + 1;
           Article a = new Article("writer" + k, "headline" + k, 111 * k);
           assertArticle(a, arts[i], "NewsPaper (\"best paper\", 1.2.22, 5) - article[" + i + "]" );
        }      
        
    }
    
    public void test_NewspaperCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1,3,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("e", "f", 3);
        
        Article [] arts = {a1, a2, a3};
        
        NewsPaper np = new NewsPaper ("best paper", d, arts);
        NewsPaper copied_np = new NewsPaper(np);
        
        Date d1 = (Date) getObjectValue(copied_np, "date");
        Date d2 = (Date) getObjectValue(np, "date");
       
        int day = getIntValue(d1,"day");
        int month = getIntValue(d1,"month");
        int year = getIntValue(d1, "year");
        
        assertEquals("copy NewsPaper (\"best paper\", 1.3.22, arts) - day", 1, day);
        assertEquals("copy NewsPaper (\"best paper\", 1.3.22, arts) - month", 3, month);
        assertEquals("copy NewsPaper (\"best paper\", 1.3.22, arts) - year", 22, year);
        
        assertTrue("copy NewsPaper (\"best paper\", 1.3.22, arts) - date copied", d2 != d1);
        
        String name = (String)getObjectValue(copied_np, "name");
        assertEquals("NewsPaper (\"best paper\", 1.3.22, arts) - name", "best paper", name);
        
        
       Article [] arts1 = (Article [])getObjectValue(copied_np, "articles");
       Article [] arts2 = (Article [])getObjectValue(np, "articles");
       // compare articles
       for (int i = 0; i < arts.length; i++)
       {
           assertArticle(arts2[i], arts1[i], "copy NewsPaper (\"best paper\", 1.3.22, arts) - article[" + i + "]" );
        }
        assertTrue("copy NewsPaper (\"best paper\", 1.3.22, arts) - array copied", arts2 != arts1);
    }
    
    public void test_sumNum()
    {
        Date d = new Date(1,4,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("e", "f", 3);
        
        Article [] arts = {a1, a2, a3};
        
        NewsPaper np = new NewsPaper ("best paper", d, arts);

        int sumNum = np.sumNum();
        
        assertEquals("newsPaper - sumNum()",6, sumNum);
    }
    
    public void test_writerAricle()
    {
        Date d = new Date(1,5,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("a", "f", 3);
        
        Article [] arts = {a1, a2, a3};
        
        NewsPaper np = new NewsPaper ("best paper", d, arts);

        int wa = np.writerArticle("a");
        
        assertEquals("newsPaper - writerArticle()",2, wa);
    }
    
    public void test_writerAricleNoArticle()
    {
        Date d = new Date(1,5,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("a", "f", 3);
        
        Article [] arts = {a1, a2, a3};
        
        NewsPaper np = new NewsPaper ("best paper", d, arts);

        int wa = np.writerArticle("nobody");
        
        assertEquals("newsPaper - writerArticle()",0, wa);
    }
    
    public void test_setArticle() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1,6,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("a", "f", 3);
        
        Article [] arts1 = {a1, a2, a3};
        
        NewsPaper np = new NewsPaper ("best paper", d, arts1);
        Article [] obj_arts = (Article [])getObjectValue(np, "articles");
        
        Article a4 = new Article ("aa", "bb", 1);
        Article a5 = new Article ("cc", "dd", 2);
   
        Article [] arts2 = {a4, a5, a3};        
        np.setArticles(arts2);
        
        
        Article [] set_arts = (Article [])getObjectValue(np, "articles");
       
       // compare articles
       for (int i = 0; i < set_arts.length; i++)
       {
           assertArticle(arts2[i], set_arts[i], "setArticles" );
        }
        assertTrue("setArticle - array copied", arts2 != set_arts);
        assertTrue("setArticle - array changed", obj_arts != set_arts);
    }  

    
    public void test_getArticle() throws NoSuchFieldException, IllegalAccessException
    {
        Date d = new Date(1,6,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("a", "f", 3);
        
        Article [] arts1 = {a1, a2, a3};
        
        NewsPaper np = new NewsPaper ("best paper", d, arts1);
        Article [] obj_arts = (Article [])getObjectValue(np, "articles");
             
        Article [] out_arts = np.getArticles();
       
       
       // compare articles
       for (int i = 0; i < out_arts.length; i++)
       {
           assertArticle(obj_arts[i], out_arts[i], "getArticles" );
        }
        assertTrue("getArticle - array copied", out_arts != obj_arts);
        
    }  
    /***************** Good News ***********************/
    public void test_betterPaperFirstBetter()
    {
        Date d1 = new Date(1,5,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("a", "f", 3);
        
        Article [] arts1 = {a1, a2, a3};
        
        NewsPaper np1 = new NewsPaper ("paper1", d1, arts1);
        
        Date d2 = new Date(1,5,22);
        Article a4 = new Article ("a", "b", 1);
        Article a5 = new Article ("c", "d", 1);
        Article a6 = new Article ("a", "f", 1);
        
        Article [] arts2 = {a4, a5, a6};
        
        NewsPaper np2 = new NewsPaper ("paper2", d2, arts2); 
        
        String name = GoodNews.betterPaper(np1, np2);
        
        assertEquals("betterPaper(\"paper1\" - 6 words, \"paper2\" - 3 words",
                      "paper1", name);
    }
    
    public void test_betterPaperSecondBetter()
    {
        Date d1 = new Date(1,5,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("a", "f", 3);
        
        Article [] arts1 = {a1, a2, a3};
        
        NewsPaper np1 = new NewsPaper ("paper1", d1, arts1);
        
        Date d2 = new Date(1,5,22);
        Article a4 = new Article ("a", "b", 4);
        Article a5 = new Article ("c", "d", 5);
        Article a6 = new Article ("a", "f", 6);
        
        Article [] arts2 = {a4, a5, a6};
        
        NewsPaper np2 = new NewsPaper ("paper2", d2, arts2); 
        
        String name = GoodNews.betterPaper(np1, np2);
        
        assertEquals("betterPaper(\"paper1\" - 6 words, \"paper2\" - 15 words",
                      "paper2", name);
    }
    
    public void test_betterPaperEqual()
    {
        Date d1 = new Date(1,5,22);
        Article a1 = new Article ("a", "b", 1);
        Article a2 = new Article ("c", "d", 2);
        Article a3 = new Article ("a", "f", 3);
        
        Article [] arts1 = {a1, a2, a3};
        
        NewsPaper np1 = new NewsPaper ("paper1", d1, arts1);
        
        Date d2 = new Date(1,5,22);
        Article a4 = new Article ("a", "b", 3);
        Article a5 = new Article ("c", "d", 2);
        Article a6 = new Article ("a", "f", 1);
        
        Article [] arts2 = {a4, a5, a6};
        
        NewsPaper np2 = new NewsPaper ("paper2", d2, arts2); 
        
        String name = GoodNews.betterPaper(np1, np2);
        
        assertEquals("betterPaper(\"paper1\" - 6 words, \"paper2\" - 6 words",
                      "paper1 paper2", name);
    }
    
    public void test_main()
    {
        String stinMain = "1" + LS + "2" + LS + "21" + LS + "paper1" + LS + "2" + LS +
                          "2" + LS + "3" + LS + "22" + LS + "paper2" + LS + "2" + LS;
        GoodNews.reader = new Scanner (stinMain);
        
        String stinNewsPaper = "a" + LS + "b" + LS + "1" + LS +
                               "c" + LS + "d" + LS + "2" + LS +
                               "e" + LS + "f" + LS + "3" + LS +
                               "g" + LS + "h" + LS + "4" + LS;
                              
        NewsPaper.reader = new Scanner (stinNewsPaper);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        String [] arr = {};
        GoodNews.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        
        String actual = removeMessage(st, "Please enter day" + LS);
        actual = removeMessage(actual, "Please enter month" + LS);
        actual = removeMessage(actual, "Please enter year" + LS);
        
        actual = removeMessage(actual, "Please enter name" + LS);
        actual = removeMessage(actual, "Please enter number of articles" + LS);
        actual = removeMessage(actual, "Please enter writer" + LS);
        actual = removeMessage(actual, "Please enter headline" + LS);
        actual = removeMessage(actual, "Please enter number of words" + LS);

        String expected = "better paper name: paper2" + LS;
        
        assertEquals("GoodNews Main(): ", expected, actual);        
    }
}
