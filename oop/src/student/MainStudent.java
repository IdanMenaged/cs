package student;

import java.util.Scanner;

public class MainStudent {
    public static Scanner reader = new Scanner(System.in);

    /**
     * create 10 students
     * create a copy of 1st student
     * find best student
     * print each student
     */
    public static void main(String[] args) {
        Student[] students = new Student[10];
        int i, year, gradeA, gradeB;
        String name, id, phone;
        Student best, copy;

        // create students
        for (i = 0; i < students.length; i++) {
            System.out.println("Student " + (i + 1));
            System.out.println("Please enter name");
            name = reader.next();
            System.out.println("Please enter id");
            id = reader.next();
            System.out.println("Please enter phone");
            phone = reader.next();
            System.out.println("Please enter year");
            year = reader.nextInt();
            System.out.println("Please enter grade A");
            gradeA = reader.nextInt();
            System.out.println("Please enter grade B");
            gradeB = reader.nextInt();

            students[i] = new Student(name, id, phone, year);
            students[i].setGradeA(gradeA);
            students[i].setGradeB(gradeB);
            students[i].updateYearlyGrade();
        }

        // create copy
        copy = new Student(students[0]);

        // find best student
        best = students[0];
        for (Student student : students) {
            if (student.getAvg() > best.getAvg()) {
                best = student;
            }
        }

        System.out.println("Best student is: " + best.getName());

        // print each student
        for (Student student : students) {
            System.out.println(student);
        }

        // find best student between 1st and last
        System.out.println("best in simester A: " + students[0].bestInSimesterA(students[students.length - 1]));
        System.out.println("best student: " + students[0].bestStudent(students[students.length - 1]));
    }
}
