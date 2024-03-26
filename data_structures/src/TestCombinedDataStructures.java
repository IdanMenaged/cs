import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

public class TestCombinedDataStructures extends TestCase
{
    private <T> Queue<T> generateQueue(T [] values)
    {
        Queue<T> q = new Queue<T>();
        for (int i = 0; i < values.length; i++)
            q.insert(values[i]);
        return q;
    }

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

     private static <T> Node<T> generateList(ArrayList<T> values)
    {
        Node<T> lst = new Node<T>(values.get(0));
        Node<T> n = lst;
        Node<T> last = lst;
        for (int i = 1; i < values.size(); i++)
        {
            n = new Node<T>(values.get(i));
            last.setNext(n);
            last = n;
        }
        return lst;
    }

    /***********************************  minQ **********************************/
    public void test_minQStd()
    {
        Integer [] arr = {3, 2, 1, 4};
        Queue<Integer> q = generateQueue(arr);
        String origQ = q.toString();
        int res = CombinedDataStructures.minQ(q);
        int expected = 1;
        String resQ = q.toString();
        assertEquals("minQ([3, 2, 1, 4]): ", expected, res);
        assertEquals("original queue was distructed", origQ, resQ);
    }

    public void test_minQEmptyQ()
    {
        Queue<Integer> q = new Queue<Integer>();
        int res = CombinedDataStructures.minQ(q);
        int expected = -999;
        assertEquals("minQ([]): ", expected, res);
    }

     public void test_minQMinFirst()
    {
        Integer [] arr = {-3, 3, 2, 1, 4};
        Queue<Integer> q = generateQueue(arr);
        String origQ = q.toString();
        int res = CombinedDataStructures.minQ(q);
        int expected = -3;
        String resQ = q.toString();
        assertEquals("minQ([-3, 3, 2, 1, 4]): ", expected, res);
        assertEquals("original queue was distructed", origQ, resQ);
    }

     public void test_minQMinLast()
    {
        Integer [] arr = {3, 2, 1, 4, -7};
        Queue<Integer> q = generateQueue(arr);
        String origQ = q.toString();
        int res = CombinedDataStructures.minQ(q);
        int expected = -7;
        String resQ = q.toString();
        assertEquals("minQ([3, 2, 1, 4, -7]): ", expected, res);
        assertEquals("original queue was distructed", origQ, resQ);
    }

    /***********************************  minList **********************************/

     public void test_minListStd()
    {
        Integer [] arr1 = {3, 2, 1, 4, -7};
        Integer [] arr2 = {3, 2, -20, 4, -7};
        Integer [] arr3 = {3, 2, 22, 4, -7};

        Queue<Integer> q1 = generateQueue(arr1);
        Queue<Integer> q2 = generateQueue(arr2);
        Queue<Integer> q3 = generateQueue(arr3);

        ArrayList<Queue<Integer>> q_arr = new ArrayList<Queue<Integer>>(3);
        q_arr.add(q1);
        q_arr.add(q2);
        q_arr.add(q3);
        Node<Queue<Integer>> lst = generateList(q_arr);

        int res = CombinedDataStructures.minList(lst);
        int expected = -20;
        assertEquals("minList({[3, 2, 1, 4, -7], [3, 2, -20, 4, -7], [3, 2, 22, 4, -7]): ", expected, res);
    }

    public void test_minListEmpty()
    {
        Node<Queue<Integer>> lst = null;

        int res = CombinedDataStructures.minList(lst);
        int expected = -999;
        assertEquals("minList(null): ", expected, res);
    }

     public void test_minListFirst()
    {
        Integer [] arr1 = {3, 2, -22, 4, -7};
        Integer [] arr2 = {3, 2, -2, 4, -7};
        Integer [] arr3 = {3, 2, 22, 4, -7};

        Queue<Integer> q1 = generateQueue(arr1);
        Queue<Integer> q2 = generateQueue(arr2);
        Queue<Integer> q3 = generateQueue(arr3);

        ArrayList<Queue<Integer>> q_arr = new ArrayList<Queue<Integer>>(3);
        q_arr.add(q1);
        q_arr.add(q2);
        q_arr.add(q3);
        Node<Queue<Integer>> lst = generateList(q_arr);

        int res = CombinedDataStructures.minList(lst);
        int expected = -22;
        assertEquals("minList([3, 2, -22, 4, -7], [3, 2, -2, 4, -7], [3, 2, 22, 4, -7]): ", expected, res);
    }

