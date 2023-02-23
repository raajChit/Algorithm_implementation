import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public static long noOfComparisions;
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

    public static <T extends Comparable<T>> Node<T> mergeSort(Node<T> head) {
        // Base case: If the linked list is empty or has only one node, return it.
        if (head == null || head.next == null) {
            return head;
        }
        
        // Find the middle node of the linked list.
        Node<T> middle = getMiddle(head);
        // Recursively sort the left and right halves of the linked list.
        Node<T> right = mergeSort(middle.next);
        middle.next = null; // Break the linked list into two halves.
        Node<T> left = mergeSort(head);
        // Merge the sorted left and right halves of the linked list.
        return merge(left, right);
    }
    
    // Helper method to find the middle node of the linked list.
    private static <T extends Comparable<T>> Node<T> getMiddle(Node<T> head) {
        if (head == null) {
            return null;
        }
        Node<T> slow = head;
        Node<T> fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    
    // Helper method to merge two sorted linked lists.
    private static <T extends Comparable<T>> Node<T> merge(Node<T> left, Node<T> right) {
        Node<T> dummy = new Node<T>(null);
        Node<T> current = dummy;
        while (left != null && right != null) {
            if (left.data.compareTo(right.data) < 0) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            noOfComparisions++;
            current = current.next;
        }
        if (left != null) {
            current.next = left;
        } else {
            current.next = right;
        }
        return dummy.next;
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static <T extends Comparable<T>> Node<T> partition(Node<T> head, Node<T> tail) {
        // Check for base cases
        if (head == tail || head == null || tail == null) {
            return head;
        }
        // Get the middle node of the linked list
        Node<T> middle = getMiddleNode(head, tail);
        // Find the median value among the first, middle, and last nodes
        Node<T> pivotNode = findMedian(head, middle, tail);
        T pivot = pivotNode.data;

        // Swap the pivot node with the tail node
        swap(pivotNode, tail);

        // Partition the linked list into two parts: nodes smaller than pivot, and nodes
        // greater than pivot
        Node<T> prev = head;
        Node<T> curr = head;

        while (head != tail) {
            noOfComparisions++;
            // Compare the data of each node to the pivot value
            if (head.data.compareTo(pivot) <= 0) {
                // If the node is smaller than the pivot, swap it with the current node
                prev = curr;
                swap(curr, head);
                curr = curr.next;
            }
            // Move to the next node
            head = head.next;
        }
        // Swap the pivot node back to its correct position
        T temp = curr.data;
        curr.data = pivot;
        tail.data = temp;

        // Return the previous node (the last node in the smaller partition)
        return prev;
    }

    private static <T> void swap(Node<T> node1, Node<T> node2) {
        // Check for null nodes
        if (node1 == null || node2 == null) {
            throw new IllegalArgumentException("Cannot swap null nodes");
        }

        // Swap the data of the two nodes
        T temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    public static <T extends Comparable<T>> Node<T> quickSort(Node<T> head, Node<T> tail) {
        // Check for base cases
        if (head == null || head == tail || head == tail.next) {
            return null;
        }

        // Partition the linked list and recursively sort the two partitions
        Node<T> prev = partition(head, tail);
        quickSort(head, prev);

        if (prev != null && prev == head) {
            quickSort(prev.next, tail);
        } else if (prev != null && prev.next != null) {
            quickSort(prev.next.next, tail);
        }

        // Return the head of the sorted linked list
        return head;
    }

    private static <T extends Comparable<T>> Node<T> getMiddleNode(Node<T> head, Node<T> tail) {
        // Check for base case
        if (head == tail) {
            return head;
        }

        // Use two pointers to find the middle node
        Node<T> slow = head;
        Node<T> fast = head.next;

        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Return the middle node
        return slow;
    }

    private static <T extends Comparable<T>> Node<T> findMedian(Node<T> head, Node<T> middle, Node<T> tail) {
        // Get the data of the first, middle, and last nodes
        T a = head.data;
        T b = middle.data;
        T c = tail.data;

        // Determine which of the three nodes has the median value
        if (a.compareTo(b) <= 0 && a.compareTo(c) <= 0) {
            if (b.compareTo(c) <= 0) {
                return middle;
            } else {
                return tail;
            }
        } else if (b.compareTo(a) <= 0 && b.compareTo(c) <= 0) {
            if (a.compareTo(c) <= 0) {
                return head;
            } else {
                return tail;
            }
        } else {
            if (a.compareTo(b) <= 0) {
                return head;
            } else {
                return middle;
            }
        }
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

    public static void main(String[] args) {

        Timer timer2 = new Timer("runtime");
        
        int flag = 0;
        noOfComparisions = 0;
        Node<Integer> head = new Node<>(0);
        Node<Integer> tail = new Node<>(0);
        try {
            File myObj = new File(args[1]);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if( flag == 0 ){
                    head.data = Integer.parseInt(data);
                    tail = head;
                    flag++;
                } else {
                    tail.next = new Node<>(Integer.parseInt(data));
                    tail = tail.next;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Node<Integer> head = new Node<>(Integer.parseInt(args[1]));
        // Node<Integer> tail = head;

        // for (int i = 2; i < args.length; i++) {
        //     tail.next = new Node<>(Integer.parseInt(args[i]));
        //     tail = tail.next;

        // }

        if (Integer.parseInt(args[0]) == 0) {
            System.out.print("Input List for insertion sort: ");
            //printList(head);
            timer2.start();
            Node<Integer> sortedHead = insertionSort(head);
            timer2.stop();
            System.out.print("Sorted List for insertion sort: ");
            printList(sortedHead);
            System.err.print(timer2.toString() + "\n");
            System.err.print("comparisons\t" + noOfComparisions + "\n");
        } else if (Integer.parseInt(args[0]) == 1) {
            System.out.print("Input List for merge sort: ");
            //printList(head);
            timer2.start();
            Node<Integer> sortedHead = mergeSort(head);
            timer2.stop();
            System.out.print("Sorted List for merge sort: ");
            printList(sortedHead);
            System.err.print(timer2.toString() + "\n");
            System.err.print("comparisons\t" + noOfComparisions + "\n");
        } else if (Integer.parseInt(args[0]) == 2) {
            System.out.print("Quick sort: ");
            //printList(head);
            timer2.start();
            Node<Integer> sortedHead = quickSort(head, tail);
            timer2.stop();
            System.out.print("Sorted List for quick sort: ");
            printList(sortedHead);
            System.err.print(timer2.toString() + "\n");
            System.err.print("comparisons\t" + noOfComparisions + "\n");
        } else {
            System.out.print("Invalid Sorting Selected.\n0 -> Insertion Sort\n1 -> Merge Sort\n2 -> Quick Sort\n");
        }
    }

}
