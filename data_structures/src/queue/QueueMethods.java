package queue;

import stackT.Stack;

public class QueueMethods {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.insert(3);
        q.insert(7);
        q.insert(5);
        q.insert(5);
        q.insert(5);
        q.insert(1);

        System.out.println(upDown(q));
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

    /**
     * create a new queue with items in reverse order
     * @param q queue
     * @return new queue
     */
    public static Queue<Integer> reverseA(Queue<Integer> q) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> out = new Queue<>();
        int n;

        // q to stack
        q.insert(null);
        while (q.head() != null) {
            n = q.remove();
            stack.push(n);
            q.insert(n);
        }
        q.remove();

        // stack to out
        while (!stack.isEmpty()) {
            out.insert(stack.pop());
        }

        return out;
    }

    /**
     * reverses q
     * @param q queue
     * @return q after it's been reversed
     */
    public static Queue<Integer> reverseB(Queue<Integer> q) {
        Stack<Integer> stack = new Stack<>();

        // q to stack
        while (!q.isEmpty()) {
            stack.push(q.remove());
        }

        // stack to q
        while (!stack.isEmpty()) {
            q.insert(stack.pop());
        }

        return q;
    }

    /**
     * create a new queue where for every pair of numbers in q, n1 and n2:
     * n1 == n2 => n1
     * n1 > n2 => all numbers from n1 to n2
     * n1 < n2 => all numbers from n1 to n2
     * @param q queue with even length
     * @return new queue
     */
    public static Queue<Integer> upDown(Queue<Integer> q) {
        Queue<Integer> out = new Queue<>();
        int n1, n2, i;

        q.insert(null);
        while (q.head() != null) {
            n1 = q.remove();
            n2 = q.remove();

            if (n1 == n2) {
                out.insert(n1);
            } else if (n1 > n2) {
                for (i = n1; i >= n2; i--) {
                    out.insert(i);
                }
            } else {
                for (i = n1; i <= n2; i++) {
                    out.insert(i);
                }
            }

            q.insert(n1);
            q.insert(n2);
        }
        q.remove();

        return out;
    }
}
