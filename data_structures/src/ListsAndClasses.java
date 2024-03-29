public class ListsAndClasses {
    /**
     * @param rolls a list of rolls
     * @return the most common roll
     */
    public static int backgammon(Node<Dice> rolls) {
        int[] counts = new int[6]; // index = roll - 1

        // init counts
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }

        // inc counters
        while (rolls != null) {
            counts[rolls.getValue().getRoll1() - 1]++;
            counts[rolls.getValue().getRoll2() - 1]++;

            rolls = rolls.getNext();
        }
        // find biggest counter
        int maxCount = 0, maxRoll = 0;
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > maxCount) {
                maxCount = counts[i];
                maxRoll = i + 1;
            }
        }
        return maxRoll;
    }

    /**
     * @param charNums a list of CharNums
     * @return a new list where for each CharNum its tav will appear num times
     */
    public static Node<Character> unpack(Node<CharNum> charNums) {
        Node<Character> out = new Node<>();
        Node<Character> curr = out;

        // go over the list
        while (charNums != null) {
            // for each object, add nodes
            for (int i = 0; i < charNums.getValue().getNum(); i++) {
                curr.setNext(new Node<>(charNums.getValue().getTav()));
                curr = curr.getNext();
            }

            charNums = charNums.getNext();
        }

        return out.getNext(); // the first node is just a placeholder
    }
}
