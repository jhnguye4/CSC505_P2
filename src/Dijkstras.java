import java.util.*;
import java.io.*;

public class Dijkstras {
    Utils distanceList;
    int n=0;
    ArrayList<Utils> adjacencyList;

    public Dijkstras(){

        Scanner console = new Scanner(System.in);
        System.out.print("Enter a filename or Q to quit: ");
        String filename = console.next().toLowerCase();

        Scanner input = null;
        PrintStream output = null;
        while (!(filename.equals("q"))) {
            if (filename.endsWith(".gph")) {
                input = getInputScanner(filename);
                if (input != null) {
                    output = getOutputPrintStream(console, filename);
                    if (output != null) {
                        process(input);
                        distanceList = initializeDistance(n);
                        updateDistance(distanceList);
                        distanceList.printList();
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
            adjacencyList.get(i).printList();
        }
        
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

    public int getMinimum(){
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
        }

        return minimum;
    }

    public void decreaseKey(ListNode node, int distance){
        node.weight = distance;
    }

    public void updateDistance(Utils distanceList){
        int minimum = 0;
        for(int i = 0; i < adjacencyList.size(); i++){
            if (adjacencyList.get(i).getSize() < 1){
                continue;
            }
            ListNode currentNode = adjacencyList.get(i).getHead();
            while (currentNode != null){
                ListNode node = distanceList.get(currentNode.target-1);
                if (!node.found){
                    if (node.weight == Integer.MAX_VALUE){
                        node.weight = currentNode.weight;
                    }
                    else {
                        //distanceList.get(currentNode.weight);
                        System.out.println("node.weight: " + node.weight);
                        System.out.println("minimum+currentNode.weight: " + (minimum+currentNode.weight));
                        node.weight = Math.min(node.weight, minimum + currentNode.weight);
                    }
                }
                currentNode = currentNode.next;
            }
            minimum = getMinimum();
            distanceList.printList();
            System.out.println();
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

    public void process(Scanner input) {
        int source = 0;
        int target= 0;
        int weight = 0;
        
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Scanner lineScan = new Scanner(line);
            String text = lineScan.next(); 
            if (text.equals("g")) {   
                n = lineScan.nextInt();
                adjacencyList = new ArrayList<Utils>(n);
                for(int i = 0; i<n; i++){
                    Utils list = new Utils();
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

    }

}

