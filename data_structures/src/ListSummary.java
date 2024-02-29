public class ListSummary {
    public static void main(String[] args) {
        Node<Integer> lst = new Node<>(1,
                new Node<>(2,
                        new Node<>(3,
                                new Node<>(-99,
                                        new Node<>(3,
                                                new Node<>(2,
                                                        new Node<>(1,
                                                                new Node<>(-99,
                                                                        new Node<>(1,
                                                                                new Node<>(3,
                                                                                        new Node<>(2)))))))))));
        System.out.println(consistentCount(lst));
    }

    /**
     * del all appearances of a string in a list
     * @param lst a list
     * @param st a string
     * @return lst after modification
     */
    public static Node<String> delAllSt(Node<String> lst, String st) {
        // edge case - st is first
        if (lst.getValue().equals(st)) {
            lst = lst.getNext();
        }

        // go over lst
        Node<String> q = lst, p = lst.getNext();
        while (p.hasNext()) {
            if (p.getValue().equals(st)) {
                q.setNext(p.getNext());
            }

            q = p;
            p = q.getNext();
        }

        // edge case = st is last
        if (p.getValue().equals(st)) {
            q.setNext(null);
        }

        return lst;
    }

    /**
     * @param lst a list
     * @return a new list in a reverse order
     */
    public static Node<Integer> reverse(Node<Integer> lst) {
        // deal w/ first element
        Node<Integer> out = new Node<>(lst.getValue());
        lst=  lst.getNext();

        // go over lst and each time create a new node and make it the head of out
        while (lst != null) {
            Node<Integer> temp = new Node<>(lst.getValue());
            temp.setNext(out);
            out = temp;

            lst = lst.getNext();
        }

        return out;
    }

    /**
     * a consistent sequence is one where it either goes up all the way or down all the way.
     * each sequence in a list is separated by the number -99.
     * @param lst a list
     * @return n of consistent sequences in lst
     */
    public static int consistentCount(Node<Integer> lst) {
        int count = 0;
        while (lst != null) {
            if (isIncreasing(lst)) {
                count++;
            }
            else if (isDecreasing(lst)) {
                count++;
            }

            while (lst != null && lst.getValue() != -99) {
                lst = lst.getNext();
            }
        }

        return count;
    }

    private static boolean isIncreasing(Node<Integer> lst) {
        while (lst.hasNext() && lst.getNext().getValue() != -99) {
            if (lst.getNext().getValue() < lst.getValue()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDecreasing(Node<Integer> lst) {
        while (lst.hasNext() && lst.getNext().getValue() != -99) {
            if (lst.getNext().getValue() > lst.getValue()) {
                return false;
            }
        }
        return true;
    }
}