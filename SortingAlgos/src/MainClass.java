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
                if(sortedHead != null && head.data.compareTo(sortedHead.data) < 0){
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
                if(!(current.next != null && head.data.compareTo(current.next.data) >= 0)){
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
    public static <T extends Comparable<T>> Node<T> quickSort(Node<T> head, Node<T> tail) {
        if (head == null || head == tail) {
            return head;
        }
        
        Node<T> pivot = partition(head, tail);
        Node<T> left = quickSort(head, pivot);
        Node<T> right = quickSort(pivot.next, tail);
    
        if (left == null) {
            head.next = right;
            return pivot;
        }
    
        Node<T> leftTail = left;
        while (leftTail.next != null) {
            leftTail = leftTail.next;
        }
        leftTail.next = pivot;
        pivot.next = right;
    
        return left;
    }
    
    private static <T extends Comparable<T>> Node<T> partition(Node<T> head, Node<T> tail) {
        if (head == null || tail == null || head == tail) {
            return head;
        }
    
        Node<T> first = head;
        Node<T> last = tail;
        Node<T> middle = head;
        for (int i = 0; middle != tail && middle.next != tail; i++) {
            middle = middle.next;
            if (i % 2 == 1) {
                first = first.next;
            }
        }
    
        if (first.data.compareTo(middle.data) > 0) {
            swap(first, middle);
        }
        if (first.data.compareTo(last.data) > 0) {
            swap(first, last);
        }
        if (middle.data.compareTo(last.data) > 0) {
            swap(middle, last);
        }
    
        Node<T> pivot = middle;
        Node<T> i = head;
        Node<T> j = head;
    
        while (j != null && j != tail) {
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

        Timer timer2 = new Timer("runtime");

        Node<Integer> head = new Node<>(Integer.parseInt(args[1]));
        Node<Integer> tail = head;

        for (int i = 2; i < args.length; i++) {
            tail.next = new Node<>(Integer.parseInt(args[i]));
            tail = tail.next;

        }

        if(Integer.parseInt(args[0]) == 0){
            System.out.print("Input List for insertion sort: ");
            printList(head);
            timer2.start();
            Node<Integer> sortedHead = insertionSort(head);
            timer2.stop();
            System.err.print(timer2.toString()+"\n");
            System.err.print("comparisons\t"+noOfComparisions+"\n");
            System.out.print("Sorted List for insertion sort: ");
            printList(sortedHead);
        }else if(Integer.parseInt(args[0]) == 1){
            System.out.print("Input List for merge sort: ");
            printList(head);
            timer2.start();
            Node<Integer> sortedHead = mergeSort(head);
            timer2.stop();
            System.err.print(timer2.toString()+"\n");
            System.err.print("comparisons\t"+noOfComparisions+"\n");
            System.out.print("Sorted List for merge sort: ");
            printList(sortedHead);
        }else if(Integer.parseInt(args[0]) == 2){
            System.out.print("Input List for quick sort: ");
            printList(head);
            timer2.start();
            Node<Integer> sortedHead = quickSort(head, tail);
            timer2.stop();
            System.err.print(timer2.toString()+"\n");
            System.err.print("comparisons\t"+noOfComparisions+"\n");
            System.out.print("Sorted List for quick sort: ");
            printList(sortedHead);
        }else{
            System.out.print("Invalid Sorting Selected.\n0 -> Insertion Sort\n1 -> Merge Sort\n2 -> Quick Sort\n");
        }
    }

}
