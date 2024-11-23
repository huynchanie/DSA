package ASM;

import java.io.*;

public class StudentManagement {

    private Node head; // Custom LinkedList implementation

    // Constructor
    public StudentManagement() {
        this.head = null;
    }
    

    // Add a student to the list
    public void addStudent(Student student) {
        if (findStudent(student.getStudentID()) != null) {
            System.out.println("Student ID already exists. Cannot add this student.");
            return;
        }

        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Student added successfully.");
    }

    // Find a student by ID
    public Student findStudent(String studentID) {
        Node current = head;
        while (current != null) {
            if (current.data.getStudentID().equals(studentID)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // Edit a student's information
    public void editStudent(String studentID, String newName, double newMarks) {
        Student student = findStudent(studentID);
        if (student != null) {
            student.setName(newName);
            student.setMarks(newMarks);
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    // Remove a student by ID
    public void removeStudent(String studentID) {
        if (head == null) {
            System.out.println("No students in the list.");
            return;
        }

        // Check if the head is the student to be removed
        if (head.data.getStudentID().equals(studentID)) {
            head = head.next;
            System.out.println("Student removed successfully.");
            return;
        }

        Node current = head;
        while (current.next != null && !current.next.data.getStudentID().equals(studentID)) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student ID not found.");
        }
    }

    // Sort students by marks using Bubble Sort
// Sort students by marks in ascending order
    public void sortStudentsAscending() {
        if (head == null || head.next == null) {
            System.out.println("No need to sort. List is empty or has only one student.");
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.data.getMarks() > current.next.data.getMarks()) { 
                    //Swap
                    Student temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);

        System.out.println("Students sorted by marks in ascending order.");
        displayAllStudents(); 
    }

// Sort students by marks in descending order
    public void sortStudentsDescending() {
        if (head == null || head.next == null) {
            System.out.println("No need to sort. List is empty or has only one student.");
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.data.getMarks() < current.next.data.getMarks()) { // Sắp xếp giảm dần
                    // Hoán đổi
                    Student temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);

        System.out.println("Students sorted by marks in descending order.");
        displayAllStudents(); // Hiển thị danh sách sau khi sắp xếp
    }

    // Display all students
    public void displayAllStudents() {
        if (head == null) {
            System.out.println("No students to display.");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    // Load data from file
    public void loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            int successCount = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                try {
                    // Split dòng thành các phần tử
                    String[] data = line.split(",");
                    if (data.length != 3) {
                        throw new IllegalArgumentException("Invalid format: Each line must have exactly 3 fields.");
                    }

                    String id = data[0].trim();
                    String name = data[1].trim();
                    double marks = Double.parseDouble(data[2].trim());

                    if (id.isEmpty() || name.isEmpty()) {
                        throw new IllegalArgumentException("ID or Name cannot be empty.");
                    }
                    if (marks < 0 || marks > 10) {
                        throw new IllegalArgumentException("Marks must be between 0 and 10.");
                    }

                    // Thêm sinh viên vào danh sách
                    addStudent(new Student(id, name, marks));
                    successCount++;
                } catch (Exception e) {
                    System.err.printf("Error in line %d: %s (Content: %s)%n", lineNumber, e.getMessage(), line);
                }
            }

            System.out.printf("Load completed: %d students added.%n", successCount);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    // Save data to file
    public void saveToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Node current = head;
            while (current != null) {
                Student student = current.data;
                writer.write(student.getStudentID() + "," + student.getName() + "," + student.getMarks());
                writer.newLine();
                current = current.next;
            }
            System.out.println("Data successfully saved to file.");
        } catch (IOException e) {
            System.err.println("An error occurred while saving the file: " + e.getMessage());
        }
    }

    // Find a student by name
    public void findStudentByName(String name) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.data.getName().equalsIgnoreCase(name)) {
                System.out.println(current.data);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No student found with name: " + name);
        }
    }

    // Node class for LinkedList
    private static class Node {

        public Student data;
        public Node next;

        public Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }
}
