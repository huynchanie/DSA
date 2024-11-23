package ASM;

public class LinkedListQueue {

    class Node {

        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node front;
    private Node rear;
    private int size;

    public LinkedListQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Add an element to the rear of the queue
    public void enqueue(int item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Remove and return the element at the front of the queue
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot remove element.");
            return -1;
        }
        int removedData = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return removedData;
    }

    // Peek the element at the front of the queue without removing it
    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        return front.data;
    }

    // Get the size of the queue
    public int getSize() {
        return size;
    }

    // Display all elements in the queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        Node current = front;
        System.out.print("Elements in the queue: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Main method to demonstrate the LinkedListQueue
    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();
        // Add elements to the queue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();

        System.out.println("Dequeued: " + queue.dequeue());
        queue.display();
        // Check size
        System.out.println("Queue size: " + queue.getSize());
        // Check empty state
        System.out.println("Is queue empty? " + queue.isEmpty());
        // Peek the front element
        System.out.println("Peek: " + queue.peek());
    }
}
