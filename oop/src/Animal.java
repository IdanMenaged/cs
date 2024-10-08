import org.hamcrest.Factory;

/**
 * Idan Menaged
 */

public class Animal {
    protected String name; // name of the animal
    protected char gender; // 'm' or 'f' for male and female
    protected int energy; // daily energy consumption

    // constructors
    public Animal(String name, char gender, int energy) {
        this.name = name;
        this.gender = gender;
        this.energy = energy;
    }

    public Animal(Animal animal) {
        this.name = animal.name;
        this.gender = animal.gender;
        this.energy = animal.energy;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    // getters

    public String getName() {
        return this.name;
    }

    public char getGender() {
        return this.gender;
    }

    public int getEnergy() {
        return this.energy;
    }

    // toString

    @Override
    public String toString() {
        return "* Animal name: " + this.name + " gender: " + this.gender + " energy: " + this.energy + " *";
    }

    // equals
    public boolean equals(Object other) {
        if (!(other instanceof Animal)) {
            return false;
        }

        Animal a = (Animal) other;
        return this.name.equals(a.name) && this.gender == a.gender && this.energy == a.energy;
    }
}
