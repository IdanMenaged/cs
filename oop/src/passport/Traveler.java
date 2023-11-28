package passport;

public class Traveler {
    String name; // name of traveler
    Passport pass; // traveler's passport
    boolean isPaid; // whether traveler paid

    /**
     * creates a new traveler with the given attributes
     * @param name name
     * @param pass passport
     * @param isPaid whether traveler paid
     */
    public Traveler(String name, Passport pass, boolean isPaid) {
        this.name = name;
        this.pass = new Passport(pass);
        this.isPaid = isPaid;
    }

    /**
     * creates a new traveler with the attributes of the given one
     * @param t another traveler
     */
    public Traveler(Traveler t) {
        this.name = t.getName();
        this.pass = new Passport(t.getPass());
        this.isPaid = t.getIsPaid();
    }

    /**
     * set a new name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * set a new passport
     * @param pass new passport
     */
    public void setPass(Passport pass) {
        this.pass = new Passport(pass);
    }

    /**
     * set whether traveler paid
     *
     * @param isPaid whether traveler paid
     */
    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * get passport
     * @return passport
     */
    public Passport getPass() {
        return new Passport(this.pass);
    }

    /**
     * get whether traveler paid
     * @return whether traveler paid
     */
    public boolean getIsPaid() {
        return this.isPaid;
    }

    /**
     * formats a string for traveler
     * @return formatted string
     */
    public String toString() {
        return "Name: " + this.name + " Paid: " + this.isPaid + " *** Passport: *** " + this.pass;
    }

    /**
     * checks if trip is valid (passport is active and traveler has paid)
     * @param d the date of the trip
     * @return true if valid, otherwise false
     */
    public boolean canTravel(Date d) {
        return this.pass.isActive(d) && this.isPaid;
    }

    /**
     * checks if 2 travelers are from the same country
     * @param trvl another traveler
     * @return true if came country, otherwise false
     */
    public boolean sameCountry(Traveler trvl) {
        return this.pass.sameCountry(trvl.pass);
    }
}
