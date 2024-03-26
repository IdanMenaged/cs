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

        while (q.head() != null) {
            min = Math.min(q.head(), min);
            q.insert(q.remove());
        }

        q.remove()
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
    public static void allSamePrity(Node<Integer> lst) {
        boolean firstEven = lst.getValue() % 2 == 0;

        if (firstEven) {

        }
    }

    private static void delOdd(Node<Integer> lst) {
        Node<Integer> q = lst, p = lst.getNext();

        while (p != null && p.hasNext()) {
            if (p.getValue() % 2 != 0) {
                q.setNext(p.getNext());
                q.setNext(null);
            }
        }

        if (p != null && p.getValue() % 2 != 0) {
            q.setNext(null);
        }
    }
}
