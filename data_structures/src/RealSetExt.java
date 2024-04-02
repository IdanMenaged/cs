/**
 * Idan Menaged
 */

public class RealSetExt {
    /**
     * @param rs a real set
     * @return a set identical to rs
     */
    public static RealSet clone(RealSet rs) {
        RealSet out = new RealSet(), rsCopy = new RealSet();
        int size = rs.size();

        // remove biggest and insert to both new sets
        for (int i = 0; i < size; i++) {
            double n = rs.findBiggest();
            rs.remove(n);
            out.insert(n);
            rsCopy.insert(n);
        }

        // repair rs
        for (int i = 0; i < size; i++) {
            double n = rsCopy.findBiggest();
            rsCopy.remove(n);
            rs.insert(n);
        }

        return out;
    }

    /**
     * @param rs a real set
     * @return a set with only the negative members of rs
     */
    public static RealSet buildNeg(RealSet rs) {
        RealSet out = new RealSet(), rsCopy = new RealSet();

        // remove biggest and insert to both new sets
        for (int i = 0; i < rs.size(); i++) {
            double n = rs.findBiggest();
            rs.remove(n);

            if (n < 0) {
                out.insert(n);
            }

            rsCopy.insert(n);
        }

        // repair rs
        for (int i = 0; i < rsCopy.size(); i++) {
            double n = rsCopy.findBiggest();
            rsCopy.remove(n);
            rs.insert(n);
        }

        return out;
    }
}
