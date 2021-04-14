/**
 * Linked list class
 */
public class MyList {
    
      /**
	 * private field for size of the list and will be updated when a link is added
	 * or removed
	 */
	private int size = 0;
	/** private field that is a ListNode that is for the head */
	private ListNode head = null;
    private ListNode tail = null;

    /**
     * Method that gets the head of the linked list
     * @return ListNode
     */
    public ListNode getHead(){
        if(head == null){
            return null;
        }
        return head;
    }

    /**
     * Method is used to manipulate and change the head of the linked list
     * @param node
     */
    public void setHead(ListNode node){
        head = node;
    }

    /**
     * Method returns the tail of the linked list
     * @return
     */
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
    public void addFront(int target, int weight) {
        ListNode newNode = new ListNode(target,weight);
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
    // Adds a number to the end of the list
    public void addEnd(int target, int weight) {
        ListNode newNode = new ListNode(target,weight);
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

    /**
     * Removes a node from the list
     * @param node
     */
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
    
    /**
     * Method that will search for a node in this list that has the same target number
     * @param id
     * @return
     */
    public ListNode search(int id) {
    	if(size == 0)
    		return null;
    	
    	ListNode itr = this.head;
    	
    	while(itr != null) {
    		if (itr.target == id)
    			return itr;
    		else
    			itr = itr.next;
    	}
    	
    	return null;
    }
}

