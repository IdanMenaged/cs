package queue.classes;

import Queue;

public class Customer
{ 
    private String name;    // customer's name
    private int age;        // customer's age
    private Queue<Measurement> measures; // measurements ordered by month


    public static void main(String[] args) {
        Customer c = new Customer("n", 1);
        c.addMeasure(1, 49.5);
        c.addMeasure(2, 49);
        c.addMeasure(4, 48);
        c.addMeasure(5, 46);

        System.out.println(c.bestMeasure());
    }


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
        currDecrease = (prev.getWeight() - curr.getWeight()) / (curr.getMonth() - prev.getMonth());
        best = Math.max(currDecrease, 0);

        while (!this.measures.isEmpty()) {
            // remove and store copy
            prev = curr;
            curr = this.measures.head();
            measuresCopy.insert(this.measures.remove());

            // compare decreases
            currDecrease = (prev.getWeight() - curr.getWeight()) / (curr.getMonth() - prev.getMonth());
            best = Math.max(currDecrease, best); // TODO: check if i need min or max

        }

        // restore measures
        while (!measuresCopy.isEmpty()) {
            this.measures.insert(measuresCopy.remove());
        }

        return best;
    }
}

