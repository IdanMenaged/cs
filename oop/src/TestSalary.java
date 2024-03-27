
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

import java.lang.reflect.*;

public class TestSalary extends TestCase
{
    private static String LS = System.lineSeparator();
    /*******************************   Access Properties  ******************************/
    private int getIntValue(Object o, String fldName, int parents) throws NoSuchFieldException, IllegalAccessException
    {

        Class cls = o.getClass();
        for (int i = 0; i < parents; i++)
            cls = cls.getSuperclass();
            
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        int value = fld.getInt(o);  
        
        return value;
    }
    
    private int getIntValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        return getIntValue(o, fldName, 0);
    }
    
    private double getDoubleValue(Object o, String fldName, int parents) throws NoSuchFieldException, IllegalAccessException
    {
        Class cls = o.getClass();
        for (int i = 0; i < parents; i++)
            cls = cls.getSuperclass();
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        double value = fld.getDouble(o);  
        
        return value;
    }
    
    private double getDoubleValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        return getDoubleValue(o, fldName, 0);
    }
    
    private char getCharValue(Object o, String fldName, int parents) throws NoSuchFieldException, IllegalAccessException
    {
        Class cls = o.getClass();
        for (int i = 0; i < parents; i++)
            cls = cls.getSuperclass();
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        char value = fld.getChar(o);  
        
        return value;
    }

    private char getCharValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        return getCharValue(o, fldName, 0);
    }
    
    private boolean getBooleanValue(Object o, String fldName, int parents) throws NoSuchFieldException, IllegalAccessException
    {
        Class cls = o.getClass();
        for (int i = 0; i < parents; i++)
            cls = cls.getSuperclass();
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        boolean value = fld.getBoolean(o);  
        
        return value;
    }
    
    private boolean getBooleanValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        return getBooleanValue(o, fldName, 0);
    }
    
     private Object getObjectValue(Object o, String fldName, int parents) throws NoSuchFieldException, IllegalAccessException
    {
        Class cls = o.getClass();
        for (int i = 0; i < parents; i++)
            cls = cls.getSuperclass();
        Field fld = cls.getDeclaredField(fldName);
        fld.setAccessible(true);

        Object value = fld.get(o);  
        
        return value;
    }

    private Object getObjectValue(Object o, String fldName) throws NoSuchFieldException, IllegalAccessException
    {
        return getObjectValue(o, fldName, 0);
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
    
    private void setIntValue(Object o, String fldName, int val,int parents) throws NoSuchFieldException, IllegalAccessException
    {
        Class cls = o.getClass();
        for (int i = 0; i < parents; i++)
            cls = cls.getSuperclass();
        Field fld = cls.getDeclaredField(fldName);
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

    /*********************************** Worker **************************/
    
   public void test_WorkerProperties() throws SecurityException, NoSuchFieldException
   {
       Field field = Worker.class.getDeclaredField("name");
       int modifiers = field.getModifiers();
       boolean isProtected = Modifier.isProtected(modifiers);
       assertTrue("name property should be protected", isProtected);
       
       field = Worker.class.getDeclaredField("id");
       modifiers = field.getModifiers();
       isProtected = Modifier.isProtected(modifiers);
       assertTrue("id property should be protected", isProtected); 
       
       field = Worker.class.getDeclaredField("number");
       modifiers = field.getModifiers();
       isProtected = Modifier.isProtected(modifiers);
       assertTrue("energy property should be protected", isProtected);
       
       // static properties
       field = Worker.class.getDeclaredField("counter");                                            
       modifiers = field.getModifiers();
       isProtected = Modifier.isProtected(modifiers);
       assertTrue("counter class property should be protected", isProtected);
       boolean isStatic = Modifier.isStatic(modifiers);
       assertTrue("counter class property should be static", isStatic); 
       
      
       field = Worker.class.getDeclaredField("MINIMAL_SALARY");
       modifiers = field.getModifiers();
       boolean isFinal = Modifier.isFinal(modifiers);
       assertTrue("MINIMAL_SALARY class property should be final", isFinal); 
       isStatic = Modifier.isStatic(modifiers);
       assertTrue("MINIMAL_SALARY class property should be static", isStatic); 
       
   }
    public void test_WorkerConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        setStaticIntValue(Worker.class, "counter", 0);
        Worker w = new Worker("Bob", "123");
        String name = (String)getObjectValue(w, "name");
        String id = (String)getObjectValue(w, "id");
        int number = getIntValue(w, "number");
        int counter = getStaticIntValue(Worker.class, "counter");
        
        assertEquals("Worker constructor- name", "Bob", name);        
        assertEquals("Worker constructor- id", "123", id); 
        assertEquals("Worker constructor- number", 0, number); 
        assertEquals("Worker constructor- counter", 1, counter); 
    }

    public void test_WorkerCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {

        Worker w = new Worker("Bob", "123");
        setStaticIntValue(Worker.class, "counter", 7);
        int orig_number = getIntValue(w, "number");
        Worker copy_w = new Worker(w);
        
        String name = (String)getObjectValue(copy_w, "name");
        String id = (String)getObjectValue(copy_w, "id");
        int number = getIntValue(copy_w, "number");
        int counter = getStaticIntValue(Worker.class, "counter");
        
        assertEquals("Worker copy constructor- name", "Bob", name);        
        assertEquals("Worker copy constructor- id", "123", id); 
        assertEquals("Worker copy constructor- number", orig_number, number); 
        assertEquals("Worker copy constructor- counter should not change in copy constructor",
                                                                                7, counter);
    }
    public void test_setName() throws NoSuchFieldException, IllegalAccessException 
    {
        Worker w = new Worker("Bob", "123");
        w.setName("Joe");
        String name = (String)getObjectValue(w, "name");
        
        assertEquals("Worker setName(\"Joe\")" , "Joe", name);        
    }
    
    public void test_setId() throws NoSuchFieldException, IllegalAccessException 
    {
        Worker w = new Worker("Bob", "123");
        w.setId("567");
       
        String id = (String)getObjectValue(w, "id");
        
       
        assertEquals("Worker setId(\"567\")", "567", id); 
    }
    
    public void test_setNumber() throws NoSuchFieldException, IllegalAccessException 
    {
        setStaticIntValue(Worker.class, "counter", 7);        
        Worker w = new Worker("Bob", "123");
        w.setNumber(990);
       
        int number = getIntValue(w, "number");
        
       
        assertEquals("Worker setNumber(990)", 990, number);
    }

    public void test_setCounter() throws NoSuchFieldException, IllegalAccessException 
    {
        setStaticIntValue(Worker.class, "counter", 7);        
        Worker.setCounter(1000);
       
        int counter = getStaticIntValue(Worker.class, "counter");
        
       
        assertEquals("Worker setCounter(1000)", 1000, counter);
    }
    public void test_getName() throws NoSuchFieldException, IllegalAccessException 
    {
        Worker w = new Worker("Bob", "123");
        String name = w.getName();
      
        
        assertEquals("Worker getName()", "Bob", name);        
    }
    
    public void test_getId() throws NoSuchFieldException, IllegalAccessException 
    {
        Worker w = new Worker("Bob", "123");
        String id = w.getId();
       
        assertEquals("Worker getId()", "123", id); 
    }

    public void test_getNumber() throws NoSuchFieldException, IllegalAccessException 
    {
        setStaticIntValue(Worker.class, "counter", 7);
        Worker w = new Worker("Bob", "123");
        int actual =getIntValue(w, "number");
        int number = w.getNumber();
       
        assertEquals("Worker getNumber()", actual, number); 
    }
    public void test_getCounter() throws NoSuchFieldException, IllegalAccessException 
    { 
        Worker w = new  Worker("Bob", "123");
        setStaticIntValue(Worker.class, "counter", 7); 
        int actual = getStaticIntValue(Worker.class, "counter");
        int counter = Worker.getCounter();
        
        assertEquals("Worker getcounter()", actual, counter); 
    }    
    
    public void test_workerToString() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Worker w = new Worker("Bob", "123");
        setIntValue(w, "number", 7);
        String expected = "Worker - name: Bob, id: 123, number: 7";
        String st = w.toString();
        assertEquals("Worker toSting()", expected, st); 
    }
    
    public void test_workerCalSalary()
    {
        Worker w = new Worker("Bob", "123");
        double salary = w.calSalary();
        
        assertEquals("Worker calSalary()", 4000.0, salary);
    }
     
   /*********************************** Administration **************************/
    
   public void test_AdministrationProperties() throws IllegalAccessException, NoSuchFieldException
   {
       Field field = Administration.class.getDeclaredField("hours");
       int modifiers = field.getModifiers();
       boolean isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("hours property should be private", isPrivate);
       field = Administration.class.getDeclaredField("payPerHour");
       modifiers = field.getModifiers();
       isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("payPerHours property should be private", isPrivate);

       assertEquals("Administration should be Workerś child", Administration.class.getSuperclass(), Worker.class);
   }
   
   public void test_AdministrationConstructor() throws NoSuchFieldException, IllegalAccessException 
   {
        setStaticIntValue(Worker.class, "counter", 0);
        Administration a = new Administration("Bob", "123", 180, 50);
        
        String name = (String)getObjectValue(a, "name", 1);
        String id = (String)getObjectValue(a, "id", 1);
        int number = getIntValue(a, "number", 1);
        double hours = getDoubleValue(a, "hours");
        double payPerHour = getDoubleValue(a, "payPerHour");
        int counter = getStaticIntValue(Worker.class, "counter");
        
        assertEquals("Administration constructor- name", "Bob", name);        
        assertEquals("Administration constructor- id", "123", id); 
        assertEquals("Administration constructor- number", 0, number); 
        assertEquals("Administration constructor- payPerHour", 50.0, payPerHour); 
        assertEquals("Administration constructor- hours", 180.0, hours); 
        assertEquals("Administration constructor- counter", 1, counter); 
    }

    public void test_AdministrationCopyConstructor() throws NoSuchFieldException, IllegalAccessException 
   {
        setStaticIntValue(Worker.class, "counter", 0);
        Administration a = new Administration("Bob", "123", 180, 50);
        int orig_number = getIntValue(a,"number", 1);
        Administration copy_a = new Administration(a);
        
        String name = (String)getObjectValue(copy_a, "name", 1);
        String id = (String)getObjectValue(copy_a, "id", 1);
        int number = getIntValue(copy_a, "number", 1);
        double hours = getDoubleValue(copy_a, "hours");
        double payPerHour = getDoubleValue(copy_a, "payPerHour");
        int counter = getStaticIntValue(Worker.class, "counter");
        
        assertEquals("Administration constructor- name", "Bob", name);        
        assertEquals("Administration constructor- id", "123", id); 
        assertEquals("Administration constructor- number", orig_number, number); 
        assertEquals("Administration constructor- payPerHour", 50.0, payPerHour); 
        assertEquals("Administration constructor- hours", 180.0, hours); 
        assertEquals("Administration constructor- counter", 1, counter); 
    }
    
    
    public void test_setHours() throws NoSuchFieldException, IllegalAccessException 
    {
               
        Administration a = new Administration("Bob", "123", 180, 50);
        a.setHours(150);
       
        double hours = getDoubleValue(a, "hours");
        
       
        assertEquals("Administration setHours(150)", 150.0, hours);
    }    

    
    public void test_setPayHour() throws NoSuchFieldException, IllegalAccessException 
    {
               
        Administration a = new Administration("Bob", "123", 180, 50);
        a.setPayPerHour(15);
       
        double payPerHour = getDoubleValue(a, "payPerHour");
        
       
        assertEquals("Administration setHours(150)", 15.0, payPerHour);
    }  
    
    public void test_getHours() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Administration a = new Administration("Bob", "123", 180, 50);
        
        double hours = a.getHours();
       
        assertEquals("Administration getNumber()", 180.0, hours); 
    }
    
    public void test_getPayPerHour() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Administration a = new Administration("Bob", "123", 180, 50);
        
        double payPerHour = a.getPayPerHour();
       
        assertEquals("Administration getPayPerHour()", 50.0, payPerHour); 
    }
    
    public void test_administartionToString() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Administration a = new Administration("Bob", "123", 180, 50);
        setIntValue(a, "number", 7, 1);
        String expected = "Administration - Worker - name: Bob, id: 123, number: 7, hours: 180.0, payPerHour: 50.0";
        String st = a.toString();
        assertEquals("Administration toSting()", expected, st); 
    }

    public void test_administrationCalSalary()
    {
        Administration a = new Administration("Bob", "123", 180, 50);
        double salary = a.calSalary();
        
        assertEquals("Administratio calSalary()", 180.0 * 50.0, salary);
    }
    
   /*********************************** Engineer **************************/
    
   public void test_EngineerProperties() throws IllegalAccessException, NoSuchFieldException
   {
       Field field = Engineer.class.getDeclaredField("salary");
       int modifiers = field.getModifiers();
       boolean isProtected = Modifier.isProtected(modifiers);
       assertTrue("salary property should be protected", isProtected);
       field = Engineer.class.getDeclaredField("degree");
       modifiers = field.getModifiers();
       isProtected = Modifier.isProtected(modifiers);
       assertTrue("degree property should be protected", isProtected);
       field = Engineer.class.getDeclaredField("years");
       modifiers = field.getModifiers();
       isProtected = Modifier.isProtected(modifiers);
       assertTrue("years property should be protected", isProtected);
       assertEquals("Engineer should be Workerś child", Engineer.class.getSuperclass(), Worker.class);
   }
   
   public void test_EngineerConstructor() throws NoSuchFieldException, IllegalAccessException 
   {
        setStaticIntValue(Worker.class, "counter", 0);
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        
        String name = (String)getObjectValue(e, "name", 1);
        String id = (String)getObjectValue(e, "id", 1);
        int number = getIntValue(e, "number", 1);
        double salary = getDoubleValue(e, "salary");
        int degree = getIntValue(e, "degree");
        int years = getIntValue(e, "years");
        int counter = getStaticIntValue(Worker.class, "counter");
        
        assertEquals("Engineer constructor- name", "Bob", name);        
        assertEquals("Engineer constructor- id", "123", id); 
        assertEquals("AEngineer constructor- number", 0, number); 
        assertEquals("Engineer constructor- salary", 20000.0, salary); 
        assertEquals("Engineer constructor- hours", 1, degree); 
        assertEquals("Engineer constructor- years", 17, years);
        assertEquals("Engineer constructor- counter", 1, counter);
    }
   public void test_EngineerCopyConstructor() throws NoSuchFieldException, IllegalAccessException 
   {
        
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        setStaticIntValue(Worker.class, "counter", 5);
        Engineer copy_e = new Engineer(e);
        int orig_number = getIntValue(e, "number", 1);
        
        String name = (String)getObjectValue(copy_e, "name", 1);
        String id = (String)getObjectValue(copy_e, "id", 1);
        int number = getIntValue(copy_e, "number", 1);
        double salary = getDoubleValue(copy_e, "salary");
        int degree = getIntValue(copy_e, "degree");
        int years = getIntValue(copy_e, "years");
        int counter = getStaticIntValue(Worker.class, "counter");
        
        assertEquals("Engineer constructor- name", "Bob", name);        
        assertEquals("Engineer constructor- id", "123", id); 
        assertEquals("AEngineer constructor- number", orig_number, number); 
        assertEquals("Engineer constructor- salary", 20000.0, salary); 
        assertEquals("Engineer constructor- hours", 1, degree); 
        assertEquals("Engineer constructor- years", 17, years);
        assertEquals("Engineer constructor- counter "
                        + "should not be change in copy constructor",
                        5, counter);
    }
    
    
    public void test_setSalary() throws NoSuchFieldException, IllegalAccessException 
    {
               
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        e.setSalary(10000);
       
        double salary = getDoubleValue(e, "salary");
        
       
        assertEquals("Engineer setSalary(10000)", 10000.0, salary);
    }    

    
    public void test_setDegree() throws NoSuchFieldException, IllegalAccessException 
    {
               
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        e.setDegree(2);
       
        int degree = getIntValue(e, "degree");
        
       
        assertEquals("Engineer setDegree(2)", 2, degree);
    } 
    
    public void test_setYears() throws NoSuchFieldException, IllegalAccessException 
    {
               
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        e.setYears(22);
       
        int years = getIntValue(e, "years");
        
       
        assertEquals("Engineer setYears(22)", 22, years);
    } 
  
    public void test_getSalary() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        
        double salary = e.getSalary();
       
        assertEquals("Engineer getSalary()", 20000.0, salary); 
    }
    
    public void test_getDegree() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        
        int degree = e.getDegree();
       
        assertEquals("Engineer getDegree()", 1, degree);  
    }

    public void test_getYears() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        
        int years = e.getYears();
       
        assertEquals("Engineer getYears()", 17, years);  
    } 
    
    
    public void test_EngineerToString() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        setIntValue(e, "number", 7, 1);
        String expected = "Engineer - Worker - name: Bob, id: 123, number: 7, years: 17, degree: 1, salary: 20000.0";
        String st = e.toString();
        assertEquals("Engineer toSting()", expected, st); 
    }
    
    public void test_engineerCalSalary()
    {
        Engineer e = new Engineer("Bob", "123", 20000, 1, 17);
        double salary = e.calSalary();
        
        assertEquals("Engineer calSalary()", 20000.0, salary);
    }    
   /*********************************** Management **************************/
    
   public void test_ManagementProperties() throws IllegalAccessException, NoSuchFieldException
   {
       Field field = Management.class.getDeclaredField("car");
       int modifiers = field.getModifiers();
       boolean isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("care property should be protected", isPrivate);
       field = Management.class.getDeclaredField("bonus");
       modifiers = field.getModifiers();
       isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("bonuse  property should be protected", isPrivate);

       assertEquals("Management should be Engineer's child", Management.class.getSuperclass(), Engineer.class);
   }
   
   public void test_ManagementConstructor() throws NoSuchFieldException, IllegalAccessException 
   {
        setStaticIntValue(Worker.class, "counter", 0);
        Management m = new Management("Bob", "123", 20000, 1, 17, true, 2000);
        
        String name = (String)getObjectValue(m, "name", 2);
        String id = (String)getObjectValue(m, "id", 2);
        int number = getIntValue(m, "number", 2);
        double salary = getDoubleValue(m, "salary", 1);
        int degree = getIntValue(m, "degree", 1);
        int years = getIntValue(m, "years", 1);
        boolean car = getBooleanValue(m, "car");
        double bonus = getDoubleValue(m, "bonus");
        int counter = getStaticIntValue(Worker.class, "counter");
        
        assertEquals("Management constructor- name", "Bob", name);        
        assertEquals("Management constructor- id", "123", id); 
        assertEquals("Management constructor- number", 0, number); 
        assertEquals("Management constructor- salary", 20000.0, salary); 
        assertEquals("Management constructor- hours", 1, degree); 
        assertEquals("Management constructor- years", 17, years);
        assertEquals("Management constructor- car", true, car);
        assertEquals("Management constructor- bonus", 2000.0, bonus);
        assertEquals("Management constructor- counter", 1, counter);        
    }
   public void test_ManagementCopyConstructor() throws NoSuchFieldException, IllegalAccessException 
   {
        Management m = new Management("Bob", "123", 20000, 1, 17, true, 2000);
        setStaticIntValue(Worker.class, "counter", 5);
        Management copy_m = new Management(m);
        int orig_number = getIntValue(m, "number", 2);

        
        
        String name = (String)getObjectValue(copy_m, "name", 2);
        String id = (String)getObjectValue(copy_m, "id", 2);
        int number = getIntValue(copy_m, "number", 2);
        double salary = getDoubleValue(copy_m, "salary", 1);
        int degree = getIntValue(copy_m, "degree", 1);
        int years = getIntValue(copy_m, "years", 1);
        boolean car = getBooleanValue(copy_m, "car");
        double bonus = getDoubleValue(copy_m, "bonus");
        int counter = getStaticIntValue(Worker.class, "counter");
        
        assertEquals("Management copy constructor- name", "Bob", name);        
        assertEquals("Management copy constructor- id", "123", id); 
        assertEquals("Management copy constructor- number", orig_number, number); 
        assertEquals("Management copy constructor- salary", 20000.0, salary); 
        assertEquals("Management copy constructor- hours", 1, degree); 
        assertEquals("Management copy constructor- years", 17, years);
        assertEquals("Management copy constructor- car", true, car);
        assertEquals("Management copy constructor- bonus", 2000.0, bonus);
        assertEquals("Engineer anagement copy constructor- counter "
                        + "should not be change in copy constructor",
                        5, counter);
                        
    }    
    
    public void test_setBonus() throws NoSuchFieldException, IllegalAccessException 
    {
               
        Management m = new Management("Bob", "123", 20000, 1, 17, true, 2000);
        m.setBonus(1000);
       
        double bonus = getDoubleValue(m, "bonus");
        
       
        assertEquals("Management setBonus(1000)", 1000.0, bonus);
    }    
    
    public void test_setCar() throws NoSuchFieldException, IllegalAccessException 
    {
               
        Management m = new Management("Bob", "123", 20000, 1, 17, true, 2000);
        m.setCar(false);
       
        boolean car = getBooleanValue(m, "car");
        
       
        assertEquals("Management setCar(false)", false, car);
    }  

  
    public void test_getBonus() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Management m = new Management("Bob", "123", 20000, 1, 17, true, 2000);
        
        double bonus = m.getBonus();
       
        assertEquals("Management getBonus()", 2000.0, bonus); 
    }
    public void test_getCar() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Management m = new Management("Bob", "123", 20000, 1, 17, true, 2000);
        
        boolean car = m.getCar();
       
        assertEquals("Management getCar()", true, car); 
    }

    
    
    public void test_ManagementToString() throws NoSuchFieldException, IllegalAccessException 
    {
        
        Management m = new Management("Bob", "123", 20000, 1, 17, true, 2000);
        setIntValue(m, "number", 7, 2);
        String expected = "Management - Engineer - Worker - name: Bob, id: 123, number: 7, " +
                           "years: 17, degree: 1, salary: 20000.0, car: true, bonus: 2000.0";
        String st = m.toString();
        assertEquals("Management toSting()", expected, st); 
    }
    
    public void test_managementCalSalary()
    {
        Management m = new Management("Bob", "123", 20000, 1, 17, true, 2000);
        double salary = m.calSalary();
        
        assertEquals("Engineer calSalary()", 22000.0, salary);
    }        

    public void test_SalaryMain() throws NoSuchFieldException, IllegalAccessException
   {
        setStaticIntValue(Worker.class, "counter", 0);
        String stin = "w" + LS + "Monika"  + LS + "1111" + LS +
                      "w" + LS + "Mona"  + LS + "2222" + LS +
                      "a" + LS + "William" + LS + "3333" + LS + "80.0" + LS + "100.0" + LS +
                      "a" + LS + "Elizabeth" + LS + "4444" + LS + "150.0" + LS + "20.0" + LS +
                      "e" + LS + "Ann" + LS + "5555" + LS + "10" + LS + "2" + LS + "10000.0" + LS +
                      "e" + LS + "George" + LS + "6666" + LS + "20" + LS + "3" + LS + "20000.0" + LS +
                      "m" + LS + "John" + LS + "7777" + LS + "15" + LS + "1" + LS + "21000.0" + LS + "true" + LS + "1000.0" + LS +
                      "m" + LS + "Betty" + LS + "8888" + LS + "10" + LS + "2" + LS + "22000.0" + LS + "false" + LS + "1500.0" + LS +
                      "w" + LS + "Jane"  + LS + "9999" + LS + LS +
                      "a" + LS + "Joana" + LS + "1234" + LS + "10.0" + LS + "30.0" + LS;
                           
        SalaryMain.reader = new Scanner (stin);
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        SalaryMain.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String actual =  removeMessage(st, "Please enter name" + LS);
        actual = removeMessage(actual, "Please enter id" + LS);
        actual = removeMessage(actual, "Please enter employee type" + LS);
        actual = removeMessage(actual, "Please enter years" + LS);
        actual = removeMessage(actual, "Please enter degree" + LS);
        actual = removeMessage(actual, "Please enter salary" + LS);
        actual = removeMessage(actual, "Please enter hours" + LS);
        actual = removeMessage(actual, "Please enter pay per hour" + LS); 
        actual = removeMessage(actual, "Please enter car" + LS);
        actual = removeMessage(actual, "Please enter bonus" + LS);
        String expected = "Worker - name: Monika, id: 1111, number: 0" + LS + 
                          "Salary: 4000.0" + LS +
                          "Worker - name: Mona, id: 2222, number: 1" + LS +
                          "Salary: 4000.0" + LS +
                          "Administration - Worker - name: William, id: 3333, number: 2, hours: 80.0, payPerHour: 100.0" + LS +
                          "Salary: 8000.0" + LS +
                          "Administration - Worker - name: Elizabeth, id: 4444, number: 3, hours: 150.0, payPerHour: 20.0" + LS +
                          "Salary: 3000.0" + LS +
                          "Engineer - Worker - name: Ann, id: 5555, number: 4, years: 10, degree: 2, salary: 10000.0" + LS +
                          "Salary: 10000.0" + LS +
                          "Engineer - Worker - name: George, id: 6666, number: 5, years: 20, degree: 3, salary: 20000.0" + LS +
                          "Salary: 20000.0" + LS +
                          "Management - Engineer - Worker - name: John, id: 7777, number: 6, years: 15, degree: 1, salary: 21000.0, car: true, bonus: 1000.0" + LS +
                          "Salary: 22000.0" + LS +
                          "Management - Engineer - Worker - name: Betty, id: 8888, number: 7, years: 10, degree: 2, salary: 22000.0, car: false, bonus: 1500.0" + LS +
                          "Salary: 23500.0" + LS +
                          "Worker - name: Jane, id: 9999, number: 8" + LS + 
                          "Salary: 4000.0" + LS +
                          "Administration - Worker - name: Joana, id: 1234, number: 9, hours: 10.0, payPerHour: 30.0" + LS + 
                          "Salary: 300.0" + LS +
                          "Total salary is: 98800.0" + LS;
        assertEquals("Window Main(): ", expected, actual);        
    } 
}
