/**
 * Idan Menaged
 */

public class ListRec {
    public static void main(String[] main) {
        Node<Integer> list = new Node<>(1,
                new Node<>(2,
                        new Node<>(3,
                                new Node<>(4))));
        Node<Integer> node = list.getNext().getNext();
        System.out.println(evenAfterNode(list, node));
    }

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
        if (list == null) {
            return;
        }
    }
}
