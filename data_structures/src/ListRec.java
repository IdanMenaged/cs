/**
 * Idan Menaged
 */

public class ListRec {
    /**
     * @param list a list
     * @return sum of even numbers in list
     */
    public static int sumEven(Node<Integer> list) {
        // break condition
        if (list == null) {
            return 0;
        }

        // inc sum
        if (list.getValue() % 2 == 0) {
            return list.getValue() + sumEven(list.getNext());
        }
        return sumEven(list.getNext());
    }

    /**
     * @param list a list
     * @param node a node
     * @return n of even numbers after node (including)
     */
    public static int evenAfterNode(Node<Integer> list, Node<Integer> node) {
        // break condition
        if (list == null) {
            return 0;
        }

        // find node
        if (list == node) {
            return countEven(list);
        }
        return evenAfterNode(list.getNext(), node);
    }

    /**
     * @param list a list
     * @return n of even numbers in list
     */
    private static int countEven(Node<Integer> list) {
        if (list == null) {
            return 0;
        }

        // inc count
        if (list.getValue() % 2 == 0) {
            return 1 + countEven(list.getNext());
        }
        return countEven(list.getNext());
    }

    /**
     * print the numbers in even indicies
     * @param list a list
     */
    public static void printEvenIndex(Node<Integer> list) {
        if (list == null || !list.hasNext()) {
            return;
        }

        System.out.println(list.getValue());
        printEvenIndex(list.getNext().getNext());
    }

    /**
     * @param list a list
     * @param p start node
     * @param q end node
     * @return sum of nodes from p to q inclusive
     */
    public static int p2q(Node<Integer> list, Node<Integer> p, Node<Integer> q) {
        // maybe p is not in list
        if (list == null) {
            return 0;
        }

        if (list == p) {
            return sumTo(list, q);
        }

        return p2q(list.getNext(), p, q);
    }

    /**
     * @param list a list
     * @param q a node
     * @return sum of numbers until q inclusive
     */
    private static int sumTo(Node<Integer> list, Node<Integer> q) {
        // maybe q not in list
        if (list == null) {
            return 0;
        }

        // last element
        if (list == q) {
            return q.getValue();
        }

        return list.getValue() + sumTo(list.getNext(), q);
    }

    /**
     * @param l1 first list
     * @param l2 second list
     * @return difference in length of l1 and l2
     */
    public static int listDiff(Node<Integer> l1, Node<Integer> l2) {
        // both are empty
        if (l1 == null && l2 == null) {
            return 0;
        }

        // if one is empty but not the other
        if (l1 == null) {
            return 1 + listDiff(l1, l2.getNext());
        }
        if (l2 == null) {
            return 1 + listDiff(l1.getNext(), l2);
        }

        // both still have elements
        return listDiff(l1.getNext(), l2.getNext());
    }
}
