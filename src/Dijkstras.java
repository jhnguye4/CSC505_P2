import java.util.*;
public class Dijkstras {
    Utils dijkstraList = new Utils();
    Utils distanceList;
    public Dijkstras(){
        
        int n=8;
        ArrayList<Utils> adjacencyList = new ArrayList<Utils>(n);

        Utils list1 = new Utils();
        list1.addFront(2,4);
        list1.addFront(7,6);
        list1.addFront(3,10);

        Utils list2 = new Utils();
        list2.addFront(4,10);
        list2.addFront(8,6);

        Utils list3 = new Utils();
        list3.addFront(7,2);
        list3.addFront(5,7);

        Utils list4 = new Utils();
        list4.addFront(5,9);
        list4.addFront(6,6);

        Utils list5 = new Utils();
        list5.addFront(6,1);

        Utils list6 = new Utils();
        list6.addFront(7,6);

        Utils list7 = new Utils();
        list7.addFront(8,4);
        
        adjacencyList.add(list1);
        adjacencyList.add(list2);
        adjacencyList.add(list3);
        adjacencyList.add(list4);
        adjacencyList.add(list5);
        adjacencyList.add(list6);
        adjacencyList.add(list7);


        // for(int i = 0; i < adjacencyList.size(); i++){
        //     list = adjacencyList.get(i);
        //     System.out.println("Vertice Start: " + (i+1) + " ");
        //     list.printList();
        // }

        distanceList = initializeDistance(n);
        distanceList.get(2).weight = 4;
        getMinimum();
        distanceList.printList();
        System.out.println();
        distanceList.get(3).weight = 6;
        getMinimum();
        distanceList.printList();
        System.out.println();
        dijkstraList.printList();
        // distanceList.printList();
        
    }
    public static void main(String[] args) {
        new Dijkstras();
    }  

    public Utils initializeDistance(int num){
        Utils list = new Utils();
        int weight = Integer.MAX_VALUE;
        for(int i = num ; i > 0; i--){
            list.addFront(i,weight);
        }
        return list;
    }

    public void getMinimum(){
        ListNode currentNode = distanceList.getHead();
        int minimum = Integer.MAX_VALUE;
        ListNode node=null;
        while(currentNode != null ){
            if(currentNode.found == false && currentNode.weight <= minimum){
                node = currentNode;
                minimum = currentNode.weight;
            }
            currentNode = currentNode.next;
        }
        
        if(minimum != Integer.MAX_VALUE){
            node.found = true;
            dijkstraList.addEnd(node.data,minimum);
        }
    }

    public void decreaseKey(ListNode node, int distance){
        node.weight = distance;
    }
}
