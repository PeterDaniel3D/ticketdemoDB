package dat.ticketdemoDB;

public class Student {

    // Template
    String name;
    
    // Constructor
    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + '}';
    }
}
