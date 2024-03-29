
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

import java.lang.reflect.*;

public class TestListAndClasses extends TestCase
{
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
    public void test_backgammonStd()
    {
        Dice [] arr = {new Dice(1, 2), new Dice(2, 2), new Dice(2, 3)};
        Node<Dice> lst = generateList(arr);

        int expected = 2;
        int res = ListsAndClasses.backgammon(lst);

        assertEquals("backgammon([(1, 2), (2, 2), (2, 3)])", expected, res);
    }

    public void test_backgammonNull()
    {
        int expected = 0;
        int res = ListsAndClasses.backgammon(null);

        assertEquals("backgammon(Null)", expected, res);
    }

    public void test_unpackStd()
    {
        CharNum [] arr = {new CharNum('a', 3), new CharNum('b', 2)};
        Node<CharNum> lst = generateList(arr);

        String expected = "a-->a-->a-->b-->b-->Null";
        Node<Character> res = ListsAndClasses.unpack(lst);

        assertEquals("unpack(('a', 3), ('b', 2))" ,
                        expected, res.toString());

    }

    public void test_unpackSingle()
    {
        CharNum [] arr = {new CharNum('a', 1)};
        Node<CharNum> lst = generateList(arr);

        String expected = "a-->Null";
        Node<Character> res = ListsAndClasses.unpack(lst);

        assertEquals("unpack(('a', 1))" ,
                        expected, res.toString());

    }

    public void test_unpack3A()
    {
        CharNum [] arr = {new CharNum('a', 3)};
        Node<CharNum> lst = generateList(arr);

        String expected = "a-->a-->a-->Null";
        Node<Character> res = ListsAndClasses.unpack(lst);

        assertEquals("unpack(('a', 3))" ,
                        expected, res.toString());

    }

    public void test_unpack1A1B()
    {
        CharNum [] arr = {new CharNum('a', 1), new CharNum('b', 1)};
        Node<CharNum> lst = generateList(arr);

        String expected = "a-->b-->Null";
        Node<Character> res = ListsAndClasses.unpack(lst);

        assertEquals("unpack(('a', 1), ('b', 1))" ,
                        expected, res.toString());

    }

   public void test_unpack1A2B1C()
    {
        CharNum [] arr = {new CharNum('a', 1), new CharNum('b', 2), new CharNum('c', 1)};
        Node<CharNum> lst = generateList(arr);

        String expected = "a-->b-->b-->c-->Null";
        Node<Character> res = ListsAndClasses.unpack(lst);

        assertEquals("unpack(('a', 1), ('b', 2), ('c', 1))" ,
                        expected, res.toString());

    }

   public void test_BusRouteConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Station s1 = new Station(1, 2);
        Station s2 = new Station(3, 4);

        BusRoute br = new BusRoute(s1, s2);
        Node<Station> lst = (Node<Station >)getObjectValue(br,"route");

       int s1X = getIntValue(lst.getValue(), "x");
       int s1Y = getIntValue(lst.getValue(), "y");
       int s2X = getIntValue(lst.getNext().getValue(), "x");
       int s2Y = getIntValue(lst.getNext().getValue(), "y");

       assertEquals("new BusRoute(s1, s2) - s1x", 1, s1X);
       assertEquals("new BusRoute(s1, s2) - s1y", 2, s1Y);
       assertEquals("new BusRoute(s1, s2) - s2x", 3, s2X);
       assertEquals("new BusRoute(s1, s2) - s2y", 4, s2Y);
    }

   public void test_BusRouteAddStation() throws NoSuchFieldException, IllegalAccessException
    {
        Station s1 = new Station(1, 2);
        Station s2 = new Station(3, 4);
        Station s3 = new Station(5, 6);

        BusRoute br = new BusRoute(s1, s2);
        br.addStation(s3);

        Node<Station> lst = (Node<Station >)getObjectValue(br,"route");

       int s1X = getIntValue(lst.getValue(), "x");
       int s1Y = getIntValue(lst.getValue(), "y");
       int s2X = getIntValue(lst.getNext().getValue(), "x");
       int s2Y = getIntValue(lst.getNext().getValue(), "y");
       int s3X = getIntValue(lst.getNext().getNext().getValue(), "x");
       int s3Y = getIntValue(lst.getNext().getNext().getValue(), "y");

       assertEquals("new BusRoute(s1, s2) - s1x", 1, s1X);
       assertEquals("new BusRoute(s1, s2) - s1y", 2, s1Y);
       assertEquals("new BusRoute(s1, s2) - s2x", 3, s2X);
       assertEquals("new BusRoute(s1, s2) - s2y", 4, s2Y);
       assertEquals("new BusRoute(s1, s2) - s3x", 5, s3X);
       assertEquals("new BusRoute(s1, s2) - s3y", 6, s3Y);
    }

   public void test_BusRouteDistance() throws NoSuchFieldException, IllegalAccessException
    {
        Station s1 = new Station(1, 2);
        Station s2 = new Station(1, 4);
        Station s3 = new Station(3, 4);

        BusRoute br = new BusRoute(s1, s2);
        br.addStation(s3);
        double expected = 4.0;
        double res = br.routeLength();

       assertEquals("distance() - (1, 2), (1, 4), (3, 4)", expected, res);
    }

    public void test_BusRouteMain() throws NoSuchFieldException, IllegalAccessException
    {
       double expected = 12.077;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        String[] stam = {};
        Program.main(stam);

        String result = baos.toString();
        result = removeMessage(result, "route length: ");

        double res = Double.parseDouble(result);
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        assertTrue("BusRoute - main() + expected distance " + expected + " actual " + res,
                    Math.abs(expected - res) < 0.01);
    }
}

