package queue.classes;

import java.util.Scanner;

public class WeightWatchers {
    private static final int N_MEASUREMENTS = 4;

    public static Scanner reader = new Scanner(System.in);

    /**
     * get input and create customer
     * get input for N_MEASUREMENTS measurements
     * print best decrease
     */
    public static void main(String[] args) {
        String name;
        int age, month, i;
        double weight;
        Customer customer;

        // customer input
        System.out.println("Please enter name");
        name = reader.next();
        System.out.println("Please enter age");
        age = reader.nextInt();

        customer = new Customer(name, age);

        // measurements input
        for (i = 0; i < N_MEASUREMENTS; i++) {
            System.out.println("Please enter month");
            month = reader.nextInt();
            System.out.println("Please enter weight");
            weight = reader.nextDouble();

            customer.addMeasure(month, weight);
        }

        // find best measurement
        System.out.println("Best decrease: " + customer.bestMeasure());
    }
}
