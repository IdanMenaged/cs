public class ZooObjectMain {
    /**
     * create an animal array with the objects specified in the worksheet
     * check if the first object appears a second time
     * sum all bones
     * count people named ron
     * print all objects
     */
    public static void main(String[] args) {
        Object[] myZoo = new Object[5];
        myZoo[0] = new Dog("bobo", 'M', 4, 5, 6);
        myZoo[1] = new Fish("goldie", 'F', 7, 60);
        myZoo[2] = new Mammal("monika", 'F', 3, 6);
        myZoo[3] = new Person("ron", "1234");
        myZoo[4] = new Dog("bobo", 'M', 4, 5, 6);

        // check if first element repeats
        for (int i = 1; i < myZoo.length; i++) {
            if (myZoo[0].equals(myZoo[i])) {
                System.out.println("First item found");
                break;
            }
        }

        // count bones
        int bonesCount = 0;
        for (int i = 0; i < myZoo.length; i++) {
            if (myZoo[i] instanceof Dog) {
                bonesCount += ((Dog) myZoo[i]).getBones();
            }
        }
        System.out.println("Sum bones is: " + bonesCount);

        // count rons
        int ronCount = 0;
        for (int i = 0; i < myZoo.length; i++) {
            if (myZoo[i] instanceof Person && ((Person) myZoo[i]).getName().equals("ron")) {
                ronCount++;
            }
        }
        System.out.println(ronCount + " rons found");

        // print objects
        for (int i = 0; i < myZoo.length; i++) {
            System.out.println(myZoo[i]);
        }
    }
}
