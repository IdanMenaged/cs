package stackT;

/*
Idan Menaged
 */

public class Methods {
    /**
     * switch the contents of 2 stacks
     * @param stack1 first stack
     * @param stack2 second stack
     */
    public static void switchStacks2(Stack<Character> stack1, Stack<Character> stack2) {
        Stack<Character> temp1 = new Stack<>(), temp2 = new Stack<>();

        while (!stack1.isEmpty()) {
            temp1.push(stack1.pop());
        }
        while (!stack2.isEmpty()) {
            temp2.push(stack2.pop());
        }

        while (!temp1.isEmpty()) {
            stack2.push(temp1.pop());
        }
        while (!temp2.isEmpty()) {
            stack1.push(temp2.pop());
        }
    }

    /**
     * switch the contents of 2 stacks using only one temp stack
     * @param stack1 first stack
     * @param stack2 second stack
     */
    public static void switchStacks1(Stack<Character> stack1, Stack<Character> stack2) {
        int stack2Len = 0, i;
        Stack<Character> temp = new Stack<>();

        while (!stack1.isEmpty()) {
            temp.push(stack1.pop());
        }
        while (!stack2.isEmpty()) {
            temp.push(stack2.pop());
            stack2Len++;
        }

        for (i = 0; i < stack2Len; i++) {
            stack1.push(temp.pop());
        }
        while (!temp.isEmpty()) {
            stack2.push(temp.pop());
        }
    }

    /**
     * generates a stack of the lengths of the strings in the given stack
     * @param stack a stack of strings
     * @return a stack with the lens of the strings
     */
    public static Stack<Integer> getStringLen(Stack<String> stack) {
        Stack<Integer> out = new Stack<>();
        Stack<String> temp = new Stack<>();
        String st;

        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }

        while (!temp.isEmpty()) {
            st = temp.pop();
            out.push(st.length());
            stack.push(st);
        }

        return out;
    }

    /**
     * move the highest number to the top of the stack
     * if the highest value appears more than twice move all occurrences to the top
     * @param stack a non-empty stack
     */
    public static void topMax(Stack<Integer> stack) {
        // you said it's not empty in the question but the test checks it so...
        if (stack.isEmpty()) {
            return;
        }

        int highestNum = stack.top(), occurrences = 0, n, i;
        Stack<Integer> temp = new Stack<>();

        while (!stack.isEmpty()) {
            n = stack.pop();

            if (n > highestNum) {
                highestNum = n;
                occurrences = 1;
            }
            else if (n == highestNum) {
                occurrences++;
            }

            temp.push(n);
        }

        while (!temp.isEmpty()) {
            n = temp.pop();

            if (n != highestNum) {
                stack.push(n);
            }
        }

        for (i = 0; i < occurrences; i++) {
            stack.push(highestNum);
        }
    }
}
