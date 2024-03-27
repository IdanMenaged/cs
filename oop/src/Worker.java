/**
 * Idan Menaged
 */

public class Worker {
    private static final double MINIMAL_SALARY = 4000;

    protected String name; // name
    protected String id; // id number
    protected int number; // for identification within the organization

    protected static int counter = 1;

    // constructors
    public Worker(String name, String id) {
        this.name = name;
        this.id = id;
        this.number = Worker.counter;
        Worker.counter++;
    }

    public Worker(Worker w) {
        this.name = w.name;
        this.id = w.id;
        this.number = w.number;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void setCounter(int counter) {
        Worker.counter = counter;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // getters

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    public static int getCounter() {
        return Worker.counter;
    }

    public int getNumber() {
        return this.number;
    }

    // toString

    @Override
    public String toString() {
        return "Worker - name: " + this.name + ", id: " + this.id + ", number: " + this.number;
    }

    // methods

    /**
     * @return min salary
     */
    public double calSalary() {
        return Worker.MINIMAL_SALARY;
    }
}
