package recursion;

/**
 * Idan Menaged
 */

import queue.Queue;

public class QueueRec {
    /**
     * sums all even numbers in a queue
     * @param q queue
     * @return sum of even numbers in q
     */
    public static int sumEven(Queue<Integer> q) {
        // base case
        if (q.isEmpty()) {
            return 0;
        }

        int n = q.remove();
        return (n % 2 == 0 ? n : 0) + sumEven(q);
    }

    /**
     * reverses the order of a queue
     * @param q a queue
     */
    public static void upsideDown(Queue<String> q) {
        // base case
        if (q.isEmpty()) {
            return;
        }

        String s = q.remove();
        upsideDown(q);
        q.insert(s);
    }

    /**
     * switch the sign of each number in the q + reverse q order
     * @param q a queue
     */
    public static void posNeg(Queue<Double> q) {
        // base case
        if (q.isEmpty()) {
            return;
        }

        double n = q.remove();
        posNeg(q);
        q.insert(n * -1);
    }

    /**
     * move all elements from one queue to another and vice versa, order reversed
     * @param q1 one queue
     * @param q2 another queue
     */
    public static void switchQueues(Queue<Integer> q1, Queue<Integer> q2) {
        // base case
        if (q1.isEmpty() && q2.isEmpty()) {
            return;
        }

        int n1, n2;

        // only q2 has items left
        if (q1.isEmpty()) {
            n2 = q2.remove();
            switchQueues(q1, q2);
            q1.insert(n2);
        }
        // only q1 has items left
        else if (q2.isEmpty()) {
            n1 = q1.remove();
            switchQueues(q1, q2);
            q2.insert(n1);
        }
        else {
            n1 = q1.remove();
            n2 = q2.remove();
            switchQueues(q1, q2);
            q1.insert(n2);
            q2.insert(n1);
        }
    }
}
