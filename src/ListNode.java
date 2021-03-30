/**
	 * private inner class that creates Nodes for the linked list
	 * 
	 * @author jhnguye4
	 *
	 */
	public class ListNode {
		/** public field that holds the data of the linked list */
		public int data;
		/** public field for the weight ListNode */
		public int weight;
		/** public field for the right ListNode */
		public ListNode next;
		public ListNode prev;
		public boolean found;

		/**
		 * Constructor method that takes in three parameters to create a node and link
		 * it to the previous link and the next linke
		 * 
		 * @param data the data that the new node will hold
		 * @param priority the priority of the node where lower values indicate higher priority
		 * @param next the node that is next to the current node
		 */
		public ListNode(int data, int weight) {
			this.data = data;
			this.weight = weight;
			this.next = null;
			this.prev = null;
			this.found = false;
		}
	}