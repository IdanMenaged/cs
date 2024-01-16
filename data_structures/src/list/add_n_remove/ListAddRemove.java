package list.add_n_remove;

import list.ListExample;
import list.Node;

/**
 * Idan Menaged
 */

public class ListAddRemove {
    public static void main(String[] args) {
        Node<Double> lst = new Node<>(1.0,
                new Node<>(2.0,
                        new Node<>(3.5)));
        System.out.println(isSeries(lst));
    }

    /**
     * check if list is a series. meaning the difference between each pair is the same
     * @param lst list
     * @return true if series, false if:
     * 1. list is not a series
     * 2. list is empty
     * 3. list has only 1 element
     */
    public static boolean isSeries(Node<Double> lst) {
        // edge case - list length <= 1
        if (lst == null || lst.getNext() == null) {
            return false;
        }

        boolean out = true;
        double diff = lst.getNext().getValue() - lst.getValue(); // define required difference

        lst = lst.getNext(); // advance to the next node

        while (lst.hasNext()) {
            if (lst.getNext().getValue() - lst.getValue() != diff) { // check difference
                return false;
            }

            lst = lst.getNext();
        }

        return true;
    }

    /**
     * add nodes to list so that the sum of each pair is equal to num
     * @param lst list
     * @param num number
     */
    public static void sumNum(Node<Integer> lst, int num) {
        while (lst != null) {
            Node<Integer> newNode = new Node<>(num - lst.getValue(),
                    lst.getNext());
            lst.setNext(newNode);

            lst = lst.getNext().getNext();  // 2 getNexts cuz we skip the newly added node
        }
    }

    /**
     * make a new list containing only the even numbers in og list
     * @param lst list
     * @return new list
     */
    public static Node<Integer> evenOnly(Node<Integer> lst) {
        Node<Integer> out = new Node<>();
        Node<Integer> last = out;

        while (lst != null) {
            if (lst.getValue() % 2 == 0) {
                // in case we have not added to the new list yet
                if (last.getValue() == null) {
                    last.setValue(lst.getValue());
                }
                else {
                    Node<Integer> newNode = new Node<>(lst.getValue());
                    last.setNext(newNode);
                    last = newNode;
                }
            }

            lst = lst.getNext();
        }

        // edge case - no even numbers
        // we should return null rather than null -> Null
        if (out.getValue() == null) {
            return null;
        }

        return out;
    }

    /**
     * count the number of times num appears in sequence (num -> num -> num is 1 sequence,
     * num -> something else -> num is 2)
     * @param lst list
     * @param num number
     * @return n of sequences
     */
    public static int countSequence(Node<Integer> lst, int num) {
        int count = 0;
        boolean inSequence = false; // is true when the current node is part of a sequence

        while (lst != null) {
            if (lst.getValue() == num) {
                if (!inSequence) { // only increase count if this is a new sequence
                    count++;
                    inSequence = true;
                }
            }
            else {
                inSequence = false; // end the sequence when we got to a number that is not num
            }

            lst = lst.getNext();
        }

        return count;
    }

    /**
     * shorten the list so that any sequence of the same number becomes just one instance of that number
     * i.e. 1 -> 1 -> 2 -> 3 => 1 -> 2 -> 3
     * @param lst list
     */
    public static void delSequence(Node<Integer> lst) {
        // edge case - list is null
        if (lst == null) {
            return;
        }

        Node<Integer> q = lst, p = lst.getNext();

        while (p != null) {
            if (p.getValue() == q.getValue()) {
                q.setNext(p.getNext()); // del p
                p.setNext(null); // clean up

                p = q.getNext(); // bring p to the new next
            }
            else {
                q = q.getNext();
                p = q.getNext();
            }
        }
    }
}
