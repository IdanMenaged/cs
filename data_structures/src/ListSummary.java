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
        // p - second node
        // q - first node
        Node<String> p = lst.getNext(), q = lst;


        // edge case - st in first node
        if (lst.getValue().equals(st))
        {
            lst = lst.getNext();
        }

        // go over lst
        while (p.hasNext())
        {
            q = p;
            p = p.getNext();

            // del node
            if (p.getValue().equals(st))
            {
                q.setNext(p.getNext());
                p.setNext(null);
            }
        }

        return lst;
    }
}