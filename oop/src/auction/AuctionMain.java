package auction;

import java.util.Scanner;

public class AuctionMain {
    private static final int N_ITEMS = 5;

    public static Scanner reader = new Scanner(System.in);

    /**
     * create an Auction object
     *
     * input 5 items
     * add bids
     * set 2nd and 4th items as sold
     * add to auction
     *
     * print item sold for the highest price
     * print n of items sold
     */
    public static void main(String[] args) {
        Auction auction = new Auction();
        int i, offer;
        String itemName, bidderName;

        for (i = 1; i <= AuctionMain.N_ITEMS; i++) {
            // input
            System.out.println("please enter item name");
            itemName = reader.next();
            System.out.println("please enter bidder");
            bidderName = reader.next();
            System.out.println("please enter offer");
            offer = reader.nextInt();

            // add item
            Item item = new Item(itemName);

            // mark sold
            if (i == 2 || i == 4) {
                item.close();
            }

            auction.addItem(item);

            // add bid
            Person bidder = new Person(bidderName);
            auction.addBid(i, bidder, offer);
        }

        // find n of items sold
        auction.printSoldCount();

        // find highest price
        System.out.println("Highest sold: " + auction.highestSold());

        //
    }
}
