package queue.classes;

import queue.Queue;

/*
Idan Menaged
 */

public class QueueClasses {
    public static void main(String[] args) {
        Queue<Runner> q = new Queue<>();
        for (int i = 0; i < 3; i++) {
            q.insert(new Runner("r" + i, i));
        }
        System.out.println(bestRunner(q));
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

        // insert null
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

        // remove null
        q.remove();

        return best.getName();
    }
}
