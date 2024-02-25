/**
 * Idan Menaged
 */

public class ListSummary {
    public static void main(String[] args) {
        Node<String> lst = new Node<>("a",
                new Node<>("b",
                        new Node<>("c",
                                new Node<>("b"))));
        System.out.println(delAllSt(lst, "b"));
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
}