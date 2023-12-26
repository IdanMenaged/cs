package auction;

public class Auction {
    private static final int MAX_ITEMS = 100;

    private Item[] items; // all items
    private int current; // current n of items

    /**
     * constructor
     * sets items to an empty array of size MAX_ITEMS
     * sets current to 0
     */
    public Auction() {
        this.items = new Item[Auction.MAX_ITEMS];
        this.current = 0;
    }

    /**
     * add a new item
     * @param item new item
     * @return was action successful?
     */
    public boolean addItem(Item item) {
        if (this.current >= Auction.MAX_ITEMS) {
            return false;
        }

        this.items[this.current] = new Item(item);
        this.current++;

        return true;
    }
}
