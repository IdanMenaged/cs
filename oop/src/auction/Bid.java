package auction;

public class Bid {
    private Person bidder; // the person placing the bid
    private int value; // value of the bid

    /**
     * create a new bid
     * @param p person placing the
     * @param v value of the bid
     */
    public Bid(Person p, int v) {
        this.bidder = new Person(p);
        this.value = v;
    }

    /**
     * create a new bid by copying an existing one
     * @param bid existing bid
     */
    public Bid(Bid bid) {
        this.bidder = new Person(bid.bidder);
        this.value = bid.value;
    }

    /**
     * get bidder
     * @return bidder
     */
    public Person getBidder() {
        return this.bidder;
    }

    /**
     * get value
     * @return value
     */
    public int getValue() {
        return this.value;
    }
}
