/**
 * Idan Menaged
 */

public class Mammal extends Animal {
    protected int milk; // daily milk consumption

    protected static final int CAL_IN_MILK = 500; // n of calories in milk

    // constructors
    public Mammal(String name, char gender, int energy, int milk) {
        super(name, gender, energy);
        this.milk = milk;
    }

    public Mammal(Mammal mammal) {
        super(mammal);
        this.milk = mammal.milk;
    }

    // setters

    public void setMilk(int milk) {
        this.milk = milk;
    }

    // getters

    public int getMilk() {
        return this.milk;
    }

    public static int getCalInMilk() {
        return Mammal.CAL_IN_MILK;
    }

    // toString

    @Override
    public String toString() {
        return "** Mammal " + super.toString() + " milk: " + this.milk + " **";
    }

    // methods

    /**
     * @return n of calories the mammal consumes
     */
    public int calories() {
        return this.milk * Mammal.CAL_IN_MILK;
    }
}
