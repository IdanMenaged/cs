package auction;/*
Idan Menaged
*/

public class Auction {
    private static final int MAX_ITEMS = 100;

    private Item[] items; // all items
    private int current; // current n of items

    public static void main(String[] args) {
        Auction auction = new Auction();
        Item item = new Item("item1");
        auction.addItem(item);
        Person p = new Person("namenson");
        auction.addBid(0, p, 200);
        p = new Person("otherman");
        auction.addBid(0, p, 100);
        System.out.println(auction.highestSold());
    }

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
     * @param itemId id of the bidded item
     * @param bidder person placing the bid
     * @param value sum of the bid
     * @return was bid accepted?
     */
    public boolean addBid(int itemId, Person bidder, int value) {
        Bid bid = new Bid(bidder, value);
        for (Item i : this.items) {
            if (i.getItemId() == itemId) {
                return i.bidFor(bid);
            }
        }
        return false;
    }

    /**
     * print n of sold items
     */
    public void printSoldCount() {
        int count = 0, i;
        for (i = 0; i < this.current; i++) {
            Item item = this.items[i];
            if (item.getSold()) {
                count++;
            }
        }
        System.out.println("Number of sold items is: " + count);
    }

    /**
     * find the item sold for the highest price
     * @return name if the item (none of no items were sold)
     */
    public String highestSold() {
        int highestBid = -1, i;
        String highestName = "none";
        for (i = 0; i < this.current; i++) {
            Item item = this.items[i];
            int val = -1;
            if (item.getFinalOffer() != null) {
                val = item.getFinalOffer().getValue();
            }

            if (item.getSold() && val > highestBid) {
                highestBid = val;
                highestName = item.getItemName();
            }
        }
        return highestName;
    }

    /**
     * mark the item at the given id as sold
     * @param id id of the item
     */
    public void markSold(int id) {
         for (Item i : this.items) {
            if (i.getItemId() == id) {
                i.close();
            }
        }
    }
}
