import java.util.*;
import java.io.*;
public class Utils{

    public ListNode get(ListNode list, ListNode node){

        ListNode currentNode = list;
        if(currentNode == null ){
            return null;
        }

        while (currentNode != null){
            if (currentNode.target == node.target){
                break;
            }
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0,0);
        ListNode tmpList = dummy;
        // Loop is used to point tmpList to the smallest element between the two lists
        // until one of the lists have reached its end
        while (list1 != null && list2 != null) {
            // Value in list1 is smaller than value of list two. tmpList points to value in
            // list1 and pointer in list1 is moved forward
            if (list1.weight < list2.weight) {
                tmpList.next = list1;
                list1 = list1.next;
                
            } else {
                // Value in list2 is smaller than value of list one. tmpList points to value in
                // list1 and pointer in list1 is moved forward
                tmpList.next = list2;
                list2 = list2.next;
            }
            // moves the pointer of tmpList forward
            tmpList = tmpList.next;
        }
        // For odd number of nodes in list1 and list2, if list1 or list2 have not
        // reached end then it will enter if statement to point to last element.
        if (list1 != null) {
            tmpList.next = list1;
        }
        if (list2 != null) {
            tmpList.next = list2;
        }
        return dummy.next;

    }

    private ListNode middle(ListNode head) {
        ListNode slow = head, fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Main function that will be called recursively to half our lists till lists
    // are one element. After halving, it will merge elements back together in
    // sorted order.
    public ListNode sort(ListNode head) {
        // Checks if the node is null or there is no elements next to the single element
        // in the list
        if (head == null || head.next == null) {
            return head;
        }

        // Gets the middle element of the list
        ListNode mid = middle(head);
        ListNode middleHead = mid.next;
        // Sets node next to middle null so that when it recursively calls it will break
        // list into halves till there is one element
        mid.next = null;
        ListNode list1 = sort(head);
        ListNode list2 = sort(middleHead);

        return merge(list1, list2);
    } 


    // Helper function that would print out the list
    public void printList(ListNode node) {
        ListNode currentNode = node;

        while (currentNode != null) {
            // Print the data at current node
            System.out.print("Node: "+currentNode.target + " Weight: " + currentNode.weight);
            System.out.println(" Found: "+currentNode.found);
            // Go to next node
            currentNode = currentNode.next;
        }

    }

    public Scanner getInputScanner(String filename) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return fileScanner;
    }

    public PrintStream getOutputPrintStream(Scanner console, String filename) {
        PrintStream output = null;
        if (filename.endsWith(".gph")) {
            filename = filename.substring(0, filename.length() - 4);
            filename = filename + ".out";

        }
        File file = new File(filename);
        try {
            if (!file.exists()) {
                output = new PrintStream(file);
            } else {
                System.out.print(filename + " exists - OK to overwrite(y,n)?: ");
                String reply = console.next().toLowerCase();
                if (reply.startsWith("y")) {
                    output = new PrintStream(file);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File unable to be written " + e);
        }
        return output;
    }

    public ArrayList<MyList> process(Scanner input) {
        int source = 0;
        int target= 0;
        int weight = 0;
        
        ArrayList<MyList> adjacencyList = null;
        
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner lineScan = new Scanner(line);
            String text = lineScan.next(); 
            
            if (text.equals("g")) {   
                int n = lineScan.nextInt();
                adjacencyList = new ArrayList<MyList>(n);
                for(int i = 0; i<n; i++){
                    MyList list = new MyList();
                    adjacencyList.add(list);
                }
            }
            
            if (text.equals("e")) {
                source = lineScan.nextInt();
                target = lineScan.nextInt();
                weight = lineScan.nextInt();
                adjacencyList.get(source-1).addFront(target,weight);
            }
            lineScan.close();
        
        }
        
        return adjacencyList;

    }

}
