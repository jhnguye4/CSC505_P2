import java.util.*;
import java.io.*;
public class Utils{
    /**
     * Helper method that is used in updateDistance that gets the node in distance list that matches the adjacency node
     * @param list
     * @param node
     * @return
     */
    public ListNode get(ListNode list, ListNode node){
        //node of the distance list
        ListNode currentNode = list;
        if(currentNode == null ){
            return null;
        }
        //iterate through the distance list till finds same node as adjacency node
        while (currentNode != null){
            if (currentNode.target == node.target){
                break;
            }
            currentNode = currentNode.next;
        }
        //returns node of the distance list
        return currentNode;
    }

    /**
     * Merge function that merges all the weights of the nodes in sorted order
     * @param list1
     * @param list2
     * @return 
     */
    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0,0);
        ListNode tmpList = dummy;

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
    /**
     * Merge function that is used in Dijkstras() to sort distance list by node order.
     * @param list1
     * @param list2
     * @return
     */
    public ListNode merge_target(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0,0);
        ListNode tmpList = dummy;

        while (list1 != null && list2 != null) {
            // Value in list1 is smaller than value of list two. tmpList points to value in
            // list1 and pointer in list1 is moved forward
            if (list1.target < list2.target) {
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
    /**
     * helper method that is used in both sort function that is used to find middle of list to split
     * @param head
     * @return
     */
    private ListNode middle(ListNode head) {
        ListNode slow = head, fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Method that will be called recursively to half our lists till lists
    // are one element. After halving, it will merge elements back together in
    // sorted order. This sort function is used to sort the weight of the nodes.
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
    // Method that will be called recursively to half our lists till lists
    // are one element. After halving, it will merge elements back together in
    // sorted order. This sort function is used to sort the target of the nodes.
    public ListNode sort_target(ListNode head) {
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
        ListNode list1 = sort_target(head);
        ListNode list2 = sort_target(middleHead);

        return merge_target(list1, list2);
    } 


    // Helper function that would print out the list, used for test purposes
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
    /**
     * Method takes in a PrintStream and head node of the distance list and prints all 
     * values of the distance list into the output file.
     * @param file
     * @param node
     */
    public void printOutput(PrintStream file, ListNode node){
        ListNode currentNode = node;

        while (currentNode != null) {
            // Print the data at current node
            file.println(currentNode.weight);
            // Go to next node
            currentNode = currentNode.next;
        }
    }

    /**
     * Method is used to scan through input file
     * @param filename
     * @return
     */
    public Scanner getInputScanner(String filename) {
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return fileScanner;
    }

    /**
     * Method manipulates the input file name and creates a PrintStream to allow user to print to output file.
     * @param console
     * @param filename
     * @return
     */
    public PrintStream getOutputPrintStream(Scanner console, String filename) {
        PrintStream output = null;
        if (filename.endsWith(".gph")) {
            filename = filename.substring(0, filename.length() - 4);
            filename = filename + "2.out";

        }
        File file = new File(filename);
        //Use to check if user wants to overwrite a file that already exists
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

    /**
     * Method takes input scanner and iterates through each line of the file.
     * This method is used to create the adjacency list which will be an ArrayList
     * of linked list. With the linked list being a class we created called MyList.
     * @param input
     * @return
     */
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
                adjacencyList.get(target-1).addFront(source,weight);
            }
            lineScan.close();
        
        }
        
        return adjacencyList;

    }

}
