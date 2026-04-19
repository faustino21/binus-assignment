package doubly_linked_list;

// Introduction to Circular Doubly Linked List
// The last node's next points to the head, and the head's prev points to the last node.
// Traversal is possible in both directions and the list loops continuously.
public class CircularDoublyLinkedList {
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

    // Append a node to the end of the circular doubly linked list
    public void append(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            // First node points to itself in both directions
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

    // Traverse forward starting from head
    public void displayForward() {
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

    // Traverse backward starting from the tail
    public void displayBackward() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        Node tail = head.prev;
        Node current = tail;
        do {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        } while (current != tail);
        System.out.println("(back to tail: " + tail.data + ")");
    }

    public static void main(String[] args) {
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();

        list.append(10);
        list.append(20);
        list.append(30);
        list.append(40);

        System.out.println("Forward traversal:");
        list.displayForward(); // 10 <-> 20 <-> 30 <-> 40 <-> (back to head: 10)

        System.out.println("Backward traversal:");
        list.displayBackward(); // 40 <-> 30 <-> 20 <-> 10 <-> (back to tail: 40)
    }
}
