import javax.naming.InsufficientResourcesException;

/**
 * Idan Menaged
 */

public class CombinedDataStructures {
    /**
     * @param q an integer queue
     * @return min item in q. if q is empty, -999
     */
    public static int minQ(Queue<Integer> q) {
        if (q.isEmpty()) {
            return -999;
        }

        q.insert(null);

        int min = q.head();
        q.insert(q.remove());

        // go over q and update min
        while (q.head() != null) {
            min = Math.min(q.head(), min);
            q.insert(q.remove());
        }

        q.remove();
        return min;
    }

    /**
     * @param lst a list of queues of integers
     * @return min item between all queues in lst. if lst is empty, -999
     */
    public static int minList(Node<Queue<Integer>> lst) {
        if (lst == null) {
            return -999;
        }

        int min = minQ(lst.getValue());
        lst = lst.getNext();

        // go over list and find min
        while (lst != null) {
            min = Math.min(minQ(lst.getValue()), min);
            lst = lst.getNext();
        }

        return min;
    }

    /**
     * if first item is even => del all odd numbers
     * if first item is odd => del all even numbers
     * @param lst a list of natural numbers
     */
    public static void allSameParity(Node<Integer> lst) {
        if (lst == null) {
            return;
        }

        boolean firstEven = lst.getValue() % 2 == 0;

        if (firstEven) {
            delOdd(lst);
        }
        else {
            delEven(lst);
        }
    }

    /**
     * deletes all odd numbers in lst
     * @param lst list of integers
     */
    private static void delOdd(Node<Integer> lst) {
        Node<Integer> q = lst, p = lst.getNext();

        // go over list and del any odd number
        while (p != null && p.hasNext()) {
            if (p.getValue() % 2 != 0) {
                q.setNext(p.getNext());
                p.setNext(null);
                p = q.getNext();
            }
            else {
                q = q.getNext();
                p = p.getNext();
            }
        }

        if (p != null && p.getValue() % 2 != 0) {
            q.setNext(null);
        }
    }

    /**
     * deletes all even numbers in lst
     * @param lst list of integers
     */
    private static void delEven(Node<Integer> lst) {
        if (lst == null) {
            return;
        }

        Node<Integer> q = lst, p = lst.getNext();

        // go over list and delete every even number
        while (p != null && p.hasNext()) {
            if (p.getValue() % 2 == 0) {
                q.setNext(p.getNext());
                p.setNext(null);
                p = q.getNext();
            }
            else {
                q = q.getNext();
                p = p.getNext();
            }
        }

        if (p != null && p.getValue() % 2 == 0) {
            q.setNext(null);
        }
    }

    /**
     * call allSameParity and than move even to the start and odd to the end
     * @param q queue of lists of integers
     */
    public static void evenFirst(Queue<Node<Integer>> q) {
        Queue<Node<Integer>> evenQ = new Queue<>(), oddQ = new Queue<>();

        // go over q, call allSameParity, and insert in the correct queue
        while (!q.isEmpty()) {
            allSameParity(q.head());

            if (q.head().getValue() % 2 == 0) {
                evenQ.insert(q.head());
            }
            else {
                oddQ.insert(q.head());
            }

            q.remove();
        }

        // put all even queues back in
        while (!evenQ.isEmpty()) {
            q.insert(evenQ.remove());
        }
        // put all odd queues back in
        while (!oddQ.isEmpty()) {
            q.insert(oddQ.remove());
        }
    }

    /**
     * @param lst a list of strings
     * @return a concatenation of first and lst strings in lst. if lst is empty, an empty string. if lst length is one,
     * 2 times the first string
     */
    public static String lstToStr(Node<String> lst) {
        if (lst == null) {
            return "";
        }
        if (!lst.hasNext()) {
            return lst.getValue() + lst.getValue();
        }

        // get to the last node
        Node<String> last = lst;
        while (last.hasNext()) {
            last = last.getNext();
        }

        return lst.getValue() + last.getValue();
    }

    /**
     * @param lst a list of lists of integers
     * @return a list of strings where each item is the first and last strings in the sub list
     */
    public static Node<String> zipLst(Node<Node<String>> lst) {
        Node<String> out = new Node<>(), last = out;

        // go over list and create new nodes
        while (lst != null) {
            last.setNext(new Node<>(
                    lstToStr(lst.getValue())
            ));

            lst = lst.getNext();
            last = last.getNext();
        }

        return out.getNext();
    }
}
