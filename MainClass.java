public class MainClass {

    public static void main(String[] args) {
        MainClass list = new MainClass();
        for (int i = 0; i < args.length; i++) {
            list.push(Integer.parseInt(args[i]));
        }

        list.printlist(list.head);
    }

    node head;
    node sorted;

    public class node {
        int val;
        node next;

        public node(int val) {
            this.val = val;
        }
    }

    void push(int val) {
        /* allocate node */
        node newnode = new node(val);
        /* link the old list of the new node */
        newnode.next = head;
        /* move the head to point to the new node */
        head = newnode;
    }

    void insertionSort(node headref) {
        // Initialize sorted linked list
        sorted = null;
        node current = headref;
        // Traverse the given linked list and insert every
        // node to sorted
        while (current != null) {
            // Store next for next iteration
            node next = current.next;
            // insert current in sorted linked list
            sortedInsert(current);
            // Update current
            current = next;
        }
        // Update head_ref to point to sorted linked list
        head = sorted;
    }

    void sortedInsert(node newnode) {
        /* Special case for the head end */
        if (sorted == null || sorted.val >= newnode.val) {
            newnode.next = sorted;
            sorted = newnode;
        } else {
            node current = sorted;
            /* Locate the node before the point of insertion */
            while (current.next != null && current.next.val < newnode.val) {
                current = current.next;
            }
            newnode.next = current.next;
            current.next = newnode;
        }
    }

    void printlist(node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

}
