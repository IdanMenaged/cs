package queue.classes;

import java.util.*;
import java.io.*;

import junit.framework.*;

import java.lang.reflect.*;

import Queue;
public class TestQueueClasses extends TestCase
{

    private <T> Queue<T> generateQueue(T [] values)
    {
        Queue<T> q = new Queue<T>();
        for (int i = 0; i < values.length; i++)
            q.insert(values[i]);
        return q;
    }

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

     private Object getObjectValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        Object value = fld.get(o);

        return value;
    }

    private void setIntValue(Object o, String fldName, int val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        fld.set(o, val);

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


    // Q 1 bestRunner
    public void test_bestRunnerStd()
    {
        Runner[] arr = {new Runner("Ron", 7.5), new Runner("Rina", 10.0),
                         new Runner("Ronny", 8.5), new Runner("Ran", 9.2)};
        Queue<Runner> q = generateQueue(arr);
        String expected = "Rina";
        String res = QueueClasses.bestRunner(q);

        assertEquals("bestRunner([(Ron, 7.5), (Rina, 10.0),(Ronny, 8.5), (Ran, 9.2)]) ", expected, res);
    }


    public void test_bestRunnerSingle()
    {
        Runner[] arr = {new Runner("Ron", 7.5)};
        Queue<Runner> q = generateQueue(arr);
        String expected = "Ron";
        String res = QueueClasses.bestRunner(q);

        assertEquals("bestRunner([(Ron, 7.5)])", expected, res);
    }

    public void test_bestRunnerFirst()
    {
        Runner[] arr = {new Runner("Rona", 10.5), new Runner("Rina", 10.0),
                         new Runner("Ronny", 8.5), new Runner("Ran", 9.2)};

        Queue<Runner> q = generateQueue(arr);
        String expected = "Rona";
        String res = QueueClasses.bestRunner(q);

        assertEquals("bestRunner([(Rona, 10.5), (Rina, 10.0),(Ronny, 8.5), (Ran, 9.2)]) ",
                        expected, res);
    }

    public void test_bestRunnerLast()
    {
        Runner[] arr = {new Runner("Rona", 10.5), new Runner("Rina", 10.0),
                         new Runner("Ronny", 8.5), new Runner("Ran", 11.2)};

        Queue<Runner> q = generateQueue(arr);
        String expected = "Ran";
        String res = QueueClasses.bestRunner(q);

        assertEquals("bestRunner([(Rona, 10.5), (Rina, 10.0),(Ronny, 8.5), (Ran, 11.2)]) ",
                        expected, res);
    }


    // Q 2 howMany

    public void test_howManyStd()
    {
        Integer [] arr = {1,1,2,1,3,2,1};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Pair[] resArr = {new Pair(1, 4), new Pair(2, 2), new Pair(3, 1)};
        Queue<Pair> expectedResQ = generateQueue(resArr);
        String expectedRes = expectedResQ.toString();
        Queue<Pair> res = QueueClasses.howMany(q);

        assertEquals("howMany([1,1,2,1,3,2,1]) ", expectedRes, res.toString());

        assertEquals("howMany([1,1,2,1,3,2,1]): q corrupted",
                     expectedQ, q.toString());
   }


    public void test_howManySingle()
    {
        Integer [] arr = {1};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Pair[] resArr = {new Pair(1, 1)};
        Queue<Pair> expectedResQ = generateQueue(resArr);
        String expectedRes = expectedResQ.toString();
        Queue<Pair> res = QueueClasses.howMany(q);

        assertEquals("howMany([1]) ", expectedRes, res.toString());


        assertEquals("howMany([1]): q corrupted",
                     expectedQ, q.toString());
   }

    public void test_howManySingles()
    {
        Integer [] arr = {1, 2, 3, 4};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Pair[] resArr = {new Pair(1, 1), new Pair(2, 1),new Pair(3, 1),new Pair(4, 1)};
        Queue<Pair> expectedResQ = generateQueue(resArr);
        String expectedRes = expectedResQ.toString();
        Queue<Pair> res = QueueClasses.howMany(q);

        assertEquals("howMany([1, 2, 3, 4]) ", expectedRes, res.toString());

        assertEquals("howMany([1, 2, 3, 4]): q corrupted",
                     expectedQ, q.toString());
   }

    public void test_howMany4Single()
    {
        Integer [] arr = {1, 1, 1, 1};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Pair[] resArr = {new Pair(1, 4)};
        Queue<Pair> expectedResQ = generateQueue(resArr);
        String expectedRes = expectedResQ.toString();
        Queue<Pair> res = QueueClasses.howMany(q);

        assertEquals("howMany([1, 1, 1, 1]) ", expectedRes, res.toString());

        assertEquals("howMany([1, 1, 1, 1]): q corrupted",
                     expectedQ, q.toString());
   }

    public void test_howManyManyandSingle()
    {
        Integer [] arr = {1, 1, 1, 1, 2};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Pair[] resArr = {new Pair(1, 4), new Pair(2, 1)};
        Queue<Pair> expectedResQ = generateQueue(resArr);
        String expectedRes = expectedResQ.toString();
        Queue<Pair> res = QueueClasses.howMany(q);

        assertEquals("howMany([1, 1, 1, 1, 2]) ", expectedRes, res.toString());

        assertEquals("howMany([1, 1, 1, 1, 2]): q corrupted",
                     expectedQ, q.toString());
   }

    public void test_howManySingleandMany()
    {
        Integer [] arr = {2, 1, 1, 1, 1};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Pair[] resArr = {new Pair(2, 1), new Pair(1, 4)};
        Queue<Pair> expectedResQ = generateQueue(resArr);
        String expectedRes = expectedResQ.toString();
        Queue<Pair> res = QueueClasses.howMany(q);

        assertEquals("howMany([2, 1, 1, 1, 1]) ", expectedRes, res.toString());

        assertEquals("howMany([2, 1, 1, 1, 1]): q corrupted",
                     expectedQ, q.toString());
   }

    public void test_howManySingleMany()
    {
        Integer [] arr = {1, 1, 2, 1, 1};
        Queue<Integer> q = generateQueue(arr);
        String expectedQ = q.toString();
        Pair[] resArr = {new Pair(1, 4), new Pair(2, 1)};
        Queue<Pair> expectedResQ = generateQueue(resArr);
        String expectedRes = expectedResQ.toString();
        Queue<Pair> res = QueueClasses.howMany(q);

        assertEquals("howMany([1, 1, 2, 1, 1]) ", expectedRes, res.toString());

        assertEquals("howMany([1, 1, 2, 1, 1]): q corrupted",
                     expectedQ, q.toString());
   }

   // q 3 a
   public void test_customerConstructor()  throws NoSuchFieldException, IllegalAccessException
   {
       Customer c = new Customer("Danny", 30);

       String name = (String)getObjectValue(c, "name");
       int age = getIntValue(c, "age");
       Queue<Measurement> measures = (Queue<Measurement>)getObjectValue(c, "measures");

       assertEquals("Customer(Danny, 30) - name", "Danny", name);
       assertEquals("Customer(Danny, 30) - age", 30, age);
       assertTrue("Customer(Danny, 30) - measurements Queue - should not be null", measures != null);
       assertTrue("Customer(Danny, 30) - measurements Queue - should be empty", measures.isEmpty());
    }

   public void test_addMeasure()  throws NoSuchFieldException, IllegalAccessException
   {
       Customer c = new Customer("Danny", 30);
       c.addMeasure(3, 50.0);

       Queue<Measurement> measures = (Queue<Measurement>)getObjectValue(c, "measures");

       assertTrue("Customer(Danny, 30) - measurements Queue - should be not empty", !measures.isEmpty());

       Measurement m = measures.remove();

       assertEquals("Customer - addMeasure(3, 50.0) - month", 3, m.getMonth());
       assertEquals("Customer - addMeasure(3, 50.0) - weight", 50.0, m.getWeight());
    }

    public void test_bestMeasureFirst()
    {
       Customer c = new Customer("Danny", 30);
       c.addMeasure(1, 50.0);
       c.addMeasure(3, 48.0);
       c.addMeasure(4, 47.5);
       c.addMeasure(5, 47.0);

       double best = c.bestMeasure();

       assertEquals("bestMeasure([(1, 50.0), (3, 48.0), (4, 47.5), (5, 47.0)])", 1.0, best);
    }

    public void test_bestMeasureEmpty()
    {
       Customer c = new Customer("Danny", 30);


       double best = c.bestMeasure();

       assertEquals("bestMeasure([])", -999.0, best);
    }

    public void test_bestMeasureSingle()
    {
       Customer c = new Customer("Danny", 30);
       c.addMeasure(1, 50.0);

       double best = c.bestMeasure();

       assertEquals("bestMeasure([(1, 50.0)])", -999.0, best);
    }

    public void test_bestMeasureMiddle()
    {
       Customer c = new Customer("Danny", 30);
       c.addMeasure(1, 49.5);
       c.addMeasure(2, 49.0);
       c.addMeasure(4, 47.0);
       c.addMeasure(5, 46.5);

       double best = c.bestMeasure();

       assertEquals("bestMeasure([(1, 49.5), (2, 49.0), (4, 47.0), (5, 46.5)])", 1.0, best);

    }

    public void test_bestMeasureLast()
    {
       Customer c = new Customer("Danny", 30);
       c.addMeasure(1, 49.5);
       c.addMeasure(2, 49.0);
       c.addMeasure(4, 48.0);
       c.addMeasure(5, 46.0);

       double best = c.bestMeasure();

       assertEquals("bestMeasure([(1, 49.5), (2, 49.0), (4, 48.0), (5, 46.0)])", 2.0, best);

    }

    public void test_main()
    {
        String stin = "Rona" + LS + "32" + LS +
                       "1" + LS + "60.0" + LS +
                       "2" + LS + "59.0" + LS +
                       "4" + LS + "54.0" + LS +
                       "5" + LS + "52.0" + LS ;

        WeightWatchers.reader = new Scanner (stin);
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        WeightWatchers.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        String actual = removeMessage(st, "Please enter name" + LS);
        actual = removeMessage(actual, "Please enter age" + LS);
        actual = removeMessage(actual, "Please enter month" + LS);
        actual = removeMessage(actual, "Please enter weight" + LS);
        String expected = "Best decrease: 2.5" + LS;


        assertEquals("WeightWatchers Main(): ", expected, actual);
    }
}
