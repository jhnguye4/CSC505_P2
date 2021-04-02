public class Test {
    public Test(){
        MyList list = new MyList();
        Utils helper = new Utils();

        list.addFront(1, 2);
        list.addFront(2, 100);
        list.addFront(3, 8);
        list.addFront(4, 9);
        helper.printList(list.getHead());
        System.out.println();
        
        ListNode newList = helper.sort(list.getHead());
        
        helper.printList(newList);
    }
    public static void main(String[] args) {
        new Test();
    }
}
