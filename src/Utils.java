import java.util.*;
import java.io.*;
public class Utils{
    /**
	 * private field for size of the list and will be updated when a link is added
	 * or removed
	 */
	private int size = 0;
	/** private field that is a ListNode that is for the head */
	private ListNode head = null;
    private ListNode tail = null;

    //Returns the data at the head
    public int peek(){
        if(head == null){
            throw new IllegalArgumentException("List is empty");
        }
        return head.data;
    }

    //Removes the top element and returns the node that was removed
    public ListNode pop(){
        if(head == null){
            throw new IllegalArgumentException("List is empty");
        }
        ListNode temp = head;
        head = head.next;
        return temp;
    }

    public void push(int data, int weight){
        ListNode currentNode = head; 
        ListNode newNode = new ListNode(data, weight);

        if(head == null){
            head = newNode;
            return;
        }

        //If the head node has a lower priority than the new node, then the new node will then be placed before
        //the current head. Remember that the lower the value for weight means that it has a higher priority
        if(head.weight > weight){
            newNode.next = head;
            head = newNode;
        } else{
            //Traverse list till the newNode weight value is greater than a weight value in the list.
            while((currentNode.next != null) && (currentNode.next.weight < weight)){
                currentNode = currentNode.next;
            }

            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
    }
    public ListNode get(int index){
        ListNode currentNode = head;
        if(head == null){
            throw new IllegalArgumentException("List is empty");
        }
        for(int i = 0; i < index; i++){
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    public ListNode getHead(){
        if(head == null){
            throw new IllegalArgumentException("List is empty");
        }
        return head;
    }

    public ListNode getTail(){
        if(tail == null){
            throw new IllegalArgumentException("List is empty");
        }
        return tail;
    }

    public int getSize(){
        return size;
    }

    // Adds a number to the front of the list
    public void addFront(int data, int weight) {
        ListNode newNode = new ListNode(data,weight);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        size++;
    }
    // Adds a number to the front of the list
    public void addEnd(int data, int weight) {
        ListNode newNode = new ListNode(data,weight);
        ListNode currentNode = head;
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        else{
            while(currentNode.next != null){
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
            newNode.prev = currentNode;
            tail = newNode;
        }  
        size++;
    }

    public void remove(ListNode node){
        if(node == head && head.next != null){
            head = head.next;
        }
        if(node == tail && tail.prev != null){
            tail = tail.prev;
        }
        if(node.prev != null && node.next != null){
            ListNode previous = node.prev;
            ListNode next = node.next;
            previous.next = next;
            next.prev = previous;
        }
        size--;      
    }


    // Helper function that would print out the list
    public void printList() {
        ListNode currentNode = head;

        while (currentNode != null) {
            // Print the data at current node
            System.out.print("Node: "+currentNode.data + " Weight: " + currentNode.weight);
            System.out.println(" Found: "+currentNode.found);
            // Go to next node
            currentNode = currentNode.next;
        }

    }
    
    
}
