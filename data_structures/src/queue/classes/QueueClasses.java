package queue.classes;

/*
Idan Menaged
 */

import queue.Queue;

public class QueueClasses {
    public static void main(String[] args) {
        Queue<Runner> q = new Queue<>();
        for (int i = 0; i < 3; i++) {
            q.insert(new Runner("r" + i, i));
        }
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

        // init best
        Runner best = q.head();
        q.insert(q.remove());

        // go over q
        q.insert(null);
        while (q.head() != null) {
            if (best.getSpeed() > q.head().getSpeed()) {
                best = q.head();
            }
        }

        // remove null
        q.remove();

        return best.getName();
    }
}
