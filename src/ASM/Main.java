package ASM;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagement management = new StudentManagement();

        boolean exit = false;
        while (!exit) {
            printMenu();
            
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    loadFromFile(management);
                    break;
                case 2:
                    management.displayAllStudents();
                    break;
                case 3:
                    addStudent(scanner, management);
                    break;
                case 4:
                    editStudent(scanner, management);
                    break;
                case 5:
                    removeStudent(scanner, management);
                    break;
                case 6:
                    System.out.println("Choose sorting order:");
                    System.out.println("1. Ascending");
                    System.out.println("2. Descending");
                    System.out.print("Enter your choice: ");
                    int sortChoice;
                    try {
                        sortChoice = Integer.parseInt(scanner.nextLine());
                        if (sortChoice == 1) {
                            System.out.println("Sorting students by marks in ascending order...");
                            management.sortStudentsAscending();; 
                        } else if (sortChoice == 2) {
                            System.out.println("Sorting students by marks in descending order...");
                            management.sortStudentsDescending(); 
                        } else {
                            System.out.println("Invalid choice. Returning to main menu.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter 1 or 2.");
                    }
                    break;

                case 7:
                    searchStudent(scanner, management);
                    break;
                case 8:
                    saveToFile(management);
                    break;
                case 9:
                    exit = true;
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n==== Student Management System ====");
        System.out.println("1. Load Students from File");
        System.out.println("2. Display All Students");
        System.out.println("3. Add a New Student");
        System.out.println("4. Edit a Student");
        System.out.println("5. Remove a Student");
        System.out.println("6. Sort Students by Marks");
        System.out.println("7. Search for a Student by Name");
        System.out.println("8. Save Students to File");
        System.out.println("9. Exit");
        System.out.print("Choose an option: ");
    }

    private static void loadFromFile(StudentManagement management) {
        String filePath = "D:/SE06303/Kì 4/DSA/src/ASM/student.csv"; // Đường dẫn file
        management.loadFromFile(filePath);
    }

    private static void saveToFile(StudentManagement management) {
        String filePath = "D:/SE06303/Kì 4/DSA/src/ASM/student.csv"; // Đường dẫn file
        management.saveToFile(filePath);
    }

    private static void addStudent(Scanner scanner, StudentManagement management) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        double marks;
        while (true) {
            System.out.print("Enter Student Marks (0-10): ");
            try {
                marks = Double.parseDouble(scanner.nextLine());
                if (marks >= 0 && marks <= 10) {
                    break;
                }
                System.out.println("Marks must be between 0 and 10.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks. Please enter a valid number.");
            }
        }

        management.addStudent(new Student(id, name, marks));
    }

    private static void editStudent(Scanner scanner, StudentManagement management) {
        System.out.print("Enter Student ID to edit: ");
        String id = scanner.nextLine();
        System.out.print("Enter New Name: ");
        String newName = scanner.nextLine();

        double newMarks;
        while (true) {
            System.out.print("Enter New Marks (0-10): ");
            try {
                newMarks = Double.parseDouble(scanner.nextLine());
                if (newMarks >= 0 && newMarks <= 10) {
                    break;
                }
                System.out.println("Marks must be between 0 and 10.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks. Please enter a valid number.");
            }
        }

        management.editStudent(id, newName, newMarks);
    }

    private static void removeStudent(Scanner scanner, StudentManagement management) {
        System.out.print("Enter the Student ID to remove: ");
        String id = scanner.nextLine();
        management.removeStudent(id);
    }

    private static void searchStudent(Scanner scanner, StudentManagement management) {
        System.out.print("Enter the Student Name to search: ");
        String name = scanner.nextLine();
        management.findStudentByName(name);
    }
}
