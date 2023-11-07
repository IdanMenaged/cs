package road_6;

public class TollRoad {
    private String carLicense; // the license plate number
    private int segmentCount; // how many segments did the driver drive on
    private boolean isCustomer; // true if the driver has a subscription
    private int travelNumber; // id of the current object

    private static double segmentPrice = 6; // price per segment
    private static int customerDiscount = 10; // a percentage representing the discount for customers (isCustomer == 
    // true)
    private static int travelCounter = 0; // increases by 1 for each new TollRoad object

    /**
     * constructs a new TollRoad object
     * sets isCustomer to true
     * sets travelNumber to the travelCounter
     * increases travelCounter by 1
     * all other properties are set to the params by the same name
     * @param carLicense license plate number
     * @param segmentCount n of segments
     */
    public TollRoad(String carLicense, int segmentCount) {
        this.carLicense = carLicense;
        this.segmentCount = segmentCount;

        this.isCustomer = true;
        this.travelNumber = TollRoad.travelCounter;
        TollRoad.travelCounter++;
    }

    /**
     * constructs a new TollRoad object
     * sets travelNumber to travelCounter
     * increases travelCounter by 1
     * all other properties are set to the params by the same name
     * @param carLicense license plate number
     * @param segmentCount n of segments
     * @param isCustomer whether or not is a customer
     */
    public TollRoad(String carLicense, int segmentCount, boolean isCustomer) {
        this.carLicense = carLicense;
        this.segmentCount = segmentCount;
        this.isCustomer = isCustomer;

        this.travelNumber = TollRoad.travelCounter;
        TollRoad.travelCounter++;
    }
    
    /**
     * sets a new value for carLicense
     * @param carLicense new value
     */
    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }
    /**
     * sets a new value for segmentCount
     * @param segmentCount new value
     */
    public void setSegmentCount(int segmentCount) {
        this.segmentCount = segmentCount;
    }

    /**
     * sets a new value for customerDiscount
     * @param customerDiscount new value
     */
    public static void setCustomerDiscount(int customerDiscount) {
        TollRoad.customerDiscount = customerDiscount;
    }

    /**
     * sets a new value for segmentPrice
     * @param segmentPrice new value
     */
    public static void setSegmentPrice(double segmentPrice) {
        TollRoad.segmentPrice = segmentPrice;
    }

    /**
     * sets a new value for travelNumber
     * @param travelNumber new value
     */
    public void setTravelNumber(int travelNumber) {
        this.travelNumber = travelNumber;
    }

    /**
     * sets a new value for travelCounter
     * @param travelCounter new value
     */
    public static void setTravelCounter(int travelCounter) {
        TollRoad.travelCounter = travelCounter;
    }

    /**
     * sets a new value for isCustomer
     * @param isCustomer new value
     */
    public void setIsCustomer(boolean isCustomer) {
        this.isCustomer = isCustomer;
    }

    /**
     * @return segmentCount
     */
    public int getSegmentCount() {
        return this.segmentCount;
    }

    /**
     * @return carLicense
     */
    public String getCarLicense() {
        return this.carLicense;
    }

    /**
     * @return customerDiscount
     */
    public static int getCustomerDiscount() {
        return TollRoad.customerDiscount;
    }

    /**
     * @return segmentPrice
     */
    public static double getSegmentPrice() {
        return TollRoad.segmentPrice;
    }

    /**
     * @return travelNumber
     */
    public int getTravelNumber() {
        return this.travelNumber;
    }

    /**
     * @return travelCounter
     */
    public static int getTravelCounter() {
        return TollRoad.travelCounter;
    }

    /**
     * @return isCustomer
     */
    public boolean getIsCustomer() {
        return this.isCustomer;
    }

    @Override
    public String toString() {
        return "license: " + carLicense + " segments: " + segmentCount + " segment price: " +
                (isCustomer ? TollRoad.getSegmentPrice() - (TollRoad.getSegmentPrice() *
                        TollRoad.getCustomerDiscount() / 100) : TollRoad.getSegmentPrice()) + " bill: " +
                this.bill();
    }

    /**
     * calculates the price for the object
     * @return the price
     */
    public double bill() {
        return this.segmentCount * TollRoad.segmentPrice * (this.isCustomer ? (double) (100 - TollRoad.customerDiscount) / 100 :
                1);
    }
}
