
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

import java.lang.reflect.*;

public class TestZooObject extends TestCase
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

    /*********************************** Animal **************************/
    
   public void test_AnimalProperties() throws SecurityException, NoSuchFieldException
   {
       Field field = Animal.class.getDeclaredField("name");
       int modifiers = field.getModifiers();
       boolean isProtected = Modifier.isProtected(modifiers);
       assertTrue("name property should be protected", isProtected);
       
       field = Animal.class.getDeclaredField("gender");
       modifiers = field.getModifiers();
       isProtected = Modifier.isProtected(modifiers);
       assertTrue("gender property should be protected", isProtected); 
       field = Animal.class.getDeclaredField("energy");
       modifiers = field.getModifiers();
       isProtected = Modifier.isProtected(modifiers);
       assertTrue("energy property should be protected", isProtected);      

   }
    public void test_AnimalConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Animal a = new Animal("Annie", 'F', 3);
        String name = (String)getObjectValue(a, "name");
        char gender = getCharValue(a, "gender");
        int energy = getIntValue(a, "energy");
        
        assertEquals("Animal constructor- name", "Annie", name);        
        assertEquals("Animal constructor- gender", 'F', gender); 
        assertEquals("Animal constructor- energy", 3, energy); 
    }

    public void test_AnimalCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Animal a = new Animal("Annie", 'F', 3);
        Animal copy_a = new Animal(a);
        String name = (String)getObjectValue(copy_a, "name");
        char gender = getCharValue(copy_a, "gender");
        int energy = getIntValue(copy_a, "energy");
        
        assertEquals("Animal copy constructor- name", "Annie", name);        
        assertEquals("Animal copy constructor- gender", 'F', gender); 
        assertEquals("Animal copy constructor- energy", 3, energy); 
        
    }
    public void test_setName() throws NoSuchFieldException, IllegalAccessException 
    {
        Animal a = new Animal("Annie", 'F', 3);
        a.setName("Lucky");
        String name = (String)getObjectValue(a, "name");
        
        assertEquals("Animal setName(\"Lucky\")" , "Lucky", name);        
    }
    
    public void test_setGender() throws NoSuchFieldException, IllegalAccessException 
    {
        Animal a = new Animal("Annie", 'F', 3);
        a.setGender('M');
       
        char gender = getCharValue(a, "gender");
        
       
        assertEquals("Animal setGender('M')", 'M', gender); 
    }
    
    public void test_setEnergy() throws NoSuchFieldException, IllegalAccessException 
    {
        Animal a = new Animal("Annie", 'F', 3);
        a.setEnergy(5);
        int energy = getIntValue(a, "energy");
        assertEquals("Animal setEnergy(5)", 5, energy); 
    }
    
    public void test_getName() throws NoSuchFieldException, IllegalAccessException 
    {
        Animal a = new Animal("Annie", 'F', 3);
        String name = a.getName();
      
        
        assertEquals("Animal getName()", "Annie", name);        
    }
    
    public void test_getGender() throws NoSuchFieldException, IllegalAccessException 
    {
        Animal a = new Animal("Annie", 'F', 3);
        char gender = a.getGender();
       
        assertEquals("Animal getGender()", 'F', gender); 
    }
    
    public void test_getEnergy() throws NoSuchFieldException, IllegalAccessException 
    {
        Animal a = new Animal("Annie", 'F', 3);
        int energy = a.getEnergy();
        
        assertEquals("Animal getEnergy()", 3, energy); 
    }    
    
    public void test_toAnimalString()
    {
        Animal a = new Animal("Annie", 'F', 3);
        
        String expected = "* Animal name: Annie gender: F energy: 3 *";
        String st = a.toString();
        assertEquals("Animel toSting()", expected, st); 
    }

    public void test_AnimalEqualTrue()
    {
        Animal a1 = new Animal("Annie", 'F', 3);
        Animal a2 = new Animal("Annie", 'F', 3);
        boolean expected = true;
        boolean actual = a1.equals(a2);
        assertEquals("Animal equual() - Annie, 'F', 3 - Annie, 'F', 3", expected, actual); 
    }

    public void test_AnimalEqualFalseNme()
    {
        Animal a1 = new Animal("Annie", 'F', 3);
        Animal a2 = new Animal("Jane", 'F', 3);
        boolean expected = false;
        boolean actual = a1.equals(a2);
        assertEquals("Animal equual() - Annie, 'F', 3 - Jane, 'F', 3", expected, actual); 
    }
    
    public void test_AnimalEqualFalseGander()
    {
        Animal a1 = new Animal("Annie", 'M', 3);
        Animal a2 = new Animal("Annie", 'F', 3);
        boolean expected = false;
        boolean actual = a1.equals(a2);
        assertEquals("Animal equual() - Annie, 'M', 3 - Annie, 'F', 3", expected, actual); 
    }
    
    public void test_AnimalEqualFalseEnergy()
    {
        Animal a1 = new Animal("Annie", 'F', 4);
        Animal a2 = new Animal("ANNIE", 'F', 3);
        boolean expected = false;
        boolean actual = a1.equals(a2);
        assertEquals("Animal equual() - Annie, 'F', 4 - Annie, 'F', 3", expected, actual); 
    }
    
    public void test_AnimalEqualsFalseType()
    {
        Animal a1 = new Animal("Annie", 'F', 4);
        Bird b = new Bird("Calimero", 'F', 3, 4);
        boolean expected = false;
        boolean actual = a1.equals(b);
        assertEquals("Animal equuals() - Bird", expected, actual); 
    }
   /*********************************** Mammal **************************/
    
   public void test_MammalProperties() throws SecurityException, NoSuchFieldException
   {
       Field field = Mammal.class.getDeclaredField("milk");
       int modifiers = field.getModifiers();
       boolean isProtected = Modifier.isProtected(modifiers);
       assertTrue("milk property should be protected", isProtected);
       
       field = Mammal.class.getDeclaredField("CAL_IN_MILK");
                                              
       modifiers = field.getModifiers();
       isProtected = Modifier.isProtected(modifiers);
       assertTrue("CAL_IN_MILK property should be protected", isProtected); 
       boolean isFinal = Modifier.isFinal(modifiers);
       assertTrue("CAL_IN_MILK property should be final", isFinal); 
       boolean isStatic = Modifier.isFinal(modifiers);
       assertTrue("CAL_IN_MILK property should be static", isStatic); 
       assertEquals("Mammal should be Animals chhild", Mammal.class.getSuperclass(), Animal.class);
   }
    public void test_MammalConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Mammal m = new Mammal("MMM", 'F', 3, 17);
        Animal a = m;
         
        String name = (String)getObjectValue(a, "name", 1);
        char gender = getCharValue(a, "gender", 1);
        int energy = getIntValue(a, "energy", 1);
        int milk = getIntValue(m, "milk");
        int CAL_IN_MILK = getStaticIntValue(Mammal.class, "CAL_IN_MILK");
        
        assertEquals("Mammal constructor- name", "MMM", name);        
        assertEquals("Mammal constructor- gender", 'F', gender); 
        assertEquals("Mammal constructor- energy", 3, energy); 
        assertEquals("Mammal constructor- milk", 17, milk); 
        
        assertEquals("Mammal constructor- CAL_IN_MILK", 500, CAL_IN_MILK); 
    }
    
     public void test_MammalCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Mammal m = new Mammal("MMM", 'F', 3, 17);
        Mammal copy_m = new Mammal(m);
        Animal copy_a = copy_m;
         
        String name = (String)getObjectValue(copy_a, "name", 1);
        char gender = getCharValue(copy_a, "gender", 1);
        int energy = getIntValue(copy_a, "energy", 1);
        int milk = getIntValue(copy_a, "milk");
        int CAL_IN_MILK = getStaticIntValue(Mammal.class, "CAL_IN_MILK");
        
        assertEquals("Mammal copy constructor- name", "MMM", name);        
        assertEquals("Mammal copy constructor- gender", 'F', gender); 
        assertEquals("Mammal copy constructor- energy", 3, energy); 
        assertEquals("Mammal copy constructor- milk", 17, milk); 
        
        assertEquals("Mammal copy constructor- CAL_IN_MILK", 500, CAL_IN_MILK); 
    }
    public void test_setMilk() throws NoSuchFieldException, IllegalAccessException 
    {
        Mammal m = new Mammal("MMM", 'F', 3, 17);
        m.setMilk(25);
        int milk = getIntValue(m, "milk");
        assertEquals("Mammal setMilk(25)", 25, milk); 
    } 
    
    public void test_getMilk() throws NoSuchFieldException, IllegalAccessException 
    {
        Mammal m = new Mammal("MMM", 'F', 3, 17);
        int milk = m.getMilk();
        
        assertEquals("Mammal getMilk()", 17, milk); 
    }    
    
    public void test_MammaltoString()
    {
        Mammal m = new Mammal("MMM", 'F', 3, 17);
        
        String expected = "** Mammal * Animal name: MMM gender: F energy: 3 * milk: 17 **";
        String st = m.toString();
        assertEquals("Mammal toSting()", expected, st); 
    } 
    
    public void test_calories() throws NoSuchFieldException, IllegalAccessException 
    {
        Mammal m = new Mammal("MMM", 'F', 3, 17);
        
        int calories = m.calories();
        
        int CAL_IN_MILK = getStaticIntValue(Mammal.class, "CAL_IN_MILK");
        int expected = CAL_IN_MILK * 17;
        
        assertEquals("Mammal: calories()", expected, calories);
    }

     public void test_MammalEqualTrue()
    {
        Mammal m1 = new Mammal("Annie", 'M', 3, 5);
        Mammal m2 = new Mammal("Annie", 'M', 3, 5);
        boolean expected = true;
        boolean actual = m1.equals(m2);
        assertEquals("Mammal equual() - Annie, 'F', 3, 5 - Annie, 'F', 3, 5", expected, actual); 
    }
      public void test_MammalEqualFalseName()
    {
        Mammal m1 = new Mammal("Annie", 'M', 3, 5);
        Mammal m2 = new Mammal("Jane", 'M', 3, 5);
        boolean expected = false;
        boolean actual = m1.equals(m2);
        assertEquals("Mammal equual() - Annie, 'F', 3, 5 - Jane, 'F', 3, 5", expected, actual); 
    }
    
      public void test_MammalEqualFalseMilk()
    {
        Mammal m1 = new Mammal("Annie", 'M', 3, 5);
        Mammal m2 = new Mammal("Annie", 'M', 3, 6);
        boolean expected = false;
        boolean actual = m1.equals(m2);
        assertEquals("Mammal equals() - Annie, 'F', 3, 5 - Annie, 'F', 3, 6", expected, actual); 
    }
    
    public void test_MammalEqualsFalseType()
    {
        Mammal m1 = new Mammal("Annie", 'M', 3, 5);
        Bird b = new Bird("Calimero", 'F', 3, 4);
        boolean expected = false;
        boolean actual = m1.equals(b);
        assertEquals("Mammal equuals() - Bird", expected, actual); 
    }
   /*********************************** Dog **************************/
    
   public void test_DogProperties() throws SecurityException, NoSuchFieldException
   {
       Field field = Dog.class.getDeclaredField("bones");
       int modifiers = field.getModifiers();
       boolean isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("bones property should be private", isPrivate);       

       assertEquals("Dog should be Mammals child", Dog.class.getSuperclass(), Mammal.class);
   }
    public void test_DogConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Dog d = new Dog("Simba", 'M', 3, 17, 22);
         
        String name = (String)getObjectValue(d, "name", 2);
        char gender = getCharValue(d, "gender", 2);
        int energy = getIntValue(d, "energy", 2);
        int milk = getIntValue(d, "milk", 1);
        int bones = getIntValue(d, "bones");
        assertEquals("Dog constructor- name", "Simba", name);        
        assertEquals("Dog constructor- gender", 'M', gender); 
        assertEquals("Dog constructor- energy", 3, energy); 
        assertEquals("Dog constructor- milk", 17, milk);
        assertEquals("Dog constructor- bones", 22, bones);
    }
    
    public void test_DogCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Dog d = new Dog("Simba", 'M', 3, 17, 22);
        Dog copy_d = new Dog(d);
        
        String name = (String)getObjectValue(copy_d, "name", 2);
        char gender = getCharValue(copy_d, "gender", 2);
        int energy = getIntValue(copy_d, "energy", 2);
        int milk = getIntValue(copy_d, "milk", 1);
        int bones = getIntValue(copy_d, "bones");
        assertEquals("Dog copy constructor- name", "Simba", name);        
        assertEquals("Dog copy constructor- gender", 'M', gender); 
        assertEquals("Dog copy constructor- energy", 3, energy); 
        assertEquals("Dog copy constructor- milk", 17, milk);
        assertEquals("Dog copy constructor- bones", 22, bones);
    }
    public void test_setBones() throws NoSuchFieldException, IllegalAccessException 
    {
        Dog d = new Dog("Simba", 'M', 3, 17, 22);
        d.setBones(25);
        int bones = getIntValue(d, "bones");
        assertEquals("Dog setBones(25)", 25, bones); 
    } 
    
    public void test_getBones() throws NoSuchFieldException, IllegalAccessException 
    {
        Dog d = new Dog("Simba", 'M', 3, 17, 22);
        int bones = d.getBones();
        
        assertEquals("Dog getBones()", 22, bones); 
    }    
    
    public void test_DogtoString()
    {
        Dog d = new Dog("Simba", 'M', 3, 17, 22);
        
        String expected = "*** Dog ** Mammal * Animal name: Simba gender: M energy: 3 * milk: 17 ** bones: 22 ***";
        String st = d.toString();
        assertEquals("Dog toSting()", expected, st); 
    } 
    
    public void test_goodDogTrue()
    {
        Dog d = new Dog("Nala", 'F', 3, 17, 22);
        
        boolean goodDog = d.goodDog();
        

        assertEquals("Dog: goodDog()", true, goodDog);
    }
          
    public void test_goodDogFalseBonesEqual5()
    {
        Dog d = new Dog("Nala", 'F', 3, 17, 5);
        
        boolean goodDog = d.goodDog();
               assertEquals("Dog: goodDog() - bones equal 5", false, goodDog);
    }
    
     public void test_goodDogFalseBonesBelow5()
    {
        Dog d = new Dog("Nala", 'F', 3, 17, 4);
        
        boolean goodDog = d.goodDog();
               assertEquals("Dog: goodDog() - bones below 5", false, goodDog);
    }
    
     public void test_goodDogFalseMale()
    {
        Dog d = new Dog("Nala", 'M', 3, 17, 10);
        
        boolean goodDog = d.goodDog();
        assertEquals("Dog: goodDog() - Male", false, goodDog);
    }
    
     public void test_goodDogFalseMaleBonesBelow5()
    {
        Dog d = new Dog("Simba", 'M', 3, 17, 2);
        
        boolean goodDog = d.goodDog();
        
         assertEquals("Dog: goodDog() - Male bones below 5", false, goodDog);
    }

      public void test_DogEqualsTrue()
    {
        Dog d1 = new Dog("Annie", 'M', 3, 5, 6);
        Dog d2 = new Dog("Annie", 'M', 3, 5, 6);
        boolean expected = true;
        boolean actual = d1.equals(d2);
        assertEquals("Dog equuals() - Annie, 'M', 3, 5, 6 - Annie, 'M', 3, 5, 6", expected, actual); 
    } 
    
    public void test_DogEqualsFalseGender()
    {
        Dog d1 = new Dog("Annie", 'M', 3, 5, 6);
        Dog d2 = new Dog("Annie", 'F', 3, 5, 6);
        boolean expected = false;
        boolean actual = d1.equals(d2);
        assertEquals("Dog equuals() - Annie, 'M', 3, 5, 6 - Annie, 'F', 3, 5, 6", expected, actual); 
    }  
    
    public void test_DogEqualsFalseMilk()
    {
        Dog d1 = new Dog("Annie", 'F', 3, 4, 6);
        Dog d2 = new Dog("Annie", 'F', 3, 5, 6);
        boolean expected = false;
        boolean actual = d1.equals(d2);
        assertEquals("Dog equuals() - Annie, 'F', 3, 4, 6 - Annie, 'F', 3, 5, 6", expected, actual); 
    }
    
    public void test_DogEqualsFalseBones()
    {
        Dog d1 = new Dog("Annie", 'F', 3, 5, 6);
        Dog d2 = new Dog("Annie", 'F', 3, 5, 7);
        boolean expected = false;
        boolean actual = d1.equals(d2);
        assertEquals("Dog equuals() - Annie, 'F', 3, 5, 6 - Annie, 'F', 3, 5, 7", expected, actual); 
    }
    
    public void test_DogEqualsFalseType()
    {
        Dog d1 = new Dog("Annie", 'F', 3, 5, 6);
        Bird b = new Bird("Calimero", 'F', 3, 4);
        boolean expected = false;
        boolean actual = d1.equals(b);
        assertEquals("Dog equuals() - Bird", expected, actual); 
    }
   /*********************************** Fish **************************/
    
   public void test_FishProperties() throws SecurityException, NoSuchFieldException
   {
       Field field = Fish.class.getDeclaredField("depth");
       int modifiers = field.getModifiers();
       boolean isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("depth property should be private", isPrivate);
       
       field = Fish.class.getDeclaredField("MAX_DEPTH");
                                              
       modifiers = field.getModifiers();
       isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("MAX_DEPTH property should be private", isPrivate); 
       boolean isFinal = Modifier.isFinal(modifiers);
       assertTrue("MAX_DEPTH property should be final", isFinal); 
       boolean isStatic = Modifier.isFinal(modifiers);
       assertTrue("MAX_DEPTH property should be static", isStatic); 
       assertEquals("Fish should be Animals child", Fish.class.getSuperclass(), Animal.class);
   }
    public void test_FishConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Fish f = new Fish("Crystal", 'F', 3, 11);
         
        String name = (String)getObjectValue(f, "name", 1);
        char gender = getCharValue(f, "gender", 1);
        int energy = getIntValue(f, "energy", 1);
        int depth = getIntValue(f, "depth");
        int MAX_DEPTH = getStaticIntValue(Fish.class, "MAX_DEPTH");
        
        assertEquals("Fish constructor- name", "Crystal", name);        
        assertEquals("Fish constructor- gender", 'F', gender); 
        assertEquals("Fish constructor- energy", 3, energy); 
        assertEquals("Fish constructor- depth", 11, depth); 
        
        assertEquals("Fish constructor- MAX_DEPTH", 800, MAX_DEPTH); 
    }
    
    public void test_FishCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Fish f = new Fish("Crystal", 'F', 3, 11);
        Fish copy_f = new Fish(f);
        
        String name = (String)getObjectValue(copy_f, "name", 1);
        char gender = getCharValue(copy_f, "gender", 1);
        int energy = getIntValue(copy_f, "energy", 1);
        int depth = getIntValue(copy_f, "depth");
        int MAX_DEPTH = getStaticIntValue(Fish.class, "MAX_DEPTH");
        
        assertEquals("Fish copy constructor- name", "Crystal", name);        
        assertEquals("Fish copy constructor- gender", 'F', gender); 
        assertEquals("Fish copy constructor- energy", 3, energy); 
        assertEquals("Fish copy constructor- depth", 11, depth); 
        
        assertEquals("Fish constructor- MAX_DEPTH", 800, MAX_DEPTH); 
    }
    public void test_setDepth() throws NoSuchFieldException, IllegalAccessException 
    {
        Fish f = new Fish("Crystal", 'F', 3, 11);
        f.setDepth(25);
        int depth = getIntValue(f, "depth");
        assertEquals("Fish setDepth(25)", 25, depth); 
    } 
    
    public void test_getDepth() throws NoSuchFieldException, IllegalAccessException 
    {
        Fish f = new Fish("Crystal", 'F', 3, 11);
        int depth = f.getDepth();
        
        assertEquals("Fish getDepth()", 11, depth); 
    }    
    
    public void test_FishtoString()
    {
        Fish f = new Fish("Crystal", 'F', 3, 11);
        
        String expected = "** Fish * Animal name: Crystal gender: F energy: 3 * depth: 11 **";
        String st = f.toString();
        assertEquals("Fish toSting()", expected, st); 
    } 
    
    public void test_diff() throws NoSuchFieldException, IllegalAccessException 
    {
        Fish f = new Fish("Crystal", 'F', 3, 11);
        
        int d = f.diff();
        
        int MAX_DEPTH = getStaticIntValue(Fish.class, "MAX_DEPTH");
        int expected = MAX_DEPTH - 11;
        
        assertEquals("Fish: diff()", expected, d);
    }

    public void test_FishEqualsTrue()
    {
        Fish f1 = new Fish("Goldie", 'F', 3, 50);
        Fish f2 = new Fish("Goldie", 'F', 3, 50);
        boolean expected = true;
        boolean actual = f1.equals(f2);
        assertEquals("Fish equuals() - Goldie, 'F', 3, 50 - Goldie, 'F', 3, 50", expected, actual); 
    } 
    
    public void test_FishEqualsFalseEnergy()
    {
        Fish f1 = new Fish("Goldie", 'F', 1, 50);
        Fish f2 = new Fish("Goldie", 'F', 3, 50);
        boolean expected = false;
        boolean actual = f1.equals(f2);
        assertEquals("Fish equuals() - Goldie, 'F', 1, 50 - Goldie, 'F', 3, 50", expected, actual); 
    } 
    
    public void test_FishEqualsFalseDepth()
    {
        Fish f1 = new Fish("Goldie", 'F', 3, 50);
        Fish f2 = new Fish("Goldie", 'F', 3, 60);
        boolean expected = false;
        boolean actual = f1.equals(f2);
        assertEquals("Fish equuals() - Goldie, 'F', 3, 50 - Goldie, 'F', 3, 60", expected, actual); 
    } 
    
    public void test_FishEqualsFalseType()
    {
        Fish f1 = new Fish("Goldie", 'F', 3, 50);
        Bird b = new Bird("Calimero", 'F', 3, 4);
        boolean expected = false;
        boolean actual = f1.equals(b);
        assertEquals("Fish equuals() - Bird", expected, actual); 
    }
    /*********************************** Bird **************************/
    
   public void test_BirdProperties() throws SecurityException, NoSuchFieldException
   {
       Field field = Bird.class.getDeclaredField("nest");
       int modifiers = field.getModifiers();
       boolean isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("nest property should be private", isPrivate);
       
       assertEquals("Bird should be Animals child", Bird.class.getSuperclass(), Animal.class);
   }
    public void test_BirdConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Bird b = new Bird("Tweety", 'F', 3, 7);
         
        String name = (String)getObjectValue(b, "name", 1);
        char gender = getCharValue(b, "gender", 1);
        int energy = getIntValue(b, "energy", 1);
        int nest = getIntValue(b, "nest");       
        
        assertEquals("Fish constructor- name", "Tweety", name);        
        assertEquals("Fish constructor- gender", 'F', gender); 
        assertEquals("Fish constructor- energy", 3, energy); 
        assertEquals("Fish constructor- nest", 7, nest); 
        
        
    }
    
    public void test_BirdCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Bird b = new Bird("Tweety", 'F', 3, 7);
        Bird copy_b = new Bird(b);
        String name = (String)getObjectValue(copy_b, "name", 1);
        char gender = getCharValue(copy_b, "gender", 1);
        int energy = getIntValue(copy_b, "energy", 1);
        int nest = getIntValue(b, "nest");       
        
        assertEquals("Fish constructor- name", "Tweety", name);        
        assertEquals("Fish constructor- gender", 'F', gender); 
        assertEquals("Fish constructor- energy", 3, energy); 
        assertEquals("Fish constructor- nest", 7, nest); 
        
        
    }
    public void test_setNest() throws NoSuchFieldException, IllegalAccessException 
    {
        Bird b = new Bird("Tweety", 'F', 3, 7);
        b.setNest(92);
        int nest = getIntValue(b, "nest");
        assertEquals("Bird setNest(92)", 92, nest); 
    } 
    
    public void test_getNest() throws NoSuchFieldException, IllegalAccessException 
    {
        Bird b = new Bird("Tweety", 'F', 3, 7);
        int nest = b.getNest();
        
        assertEquals("Bird getNest()", 7, nest); 
    }    
    
    public void test_BirdtoString()
    {
        Bird b = new Bird("Tweety", 'F', 3, 7);
        
        String expected = "** Bird * Animal name: Tweety gender: F energy: 3 * nest: 7 **";
        String st = b.toString();
        assertEquals("Bird toSting()", expected, st); 
    } 
    
    public void test_BirdEqualsTrue()
    {
        Bird b1 = new Bird("Calimero", 'F', 3, 5);
        Bird b2 = new Bird("Calimero", 'F', 3, 5);
        boolean expected = true;
        boolean actual = b1.equals(b2);
        assertEquals("Bird equuals() - Calimero, 'F', 3, 5 - Calimero, 'F', 3, 5", expected, actual); 
    } 
    
    public void test_BirdEqualsFalseEnergy()
    {
        Bird b1 = new Bird("Calimero", 'F', 1, 5);
        Bird b2 = new Bird("Calimero", 'F', 3, 5);
        boolean expected = false;
        boolean actual = b1.equals(b2);
        assertEquals("Fish equuals() - Calimero, 'F', 1, 5 - Calimero, 'F', 3, 5", expected, actual); 
    } 
    
    public void test_BirdEqualsFalseNest()
    {
        Bird b1 = new Bird("Calimero", 'F', 3, 5);
        Bird b2 = new Bird("Calimero", 'F', 3, 6);
        boolean expected = false;
        boolean actual = b1.equals(b2);
        assertEquals("Fish equuals() - Calimero, 'F', 3, 5 - Calimero, 'F', 3, 6", expected, actual); 
    } 
    
    public void test_BirdEqualsFalseType()
    {
        Fish f = new Fish("Goldie", 'F', 3, 50);
        Bird b = new Bird("Calimero", 'F', 3, 4);
        boolean expected = false;
        boolean actual = b.equals(f);
        assertEquals("Bird equuals() - Fish", expected, actual); 
    }
    
   /*********************************** Person **************************/
    
   public void test_PersonProperties() throws SecurityException, NoSuchFieldException
   {
       Field field = Person.class.getDeclaredField("name");
       int modifiers = field.getModifiers();
       boolean isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("Person's name property should be private", isPrivate);
       
       field = Person.class.getDeclaredField("id");
       modifiers = field.getModifiers();
       isPrivate = Modifier.isPrivate(modifiers);
       assertTrue("Person's id property should be private", isPrivate);
   }
   
    public void test_PersonConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Person p = new Person("Ronna", "1234");
         
        String name = (String)getObjectValue(p, "name");
        String id = (String)getObjectValue(p, "id");     
        
        assertEquals("Person constructor- name", "Ronna", name);        
        assertEquals("Person constructor- id", "1234", id); 
         
    }
    
    public void test_PersonCopyConstructor() throws NoSuchFieldException, IllegalAccessException
    {
        Person p = new Person("Ronna", "1234");
        Person copy_p = new Person(p);
        String name = (String)getObjectValue(copy_p, "name");
        String id = (String)getObjectValue(copy_p, "id");
      
        
        assertEquals("Person copy constructor- name", "Ronna", name);        
        assertEquals("Fish copy constructor- id", "1234", id); 

        
    }
    public void test_PersonSetName() throws NoSuchFieldException, IllegalAccessException 
    {
        Person p = new Person("Ronna", "1234");
        p.setName("Donna");
        String name = (String)getObjectValue(p, "name");
        assertEquals("Person setName(\"Donna\")", "Donna", name); 
    } 
    public void test_PersonSetId() throws NoSuchFieldException, IllegalAccessException 
    {
        Person p = new Person("Ronna", "1234");
        p.setId("5678");
        String id = (String)getObjectValue(p, "id");
        assertEquals("Person setid\"5678\")", "5678", id); 
    }     
    public void test_PersonGetName() throws NoSuchFieldException, IllegalAccessException 
    {
        Person p = new Person("Ronna", "1234");
        String name = p.getName();
        
        assertEquals("Person getName()", "Ronna", name); 
    }    
    public void test_PersonGetId() throws NoSuchFieldException, IllegalAccessException 
    {
        Person p = new Person("Ronna", "1234");
        String id = p.getId();
        
        assertEquals("Person getId()", "1234", id); 
    }     
    public void test_PersonToString()
    {
        Person p = new Person("Ronna", "1234");
        
        String expected = "* Person name: Ronna id: 1234 *";
        String st = p.toString();
        assertEquals("Person toSting()", expected, st); 
    } 
    
    public void test_PersonEqualsTrue()
    {
        Person p1 = new Person("Ronna", "1234");
        Person p2 = new Person("Ronna", "1234");        
        boolean expected = true;
        boolean actual = p1.equals(p2);
        assertEquals("Person equals() - Ronna, 1234 - Ronna, 1234", expected, actual); 
    } 
    
    public void test_PersonEqualsFalseName()
    {
        Person p1 = new Person("Ronna", "1234");
        Person p2 = new Person("Donna", "1234");   
        boolean expected = false;
        boolean actual = p1.equals(p2);
        assertEquals("Person equals() - Ronna, 1234 - Donna, 1234", expected, actual); 
    } 
    
    public void test_PersonEqualsFalseId()
    {
        Person p1 = new Person("Ronna", "1234");
        Person p2 = new Person("Ronna", "7890");   
        boolean expected = false;
        boolean actual = p1.equals(p2);
        assertEquals("Person equals() - Ronna, 1234 - Ronna, 7890", expected, actual); 
    } 
    
    public void test_PersonEqualsFalseType()
    {
        Person p = new Person("Ronna", "1234");
        Bird b = new Bird("Calimero", 'F', 3, 4);
        boolean expected = false;
        boolean actual = p.equals(b);
        assertEquals("Person equuals() - Bird", expected, actual); 
    }
    
        

    public void test_ZooMain() throws NoSuchFieldException, IllegalAccessException
   {
        
        
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ZooObjectMain.main(arr);

        String actual = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
 
        String expected = "First item found" + LS + "Sum bones is: " + 12 + LS + "1 rons found" + LS +
                          "*** Dog ** Mammal * Animal name: bobo gender: M energy: 4 * milk: 5 ** bones: 6 ***" + LS +
                          "** Fish * Animal name: goldie gender: F energy: 7 * depth: 60 **" + LS +
                          "** Mammal * Animal name: monika gender: F energy: 3 * milk: 6 **" + LS +
                          "* Person name: ron id: 1234 *" + LS +
                          "*** Dog ** Mammal * Animal name: bobo gender: M energy: 4 * milk: 5 ** bones: 6 ***" + LS;
        assertEquals("Window Main(): ", expected, actual);        
    }
   
}
