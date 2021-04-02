import java.util.*;
import java.io.*;

public class Dijkstras {
    MyList distanceList;
    int n=0;
    int comp = 0;
    ArrayList<MyList> adjacencyList;

    public Dijkstras(){

        Scanner console = new Scanner(System.in);
        System.out.print("Enter a filename or Q to quit: ");
        String filename = console.next().toLowerCase();

        Scanner input = null;
        PrintStream output = null;
        Utils helper = new Utils();
        while (!(filename.equals("q"))) {
            if (filename.endsWith(".gph")) {
                input = helper.getInputScanner(filename);
                if (input != null) {
                    output = helper.getOutputPrintStream(console, filename);
                    if (output != null) {
                        helper.process(input);
                        distanceList = initializeDistance(n);
                        updateDistance(distanceList,0);
                        helper.printList(distanceList.getHead());
                        System.out.println();
                    }
                }
            } else {
                System.out.println("Invalid filename");
            }
            System.out.print("Enter a filename or Q to quit: ");
            filename = console.next().toLowerCase();
        }
        
        for(int i =0; i<n; i++){
            System.out.println("Source: " + (i));
            ListNode nodeHead = adjacencyList.get(i).getHead();
            helper.printList(nodeHead);
        }
        
    }
    public static void main(String[] args) {
        new Dijkstras();
    }  

    public MyList initializeDistance(int num){
        MyList list = new MyList();
        int weight = Integer.MAX_VALUE;
        for(int i = num ; i > 0; i--){
            list.addFront(i,weight);
        }
        return list;
    }

    public ListNode getMinimum(){
        ListNode currentNode = distanceList.getHead();
        Utils helper = new Utils();
        currentNode = helper.sort(currentNode);
        distanceList.setHead(currentNode);
        ListNode node=null;
        while(currentNode != null ){
            if(currentNode.found == false){
                currentNode.found = true;
                node = currentNode;
                break;
            }
            currentNode = currentNode.next;
        }
        return node;
    }

    public void updateDistance(MyList distanceList, int start){
        Utils helper = new Utils();
        int minimum = 0;
        ListNode minimumNode = adjacencyList.get(start).getHead();
        ListNode currentNode = adjacencyList.get(start).getHead();

        for(int i = 0; i < adjacencyList.size(); i++){
            if (adjacencyList.get(i).getSize() < 1){
                continue;
            }
            
            while (currentNode != null){
                ListNode node = helper.get(distanceList.getHead(), currentNode);
                
                if (!node.found){
                    if (node.weight == Integer.MAX_VALUE){
                        node.weight = currentNode.weight + minimum;
                        
                    }
                    else {
                        node.weight = Math.min(node.weight, minimum + currentNode.weight);
                    }
                }
                currentNode = currentNode.next;
                comp++;
            }
            minimumNode = getMinimum();
            minimum = minimumNode.weight;
            currentNode = adjacencyList.get(minimumNode.target-1).getHead();
        }
    }
}