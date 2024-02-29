/**
 * Idan Menaged
 */

public class Fish extends Animal {
    private int depth; // how deep can it go?

    private static final int MAX_DEPTH = 800; // deepest depth possible

    // constructors
    public Fish(String name, char gender, int energy, int depth) {
        super(name, gender, energy);
        this.depth = depth;
    }

    public Fish(Fish fish) {
        super(fish);
        this.depth = fish.depth;
    }

    // setters

    public void setDepth(int depth) {
        this.depth = depth;
    }

    // getters

    public int getDepth() {
        return this.depth;
    }

    public static int getMaxDepth() {
        return Fish.MAX_DEPTH;
    }

    // toString

    @Override
    public String toString() {
        return "** Fish " + super.toString() + " depth: " + this.depth + " **";
    }

    // methods

    /**
     * @return difference between depth of this fish and max depth for fish
     */
    public int diff() {
        return Fish.MAX_DEPTH - this.depth;
    }
}
