/**
 * Idan Menaged
 */

public class Worker {
    private static final double MIN_SALARY = 4000;

    protected String name; // name
    protected String id; // id number
    protected int number; // for identification within the organization

    private static int count = 0;

    // constructors
    public Worker(String name, String id) {
        this.name = name;
        this.id = id;
        this.number = Worker.count;
        Worker.count++;
    }

    public Worker(Worker w) {
        this.name = w.name;
        this.id = w.id;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void setCount(int count) {
        Worker.count = count;
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

    public static int getCount() {
        return Worker.count;
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
        return Worker.MIN_SALARY;
    }
}
