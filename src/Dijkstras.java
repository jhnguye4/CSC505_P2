import java.util.*;
import java.io.*;

public class Dijkstras {
    //Delcaring the distance list, number of comparisons, and adjacency list
    MyList distanceList;
    int comp = 0;
    ArrayList<MyList> adjacencyList;

    public Dijkstras(String filename){
        Scanner console = new Scanner(System.in);
//        System.out.print("Enter a filename or Q to quit: ");
//        String filename = console.next().toLowerCase();

        Scanner input = null;
        PrintStream output = null;
        Utils helper = new Utils();
        if (filename.endsWith(".gph")) {
            input = helper.getInputScanner(filename);
            if (input != null) {
                output = helper.getOutputPrintStream(console, filename);
                if (output != null) {
                    //Calls the process function from the Utils class which will take the input file and create the adjacency list.
                    //The list will be an ArrayList of linked list, with the linked list being a class we created called MyList  
                    adjacencyList = helper.process(input);
                    
                    ListNode currentNode;
                    long start = System.nanoTime();
                    //Prints the number of vertices of the file at the top of the output file.
                    output.println(adjacencyList.size());
                    //Loop is used to change the starting node of each iteration. This loop will find the 
                    //shortest path for every node and output it to a file. In order to read the output file
                    //the top number is the number of vertices in the graph, the number that starts with 0 is the starting node 
                    //and all the other numbers are the distance from that starting node. The -1 is used to separate the distances of each starting node.
                    for(int i =0; i <adjacencyList.size(); i++){

                        distanceList = initializeDistance(adjacencyList.size(),i);
                        updateDistance(distanceList,i);
                        //Since our distance list is sorted by weight after updateDistance. The method below will sort it by node in order to print into
                        //output file.
                        currentNode = helper.sort_target(distanceList.getHead());
                        distanceList.setHead(currentNode);
                        helper.printOutput(output,currentNode);
                        if(i < adjacencyList.size()-1){
                            output.println(-1);
                        }
                    }
                    long end = System.nanoTime();
                    //Calculating the runtime of this algorithm and the number of comparisons it would take.
                    long sortTimeInNano = end - start;
                    double sortTimeIn10thSeconds = (double) sortTimeInNano / Math.pow(10, 8);
                    System.out.println("RUN_TIME " + sortTimeIn10thSeconds);
                    System.out.println("COMPARISONS " + comp);
                    comp =0;
                    
                }
            }
        } 
        else {
            System.out.println("Invalid filename");
        }
    }



    
    public static void main(String[] args) {
//    	System.out.println(args[0]);
        new Dijkstras(args[0]);
    }  

    /**
     * Method is used to initialize the starting vertice to have a distance of 0 and all the other vertices to have a distance of infinity.
     * It takes in a num for number of vertices the graph has and a start which is the node that the shortest distances will be found for.
     * @param num
     * @param start
     * @return MyList which is a linked list class that we created
     */
    public MyList initializeDistance(int num, int start){
        MyList list = new MyList();
        int weight = Integer.MAX_VALUE;
        Utils helper = new Utils();

        for(int i = num ; i > 0; i--){
            //if this is the starting node, then set it to 0. if not then set it to infinity
            if (i == start+1) {
                list.addFront(i,0);
                list.getHead().found = true;
                continue;
            }
            list.addFront(i,weight);
        }

        return list;
    }
    /**
     * Method finds the minimum value in the distance list and marks it as found.
     * Returns the node that has the minimum distance
     */
    public ListNode getMinimum(){
        ListNode currentNode = distanceList.getHead();
        Utils helper = new Utils();
        //Sorts the list with merge sort, and then iterate through this list.
        //Distance list is sorted every iteration.
        currentNode = helper.sort(currentNode);
        distanceList.setHead(currentNode);
        ListNode node = null;

        //iterate through the distance list till it finds a node that isnt found(false) and is the minimum distance
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
    /**
     * Method is use to update the distance list to have all the shortest paths from the starting node to all other nodes in the graph.
     * It does this by finding the adjacent nodes of the starting node and then filling in the distance list. If the node in the distance list is infinity then 
     * it will add the current minimum and the weight of the adjacent node as the distance in the list. If it is not infinity, it will compare the distance already
     * in the list to the minimum distance plus the distance to the adjacent node, it will then take the minimum number of those two distances and update the 
     * distance list at that adjacent node. It will iterate all nodes of the graph till the list has all the minimum distances. Afterwards the distance list will
     * be sorted by node in Dijkstras() and printed to output.
     * @param distanceList
     * @param start
     */
    public void updateDistance(MyList distanceList, int start){
        Utils helper = new Utils();
        //declare and initialize the minimum distance
        int minimum = 0;
        //declare and initialize the minimum node which will be used when calling getMinimum
        ListNode minimumNode = null;
        //declare and initialize the current node which is the head of the linked list of the index of the adjacency list for the starting node.
        //example:
        //If starting node is 0 then it will get index 0 of the adjacency list and get the head of linked list, which is a linked list of adjacency nodes/distances
        ListNode currentNode = adjacencyList.get(start).getHead();

        //Loop is used to go through all the nodes of the graph
        for(int i = 0; i < adjacencyList.size(); i++){
            //Checks if the linked list in the adjacencyList at index i is empty
            if (adjacencyList.get(i).getSize() < 1){
                continue;
            }
            
            //Go through all the adjacency nodes
            while (currentNode != null){
                //grab the node in distance list that is the same adjacency node
                //example:
                //if adjacency node is 2 then get node 2 in the distance list so that its distance may potentially be updated
                ListNode node = helper.get(distanceList.getHead(), currentNode);
                
                //only update the distance list of the node if it is not found(false)
                if (!node.found){
                    //if the node is infinity then make the minimum distance the distance of the adjacency node plus the minimum distance
                    if (node.weight == Integer.MAX_VALUE){
                        node.weight = currentNode.weight + minimum;
                        
                    }
                    else {
                        //if node is any other value then take the minimum distance of node in distance list and adjacency node distance + minimum  
                        node.weight = Math.min(node.weight, minimum + currentNode.weight);
                        comp++;
                    }
                }
                //move to the next adjacency node
                currentNode = currentNode.next;

            }
            //find the next minimum value in the distance list
            minimumNode = getMinimum();
            comp++;
            //used to catch edge case when all nodes in distance list are found and minimumNode is null
            if (minimumNode != null){
                minimum = minimumNode.weight;
                currentNode = adjacencyList.get(minimumNode.target-1).getHead();
            }
        }
    }
}
