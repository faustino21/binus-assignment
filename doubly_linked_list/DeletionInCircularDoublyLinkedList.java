package doubly_linked_list;

class DNode {
    int data;
    DNode next;
    DNode prev;

    DNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class DeletionInCircularDoublyLinkedList {
    DNode head;

    // Helper method to add nodes at the end
    public void add(int data) {
        DNode newNode = new DNode(data);
        if (head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
            head = newNode;
            return;
        }
        DNode tail = head.prev;
        tail.next = newNode;
        newNode.prev = tail;
        newNode.next = head;
        head.prev = newNode;
    }

    // 1. Removing the first node
    public void removeFirst() {
        if (head == null) {
            System.out.println("List is empty, nothing to delete.");
            return;
        }
        System.out.println("Removing first node: " + head.data);
        if (head.next == head) {
            // Only one node in the list
            head = null;
            return;
        }
        DNode tail = head.prev;
        head = head.next;
        head.prev = tail;
        tail.next = head;
    }

    // 2. Removing the last node
    public void removeLast() {
        if (head == null) {
            System.out.println("List is empty, nothing to delete.");
            return;
        }
        DNode tail = head.prev;
        System.out.println("Removing last node: " + tail.data);
        if (tail == head) {
            // Only one node in the list
            head = null;
            return;
        }
        DNode newTail = tail.prev;
        newTail.next = head;
        head.prev = newTail;
    }

    // 3. Removing node in the middle (by value)
    public void removeMiddle(int value) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        // If head contains the value, it's actually removeFirst
        if (head.data == value) {
            removeFirst();
            return;
        }

        DNode current = head.next;
        while (current != head && current.data != value) {
            current = current.next;
        }

        if (current == head) {
            System.out.println("Node with value " + value + " not found.");
        } else {
            System.out.println("Removing node with value: " + value);
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
    }

    // Helper method to display the list
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        DNode current = head;
        do {
            System.out.print(current.data + " <-> ");
            current = current.next;
        } while (current != head);
        System.out.println("(back to head: " + head.data + ")");
    }

    public static void main(String[] args) {
        DeletionInCircularDoublyLinkedList list = new DeletionInCircularDoublyLinkedList();

        // Initializing the list
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        System.out.println("Initial List:");
        list.display();

        // 1. Removing the first node
        list.removeFirst();
        list.display();

        // 2. Removing the last node
        list.removeLast();
        list.display();

        // 3. Removing a node in the middle (e.g., node with value 30)
        list.removeMiddle(30);
        list.display();
    }
}
