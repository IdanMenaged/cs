package passport;

import java.util.Scanner;

public class TravelerMain {
    public static Scanner reader = new Scanner(System.in);

    /**
     * create 10 travelers
     * print the travelers able to travel
     * if all from the same country, including those who can't travel, print "all travelers from the same country"
     */
    public static void main(String[] args) {
        int i, day, month, year;
        Traveler[] travelers = new Traveler[10];
        String name, country;
        boolean paid, sameCountry = true;
        Date travelDate = new Date(1, 1, 22), date;
        Passport passport;

        // create travelers
        for (i = 0; i < travelers.length; i++) {
            System.out.println("please enter day month year");
            day = reader.nextInt();
            month = reader.nextInt();
            year = reader.nextInt();
            System.out.println("please enter name");
            name = reader.next();
            System.out.println("please enter country");
            country = reader.next();
            System.out.println("please enter paid");
            paid = reader.nextBoolean();

            date = new Date(day, month, year);
            passport = new Passport(name, country, date);

            travelers[i] = new Traveler(name, passport, paid);
        }

        // print valid ones and check country
        for (Traveler traveler :
                travelers) {
            if (traveler.canTravel(travelDate)) {
                System.out.println(traveler);
            }

            if (!traveler.sameCountry(travelers[0])) {
                sameCountry = false;
            }
        }

        // print all from same country if needed
        if (sameCountry) {
            System.out.println("all travelers from the same country");
        }
    }
}
