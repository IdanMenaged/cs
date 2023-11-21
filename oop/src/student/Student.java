package student;

public class Student {
    private String name; // name of student
    private String id; // number on the id card
    private int year;  // birth year
    private String phone; // phone number of student
    private int gradeA; // grade in first semester
    private int gradeB; // grade in second semester
    private double avg; // average
    private int stdNum; // a number to identify student

    private static int stdCounter = 0; // a count of the number of students in existence

    /**
     * constructs a new student with both grades as 0
     * @param name name of student
     * @param id id of student
     * @param phone phone number
     * @param year birth year
     */
    public Student(String name, String id, String phone, int year) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.year = year;

        this.gradeA = 0;
        this.gradeB = 0;

        Student.stdCounter++;
        this.stdNum = Student.stdCounter;
    }

    /**
     * copies all properties of a given student into a new student
     * DOES NOT update stdCounter
     * @param student student to copy
     */
    public Student(Student student) {
        this.name = student.getName();
        this.id = student.getId();
        this.phone = student.getPhone();
        this.year = student.getYear();
        this.gradeA = student.getGradeA();
        this.gradeB = student.getGradeB();
        this.avg = student.getAvg();
        this.stdNum = student.getStdNum();
    }

    /**
     * set the name property
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets a new id
     * @param id new id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * sets a new birth year
     * @param year new birth year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * sets a new phone number
     * @param phone new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * sets a new grade for semester A
     * @param gradeA new grade
     */
    public void setGradeA(int gradeA) {
        this.gradeA = gradeA;
    }

    /**
     * sets a new grade for semester B
     * @param gradeB semester B
     */
    public void setGradeB(int gradeB) {
        this.gradeB = gradeB;
    }

    /**
     * sets a new average grade
     * @param avg new average
     */
    public void setAvg(double avg) {
        this.avg = avg;
    }

    /**
     * sets a new student count
     * @param stdCount new student count
     */
    public static void setStdCounter(int stdCount) {
        Student.stdCounter = stdCount;
    }

    /**
     * sets a new student num
     * @param stdNum new student num
     */
    public void setStdNum(int stdNum) {
        this.stdNum = stdNum;
    }

    /**
     * get name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * get id
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * get birth year
     * @return birth year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * get phone number
     * @return phone number
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * get grade for semester A
     * @return grade
     */
    public int getGradeA() {
        return this.gradeA;
    }

    /**
     * get grade for semester B
     * @return grade
     */
    public int getGradeB() {
        return this.gradeB;
    }

    /**
     * get yearly average
     * @return average
     */
    public double getAvg() {
        return this.avg;
    }

    /**
     * get student count
     * @return student count
     */
    public static int getStdCounter() {
        return Student.stdCounter;
    }

    /**
     * get student num
     * @return student num
     */
    public int getStdNum() {
        return this.stdNum;
    }

    /**
     * create a string representing the student + excellent/failed
     * @return the string
     */
    public String toString() {
        return "name = " + this.name + ", id = " + this.id + ", phone = " + this.phone + ", year = " + this.year  +
                ", gradeA = " + this.gradeA + ", gradeB = " + this.gradeB + ", avg = " + this.avg + ", num = " +
                this.stdNum + (this.highYearlyGrade() ? " - excellent" : "") + (this.avg < 60 ? " - failed" : "");
    }

    /**
     * calculates, stores in {@code this.avg}, and return the avg grade
     * @return avg grade
     */
    public double updateYearlyGrade() {
        this.avg = (double) (this.gradeA + this.gradeB) / 2;
        return this.avg;
    }

    /**
     * is the avg above 90?
     * @return true if {@code this.avg > 90}
     */
    public boolean highYearlyGrade() {
        return this.avg > 90;
    }

    /**
     * finds the better student only considering semester A
     * @param student student to compare with
     * @return name of the better student
     */
    public String bestInSimesterA(Student student) {
        return this.gradeA > student.getGradeA() ? this.name : student.getName();
    }

    /**
     * finds the better student throughout the whole year
     * @param student student to compare with
     * @return name of the better student
     */
    public String bestStudent(Student student) {
        return this.avg > student.getAvg() ? this.name : student.getName();
    }
}
