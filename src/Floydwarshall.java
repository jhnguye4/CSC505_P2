import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Floydwarshall {

	int n;
	int comp = 0;
	ArrayList<MyList> adjacencyList;
	Integer[][] dist;

	public Floydwarshall() {

		// TODO: readdata to the adjacencyList;

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
                    output = helper.getOutputPrintStream(console, filename + "");
                    if (output != null) {
                        adjacencyList = helper.process(input);
                        init();
                        run();
                        result();
                    }
                }
            } else {
                System.out.println("Invalid filename");
            }
            
            System.out.print("Enter a filename or Q to quit: ");
            filename = console.next().toLowerCase();
        }

		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				
				String weight = "INF";
				
				if(dist[i][j] != null)
					weight = dist[i][j] + "";
				
				
				output.println(weight);
				
				//System.out.println(String.format("from node %d to node %d, weight: %d", i + 1 ,j + 1,dist[i][j]));
			}
			
			output.println("====");
		}
        
	}

	public void init() {
		
		n = adjacencyList.size();
		
		dist = new Integer[n][n];
		
		// Initialize the distance map.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				ListNode tar = adjacencyList.get(i).search(j + 1);
				
				if (i == j) {
					dist[i][j] = 0;
				} else if (tar != null) {
					dist[i][j] = tar.weight;
				} else {
					dist[i][j] = null;
				}

			}
		}
	}

	public void run() {
		long start = System.nanoTime();
		// Run algorithm.
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					if (dist[i][k] == null || dist[k][j] == null) {

						dist[i][j] = dist[i][j];
						comp++;

					} else if (dist[i][j] == null) {

						dist[i][j] = dist[i][k] + dist[k][j];
						comp++;

					} else if (dist[i][j] <= dist[i][k] + dist[k][j]) {

						dist[i][j] = dist[i][j];
						comp++;

					} else {

						dist[i][j] = dist[i][k] + dist[k][j];
						comp++;

					}

				}
			}

		}
		long end = System.nanoTime();
		long sortTimeInNano = end - start;
		double sortTimeIn10thSeconds = (double) sortTimeInNano / Math.pow(10, 8);
		System.out.println("RUN_TIME " + sortTimeIn10thSeconds);

	}
	
	public void result() {
		
		System.err.println("COMPARISONS " + comp);
		
	}
	
    public static void main(String[] args) {
        new Floydwarshall();
    } 
}
