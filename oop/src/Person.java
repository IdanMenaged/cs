public class Person {
    private String name; // person's name
    private String id; // id number

    // constructors
    public Person(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Person(Person p) {
        this.name = p.name;
        this.id = p.id;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    // getters

    public String getName() {
        return this.name;
    }

    public String getId() {
        return this.id;
    }

    // toString

    @Override
    public String toString() {
        return "* Person name: " + this.name + " id: " + this.id + " *";
    }

    // equals

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Person)) {
            return false;
        }

        Person p = (Person) other;
        return this.name.equals(p.name) && this.id.equals(p.id);
    }
}
