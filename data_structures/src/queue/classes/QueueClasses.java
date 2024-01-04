package queue.classes;

import queue.Queue;

/*
Idan Menaged
 */

public class QueueClasses {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        for (int i = 0; i < 3; i++) {
            q.insert(i);
        }
        q.insert(1);
        System.out.println(howMany(q));
    }

    /**
     * count how many times a number appears in a q
     * @param q queue of integers
     * @param n number to count appearances of
     * @return number of times n appears in q
     */
    private static int countAppearances(Queue<Integer> q, int n) {
        // insert flag
        q.insert(null);

        int count = 0;

        // go over q
        while (q.head() != null) {
            if (q.head() == n) {
                count++;
            }

            // move to the back of the q
            q.insert(q.remove());
        }

        // remove flag
        q.remove();

        return count;
    }

    /**
     * find the best runner in a queue
     * @param q queue of runners
     * @return name of best runner
     */
    public static String bestRunner(Queue<Runner> q) {
        // edge case
        if (q.isEmpty()) {
            return "";
        }

        // insert flag
        q.insert(null);

        // init best
        Runner best = q.head();
        q.insert(q.remove());

        // go over q
        while (q.head() != null) {
            if (q.head().getSpeed() > best.getSpeed()) {
                best = q.head();
            }

            // move to the back of the queue
            q.insert(q.remove());
        }

        // remove flag
        q.remove();

        return best.getName();
    }

    /**
     * create a queue of pairs where each pair contains a number from the original queue and the number of times it
     * appears in the original queue
     * @param q queue of integers
     * @return queue of pairs based on the rules above
     */
    public static Queue<Pair> howMany(Queue<Integer> q) {
        // insert flag
        q.insert(null);

        // go over q
        Queue<Pair> out = new Queue<>();
        while (q.head() != null) {
            Pair pair = new Pair(q.head(), countAppearances(q, q.head())); // FIXME: null appears twice
            out.insert(pair);

            // move to the back of the q
            q.insert(q.remove());
        }

        // remove flag
        q.remove();

        return out;
    }
}
