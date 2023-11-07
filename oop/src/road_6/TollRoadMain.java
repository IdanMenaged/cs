package road_6;

import java.util.Scanner;
public class TollRoadMain {
    public static Scanner reader = new Scanner(System.in);

    /**
     * creates TollRoad objects until a car license of -999 is entered or 150 objects were created
     * print each object's info
     * print the number of objects
     * print the sum of all prices all drivers paid
     */
    public static void main(String[] args) {
        final int MAX_DRIVERS = 150;
        final String EXIT_CODE = "-999";
        int i = 0;
        String carLicense = "";
        int segmentCount, driverCount = 0;
        boolean isCustomer;
        TollRoad tollRoad;
        double totEarnings = 0;

        for (i = 0; i < MAX_DRIVERS; i++) {
            System.out.println("Please enter car license: ");
            carLicense = reader.next();

            if (carLicense.equals(EXIT_CODE)) {
                break;
            }

            System.out.println("Please enter segment count: ");
            segmentCount = reader.nextInt();
            System.out.println("subscribed? ");
            isCustomer = reader.nextBoolean();

            tollRoad = new TollRoad(carLicense, segmentCount, isCustomer);

            System.out.println(tollRoad);

            driverCount++;
            totEarnings += tollRoad.bill();
        }

        System.out.println("car count: " + driverCount + " earnings: " + totEarnings);
    }
}
