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
                        adjacencyList = helper.process(input);
                        
                        ListNode currentNode;
                        for(int i =0; i <adjacencyList.size(); i++){
                            
                            //System.out.println("Source: " + (i+1));
                            currentNode = adjacencyList.get(i).getHead();
                            helper.printList(currentNode);
                            helper.printOutput(output,currentNode);
                        }
                        
                        long start = System.nanoTime();
                        for(int i =0; i <adjacencyList.size(); i++){

                            distanceList = initializeDistance(adjacencyList.size(),i);
                            updateDistance(distanceList,i);
                            
                            currentNode = helper.sort_target(distanceList.getHead());
                            distanceList.setHead(currentNode);
                            helper.printList(distanceList.getHead());
                            helper.printOutput(output,currentNode);
                            System.out.println("---");
                        }

                        long end = System.nanoTime();
                        long sortTimeInNano = end - start;
                        double sortTimeIn10thSeconds = (double) sortTimeInNano / Math.pow(10, 8);
                        System.out.println("runtime " + sortTimeIn10thSeconds);
                        System.out.println("comparisons " + comp);
                        
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

    public MyList initializeDistance(int num, int start){
        MyList list = new MyList();
        int weight = Integer.MAX_VALUE;
        Utils helper = new Utils();
        for(int i = num ; i > 0; i--){
            if (i == start+1) {
                list.addFront(i,0);
                list.getHead().found = true;
                continue;
            }
            list.addFront(i,weight);
        }
        System.out.println();
        

        return list;
    }

    public ListNode getMinimum(){
        ListNode currentNode = distanceList.getHead();
        Utils helper = new Utils();
        currentNode = helper.sort(currentNode);
        distanceList.setHead(currentNode);
        ListNode node = null;

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

       // helper.printList(distanceList.getHead());

        for(int i = 0; i < adjacencyList.size(); i++){
            if (adjacencyList.get(i).getSize() < 1){
                continue;
            }
            
            while (currentNode != null){
                ListNode node = helper.get(distanceList.getHead(), currentNode);

                //System.out.println(currentNode.target);
                
                if (!node.found){
                    if (node.weight == Integer.MAX_VALUE){
                        node.weight = currentNode.weight + minimum;
                        
                    }
                    else {
                        node.weight = Math.min(node.weight, minimum + currentNode.weight);
                        comp++;
                    }
                }
                currentNode = currentNode.next;


                //System.out.println("Distance List:");
                //helper.printList(distanceList.getHead());
                System.out.println("");

            }

            minimumNode = getMinimum();
            comp++;
            
            if (minimumNode != null){
                minimum = minimumNode.weight;
                currentNode = adjacencyList.get(minimumNode.target-1).getHead();
            }
        }
    }
}