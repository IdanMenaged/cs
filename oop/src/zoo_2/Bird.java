package zoo_2;

/**
 * Idan Menaged
 */

public class Bird extends Animal {
    private int nest; // height in which it nests

    // constructors
    public Bird(String name, char gender, int energy, int nest) {
        super(name, gender, energy);
        this.nest = nest;
    }

    public Bird(Bird bird) {
        super(bird); // TODO: should i upcast?
        this.nest = bird.nest;
    }

    // setters

    public void setNest(int nest) {
        this.nest = nest;
    }

    // getters

    public int getNest() {
        return this.nest;
    }

    // toString

    @Override
    public String toString() {
        return "** Bird " + super.toString() + " nest: " + this.nest + " **";
    }
}
