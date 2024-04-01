/**
 * Idan Menaged
 */

public class GroupExt {
    /**
     * @param g1 a group
     * @param g2 another group
     * @return true if the 2 groups are identical (aka have the same members and the same number of members)
     */
    public static boolean identicalGroups(Group g1, Group g2) {
        Group g1Copy = g1.copyGroup(), g2Copy = g2.copyGroup();
        boolean out = true;

        // remove min number until one is empty
        while (!g1Copy.isEmpty() && !g2Copy.isEmpty()) {
            if (g1Copy.removeMin() != g2Copy.removeMin()) {
                out = false;
            }
        }

        // check there are no remaining elements
        if (!g1Copy.isEmpty() || !g2Copy.isEmpty()) {
            out = false;
        }

        return out;
    }

    /**
     * @param group a group
     * @return the median of group
     */
    public static double median(Group group) {
        int min = -1, max = -1;

        // each time remove the min and max element
        while (!group.isEmpty()) {
            min = group.removeMin();

            // if group is empty, the number of elements is odd
            if (group.isEmpty()) {
                return min;
            }
            else {
                max = group.removeMax();
            }
        }

         return (double) (min + max) / 2;
    }
}
