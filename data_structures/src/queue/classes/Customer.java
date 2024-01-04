package queue.classes;

import queue.Queue;

public class Customer
{ 
    private String name;    // customer's name
    private int age;        // customer's age
    private Queue<Measurement> measures; // measurements ordered by month


    /**
     * create a new customer with an empty queue for measures
     * @param name name
     * @param age age
     */
    public Customer(String name, int age) {
        this.name = name;
        this.age = age;

        this.measures = new Queue<>();
    }
    
    
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


    /**
     * add a new measure into the queue
     * @param month month the measure was taken
     * @param weight in kg
     */
    public void addMeasure(int month, double weight) {
        Measurement measurement = new Measurement(month, weight);
        this.measures.insert(measurement);
    }

    /**
     * find the best monthly decrease
     * if there are <=1 measures return -999
     * @return best monthly decrease
     */
    public double bestMeasure() {
        Queue<Measurement> measuresCopy = new Queue<>();
        Measurement curr, prev;
        double best, currDecrease;

        // q is empty
        if (this.measures.isEmpty()) {
            return -999;
        }

        // q has one measure only
        prev = this.measures.head();
        measuresCopy.insert(this.measures.remove());
        if (this.measures.isEmpty()) {
            this.measures.insert(prev);
            return -999;
        }

        // compare measures
        curr = this.measures.head();
        measuresCopy.insert(this.measures.remove());
        best = (curr.getWeight() - prev.getWeight()) / (curr.getMonth() - prev.getMonth());
        while (!this.measures.isEmpty()) {
            prev = curr;

            // compare decreases
            currDecrease = (curr.getWeight() - prev.getWeight()) / (curr.getMonth() - prev.getMonth());
            best = Math.min(currDecrease, best);

            // remove and store copy
            curr = this.measures.head();
            measuresCopy.insert(this.measures.remove());
        }

        // restore measures
        while (!measuresCopy.isEmpty()) {
            this.measures.insert(measuresCopy.remove());
        }

        return best;
    }
}

