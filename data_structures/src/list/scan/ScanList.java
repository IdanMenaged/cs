package list.scan;

import list.Node;
import list.ListExample;

public class ScanList {
    public static void main(String[] args) {
        Node<Integer> list = ListExample.buildList();
        System.out.println(zugiEzugi(list));
    }

    /**
     * sum even numbers in a list
     * @param list list of numbers
     * @return sum of even members of list
     */
    public static int sumEven(Node<Integer> list) {
        int sum = 0;
        while (list != null) {
            if (list.getValue() % 2 == 0) {
                sum += list.getValue();
            }
            list = list.getNext();
        }
        return sum;
    }

    /**
     * print biggest value in a list
     * @param list list of numbers
     */
    public static void maxList(Node<Integer> list) {
        if (list == null) {
            System.out.println("null\r"); // CRLF instead of LF
            return;
        }

        int biggest = list.getValue();
        while (list.hasNext()) {
            list = list.getNext();
            biggest = Math.max(biggest, list.getValue());
        }
        System.out.println(biggest + "\r"); // CRLF instead of LF
    }

    /**
     * make all elements of list positive
     * @param list list of numbers
     */
    public static void allPos(Node<Integer> list) {
        while (list != null) {
            list.setValue(Math.abs(list.getValue()));
            list = list.getNext();
        }
    }

    /**
     * are there more even or odd numbers in the list?
     * @param list list of numbers
     * @return 'z' for even, 'e' for odd, 's' if they are equal
     */
    public static char zugiEzugi(Node<Integer> list) {
        int evenCount = 0, oddCount = 0;
        while (list != null) {
            if (list.getValue() % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
            list = list.getNext();
        }

        if (evenCount > oddCount) {
            return 'z';
        }
        if (evenCount < oddCount) {
            return 'e';
        }
        return 's';
    }
}
