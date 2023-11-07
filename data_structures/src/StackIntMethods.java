import java.util.Scanner;

public class StackIntMethods {
    public static Scanner reader = new Scanner(System.in);

    /**
     * finds the biggest number in a stack
     * @param stack a stack of ints
     * @return biggest number in the stack
     */
    public static int maxStack(StackInt stack) {
        int max, n;
        StackInt temp = new StackInt();

        // edge case
        if (stack.isEmpty()) {
            return 0;
        }

        // find max
        max = stack.top();
        while (!stack.isEmpty()) {
            n = stack.pop();
            temp.push(n); // backup
            max = Math.max(n, max);
        }

        // repopulate stack
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return max;
    }

    /**
     * prints the smallest number in a stack
     * @param stack a stack if ints
     */
    public static void minStack(StackInt stack) {
        int min, n;
        StackInt temp = new StackInt();

        // edge case
        if (stack.isEmpty()) {
            System.out.print("empty\r\n"); // why does the tester want a CRLF I do not know
            return;
        }

        // find min
        min = stack.top();
        while (!stack.isEmpty()) {
            n = stack.pop();
            temp.push(n); // backup
            min = Math.min(n, min);
        }

        // repopulate stack
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        System.out.print(min + "\r\n");
    }

    /**
     * takes input from the user and builds a stack containing it
     * input ends when user inputs -999
     * @return a stack containing user inputs
     */
    public static StackInt buildStack() {
        StackInt stack = new StackInt();
        int input;

        // initial input
        System.out.print("enter a number\r\n");
        input = reader.nextInt();

        while (input != -999) {
            stack.push(input);
            System.out.print("enter a number\r\n");
            input = reader.nextInt();
        }

        return stack;
    }

    /**
     * print Plus if there are more positives than negatives
     * print Negative if there are more negatives than positives
     * print Equal if there is the same number of positives and negatives or if stack is empty
     * @param stack a stack of ints
     */
    public static void plusMinus(StackInt stack) {
        int posCount = 0, negCount = 0, n;
        StackInt temp = new StackInt();

        // edge case
        if (stack.isEmpty()) {
            System.out.print("Equal\r\n");
            return;
        }

        // count numbers
        while (!stack.isEmpty()) {
            n = stack.pop();

            temp.push(n); // backup

            if (n > 0) {
                posCount++;
            } else if (n < 0) {
                negCount++;
            }
        }

        // repopulate stack
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        // output
        if (posCount > negCount) {
            System.out.print("Plus\r\n");
        } else if (posCount < negCount) {
            System.out.print("Minus\r\n");
        }
        else {
            System.out.print("Equal\r\n");
        }
    }

    /**
     * @param stack a stack of ints
     * @return true if the stack is in ascending order from the bottom up, false otherwise
     */
    public static boolean isSorted(StackInt stack) {
        boolean out = true;
        int last, curr;
        StackInt temp = new StackInt();

        // edge case
        if (stack.isEmpty()) {
            return true;
        }

        // go over the stack
        last = stack.pop();
        temp.push(last); // backup
        while (!stack.isEmpty()) {
            curr = stack.pop();
            temp.push(curr); // backup


            if (curr > last) {
                out = false;
                break;
            }

            last = curr;
        }

        // repopulate stack
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }

        return out;
    }
}
