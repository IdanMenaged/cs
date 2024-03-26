/**
 * Idan Menaged
 */
public class Administration extends Worker {
    private double hours; // hours worked
    private double payPerHour; // paycheck per hour

    // constructors
    public Administration(String name, String id, double hours, double payPerHour) {
        super(name, id);
        this.hours = hours;
        this.payPerHour = payPerHour;
    }

    public Administration(Administration a) {
        super(a);
        this.hours = a.hours;
        this.payPerHour = a.payPerHour;
    }

    // setters

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setPayPerHour(double payPerHour) {
        this.payPerHour = payPerHour;
    }

    // getters

    public double getHours() {
        return this.hours;
    }

    public double getPayPerHour() {
        return this.payPerHour;
    }

    // toString

    @Override
    public String toString() {
        return "Administration - " + super.toString() + ", hours: " + this.hours + ", payPerHour: " + this.payPerHour;
    }

    // methods

    /**
     * @return hours * payPerHour
     */
    @Override
    public double calSalary() {
        return this.hours * this.payPerHour;
    }
}
