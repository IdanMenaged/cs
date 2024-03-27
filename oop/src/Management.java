/**
 * Idan Menaged
 */
public class Management extends Engineer {
    private boolean car; // do they own a car?
    private double bonus; // yearly bonus

    // constructors
    public Management(String name, String id, double salary, int degree, int years, boolean car, double bonus) {
        super(name, id, salary, degree, years);
        this.car = car;
        this.bonus = bonus;
    }

    public Management(Management m) {
        super(m);
        this.car = m.car;
        this.bonus = m.bonus;
    }

    // setters

    public void setCar(boolean car) {
        this.car = car;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    // getters
    public boolean getCar() {
        return this.car;
    }

    public double getBonus() {
        return this.bonus;
    }

    // toString

    @Override
    public String toString() {
        return "Management - " + super.toString() + ", car: " + this.car + ", bonus: " + this.bonus;
    }

    // methods

    /**
     * @return monthly salary + bonus
     */
    @Override
    public double calSalary() {
        return this.salary + this.bonus;
    }
}
