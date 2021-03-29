public class Test {
    public Test(){
        Utils list = new Utils();
        Utils list2 = new Utils();
        list.addEnd(1);
        list.addEnd(2);
        list.addEnd(3);
        list.addFront(7);
        ListNode headNode = list.getHead();
        ListNode tailNode = list.getTail();
        list.printList(headNode);
        
        System.out.println("Head " + headNode.data);
        System.out.println("Tail "+tailNode.data);
        System.out.println("Before "+list.getSize());

        ListNode currentNode = list.getHead();
        list.remove(currentNode);
        list.printList(list.getHead());
        System.out.println("After " + list.getSize());


        
    }
    public static void main(String[] args) {
        new Test();
    }
}
