package car;

/**
 * Idan Menaged
 */

public class Car extends Vehicle {
    protected int seats; // n of seats
    protected boolean media; // is there a media player?
    protected boolean defense; // is there a defense system

    // constructors
    public Car(String manufacturer, String model, int wheelsNum, String color, int seats, boolean media,
               boolean defense) {
        super(manufacturer, model, wheelsNum, color);
        this.seats = seats;
        this.media = media;
        this.defense = defense;
    }

    // setters

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setMedia(boolean media) {
        this.media = media;
    }

    public void setDefense(boolean defense) {
        this.defense = defense;
    }

    // getters

    public int getSeats() {
        return this.seats;
    }

    public boolean getMedia() {
        return this.media;
    }

    public boolean getDefense() {
        return this.defense;
    }

    // toString
    @Override
    public String toString() {
        return "Car{" +
                "seats=" + this.seats +
                ", media=" + this.media +
                ", defense=" + this.defense +
                ", manufacturer='" + this.manufacturer + '\'' +
                ", model='" + this.model + '\'' +
                ", wheelsNum=" + this.wheelsNum +
                ", color='" + this.color + '\'' +
                '}';
    }

    /**
     * increase the price of the car by 10% if it has media and defense systems
     * @param basePrice price before up-charging
     * @return price + up-charge if needed
     */
    public double upCharge(double basePrice) {
        return basePrice * (this.media && this.defense ? 1.1 : 1);
    }

    /**
     * @return is the car both red and has at least 4 seats
     */
    public boolean bigAndRed() {
        return this.seats >= 4 && this.color.equals("Red");
    }
}
