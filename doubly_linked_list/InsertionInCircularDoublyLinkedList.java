package doubly_linked_list;

public class InsertionInCircularDoublyLinkedList {
    static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;

    // 1. Inserting an Element at the Head
    public void insertAtHead(int data) {
        System.out.println("Inserting " + data + " at the head.");
        Node newNode = new Node(data);
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
            return;
        }
        Node tail = head.prev;
        newNode.next = head;
        newNode.prev = tail;
        tail.next = newNode;
        head.prev = newNode;
        head = newNode;
    }

    // 2. Inserting an Element at the Tail
    public void insertAtTail(int data) {
        System.out.println("Inserting " + data + " at the tail.");
        Node newNode = new Node(data);
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
            return;
        }
        Node tail = head.prev;
        tail.next = newNode;
        newNode.prev = tail;
        newNode.next = head;
        head.prev = newNode;
    }

    // 3. Inserting an Element at the middle (at a specific position)
    public void insertAtMiddle(int data, int position) {
        System.out.println("Inserting " + data + " at position " + position + ".");
        if (position <= 0 || head == null) {
            insertAtHead(data);
            return;
        }

        Node current = head;
        for (int i = 0; i < position - 1; i++) {
            current = current.next;
            // If we looped back to head, the position is out of bounds
            if (current == head) {
                System.out.println("Position out of bounds. Inserting at tail instead.");
                insertAtTail(data);
                return;
            }
        }

        Node newNode = new Node(data);
        newNode.next = current.next;
        newNode.prev = current;
        current.next.prev = newNode;
        current.next = newNode;
    }

    // Helper method to display the list
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.data + " <-> ");
            current = current.next;
        } while (current != head);
        System.out.println("(back to head: " + head.data + ")");
    }

    public static void main(String[] args) {
        InsertionInCircularDoublyLinkedList list = new InsertionInCircularDoublyLinkedList();

        // 1. Insert at Head
        list.insertAtHead(20);
        list.insertAtHead(10);
        list.display(); // Expected: 10 <-> 20 <-> (back to head: 10)

        // 2. Insert at Tail
        list.insertAtTail(40);
        list.insertAtTail(50);
        list.display(); // Expected: 10 <-> 20 <-> 40 <-> 50 <-> (back to head: 10)

        // 3. Insert in the Middle (at position 2, after 20)
        list.insertAtMiddle(30, 2);
        list.display(); // Expected: 10 <-> 20 <-> 30 <-> 40 <-> 50 <-> (back to head: 10)

        // Insert at another middle position
        list.insertAtMiddle(35, 3);
        list.display(); // Expected: 10 <-> 20 <-> 30 <-> 35 <-> 40 <-> 50 <-> (back to head: 10)
    }
}
