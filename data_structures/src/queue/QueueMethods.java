package queue;

public class QueueMethods {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.insert(1);
        q.insert(2);
        q.insert(4);
        q.insert(1);

        oddEven(q);
        System.out.println(q);
    }

    /**
     * return sum of even elements
     * @param q queue
     * @return sum
     */
    public static int sumEven(Queue<Integer> q) {
        int sum = 0, n;

        q.insert(null);

        while (q.head() != null) {
            n = q.remove();
            if (n % 2 == 0) {
                sum += n;
            }
            q.insert(n);
        }

        q.remove();

        return sum;
    }

    /**
     * create a new queue with no repetitions
     * @param q queue
     * @return queue with no repetitions
     */
    public static Queue<Integer> noRepeat(Queue<Integer> q) {
        Queue<Integer> out = new Queue<>();
        int n;

        q.insert(null);

        while (q.head() != null) {
            n = q.remove();

            if (!isInQ(n, out)) {
                out.insert(n);
            }

            q.insert(n);
        }

        q.remove();

        return out;
    }

    /**
     * checks if a number is in queue
     * @param n number
     * @param q queue
     * @return true if n is in q
     */
    static boolean isInQ(int n, Queue<Integer> q) {
        int curr;
        boolean out = false;

        q.insert(null);

        while (q.head() != null) {
            curr = q.remove();

            if (curr == n) {
                out = true;
            }

            q.insert(curr);
        }

        q.remove();

        return out;
    }

    /**
     * modifies the queue so that first are the even numbers, then the odd
     * @param q a queue
     */
    public static void oddEven(Queue<Integer> q) {
        Queue<Integer> copy = copyQ(q);
        int n;

        // empty q
        while (!q.isEmpty()) {
            q.remove();
        }

        // add even
        copy.insert(null);
        while (copy.head() != null) {
            n = copy.remove();

            if (n % 2 == 0) {
                q.insert(n);
            } else {
                copy.insert(n);
            }
        }
        copy.remove();

        // add odd
        while (!copy.isEmpty()) {
            q.insert(copy.remove());
        }
    }

    /**
     * copies q into another queue
     * @param q a queue
     * @return new queue
     */
    static Queue<Integer> copyQ(Queue<Integer> q) {
        Queue<Integer> out = new Queue<>();
        int n;

        q.insert(null);

        while (q.head() != null) {
            n = q.remove();

            out.insert(n);

            q.insert(n);
        }

        q.remove();

        return out;
    }
}
