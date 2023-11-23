/**
 * Idan Menaged
 */

package stackT.summary;

import stackT.Stack;

public class Summary {
    /**
     * switches the first half of the stack with the second half
     * @param s a stack of characters
     */
    public static void ChangeHalf(Stack<Character> s) {
        Stack<Character> top = new Stack<>(), bottom = new Stack<>(), temp = new Stack<>();
        int len = 0, i;

        // find len
        while (!s.isEmpty()) {
            top.push(s.pop());
            len++;
        }

        // split into top and bottom
        for (i = 0; i < len / 2; i++) {
            temp.push(top.pop());
        }

        // reverse bottom
        while (!temp.isEmpty()) {
            bottom.push(temp.pop());
        }

        // refill og
        while (!top.isEmpty()) {
            s.push(top.pop());
        }
        while (!bottom.isEmpty()) {
            s.push(bottom.pop());
        }
    }

    /**
     * in the stack, up to a certain number it is sorted and afterwards it is sorted in reverse
     * finds the number where the switch occurs
     * @param s a stack
     * @return the number where the switch happens (-1 for an empty stack)
     */
    public static int stackPick(Stack<Integer> s) {
        Stack<Integer> temp = new Stack<>();
        int prev, curr, switchPoint;

        // edge case
        if (s.isEmpty()) {
            return -1;
        }

        // find curr
        prev = s.top();
        switchPoint = prev;
        temp.push(s.pop());

        while (!s.isEmpty()) {
            curr = s.top();
            temp.push(s.pop());

            if (curr < prev) {
                switchPoint = curr;
                break;
            }
        }

        // fix s
        while (!temp.isEmpty()) {
            s.push(temp.pop());
        }

        return switchPoint;
    }

    /**
     * puts a number in the right spot of a sorted stack
     * @param s a sorted stack
     * @param num num to be inserted
     * @return the updated stack
     */
    public static Stack<Double> putInPlace(Stack<Double> s, double num) {
        Stack<Double> temp = new Stack<>();
        int curr;

        while (!s.isEmpty()) {
            curr = s.pop();
        }
    }
}
