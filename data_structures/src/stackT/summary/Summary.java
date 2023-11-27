package stackT.summary;

import stackT.Stack;

public class Summary {
    /**
     * swap the first half of a stack with the second
     * @param s a stack
     */
    public static void ChangeHalf(Stack<Character> s) {
        int length = 0, i;
        Stack<Character> top = new Stack<>(), bottom = new Stack<>();

        // edge case
        if (s.isEmpty()) {
            return;
        }

        // determine size
        while (!s.isEmpty()) {
            top.push(s.pop());
            length++;
        }

        // refill
        while (!top.isEmpty()) {
            s.push(top.pop());
        }

        // separate halves
        for (i = 0; i < length / 2; i++) {
            top.push(s.pop());
        }
        while (!s.isEmpty()) {
            bottom.push(s.pop());
        }

        // refill
        while (!top.isEmpty()) {
            s.push(top.pop());
        }
        while (!bottom.isEmpty()) {
            s.push(bottom.pop());
        }
    }

    /**
     * for a stack that's sorted up to a number n and then reverse-sorted, find n
     * @param s a stack fulfilling the requirements above
     * @return the number n as described above
     */
    public static int stackPick(Stack<Integer> s) {
        Stack<Integer> backup = new Stack<>();
        int curr, prev, n;
        boolean found = false;

        // edge case - s is empty
        if (s.isEmpty()) {
            return -1;
        }

        // find n
        prev = s.top();
        n = s.top();
        while (!s.isEmpty() && !found) {
            curr = s.pop();
            backup.push(curr);

            if (curr < prev) {
                n = prev;
                found = true;
            }

            prev = curr;
        }

        // edge case - all sorted normally
        if (!found) {
            n = backup.top();
        }

        // restore s
        while (!backup.isEmpty()) {
            s.push(backup.pop());
        }

        // return
        return n;
    }

    /**
     * insert a number in a sorted stack
     * @param s a sorted stack
     * @param num a number to be inserted
     * @return the stack
     */
    public static Stack<Double> putInPlace(Stack<Double> s, double num) {
        Stack<Double> backup = new Stack<>();
        boolean inserted = false;

        // edge case - s is empty
        if (s.isEmpty()) {
            s.push(num);
            return s;
        }

        // go over stack
        while (!s.isEmpty()) {
            if (s.top() < num && !inserted) {
                backup.push(num);
                inserted = true;
            }

            backup.push(s.pop());
        }

        // if was never inserted and is the smallest, put at the bottom
        if (!inserted && num <= backup.top()) {
                s.push(num);
        }

        // refill s
        while (!backup.isEmpty()) {
            s.push(backup.pop());
        }

        // if was never inserted and is the biggest, put at the top
        if (!inserted && num >= s.top()) {
            s.push(num);
        }

        // return
        return s;
    }

    /**
     * merge 2 sorted stacks into a single reverse sorted one
     * @param s1 a sorted stack
     * @param s2 a sorted stack
     * @return the new stack
     */
    public static Stack<Integer> mergeStacks(Stack<Integer> s1, Stack<Integer> s2) {
        Stack<Integer> backup1 = new Stack<>(), backup2 = new Stack<>(), out = new Stack<>();

        // go over both
        while (!s1.isEmpty() && !s2.isEmpty()) {
            if (s1.top() > s2.top()) {
                backup1.push(s1.top());
                out.push(s1.pop());
            } else if (s1.top() < s2.top()) {
                backup2.push(s2.top());
                out.push(s2.pop());
            } else {
                backup1.push(s1.top());
                backup2.push(s2.top());
                out.push(s1.pop());
                out.push(s2.pop());
            }
        }

        // go over remainders
        while (!s1.isEmpty()) {
            out.push(s1.top());
            backup1.push(s1.pop());
        }

        while (!s2.isEmpty()) {
            out.push(s2.top());
            backup2.push(s2.pop());
        }

        // return
        return out;
    }

    /**
     * determines whether the given string is a mirror
     * @param st a string
     * @return true if the string is a mirror, otherwise false
     */
    public static boolean isMirror(String st) {
        Stack<Character> stack = new Stack<>();
        int i;

        // check middle
        if (st.charAt(st.length() / 2) != 'c') {
            return false;
        }

        // go over first half
        for (i = 0; i < st.length() / 2; i++) {
            stack.push(st.charAt(i));
        }

        // compare with second half
        for (i = st.length() / 2 + 1; i < st.length(); i++) {
            if (st.charAt(i) != stack.pop()) {
                return false;
            }
        }

        return true;
    }

    /**
     * creates a stack that consists of all the characters in the original but a sequence of the same char only appears
     * as one char
     * @param s a stack
     * @return the new stack according to the requirements above
     */
    public static Stack<Character> zip(Stack<Character> s) {
        Stack<Character> backup = new Stack<>(), out = new Stack<>(), temp = new Stack<>();
        char sequenceChar;

        // edge case
        if (s.isEmpty()) {
            return out;
        }

        // go over s
        backup.push(s.top());
        temp.push(s.top());
        sequenceChar = s.pop();
        while (!s.isEmpty()) {
            if (s.top() != sequenceChar) {
                sequenceChar = s.top();
                temp.push(s.top());
            }
            backup.push(s.pop());
        }

        // reverse out
        while (!temp.isEmpty()) {
            out.push(temp.pop());
        }

        // refill s
        while (!backup.isEmpty()) {
            s.push(backup.pop());
        }

        return out;
    }
}