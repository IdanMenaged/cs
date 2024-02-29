
import java.util.*;
import java.io.*;
import java.nio.charset.*;
import org.junit.Test;
import org.junit.Ignore;

import junit.framework.*;

import java.lang.reflect.*;

public class TestZooPoly extends TestCase
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

    public void test_ZooMain() throws NoSuchFieldException, IllegalAccessException
   {
        
        String stin = "Monika"  + LS + "F" + LS + "3" + LS + "4" + LS +
                      "Mona"  + LS + "F" + LS + "5" + LS + "6" + LS +
                      "Wiliam" + LS + "M" + LS + "7" + LS + "7" + LS +
                      "Kaspion" + LS + "M" + LS + "8" + LS + "9" + LS +
                      "Goldie" + LS + "F" + LS + "10" + LS + "11" + LS +
                      "Tweety" + LS + "F" + LS + "12" + LS + "13" + LS +
                      "Calimero" + LS + "M" + LS + "14" + LS + "15" + LS +
                      "Pluto" + LS + "M" + LS + "16" + LS + "17" + LS + "18" + LS +
                      "Lady" + LS + "F" + LS + "19" + LS + "20" + LS + "21"+ LS;                         
        ZooPoly.reader = new Scanner (stin);
        String [] arr = {};
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        
        ZooPoly.main(arr);

        String st = baos.toString();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String actual = removeMessage(st, "Mammal: Please enter name gender energy milk" + LS);
        actual = removeMessage(actual, "Fish: Please enter name gender energy depth" + LS);
        actual = removeMessage(actual, "Bird: Please enter name gender energy nest" + LS);
        actual = removeMessage(actual, "Dog: Please enter name gender energy milk bones" + LS);
 
        String expected = "** Mammal * Animal name: Monika gender: F energy: 3 * milk: 4 **" + LS +
                          "** Mammal * Animal name: Mona gender: F energy: 5 * milk: 6 **" + LS +
                          "** Mammal * Animal name: Wiliam gender: M energy: 7 * milk: 7 **" + LS +
                          "** Fish * Animal name: Kaspion gender: M energy: 8 * depth: 9 **" + LS +
                          "** Fish * Animal name: Goldie gender: F energy: 10 * depth: 11 **" + LS +
                          "** Bird * Animal name: Tweety gender: F energy: 12 * nest: 13 **" + LS +
                          "** Bird * Animal name: Calimero gender: M energy: 14 * nest: 15 **" + LS +
                          "*** Dog ** Mammal * Animal name: Pluto gender: M energy: 16 * milk: 17 ** bones: 18 ***" + LS +
                          "*** Dog ** Mammal * Animal name: Lady gender: F energy: 19 * milk: 20 ** bones: 21 ***" + LS +
                          "Best swimmer is: Goldie" + LS + "Sum bones is: " + 39 + LS;
        assertEquals("Window Main(): ", expected, actual);        
    }
}