     public void test_minListLast()
    {
        Integer [] arr1 = {3, 2, -1, 4, -7};
        Integer [] arr2 = {3, 2, -2, 4, -7};
        Integer [] arr3 = {3, 2, -28, 4, -7};

        Queue<Integer> q1 = generateQueue(arr1);
        Queue<Integer> q2 = generateQueue(arr2);
        Queue<Integer> q3 = generateQueue(arr3);

        ArrayList<Queue<Integer>> q_arr = new ArrayList<Queue<Integer>>(3);
        q_arr.add(q1);
        q_arr.add(q2);
        q_arr.add(q3);
        Node<Queue<Integer>> lst = generateList(q_arr);

        int res = CombinedDataStructures.minList(lst);
        int expected = -28;
        assertEquals("minList([3, 2, -1, 4, -7], [3, 2, -2, 4, -7], [3, 2, -28, 4, -7]): ", expected, res);
    }

     public void test_minSingle()
    {
        Integer [] arr = {3, 2, -1, 4, -7};

        Queue<Integer> q = generateQueue(arr);

        ArrayList<Queue<Integer>> q_arr = new ArrayList<Queue<Integer>>(1);
        q_arr.add(q);

        Node<Queue<Integer>> lst = generateList(q_arr);

        int res = CombinedDataStructures.minList(lst);
        int expected = -7;
        assertEquals("minList([3, 2, -1, 4, -7]): ", expected, res);
    }

     public void test_minEmptyQ()
    {

        Queue<Integer> q = new Queue<Integer> ();

        ArrayList<Queue<Integer>> q_arr = new ArrayList<Queue<Integer>>(1);
        q_arr.add(q);

        Node<Queue<Integer>> lst = generateList(q_arr);

        int res = CombinedDataStructures.minList(lst);
        int expected = -999;
        assertEquals("minList([3, 2, -1, 4, -7]): ", expected, res);
    }



    /***********************************  allSameParity **********************************/
     public void test_allSameParityStdOdd()
    {
        Integer [] arr = {3, 2, 1, 4, 7};

        Node<Integer> lst = generateList(arr);

        CombinedDataStructures.allSameParity(lst);
        String expected = "3-->1-->7-->Null";
        String actual = lst.toString();
        assertEquals("allSameParity([3, 2, 1, 4, 7]): ", expected, actual);
    }

     public void test_allSameParityStdEven()
    {
        Integer [] arr = {6, 2, 1, 4, 7};

        Node<Integer> lst = generateList(arr);

        CombinedDataStructures.allSameParity(lst);
        String expected = "6-->2-->4-->Null";
        String actual = lst.toString();
        assertEquals("allSameParity([6, 2, 1, 4, 7]): ", expected, actual);
    }

    public void test_allSameParityEmpty()
    {
        Node<Integer> lst = null;

        CombinedDataStructures.allSameParity(lst);
        String expected = null;
        String actual = null;
        assertEquals("allSameParity(null): ", expected, actual);
    }

     public void test_allSameParityEvenOnly()
    {
        Integer [] arr = {6, 2, 8, 4, 100};

        Node<Integer> lst = generateList(arr);

        CombinedDataStructures.allSameParity(lst);
        String expected = "6-->2-->8-->4-->100-->Null";
        String actual = lst.toString();
        assertEquals("allSameParity([6, 2, 8, 4, 100]): ", expected, actual);
    }

    public void test_allSameParityOddOnly()
    {
        Integer [] arr = {1, 3, 5, 7, 101};

        Node<Integer> lst = generateList(arr);

        CombinedDataStructures.allSameParity(lst);
        String expected = "1-->3-->5-->7-->101-->Null";
        String actual = lst.toString();
        assertEquals("allSameParity([1, 3, 5, 7, 101]): ", expected, actual);
    }

    public void test_allSameParitySingleOdd()
    {
        Integer [] arr = {1};

        Node<Integer> lst = generateList(arr);

        CombinedDataStructures.allSameParity(lst);
        String expected = "1-->Null";
        String actual = lst.toString();
        assertEquals("allSameParity([1]): ", expected, actual);
    }

    public void test_allSameParitySingleEven()
    {
        Integer [] arr = {10};

        Node<Integer> lst = generateList(arr);

        CombinedDataStructures.allSameParity(lst);
        String expected = "10-->Null";
        String actual = lst.toString();
        assertEquals("allSameParity([10]): ", expected, actual);
    }

    /***********************************  evenFirst **********************************/

    public void test_evenFirstStd()
    {
        Integer [] arr1 = {1, 2, 3};
        Node<Integer> lst1 = generateList(arr1);

        Integer [] arr2 = {4, 8, 7};
        Node<Integer> lst2 = generateList(arr2);

        Integer [] arr3 = {99, 100, 101};
        Node<Integer> lst3 = generateList(arr3);

        Queue<Node<Integer>> q = new Queue<Node<Integer>>();
        q.insert(lst1);
        q.insert(lst2);
        q.insert(lst3);
        String expected = "[4-->8-->Null,1-->3-->Null,99-->101-->Null]";

        CombinedDataStructures.evenFirst(q);
        String actual = q.toString();
        assertEquals("evenFirst([1-->2-->3, 4-->8-->7, 99-->100-->101])", expected, actual);
    }


