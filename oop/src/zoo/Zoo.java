package zoo;

import java.util.Scanner;

public class Zoo {
    public static Scanner reader = new Scanner(System.in);

    /**
     * create 3 mammals, 2 dogs, 2 fish, 2 birds
     * print sum of all dogs' bones
     * print fish that swims the deepest
     * print details of all animals
     */
    public static void main(String[] args) {
        int i, energy, milk, depth, nest, bones, boneSum = 0;
        Mammal[] mammals = new Mammal[3];
        Dog[] dogs = new Dog[2];
        Fish[] fishArr = new Fish[2];
        Bird[] birds = new Bird[2];
        String name;
        char gender;

        // create animals
        // mammals
        for (i = 0; i < mammals.length; i++) {
            System.out.println("Mammal: Please enter name gender energy milk");
            name = reader.next();
            gender = reader.next().charAt(0);
            energy = reader.nextInt();
            milk = reader.nextInt();

            mammals[i] = new Mammal(name, gender, energy, milk);

            // print
            System.out.println(mammals[i]);
        }

        // fish
        for (i = 0; i < fishArr.length; i++) {
            System.out.println("Fish: Please enter name gender energy depth");
            name = reader.next();
            gender = reader.next().charAt(0);
            energy = reader.nextInt();
            depth = reader.nextInt();

            fishArr[i] = new Fish(name, gender, energy, depth);

            // print
            System.out.println(fishArr[i]);
        }

        // birds
        for (i = 0; i < birds.length; i++) {
            System.out.println("Bird: Please enter name gender energy nest");
            name = reader.next();
            gender = reader.next().charAt(0);
            energy = reader.nextInt();
            nest = reader.nextInt();

            birds[i] = new Bird(name, gender, energy, nest);

            // print
            System.out.println(birds[i]);
        }

        // dogs
        for (i = 0; i < dogs.length; i++) {
            System.out.println("Dog: Please enter name gender energy milk bones");
            name = reader.next();
            gender = reader.next().charAt(0);
            energy = reader.nextInt();
            milk = reader.nextInt();
            bones = reader.nextInt();

            dogs[i] = new Dog(name, gender, energy, milk, bones);

            // print
            System.out.println(dogs[i]);
        }

        // find best swimmer
        System.out.print("Best swimmer is: ");
        if (fishArr[0].getDepth() >= fishArr[1].getDepth()) {
            System.out.println(fishArr[0].getName());
        } else {
            System.out.println(fishArr[1].getName());
        }

        // sum bones
        for (Dog dog : dogs) {
            boneSum += dog.getBones();
        }
        System.out.println("Sum bones is: " + boneSum);
    }
}
