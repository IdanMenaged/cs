//package list.add_n_remove;
//
//import java.util.*;
//import java.io.*;
//import java.nio.charset.*;
//import org.junit.Test;
//import org.junit.Ignore;
//
//import junit.framework.*;
//
//public class TestListAddRemove extends TestCase
//{
//
//    private static <T> Node<T> generateList(T [] values)
//    {
//        Node<T> lst = new Node<T>(values[0]);
//        Node<T> n = lst;
//        Node<T> last = lst;
//        for (int i = 1; i < values.length; i++)
//        {
//            n = new Node<T>(values[i]);
//            last.setNext(n);
//            last = n;
//        }
//        return lst;
//    }
//
//    // Q 1
//    public static void test_isSeriesTrue()
//    {
//        Double [] arr = {1.0, 1.5, 2.0, 2.5};
//        Node<Double> lst = generateList(arr);
//
//        boolean expected = true;
//        boolean res = ListAddRemove.isSeries(lst);
//
//        assertEquals("isSeries([1.0, 1.5, 2.0, 2.5]) ",
//                        expected, res);
//
//
//    }
//
//    public static void test_isSeriesFalse()
//    {
//        Double [] arr = {1.0, 1.5, 2.2, 2.5};
//        Node<Double> lst = generateList(arr);
//
//        boolean expected = false;
//        boolean res = ListAddRemove.isSeries(lst);
//
//        assertEquals("isSeries([1.0, 1.5, 2.2, 2.5]) ",
//                        expected, res);
//
//    }
//
//    public static void test_isSeriesNull()
//    {
//        boolean expected = false;
//        boolean res = ListAddRemove.isSeries(null);
//
//        assertEquals("isSeries(null) ",
//                        expected, res);
//
//    }
//
//    public static void test_isSeries1Item()
//    {
//        Double [] arr = {1.0};
//        Node<Double> lst = generateList(arr);
//
//        boolean expected = false;
//        boolean res = ListAddRemove.isSeries(lst);
//
//        assertEquals("isSeries([1.0]) ",
//                        expected, res);
//
//    }
//
//    public static void test_isSeriesNegative()
//    {
//        Double [] arr = {1.0, 0.5, 0.0, -0.5, -1.0};
//        Node<Double> lst = generateList(arr);
//
//        boolean expected = true;
//        boolean res = ListAddRemove.isSeries(lst);
//
//        assertEquals("isSeries([1.0, 0.5, 0.0, -0.5, -1.0]) ",
//                        expected, res);
//
//    }
//
//    // Q2
//    public static void test_sumNumStd()
//    {
//        Integer [] arr = {1, 2, 3};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->4-->2-->3-->3-->2-->Null";
//        ListAddRemove.sumNum(lst, 5);
//
//        assertEquals("sumNum([1, 2, 3]) ",
//                        expected, lst.toString());
//
//    }
//
//    public static void test_sumNumNegative()
//    {
//        Integer [] arr = {1, 2, 3};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->-2-->2-->-3-->3-->-4-->Null";
//        ListAddRemove.sumNum(lst, -1);
//
//        assertEquals("sumNum([1, 2, 3], -1) ",
//                        expected, lst.toString());
//
//    }
//
//    public static void test_sumNumPositiveNegative()
//    {
//        Integer [] arr = {1, 2, 5, 6};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->3-->2-->2-->5-->-1-->6-->-2-->Null";
//        ListAddRemove.sumNum(lst, 4);
//
//        assertEquals("sumNum([1, 2, 5, 6], 4) ",
//                        expected, lst.toString());
//
//    }
//
//    public static void test_sumNum1Item()
//    {
//        Integer [] arr = {1};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->3-->Null";
//        ListAddRemove.sumNum(lst, 4);
//
//        assertEquals("sumNum([1], 4) ",
//                        expected, lst.toString());
//
//    }
//
//    public static void test_sumNum1Null()
//    {
//
//        Node<Integer> expected = null;
//        ListAddRemove.sumNum(null, 4);
//
//        assertEquals("sumNum([1], 4) ",
//                        expected, null);
//
//    }
//
//    // Q 3
//    public static void test_evenOnlySTD()
//
//    {
//        Integer [] arr = {1, 2, 3, 4};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "2-->4-->Null";
//        Node<Integer> res = ListAddRemove.evenOnly(lst);
//
//        assertEquals("evenOnly([1, 2, 3, 4]) ",
//                        expected, res.toString());
//
//    }
//
//    public static void test_evenOnlyNoEven()
//    {
//        Integer [] arr = {1, 3, 11};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = null;
//        Node<Integer> res = ListAddRemove.evenOnly(lst);
//
//        assertEquals("evenOnly([11]) ",
//                        expected, res);
//
//    }
//
//     public static void test_evenOnlyNoOdd()
//    {
//        Integer [] arr = {2, 4, 6};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "2-->4-->6-->Null";
//        Node<Integer> res = ListAddRemove.evenOnly(lst);
//
//        assertEquals("evenOnly([2, 4, 6]) ",
//                        expected, res.toString());
//
//    }
//
//    public static void test_evenOnly1EvenOnly()
//    {
//        Integer [] arr = {2};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "2-->Null";
//        Node<Integer> res = ListAddRemove.evenOnly(lst);
//
//        assertEquals("evenOnly([2]) ",
//                        expected, res.toString());
//
//    }
//
//    public static void test_evenOnly1OddOnly()
//    {
//        Integer [] arr = {1};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = null;
//        Node<Integer> res = ListAddRemove.evenOnly(lst);
//
//        assertEquals("evenOnly([1]) ",
//                        expected, res);
//
//    }
//
//    public static void test_evenOnlyNull()
//    {
//        Node<Integer> expected = null;
//        Node<Integer> res = ListAddRemove.evenOnly(null);
//
//        assertEquals("evenOnly(null) ",
//                        expected, res);
//
//    }
//
//    public static void test_countSequenceStd()
//    {
//        Integer [] arr = {1, 1, 2, 1, 4, 1};
//        Node<Integer> lst = generateList(arr);
//
//        int expected = 3;
//        int res = ListAddRemove.countSequence(lst, 1);
//
//        assertEquals("countSequence([1, 1, 2, 1, 4, 1], 1) ",
//                        expected, res);
//
//    }
//
//    public static void test_countSequenceNoSeq()
//    {
//        Integer [] arr = {1, 1, 2, 1, 4, 1};
//        Node<Integer> lst = generateList(arr);
//
//        int expected = 0;
//        int res = ListAddRemove.countSequence(lst, 7);
//
//        assertEquals("countSequence([1, 1, 2, 1, 4, 1], 7) ",
//                        expected, res);
//
//    }
//
//    public static void test_countSequenceSingleNum()
//    {
//        Integer [] arr = {1, 2, 4};
//        Node<Integer> lst = generateList(arr);
//
//        int expected = 1;
//        int res = ListAddRemove.countSequence(lst, 1);
//
//        assertEquals("countSequence([1, 2, 4], 1) ",
//                        expected, res);
//
//    }
//
//        public static void test_countSequenceSingleSeq()
//    {
//        Integer [] arr = {1, 1, 1, 1};
//        Node<Integer> lst = generateList(arr);
//
//        int expected = 1;
//        int res = ListAddRemove.countSequence(lst, 1);
//
//        assertEquals("countSequence([1, 1, 1, 1], 1) ",
//                        expected, res);
//
//    }
//
//    public static void test_countSequenceNull()
//    {
//        int expected = 0;
//        int res = ListAddRemove.countSequence(null, 1);
//
//        assertEquals("countSequence(null, 1) ",
//                        expected, res);
//
//    }
//
//    // Q 5
//    public static void test_delSequenceStd()
//    {
//        Integer [] arr = {1, 1, 2, 2,2, 3,4,4,4};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->2-->3-->4-->Null";
//        ListAddRemove.delSequence(lst);
//
//        assertEquals("countSequence([1, 1, 2, 2,2, 3,4,4,4]) ",
//                        expected, lst.toString());
//    }
//
//    public static void test_delSequencenoSeq()
//    {
//        Integer [] arr = {1,2,3,4};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->2-->3-->4-->Null";
//        ListAddRemove.delSequence(lst);
//
//        assertEquals("countSequence([1,2,3,4]) ",
//                        expected, lst.toString());
//    }
//
//    public static void test_delSequence1Seq()
//    {
//        Integer [] arr = {1,1,1,1};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->Null";
//        ListAddRemove.delSequence(lst);
//
//        assertEquals("countSequence([1,1,1,1]) ",
//                        expected, lst.toString());
//    }
//
//    public static void test_delSequenceSingleStart()
//    {
//        Integer [] arr = {1,2,2, 3, 3};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->2-->3-->Null";
//        ListAddRemove.delSequence(lst);
//
//        assertEquals("countSequence([1,2,2, 3, 3]) ",
//                        expected, lst.toString());
//    }
//
//    public static void test_delSequenceSingleEnd()
//    {
//        Integer [] arr = {1,2,2, 3};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->2-->3-->Null";
//        ListAddRemove.delSequence(lst);
//
//        assertEquals("countSequence([,2,2, 3]) ",
//                        expected, lst.toString());
//    }
//
//    public static void test_delSequenceNull()
//    {
//
//        Node<Integer> expected = null, lst = null;
//        ListAddRemove.delSequence(lst);
//
//        assertEquals("countSequence([,2,2, 3]) ",
//                        expected, lst);
//    }
//}
