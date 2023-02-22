//import java.security.DrbgParameters.NextBytes;

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

    public static int noOfComparisions = 0;
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
                if (sortedHead != null && head.data.compareTo(sortedHead.data) < 0) {
                    noOfComparisions++;
                }
                head.next = sortedHead;
                sortedHead = head;
            } else {
                Node<T> current = sortedHead;
                while (current.next != null && head.data.compareTo(current.next.data) >= 0) {
                    noOfComparisions++;
                    current = current.next;
                }
                if (!(current.next != null && head.data.compareTo(current.next.data) >= 0)) {
                    noOfComparisions++;
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
        noOfComparisions++;
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

    public static <T extends Comparable<T>> Node<T> partition(Node<T> head, Node<T> tail) {
        if (head == tail || head == null || tail == null) {
            return head;
        }

        Node<T> prev = head;
        Node<T> curr = head;
        T pivot = tail.data;

        while (head != tail) {
            noOfComparisions++;
            if (head.data.compareTo(pivot) < 0) {
                prev = curr;
                T temp = curr.data;
                curr.data = head.data;
                head.data = temp;
                curr = curr.next;
            }
            head = head.next;
        }

        T temp = curr.data;
        curr.data = pivot;
        tail.data = temp;

        return prev;
    }

    public static <T extends Comparable<T>> Node<T> quickSort(Node<T> head, Node<T> tail) {
        if (head == null || head == tail || head == tail.next) {
            return null;
        }

        Node<T> prev = partition(head, tail);
        quickSort(head, prev);

        if (prev != null && prev == head) {
            quickSort(prev.next, tail);
        } else if (prev != null && prev.next != null) {
            quickSort(prev.next.next, tail);
        }

        return head;
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

        Timer timer2 = new Timer("runtime");

        Node<Integer> head = new Node<>(Integer.parseInt(args[1]));
        Node<Integer> tail = head;

        for (int i = 2; i < args.length; i++) {
            tail.next = new Node<>(Integer.parseInt(args[i]));
            tail = tail.next;

        }

        if (Integer.parseInt(args[0]) == 0) {
            System.out.print("Input List for insertion sort: ");
            printList(head);
            timer2.start();
            Node<Integer> sortedHead = insertionSort(head);
            timer2.stop();
            System.err.print(timer2.toString() + "\n");
            System.err.print("comparisons\t" + noOfComparisions + "\n");
            System.out.print("Sorted List for insertion sort: ");
            printList(sortedHead);
        } else if (Integer.parseInt(args[0]) == 1) {
            System.out.print("Input List for merge sort: ");
            printList(head);
            timer2.start();
            Node<Integer> sortedHead = mergeSort(head);
            timer2.stop();
            System.err.print(timer2.toString() + "\n");
            System.err.print("comparisons\t" + noOfComparisions + "\n");
            System.out.print("Sorted List for merge sort: ");
            printList(sortedHead);
        } else if (Integer.parseInt(args[0]) == 2) {
            System.out.print("Input List for quick sort: ");
            printList(head);
            timer2.start();
            Node<Integer> sortedHead = quickSort(head, tail);
            timer2.stop();
            System.err.print(timer2.toString() + "\n");
            System.err.print("comparisons\t" + noOfComparisions + "\n");
            System.out.print("Sorted List for quick sort: ");
            printList(sortedHead);
        } else {
            System.out.print("Invalid Sorting Selected.\n0 -> Insertion Sort\n1 -> Merge Sort\n2 -> Quick Sort\n");
        }
    }

}
