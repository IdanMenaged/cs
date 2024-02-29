import java.util.Scanner;

public class ZooPoly {
    public static Scanner reader = new Scanner(System.in);

    public static void main(String[] args) {
        int i, energy, milk, depth, nest, bones, boneSum = 0;
        Animal[] animals = new Animal[9];
        String name;
        char gender;

        // create animals
        // mammals
        for (i = 0; i < 3; i++) {
            System.out.println("Mammal: Please enter name gender energy milk");
            name = reader.next();
            gender = reader.next().charAt(0);
            energy = reader.nextInt();
            milk = reader.nextInt();

            animals[i] = new Mammal(name, gender, energy, milk);

            // print
            System.out.println(animals[i]);
        }

        // fish
        for (i = 3; i < 5; i++) {
            System.out.println("Fish: Please enter name gender energy depth");
            name = reader.next();
            gender = reader.next().charAt(0);
            energy = reader.nextInt();
            depth = reader.nextInt();

            animals[i] = new Fish(name, gender, energy, depth);

            // print
            System.out.println(animals[i]);
        }

        // birds
        for (i = 5; i < 7; i++) {
            System.out.println("Bird: Please enter name gender energy nest");
            name = reader.next();
            gender = reader.next().charAt(0);
            energy = reader.nextInt();
            nest = reader.nextInt();

            animals[i] = new Bird(name, gender, energy, nest);

            // print
            System.out.println(animals[i]);
        }

        // dogs
        for (i = 7; i < 9; i++) {
            System.out.println("Dog: Please enter name gender energy milk bones");
            name = reader.next();
            gender = reader.next().charAt(0);
            energy = reader.nextInt();
            milk = reader.nextInt();
            bones = reader.nextInt();

            animals[i] = new Dog(name, gender, energy, milk, bones);

            // print
            System.out.println(animals[i]);
        }

        // find best swimmer
        System.out.print("Best swimmer is: ");
        Fish best = null;
        for (i = 0; i < animals.length; i++) {
            if (animals[i] instanceof Fish) {
                if (best == null) {
                    best = (Fish) animals[i];
                }
                else if (((Fish) animals[i]).getDepth() > best.getDepth()) {
                    best = (Fish) animals[i];
                }
            }
        }
        if (best == null) {
            System.out.println("no fish");
        }
        else {
            System.out.println(best.getName());
        }

        // sum bones
        for (i = 0; i < animals.length; i++) {
            if (animals[i] instanceof Dog) {
                boneSum += ((Dog) animals[i]).getBones();
            }
        }
        System.out.println("Sum bones is: " + boneSum);
    }
}
