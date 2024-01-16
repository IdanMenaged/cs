package auction;/*
Idan Menaged
*/

public class Item {
    private static int counter = 0; // n of items in existence

    private int itemId; // id of the item
    private String itemName; // name of the item
    private Bid highBid; // highest bid placed on this item
    private boolean sold; // was the item sold yet

    /**
     * create a new item
     * set an automatic id
     * set highBid to null and sold to false
     * @param itemName name of the item
     */
    public Item(String itemName) {
        this.itemName = itemName;

        Item.counter++;
        this.itemId = Item.counter;

        this.highBid = null;
        this.sold = false;
    }

    /**
     * create a new item by copying an existing one
     * @param item existing item
     */
    public Item(Item item) {
        this.itemId = item.itemId;
        this.itemName = item.itemName;

        // edge case
        if (item.highBid == null) {
            this.highBid = null;
        }
        else {
            this.highBid = new Bid(item.highBid);
        }

        this.sold = item.sold;
    }

    /**
     * get item name
     * @return item name
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * get item id
     * @return item id
     */
    public int getItemId() {
        return this.itemId;
    }

    /**
     * get sold
     * @return was item sold yet?
     */
    public boolean getSold() {
        return this.sold;
    }

    /**
     * get high bid
     * @return high bid
     */
    public Bid getFinalOffer() {
        return new Bid(this.highBid);
    }

    /**
     * get person placing high bid
     * @return bidder
     */
    public Person getBuyer() {
        return this.highBid.getBidder();
    }

    /**
     * check if the new bid is the highest
     * update highBid accordingly
     * @param b new bid
     * @return was b the highest bid?
     */
    public boolean bidFor(Bid b) {
        if ((this.highBid != null && b.getValue() < this.highBid.getValue()) || this.sold) {
            return false;
        }

        this.highBid = new Bid(b);
        return true;
    }

    /**
     * set item status to sold
     */
    public void close() {
        this.sold = true;
    }
}
