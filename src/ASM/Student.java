package ASM;

public class Student {

    private String studentID;
    private String name;
    private double marks;

    interface IStudent {
        String getStudentID();
        String getName();
        void setName(String newName);
        double getMarks();
        void setMarks(double newMarks);
        String getRanking();
    }
    interface IStudentManagement {
    void addStudent(Student student);
    Student findStudent(String studentID);
    void editStudent(String studentID, String newName, double newMarks);
    void removeStudent(String studentID);
    void sortStudents(boolean ascending); // true: ascending, false: descending
    void displayAllStudents();
    void loadFromFile(String filePath);
    void saveToFile(String filePath);
    void findStudentsByName(String name);
    
}


    // Constructor
    public Student(String studentID, String name, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.marks = marks;
    }

    // Getter and Setter
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        if (marks >= 0 && marks <= 10) {
            this.marks = marks;
        } else {
            throw new IllegalArgumentException("Marks must be between 0 and 10.");
        }
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    // Ranking logic
    public String getRanking() {
        if (marks < 5.0) {
            return "Fail";
        } else if (marks < 6.5) {
            return "Medium";
        } else if (marks < 7.5) {
            return "Good";
        } else if (marks < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Marks: %.2f, Rank: %s",
                studentID, name, marks, getRanking());
    }
}
