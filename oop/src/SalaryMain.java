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
            System.out.println("Please enter employee type");
            char role = reader.next().charAt(0);
            System.out.println("Please enter name");
            String name = reader.next();
            System.out.println("Please enter id");
            String id = reader.next();

            if (role == 'w') {
                workers[i] = new Worker(name, id);
            }
            else if (role == 'a') {
                System.out.println("Please enter hours");
                double hours = reader.nextDouble();
                System.out.println("Please enter pay per hour");
                double payPerHour = reader.nextDouble();

                workers[i] = new Administration(name, id, hours, payPerHour);
            }
            else {
                System.out.println("Please enter years");
                int years = reader.nextInt();
                System.out.println("Please enter degree");
                int degree = reader.nextInt();
                System.out.println("Please enter salary");
                double salary = reader.nextDouble();

                if (role == 'e') {
                    workers[i] = new Engineer(name, id, salary, degree, years);
                }
                else {
                    System.out.println("Please enter car");
                    boolean car = reader.nextBoolean();
                    System.out.println("Please enter bonus");
                    double bonus = reader.nextDouble();

                    workers[i] = new Management(name, id, salary, degree, years, car, bonus);
                }
            }

        }

        double sumSal = 0;
        for (Worker worker : workers) {
            // print each worker
            System.out.println(worker);

            // print salary
            System.out.println("Salary: " + worker.calSalary());

            // sum salaries
            sumSal += worker.calSalary();
        }

        System.out.println("Total salary is: " + sumSal);
    }
}
