package ASM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortComparison {

    public static void main(String[] args) {
        String filePath = "D:/SE06303/Kì 4/DSA/src/ASM/students.csv"; // Đường dẫn tới file CSV chứa dữ liệu sinh viên
        List<Student> students = loadFromFile(filePath);
        System.out.println("\n=== Total Valid Records in File ===");
        System.out.printf("Number of valid student records: %d%n", students.size());

        if (students.isEmpty()) {
            System.out.println("No student data found. Exiting.");
            return;
        }

        // Chuyển danh sách sinh viên thành mảng
        Student[] bubbleSortArray = students.toArray(new Student[0]);
        Student[] mergeSortArray = students.toArray(new Student[0]);

        // Tính thời gian cho Bubble Sort
        long bubbleSortTime = measureExecutionTime(() -> bubbleSort(bubbleSortArray));

        // Tính thời gian cho Merge Sort
        long mergeSortTime = measureExecutionTime(() -> mergeSort(mergeSortArray, 0, mergeSortArray.length - 1));

        // Kết quả
        
        System.out.println("\n=== Total Valid Records in File ===");
        System.out.printf("Number of valid student records: %d%n", students.size());
        System.out.println("\n=== Time Comparison ===");
        System.out.printf("Bubble Sort Time: %.9f seconds%n", bubbleSortTime / 1_000_000_000.0);
        System.out.printf(" Merge Sort Time: %.9f seconds%n", mergeSortTime / 1_000_000_000.0);
    }

    // Hàm đo thời gian thực hiện
    public static long measureExecutionTime(Runnable sortingMethod) {
        long startTime = System.nanoTime();
        sortingMethod.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Load dữ liệu từ file CSV
    public static List<Student> loadFromFile(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    double marks = Double.parseDouble(parts[2].trim());
                    students.add(new Student(id, name, marks));
                }
            }
            System.out.println("Data loaded successfully from file: " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return students;
    }

    // Bubble Sort
    public static void bubbleSort(Student[] arr) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].getMarks() > arr[i + 1].getMarks()) {
                    // Swap
                    Student temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
    }

    // Merge Sort
    public static void mergeSort(Student[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Student[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Student[] L = new Student[n1];
        Student[] R = new Student[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].getMarks() <= R[j].getMarks()) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    // In danh sách sinh viên
    public static void printArray(Student[] arr) {
        for (Student student : arr) {
            System.out.println(student);
        }
    }
}
