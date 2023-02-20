public class MainClass {
    // Node class to represent a node in the linked list
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            next = null;
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Insertion sort method that takes a linked list as input and returns the
    // sorted list
    public static <T extends Comparable<T>> Node<T> insertionSort(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Create a new list to hold the sorted elements
        Node<T> sortedHead = null;

        // Iterate through the input list
        while (head != null) {
            // Store the next element in the input list
            Node<T> next = head.next;

            // Find the correct position to insert the current element in the sorted list
            if (sortedHead == null || head.data.compareTo(sortedHead.data) < 0) {
                head.next = sortedHead;
                sortedHead = head;
            } else {
                Node<T> current = sortedHead;
                while (current.next != null && head.data.compareTo(current.next.data) >= 0) {
                    current = current.next;
                }
                head.next = current.next;
                current.next = head;
            }

            // Move to the next element in the input list
            head = next;
        }

        return sortedHead;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static <T extends Comparable<T>> Node<T> mergeSort(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> mid = getMiddle(head);
        Node<T> left = head;
        Node<T> right = mid.next;
        mid.next = null;

        Node<T> sortedLeft = mergeSort(left);
        Node<T> sortedRight = mergeSort(right);

        return merge(sortedLeft, sortedRight);
    }

    private static <T extends Comparable<T>> Node<T> merge(Node<T> left, Node<T> right) {
        Node<T> result = null;

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }

        if (left.data.compareTo(right.data) <= 0) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }

        return result;
    }

    private static <T extends Comparable<T>> Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return null;
        }

        Node<T> slow = head;
        Node<T> fast = head.next;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }

        return slow;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static <T extends Comparable<T>> Node<T> quickSort(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> pivot = partition(head);
        Node<T> left = pivot != null ? quickSort(head) : null;
        Node<T> right = pivot != null ? quickSort(pivot.next) : null;

        if (left == null) {
            return pivot;
        }

        Node<T> tail = left;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = pivot;

        pivot.next = right;
        return left;
    }

    private static <T extends Comparable<T>> Node<T> partition(Node<T> head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node<T> pivot = head;
        Node<T> i = head.next;
        Node<T> j = head.next;

        while (j != null) {
            if (j.data.compareTo(pivot.data) < 0) {
                i = i.next;
                swap(i, j);
            }
            j = j.next;
        }

        swap(head, i);
        return i;
    }

    private static <T extends Comparable<T>> void swap(Node<T> a, Node<T> b) {
        T temp = a.data;
        a.data = b.data;
        b.data = temp;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Helper method to print the elements of a linked list
    public static <T> void printList(Node<T> head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Main method to test the insertion sort implementation
    public static void main(String[] args) {
        Node<Integer> head = new Node<>(Integer.parseInt(args[0]));
        Node<Integer> tail = head;

        for (int i = 1; i < args.length; i++) {
            tail.next = new Node<>(Integer.parseInt(args[i]));
            tail = tail.next;

        }

        // head.next = new Node<>(1);
        // head.next.next = new Node<>(4);
        // head.next.next.next = new Node<>(2);
        // head.next.next.next.next = new Node<>(5);

        System.out.print("Input List for insertion sort: ");
        printList(head);

        Node<Integer> sortedHead = insertionSort(head);

        System.out.print("Sorted List for insertion sort: ");
        printList(sortedHead);
        // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Node<Integer> head2 = new Node<>(3);
        // head2.next = new Node<>(1);
        // head2.next.next = new Node<>(4);
        // head2.next.next.next = new Node<>(2);
        // head2.next.next.next.next = new Node<>(5);

        // System.out.print("Input List for merge sort: ");
        // printList(head2);

        // Node<Integer> sortedHead2 = mergeSort(head2);

        // System.out.print("Sorted List for merge sort: ");
        // printList(sortedHead2);

        // /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Node<Integer> head3 = new Node<>(3);
        // head3.next = new Node<>(1);
        // head3.next.next = new Node<>(4);
        // head3.next.next.next = new Node<>(2);
        // head3.next.next.next.next = new Node<>(5);

        // System.out.print("Input List for quick sort: ");
        // printList(head3);

        // Node<Integer> sortedHead3 = mergeSort(head3);

        // System.out.print("Sorted List for quick sort: ");
        // printList(sortedHead3);
    }

}
