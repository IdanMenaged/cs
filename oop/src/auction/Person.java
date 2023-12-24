package auction;

public class Person {
    private String name; // person's name

    /**
     * create a new person
     * @param name name
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * create a new person by copying an existing person
     * @param person another person
     */
    public Person(Person person) {
        this.name = person.name;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }
}
