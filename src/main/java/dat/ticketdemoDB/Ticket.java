package dat.ticketdemoDB;

public class Ticket {
    
    // Template
    int id;
    Student students;
    
    // Constructors
    public Ticket(Student students) {
        this.students = students;
    }
    
    public Ticket(int id, Student students) {    
        this.id = id;
        this.students = students;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public Student getStudents() {
        return students;
    }

    public void setStudents(Student students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", students=" + students + '}';
    }
}
