//import java.util.*;
//import java.io.*;
//import java.nio.charset.*;
//import org.junit.Test;
//import org.junit.Ignore;
//
//import junit.framework.*;
//
//public class TestListSummary extends TestCase
//{
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
//    public static void test_delAllStStd()
//    {
//        String [] arr = {"bbb", "aaa", "ccc", "aaa", "bbb"};
//        Node<String> lst = generateList(arr);
//
//        String expected = "bbb-->ccc-->bbb-->Null";
//        Node<String> result = ListSummary.delAllSt(lst, "aaa");
//
//        assertEquals("delAllSt([\"bbb\", \"aaa\", \"ccc\", \"aaa\", \"bbb\"], \"aaa\")", expected, result.toString());
//    }
//
//    public static void test_delAllStDelFirst()
//    {
//        String [] arr = {"aaa", "bbb", "ccc", "aaa", "bbb"};
//        Node<String> lst = generateList(arr);
//
//        String expected = "bbb-->ccc-->bbb-->Null";
//        Node<String> result = ListSummary.delAllSt(lst, "aaa");
//
//        assertEquals("delAllSt([\"aaa\", \"bbb\", \"ccc\", \"aaa\", \"bbb\"], \"aaa\")", expected, result.toString());
//    }
//
//    public static void test_delAllStDelLast()
//    {
//        String [] arr = {"bbb", "aaa", "ccc", "aaa", "bbb", "aaa"};
//        Node<String> lst = generateList(arr);
//
//        String expected = "bbb-->ccc-->bbb-->Null";
//        Node<String> result = ListSummary.delAllSt(lst, "aaa");
//
//        assertEquals("delAllSt([\"bbb\", \"aaa\", \"ccc\", \"bbb\", \"aaa\"], \"aaa\")", expected, result.toString());
//    }
//
//    public static void test_delAllStFew()
//    {
//        String [] arr = {"bbb", "aaa", "aaa","ccc", "aaa", "bbb", "aaa"};
//        Node<String> lst = generateList(arr);
//
//        String expected = "bbb-->ccc-->bbb-->Null";
//        Node<String> result = ListSummary.delAllSt(lst, "aaa");
//
//        assertEquals("delAllSt([\"bbb\", \"aaa\", \"aaa\", \"ccc\", \"bbb\", \"aaa\"], \"aaa\")", expected, result.toString());
//    }
//
//    public static void test_delAllStFewStart()
//    {
//        String [] arr = {"aaa", "aaa", "aaa", "bbb", "aaa","ccc", "aaa", "bbb", "aaa"};
//        Node<String> lst = generateList(arr);
//
//        String expected = "bbb-->ccc-->bbb-->Null";
//        Node<String> result = ListSummary.delAllSt(lst, "aaa");
//
//        assertEquals("delAllSt([\"aaa\", \"aaa\", \"aaa\", \"bbb\", \"aaa\", \"ccc\", \"bbb\", \"aaa\"], \"aaa\")", expected, result.toString());
//    }
//
//    public static void test_delAllStFewEnd()
//    {
//        String [] arr = {"bbb", "aaa","ccc", "aaa", "bbb", "aaa", "aaa", "aaa"};
//        Node<String> lst = generateList(arr);
//
//        String expected = "bbb-->ccc-->bbb-->Null";
//        Node<String> result = ListSummary.delAllSt(lst, "aaa");
//
//        assertEquals("delAllSt([\"bbb\", \"aaa\", \"ccc\", \"bbb\", \"aaa\", \"aaa\", \"aaa\"], \"aaa\")", expected, result.toString());
//    }
//
//    public static void test_delAllStNoDelete()
//    {
//        String [] arr = {"bbb", "ccc", "bbb"};
//        Node<String> lst = generateList(arr);
//
//        String expected = "bbb-->ccc-->bbb-->Null";
//        Node<String> result = ListSummary.delAllSt(lst, "aaa");
//
//        assertEquals("delAllSt([\"bbb\", \"ccc\", \"bbb\"], \"aaa\")", expected, result.toString());
//    }
//
//     public static void test_delAllStDeleteAll()
//    {
//        String [] arr = {"aaa", "aaa", "aaa"};
//        Node<String> lst = generateList(arr);
//
//        String expected = null;
//        Node<String> result = ListSummary.delAllSt(lst, "aaa");
//
//        assertEquals("delAllSt([\"aaa\", \"aaa\"], \"aaa\")", expected, result);
//    }
//
//     public static void test_delAllStNulll()
//    {
//        String expected = null;
//        Node<String> result = ListSummary.delAllSt(null, "aaa");
//
//        assertEquals("delAllSt(null, \"aaa\")", expected, result);
//    }
//
//    // Q 2
//    public static void test_reverseStd()
//    {
//        Integer [] arr = {1, 2, 3};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "3-->2-->1-->Null";
//        Node<Integer> result = ListSummary.reverse(lst);
//
//        assertEquals("reverse([1, 2, 3])", expected, result.toString());
//    }
//
//    public static void test_reverseSingle()
//    {
//        Integer [] arr = {1};
//        Node<Integer> lst = generateList(arr);
//
//        String expected = "1-->Null";
//        Node<Integer> result = ListSummary.reverse(lst);
//
//        assertEquals("reverse([1])", expected, result.toString());
//    }
//
//    public static void test_reverseNull()
//    {
//
//        Node<Integer> expected = null;
//        Node<Integer> result = ListSummary.reverse(null);
//
//        assertEquals("reverse(null)", expected, result);
//    }
//
//    // Q 3
//    public static void test_consistentCountStd()
//    {
//        Integer [] arr = {1, 2, 3, -99, 6, 3, 2, -99, 3, 1, 6};
//        Node<Integer> lst = generateList(arr);
//
//        int expected = 2;
//        int result = ListSummary.consistentCount(lst);
//
//        assertEquals("reverse([1, 2, 3, -99, 6, 3, 2, -99, 3, 1])",
//                        expected, result);
//    }
//
//    public static void test_consistentCountNoConsistent()
//    {
//        Integer [] arr = {1, 9, 3, -99, 6, 9, 2, -99, 3, 1, 6};
//        Node<Integer> lst = generateList(arr);
//
//        int expected = 0;
//        int result = ListSummary.consistentCount(lst);
//
//        assertEquals("reverse([1, 2, 3, -99, 6, 3, 2, -99, 3, 1, 6])",
//                        expected, result);
//    }
//
//    public static void test_consistentCountAllConsistent()
//    {
//        Integer [] arr = {1, 2, 3, -99, 6, 3, 2, -99, 3, 5, 6};
//        Node<Integer> lst = generateList(arr);
//
//        int expected = 3;
//        int result = ListSummary.consistentCount(lst);
//
//        assertEquals("reverse([1, 2, 3, -99, 6, 3, 2, -99, 3, 5, 6])",
//                        expected, result);
//    }
//
//    // Q 4
//    public static void test_compare1Longer()
//    {
//        Integer [] arr1 = {2, 2, 3};
//        Node<Integer> lst1 = generateList(arr1);
//        Integer [] arr2 = {2, 2};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = 1;
//        int result = ListSummary.compare(lst1, lst2);
//
//        assertEquals("compare([2, 2, 3], [2,2])",
//                        expected, result);
//    }
//
//    public static void test_compare2Longer()
//    {
//        Integer [] arr1 = {2, 2};
//        Node<Integer> lst1 = generateList(arr1);
//        Integer [] arr2 = {2, 2, 3};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = 2;
//        int result = ListSummary.compare(lst1, lst2);
//
//        assertEquals("compare([2, 2], [2,2, 3])",
//                        expected, result);
//    }
//
//    public static void test_compareSameLength1Bigger()
//    {
//        Integer [] arr1 = {2, 2, 6};
//        Node<Integer> lst1 = generateList(arr1);
//        Integer [] arr2 = {2, 2, 3};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = 1;
//        int result = ListSummary.compare(lst1, lst2);
//
//        assertEquals("compare([2, 2, 6], [2, 2, 3])",
//                        expected, result);
//    }
//
//    public static void test_compareSameLength2Bigger()
//    {
//        Integer [] arr1 = {2, 2, 3};
//        Node<Integer> lst1 = generateList(arr1);
//        Integer [] arr2 = {2, 2, 6};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = 2;
//        int result = ListSummary.compare(lst1, lst2);
//
//        assertEquals("compare([2, 2, 3], [2, 2, 6])",
//                        expected, result);
//    }
//
//    public static void test_compareEqual()
//    {
//        Integer [] arr1 = {2, 2, 6};
//        Node<Integer> lst1 = generateList(arr1);
//        Integer [] arr2 = {2, 2, 6};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = 3;
//        int result = ListSummary.compare(lst1, lst2);
//
//        assertEquals("compare([2, 2, 6], [2, 2, 6])",
//                        expected, result);
//    }
//
//    // Q 5
//    public static void test_smallestCommonStd()
//    {
//        Integer [] arr1 = {1, 3, 5};
//        Node<Integer> lst1 = generateList(arr1);
//        Integer [] arr2 = {2, 3, 4};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = 3;
//        int result = ListSummary.smallestCommon(lst1, lst2);
//
//        assertEquals("smallestCommon([1, 3, 5], [2, 3, 4])",
//                        expected, result);
//    }
//
//    public static void test_smallestCommonFirst()
//    {
//        Integer [] arr1 = {1, 3, 5};
//        Node<Integer> lst1 = generateList(arr1);
//        Integer [] arr2 = {1, 3, 4, 5};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = 1;
//        int result = ListSummary.smallestCommon(lst1, lst2);
//
//        assertEquals("smallestCommon(1, 3, 5], [1, 3, 4, 5])",
//                        expected, result);
//    }
//
//    public static void test_smallestCommonLast()
//    {
//        Integer [] arr1 = {3, 5};
//        Node<Integer> lst1 = generateList(arr1);
//        Integer [] arr2 = {2,  4, 5};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = 5;
//        int result = ListSummary.smallestCommon(lst1, lst2);
//
//        assertEquals("smallestCommon([3, 5], [2, 4, 5])",
//                        expected, result);
//    }
//
//    public static void test_smallestCommonNoCommon()
//    {
//        Integer [] arr1 = {3, 5};
//        Node<Integer> lst1 = generateList(arr1);
//        Integer [] arr2 = {4, 6, 8};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = -999;
//        int result = ListSummary.smallestCommon(lst1, lst2);
//
//        assertEquals("smallestCommon([3, 5], [4, 6, 8])",
//                        expected, result);
//    }
//
//    public static void test_smallestCommonFirstNull()
//    {
//        Integer [] arr2 = {4, 6, 8};
//        Node<Integer> lst2 = generateList(arr2);
//
//        int expected = -999;
//        int result = ListSummary.smallestCommon(null, lst2);
//
//        assertEquals("smallestCommon(null, [4, 6, 8])",
//                        expected, result);
//    }
//
//    public static void test_smallestCommonSecondNull()
//    {
//        Integer [] arr1 = {4, 6, 8};
//        Node<Integer> lst1 = generateList(arr1);
//
//        int expected = -999;
//        int result = ListSummary.smallestCommon(lst1, null);
//
//        assertEquals("smallestCommon([4, 6, 8], null)",
//                        expected, result);
//    }
//}
