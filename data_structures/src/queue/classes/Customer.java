package queue.classes;

public class Customer4Students
{ 
    private String name;    // customer's name
    private int age;        // customer's age
    private Queue<Measurement> measures; // measurements ordered by month
    
    
    // getters
    public String getName()
    {
        return this.name;       
    }
    
    public int getAge()
    {
        return this.age;       
    }  
    
    // setters
    public void setName(String name)
    {
        this.name = name;       
    }
    
    public void setAge(int age)
    {
         this.age = age;       
    } 
    
   
}

