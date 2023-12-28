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

    /**
     * add a bid
     * @param itemIndex index of the bidded item
     * @param bidder person placing the bid
     * @param value sum of the bid
     * @return was bid accepted?
     */
    public boolean addBid(int itemIndex, Person bidder, int value) {
        Bid bid = new Bid(bidder, value);
        return this.items[itemIndex].bidFor(bid);
    }

    /**
     * print n of sold items
     */
    public void printSoldCount() {
        int count = 0;
        for (Item item : this.items) {
            if (item.getSold()) {
                count++;
            }
        }
        System.out.println("Number of sold items: " + count);
    }

    /**
     * find the item sold for the highest price
     * @return name of the item (none of no items were sold)
     */
    public String highestSold() {
        int highestBid = -1;
        String highestName = "none";
        for (Item item : this.items) {
            if (item.getSold() && item.getFinalOffer().getValue() > highestBid) {
                highestBid = item.getFinalOffer().getValue();
                highestName = item.getItemName();
            }
        }
        return highestName;
    }
}
