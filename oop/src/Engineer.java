/**
 * Idan Menaged
 */
public class Engineer extends Worker {
    protected double salary; // monthly salary
    protected int degree; // 1st 2nd or 3rd degree
    protected int years; // years of experience

    // constructors
    public Engineer(String name, String id, double salary, int degree, int years) {
        super(name, id);
        this.salary = salary;
        this.degree = degree;
        this.years = years;
    }

    public Engineer(Engineer e) {
        super(e);
        this.salary = e.salary;
        this.degree = e.degree;
        this.years = e.years;
    }

    // setters

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void setYears(int years) {
        this.years = years;
    }

    // getters

    public double getSalary() {
        return this.salary;
    }

    public int getDegree() {
        return this.degree;
    }

    public int getYears() {
        return this.years;
    }

    // toString

    @Override
    public String toString() {
        return "Engineer - " + super.toString() + ", years: " + this.years + ", degree: " + this.degree + ", salary: "
                + this.salary;
    }

    // methods

    /**
     * @return monthly salary
     */
    @Override
    public double calSalary() {
        return this.salary;
    }
}