    public void test_evenFirstEvenOnly()
    {
        Integer [] arr1 = {6, 2, 3};
        Node<Integer> lst1 = generateList(arr1);

        Integer [] arr2 = {4, 8, 7};
        Node<Integer> lst2 = generateList(arr2);

        Integer [] arr3 = {98, 100, 101};
        Node<Integer> lst3 = generateList(arr3);

        Queue<Node<Integer>> q = new Queue<Node<Integer>>();
        q.insert(lst1);
        q.insert(lst2);
        q.insert(lst3);
        String expected = "[6-->2-->Null,4-->8-->Null,98-->100-->Null]";

        CombinedDataStructures.evenFirst(q);
        String actual = q.toString();
        assertEquals("evenFirst([6-->2-->3, 4-->8-->7, 98-->100-->101])", expected, actual);
    }

    public void test_evenFirstOddOnly()
    {
        Integer [] arr1 = {1, 2, 3};
        Node<Integer> lst1 = generateList(arr1);

        Integer [] arr2 = {5, 8, 7};
        Node<Integer> lst2 = generateList(arr2);

        Integer [] arr3 = {99, 100, 101};
        Node<Integer> lst3 = generateList(arr3);

        Queue<Node<Integer>> q = new Queue<Node<Integer>>();
        q.insert(lst1);
        q.insert(lst2);
        q.insert(lst3);
        String expected = "[1-->3-->Null,5-->7-->Null,99-->101-->Null]";

        CombinedDataStructures.evenFirst(q);
        String actual = q.toString();
        assertEquals("evenFirst([1-->2-->3, 5-->8-->7, 99-->100-->101])", expected, actual);
    }

    public void test_evenFirstEmpty()
    {
        Queue<Node<Integer>> q = new Queue<Node<Integer>>();

        String expected = "[]";

        CombinedDataStructures.evenFirst(q);
        String actual = q.toString();
        assertEquals("evenFirst([1-->2-->3, 5-->8-->7, 99-->100-->101])", expected, actual);
    }

    /*********************************** lstToStr **********************************/

    public void test_lstToStrStd()
    {
        String [] arr = {"aa", "bb", "cc"};
        Node<String> lst = generateList(arr);

        String expected = "aacc";
        String actual = CombinedDataStructures.lstToStr(lst);

        assertEquals("test_lstToStr(\"aa\"-->\"bb\"-->\"cc\")", expected, actual);
    }

    public void test_lstToStrSingle()
    {
        String [] arr = {"ab"};
        Node<String> lst = generateList(arr);

        String expected = "abab";
        String actual = CombinedDataStructures.lstToStr(lst);

        assertEquals("test_lstToStr(\"ab\")", expected, actual);
    }

    public void test_lstToStr2Items()
    {
        String [] arr = {"ab", "cd"};
        Node<String> lst = generateList(arr);

        String expected = "abcd";
        String actual = CombinedDataStructures.lstToStr(lst);

        assertEquals("test_lstToStr(\"ab\"-->\"cd\")", expected, actual);
    }


    public void test_lstToStrempty()
    {
        Node<String> lst = null;

        String expected = "";
        String actual = CombinedDataStructures.lstToStr(lst);

        assertEquals("test_lstToStr(null)", expected, actual);
    }

    /*********************************** zipLst **********************************/
    public void test_zipLstStd()
    {
        String [] arr1 = {"aa", "bb", "cc"};
        String [] arr2 = {"dd", "ee", "ff"};
        String [] arr3 = {"gg", "hh", "ii"};

        Node<String> lst1 = generateList(arr1);
        Node<String> lst2 = generateList(arr2);
        Node<String> lst3 = generateList(arr3);

        ArrayList<Node<String>> arr = new ArrayList<Node<String>>(3);
        arr.add(lst1);
        arr.add(lst2);
        arr.add(lst3);
        Node<Node<String>> lst = generateList(arr);

        String expected = "aacc-->ddff-->ggii-->Null";
        Node<String> res = CombinedDataStructures.zipLst(lst);
        String actual = res.toString();

        assertEquals("zipLst(aa-->bb-->cc  -->  dd-->ee-->ff  -->  gg-->hh-->ii)", expected, actual);
    }

    public void test_zipLstSingleList()
    {
        String [] arr1 = {"ab"};


        Node<String> lst1 = generateList(arr1);


        ArrayList<Node<String>> arr = new ArrayList<Node<String>>(1);
        arr.add(lst1);

        Node<Node<String>> lst = generateList(arr);

        String expected = "abab-->Null";
        Node<String> res = CombinedDataStructures.zipLst(lst);
        String actual = res.toString();

        assertEquals("zipLst(lst-->ab)", expected, actual);
    }

    public void test_zipLstEmpty()
    {

        Node<Node<String>> lst = null;

        Node<String> expected = null;
        Node<String> actual = CombinedDataStructures.zipLst(lst);

        assertEquals("zipLst(null)", expected, actual);
    }

}
