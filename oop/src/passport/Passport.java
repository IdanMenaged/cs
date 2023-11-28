package passport;

public class Passport {
    String name; // name on the passport
    int number; // the number on the passport
    String country; // the country where the passport was issued
    Date expiryDate; // the expiration date of the passport

    static int passCounter = 0; // number of passports in the system

    /**
     * creates a new passport based on params and updates passCounter
     * sets number according to pass counter
     * @param cName name on the passport
     * @param Country name of the country
     * @param expiryDate the expiration date
     */
    public Passport(String cName, String Country, Date expiryDate) {
        this.name = cName;
        this.country = Country;
        this.expiryDate = new Date(expiryDate);

        this.number = Passport.passCounter + 1;

        Passport.passCounter++;
    }

    /**
     * copies all info from pass into this
     * does not advance passCounter
     * @param pass another passport
     */
    public Passport(Passport pass) {
        this.name = pass.getName();
        this.number = pass.getNumber();
        this.country = pass.getCountry();
        this.expiryDate = new Date(pass.getExpiryDate());
    }

    /**
     * sets a new name
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets a new number
     * @param number new number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * sets a new country
     * @param country new country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * sets a new expiration date
     * @param expiryDate new date
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = new Date(expiryDate);
    }

    /**
     * sets a new pass counter
     * @param passCounter new pass counter
     */
    public static void setPassCounter(int passCounter) {
        Passport.passCounter = passCounter;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * get number
     * @return number
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * get country
     * @return country
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * get expiration date
     * @return expiration date
     */
    public Date getExpiryDate() {
        return new Date(this.expiryDate);
    }

    /**
     * get pass counter
     * @return pass counter
     */
    public static int getPassCounter() {
        return Passport.passCounter;
    }

    /**
     * formats the passport for print
     * @return formatted string
     */
    public String toString() {
        return "Name: " + this.name + " Country: " + this.country + " number: " + this.number + " expiry: " +
                this.expiryDate;
    }

    /**
     * checks if the passport is active for the given date
     * @param d a date
     * @return true if the passport is active, otherwise false
     */
    public boolean isActive(Date d) {
        return this.expiryDate.isOlder(d);
    }

    /**
     * checks if the 2 passports are from the same country
     * @param p another passport
     * @return true if same country, otherwise false
     */
    public boolean sameCountry(Passport p) {
        return this.country.equals(p.getCountry());
    }
}
