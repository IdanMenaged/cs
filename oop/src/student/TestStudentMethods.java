package student;

import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

import java.lang.reflect.*;


public class TestStudentMethods extends TestCase
{
    private static String LS = System.lineSeparator();
    
    /*******************************   Access Properties  ******************************/
    private int getIntValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        int value = fld.getInt(o);  
        
        return value;
    }
    
    private double getDoubleValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        double value = fld.getDouble(o);  
        
        return value;
    }
    
    private char getCharValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        char value = fld.getChar(o);  
        
        return value;
    }
    
     private Object getObjectValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);

        Object value = fld.get(o);  
        
        return value;
    }
    
    private int getStaticIntValue(Class cls, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        int value = fld.getInt(null);  
        
        return value;        
    }
    
    private void setIntValue(Object o, String fldName, int val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    }

     private void setStaticIntValue(Class cls, String fldName, int val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(null, val);
     
    }
    private void setStringValue(Object o, String fldName, String val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    }
    
    private void setDoubleValue(Object o, String fldName, double val) throws NoSuchFieldException, IllegalAccessException
    {
        Field fld = o.getClass().getDeclaredField(fldName);
        fld.setAccessible(true);
        
        fld.set(o, val);
     
    } 
    
    private String removeMessage (String st, String message)
    {
        return st.replace(message, "");
    }

    /*********************************** Student **************************/
    public void test_Constructor() throws NoSuchFieldException, IllegalAccessException
    {
        int preStudCounter = getStaticIntValue(Student.class, "stdCounter");
        Student s = new Student("best student", "1234","0987", 2000);
        int postStudCounter = getStaticIntValue(Student.class, "stdCounter");
        
        assertEquals("Student(\"best student\", \"1234\",\"0987\") - automatic numbering",
                       preStudCounter + 1, postStudCounter);

        int stdNum = getIntValue(s, "stdNum");
        
        assertEquals("Student(\"best student\", \"1234\",\"0987\") - automatic numbering",
                       postStudCounter, stdNum);
                       
        String name = (String)getObjectValue(s, "name");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") name",
                        "best student", name);
                        
        String id = (String)getObjectValue(s, "id");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") id",
                        "1234", id);
                        
        String phone = (String)getObjectValue(s, "phone");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") phone",
                        "0987", phone);
                        
        int year = getIntValue(s, "year");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") year",
                        2000, year);
                        
        int gradeA = getIntValue(s, "gradeA");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") gradeA",
                        0, gradeA);
                        
        int gradeB = getIntValue(s, "gradeB");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") gradeB",
                        0, gradeB);
                        
        double avg = getDoubleValue(s, "avg");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") avg",
                        0.0, avg);
                        

                        
    }
    
    public void test_copyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        int preStudCounter = getStaticIntValue(Student.class, "stdCounter");
        Student s = new Student("best student", "1234","0987", 2000);
        Student copys = new Student(s);
        
        int postStudCounter = getStaticIntValue(Student.class, "stdCounter");
        
        assertEquals("Student(\"best student\", \"1234\",\"0987\") - automatic numbering",
                       preStudCounter + 1, postStudCounter);

        int stdNum = getIntValue(s, "stdNum");
        int copyNum = getIntValue(copys, "stdNum");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") - automatic numbering",
                       stdNum, copyNum);
                       
        String name = (String)getObjectValue(s, "name");
        String copyName = (String)getObjectValue(copys, "name");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") name",
                        name, copyName);
                        
        String id = (String)getObjectValue(s, "id");
        String copyId = (String)getObjectValue(copys, "id");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") id",
                        id, copyId);
                        
        String phone = (String)getObjectValue(s, "phone");
        String copyPhone = (String)getObjectValue(copys, "phone");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") phone",
                        phone, copyPhone);
                        
        int year = getIntValue(s, "year");
        int copyYear = getIntValue(copys, "year");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") year",
                        year, copyYear);
                        
        int gradeA = getIntValue(s, "gradeA");
        int copyGradeA = getIntValue(copys, "gradeA");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") gradeA",
                        gradeA, copyGradeA);
                        
        int gradeB = getIntValue(s, "gradeB");
        int copyGradeB = getIntValue(copys, "gradeB");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") gradeB",
                        gradeB, copyGradeB);
                        
        double avg = getDoubleValue(s, "avg");
        double copyAvg = getDoubleValue(copys, "avg");
        assertEquals("Student(\"best student\", \"1234\",\"0987\") avg",
                        avg, copyAvg);                    
    }
    
    /**************************** getters ********************/
    public void test_getName() throws NoSuchFieldException, IllegalAccessException
    {
        Student s = new Student("best student", "1234","0987", 2000);
        
        String name = (String)getObjectValue(s, "name");
        assertEquals("getName()",name, s.getName());
         
    }
    
                            
    public void test_getID() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        
        String id = (String)getObjectValue(s, "id");
        assertEquals("getId()",id, s.getId());
    }
                        
    public void test_getPhone() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        
        String phone = (String)getObjectValue(s, "phone");
        assertEquals("getPhone()",phone, s.getPhone());
    }
    
    
    public void test_getYear() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        
        int year = getIntValue(s, "year");
        assertEquals("getYear()", year, s.getYear());
    }
                        
    public void test_getGradeA() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        int gradeA = getIntValue(s, "gradeA");
        assertEquals("getGradeA()", gradeA, s.getGradeA());
    }
                        
    public void test_getGradeB() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        int gradeB = getIntValue(s, "gradeB");
        assertEquals("getGradeB()", gradeB, s.getGradeB());
    }
                        
    public void test_getAvg() throws NoSuchFieldException, IllegalAccessException
    { 
        Student s = new Student("best student", "1234","0987", 2000);
        double avg = getDoubleValue(s, "avg");
        assertEquals("getAvg()", avg, s.getAvg()); 
    }
    
    public void test_getStdNum() throws NoSuchFieldException, IllegalAccessException
    { 
        Student s = new Student("best student", "1234","0987", 2000);
        int stdNum = getIntValue(s, "stdNum");
        assertEquals("getStdNum()", stdNum, s.getStdNum()); 
    }    
    public void test_getStdCounter() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Student.class, "stdCounter", 17);
        int stdCount = Student.getStdCounter();
        assertEquals("getStdCount()", 17, stdCount); 
    }
    /**************************** setters ********************/
    public void test_setName() throws NoSuchFieldException, IllegalAccessException
    {
        Student s = new Student("best student", "1234","0987", 2000);
        s.setName("worst student");
        String name = (String)getObjectValue(s, "name");
        assertEquals("setName(\"worst student\")", "worst student", name);
         
    }
    
                            
    public void test_setId() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        
        s.setId("4321");
        String id = (String)getObjectValue(s, "id");
        assertEquals("setId(\"4321\")","4321", id);
    }
                        
    public void test_setPhone() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        s.setPhone("7890");
        String phone = (String)getObjectValue(s, "phone");
        assertEquals("setPhone(\"7890\")","7890", phone);
    }
    
    
    public void test_setYear() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        s.setYear(1000);
        int year = getIntValue(s, "year");
        assertEquals("setYear(1000)", 1000, year);
    }
                        
    public void test_setGradeA() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        s.setGradeA(100);
        int gradeA = getIntValue(s, "gradeA");
        assertEquals("setGradeA(100)", 100, gradeA);
    }
                        
    public void test_setGradeB() throws NoSuchFieldException, IllegalAccessException
    {   
        Student s = new Student("best student", "1234","0987", 2000);
        s.setGradeB(70);
        int gradeB = getIntValue(s, "gradeB");
        assertEquals("setGradeB(79)", 70, gradeB);
    }
                        
    public void test_setAvg() throws NoSuchFieldException, IllegalAccessException
    { 
        Student s = new Student("best student", "1234","0987", 2000);
        s.setAvg(80);
        double avg = getDoubleValue(s, "avg");
        assertEquals("setAvg(80)", 80.0, avg); 
    }  

    public void test_setStdNum() throws NoSuchFieldException, IllegalAccessException
    { 
        Student s = new Student("best student", "1234","0987", 2000);
        s.setStdNum(80);
        int stdNum = getIntValue(s,"stdNum");
        assertEquals("setStdNum()", 80, stdNum); 
    }  
     public void test_setStdCounter() throws NoSuchFieldException, IllegalAccessException
    { 
        Student s = new Student("best student", "1234","0987", 2000);
        s.setStdCounter(15);
        int stdCounter = getStaticIntValue(Student.class, "stdCounter");
        assertEquals("setStdCounter()", 15, stdCounter); 
    } 
    public void test_highYearlyGeadeEqual()
    {
        Student s = new Student("best student", "1234","0987", 2000);
        s.setAvg(90);
       
        assertEquals("highYearlyGeade()", false, s.highYearlyGrade()); 
    } 
    public void test_toString() throws NoSuchFieldException, IllegalAccessException
    {
        
        Student s = new Student("best student", "1234","0987", 2000);
        s.setGradeA(100);
        s.setGradeB(80);
        s.setAvg(90.0);
        setIntValue(s, "stdNum", 11);
        
        String expected = "name = best student, id = 1234, phone = 0987, year = 2000, " +
                            "gradeA = 100, gradeB = 80, avg = 90.0, num = 11";
        assertEquals("toSting() ", expected, s.toString());
    }

    public void test_updateYearlyGeade() throws NoSuchFieldException, IllegalAccessException
    {
        Student s = new Student("best student", "1234","0987", 2000);
        s.setGradeA(100);
        s.setGradeB(85);
        
        assertEquals("updateYearlyGeade() ", 92.5, s.updateYearlyGrade());
        double avg = getDoubleValue(s, "avg");
        assertEquals("updateYearlyGeade()", 92.5, avg); 
    }
        
    public void test_highYearlyGeadeTrue()
    {
        Student s = new Student("best student", "1234","0987", 2000);
        s.setAvg(100);
       
        assertEquals("highYearlyGeade()", true, s.highYearlyGrade()); 
    }  
    
    public void test_highYearlyGeadeFalse()
    {
        Student s = new Student("best student", "1234","0987", 2000);
        s.setAvg(70);
       
        assertEquals("highYearlyGeade()", false, s.highYearlyGrade()); 
    } 
    
    public void test_bestInSemesterA()
    {
        Student s1 = new Student("student", "1234","0987", 2000);
        Student s2 = new Student("better student",  "1234","0987", 2000);
        
        s1.setGradeA(60);
        s2.setGradeA(90);
        String best = s1.bestInSimesterA(s2);
        assertEquals("s1(gradeA = 60).bestInSimesterA(s2(gradeA = 90)) - ",
                        "better student", best);
        
    }

    public void test_bestStudent()
    {
        Student s1 = new Student("student", "1234","0987", 2000);
        Student s2 = new Student("better student",  "1234","0987", 2000);
        
        s1.setGradeA(90);
        s2.setGradeA(92);
        s1.setGradeB(88);
        s2.setGradeB(100); 
        s1.updateYearlyGrade();
        s2.updateYearlyGrade();
        String best = s1.bestStudent(s2);
 
        assertEquals("s1(gradeA = 60).bestInSimesterA(s2(gradeA = 90)) - ",
                        "better student", best);
        
    }
    /***************************** Main ***************************/
    public void test_MainStudent() throws NoSuchFieldException, IllegalAccessException
    {
        String stin = "student1" +  LS + "1234" +  LS + "0987" +  LS + "2000" +  LS + "100" +  LS + "90" +  LS +
                      "student2" +  LS + "5678" +  LS + "0123" +  LS + "3000" +  LS + "55" +  LS + "60" +  LS +
                      "student3" +  LS + "2468" +  LS + "0975" +  LS + "4000" +  LS + "80" +  LS + "65" +  LS +
                      "student4" +  LS + "1111" +  LS + "0111" +  LS + "1000" +  LS + "60" +  LS + "76" +  LS +
                      "student5" +  LS + "2222" +  LS + "0222" +  LS + "5000" +  LS + "80" +  LS + "90" +  LS +
                      "student6" +  LS + "3333" +  LS + "0333" +  LS + "6000" +  LS + "67" +  LS + "77" +  LS +
                      "student7" +  LS + "4444" +  LS + "0444" +  LS + "7000" +  LS + "40" +  LS + "60" +  LS +
                      "student8" +  LS + "5555" +  LS + "0555" +  LS + "8000" +  LS + "55" +  LS + "65" +  LS +
                      "student9" +  LS + "6666" +  LS + "0666" +  LS + "9000" +  LS + "100" +  LS + "100" +  LS +
                      "student10" +  LS + "7777" +  LS + "0777" +  LS + "9999" +  LS + "90" +  LS + "60" +  LS;
        MainStudent.reader = new Scanner (stin);
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        setStaticIntValue(Student.class, "stdCounter", 0);
        MainStudent.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        
        String actual = removeMessage(st, "Please enter id" + LS);
        actual = removeMessage(actual, "Please enter name" +  LS );
        actual = removeMessage(actual, "Please enter num phone" +  LS );
        actual = removeMessage(actual, "Please enter phone" +  LS );
        actual = removeMessage(actual, "Please enter year" +  LS );
        actual = removeMessage(actual, "Please enter grade A" +  LS );
        actual = removeMessage(actual, "Please enter grade B" +  LS );

        actual = removeMessage(actual, "Student 1" +  LS );
        actual = removeMessage(actual, "Student 2" +  LS );
        actual = removeMessage(actual, "Student 3" +  LS );
        actual = removeMessage(actual, "Student 4" +  LS );
        actual = removeMessage(actual, "Student 5" +  LS );
        actual = removeMessage(actual, "Student 6" +  LS );
        actual = removeMessage(actual, "Student 7" +  LS );
        actual = removeMessage(actual, "Student 8" +  LS );
        actual = removeMessage(actual, "Student 9" +  LS );
        actual = removeMessage(actual, "Student 10" +  LS );
        String expected = "Best student is: student9" +  LS  + 
                          "name = student1, id = 1234, phone = 0987, year = 2000, gradeA = 100, gradeB = 90, avg = 95.0, num = 1 - excellent" +  LS  +
                          "name = student2, id = 5678, phone = 0123, year = 3000, gradeA = 55, gradeB = 60, avg = 57.5, num = 2 - failed" +  LS  + 
                          "name = student3, id = 2468, phone = 0975, year = 4000, gradeA = 80, gradeB = 65, avg = 72.5, num = 3" +  LS  +
                          "name = student4, id = 1111, phone = 0111, year = 1000, gradeA = 60, gradeB = 76, avg = 68.0, num = 4" +  LS  +
                          "name = student5, id = 2222, phone = 0222, year = 5000, gradeA = 80, gradeB = 90, avg = 85.0, num = 5" +  LS  +
                          "name = student6, id = 3333, phone = 0333, year = 6000, gradeA = 67, gradeB = 77, avg = 72.0, num = 6" +  LS  +
                          "name = student7, id = 4444, phone = 0444, year = 7000, gradeA = 40, gradeB = 60, avg = 50.0, num = 7 - failed" +  LS  +
                          "name = student8, id = 5555, phone = 0555, year = 8000, gradeA = 55, gradeB = 65, avg = 60.0, num = 8" +  LS  +
                          "name = student9, id = 6666, phone = 0666, year = 9000, gradeA = 100, gradeB = 100, avg = 100.0, num = 9 - excellent" +  LS  +
                          "name = student10, id = 7777, phone = 0777, year = 9999, gradeA = 90, gradeB = 60, avg = 75.0, num = 10" +  LS  + 
                          "best in simester A: student1" +  LS  + 
                          "best student: student1" +  LS ;
        
        assertEquals("Window Main(): ", expected, actual);
    }
}
