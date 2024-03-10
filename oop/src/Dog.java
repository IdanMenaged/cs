/**
 * Idan Menaged
 */

public class Dog extends Mammal {
    private int bones; // n of bones it consumes per day

    // constructors
    public Dog(String name, char gender, int energy, int milk, int bones) {
        super(name, gender, energy, milk);
        this.bones = bones;
    }

    public Dog(Dog dog) {
        super(dog);
        this.bones = dog.bones;
    }

    // setters

    public void setBones(int bones) {
        this.bones = bones;
    }

    // getters

    public int getBones() {
        return this.bones;
    }

    // toString

    @Override
    public String toString() {
        return "*** Dog " + super.toString() + " bones: " + this.bones + " ***";
    }

    // methods

    /**
     * a good dog is one that has more than 5 bones and is female (kinda sexist but ok)
     * @return is dog a good dog?
     */
    public boolean goodDog() {
        return this.bones > 5 && this.gender == 'F';
    }
}
