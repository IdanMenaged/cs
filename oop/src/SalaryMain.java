import java.util.Scanner;

/**
 * Idan Menaged
 */
public class SalaryMain {
    public static Scanner reader = new Scanner(System.in);

    /**
     * create 10 workers of different roles (get a char to determine what type of worker)
     * print each worker
     * print each one's salary
     * print total salary of everyone
     */
    public static void main(String[] args) {
        // create workers
        Worker[] workers = new Worker[10];

        for (int i = 0; i < workers.length; i++) {
            char role = reader.next().charAt(0);
        }
    }
}
