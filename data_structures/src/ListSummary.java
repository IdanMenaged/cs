///**
// * Idan Menaged
// */
//
//public class ListSummary {
//    public static void main(String[] args) {
//        Node<Integer> lst = new Node<>(1,
//                new Node<>(2,
//                        new Node<>(3)));
//        System.out.println(reverse(lst));
//    }
//
//    /**
//     * delete all occurrences of st from lst
//     * @param lst a list
//     * @param st a string
//     * @return modified list
//     */
//    public static Node<String> delAllSt(Node<String> lst, String st) {
//
//    /**
//     * count occurrences of st in lst
//     * @param lst a list
//     * @param st a string
//     * @return count of times st appears in lst
//     */
//    private static int countOccurrences(Node<String> lst, String st) {
//        int count = 0;
//        while (lst != null) {
//            if (lst.getValue().equals(st)) {
//                count++;
//            }
//
//            lst = lst.getNext();
//        }
//
//        return count;
//    }
//
//    /**
//     * deletes the first instance of x in lst
//     * @param lst a list
//     * @param x a string
//     * @return modified list
//     */
//    private static Node<String> deleteByValueFromList(Node<String> lst, String x)
//    {
//        // p - second node
//        // q - first node
//        Node<String> p = lst.getNext(), q = lst;
//
//
//        // edge case - x is the first value
//        if (lst.getValue().equals(x))
//        {
//            lst = lst.getNext();
//            return lst;
//        }
//
//        // go over lst until p == x or end of lst
//        while (p.hasNext() && !p.getValue().equals(x))
//        {
//            q = p;
//            p = p.getNext();
//        }
//
//        // delete the node with x
//        if (p.getValue().equals(x))
//        {
//            q.setNext(p.getNext());
//            p.setNext(null);
//        }
//        return lst;
//    }
//
//    /**
//     * create a list who's a reverse of the original list
//     * @param lst a list
//     * @return the new list
//     */
//    public static Node<Integer> reverse(Node<Integer> lst) {
//        /*
//        go over lst and change the values in a reverse order
//         */
//
//        // create out's skeleton
//        int len = length(lst);
//        Node<Integer> out = new Node<>();
//        Node<Integer> curr = out;
//        for (int i = 0; i < len - 1; i++) {
//            curr.setNext(new Node<>());
//            curr = curr.getNext();
//        }
//
//        // add values
//        int count = 0; // counts how far into lst we are
//        while (lst != null) {
//            // for each step taken in lst, go from the opposite direction on out
//            curr = out;
//            for (int i = 0; i < len - count - 1; i++) {
//                curr = curr.getNext();
//            }
//
//            // set the value
//            curr.setValue(lst.getValue());
//
//            lst = lst.getNext();
//            count++;
//        }
//
//        return out;
//    }
//
//    /**
//     * find out a list's length
//     * @param lst a list
//     * @return lst's length
//     */
//    private static int length(Node<Integer> lst) {
//        int out = 0;
//        while (lst != null) {
//            out++;
//            lst = lst.getNext();
//        }
//
//        return out;
//    }
//
//    /**
//     * count consistent sequences
//     * @param lst a list
//     * @return n of sequences
//     */
//    public static int consistentCount(Node<Integer> lst) {
//
//    }
//
//    /**
//     * checks if a sequence is consistent
//     * @param start the first node of the sequence
//     * @return true if sequence is consistent, else false
//     */
//    private boolean isConsistent(Node<Integer> start) {
//        Node<Integer> p = start.getNext(), q = start;
//        /*
//        2 options
//        1. one function to check if it is ascending, one for descending
//        2. check only first 2 and find a solution for equal numbers
//         */
//    }
//}
public class ListSummary {
    public static void main(String[] args) {

    }

    /**
     * delete all appearances of st
     * @param lst a list of strings
     * @param st a string
     * @return lst after modification
     */
    public static Node<String> DellAllSt(Node<String> lst, String st) {

    }
}