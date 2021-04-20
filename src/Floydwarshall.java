import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Floydwarshall {

	int n;
	int comp = 0;
	ArrayList<MyList> adjacencyList;
	Integer[][] dist;

	public Floydwarshall(String filename) {

		Scanner console = new Scanner(System.in);
        Scanner input = null;
        PrintStream output = null;
        Utils helper = new Utils();
        if (filename.endsWith(".gph")) {
            input = helper.getInputScanner(filename);
            if (input != null) {
                output = helper.getOutputPrintStream(console, filename + "");
                if (output != null) {
                	//read adjacencylist from input.
                    adjacencyList = helper.process(input);
                    init();
                    run();
                    result();
                }
            }
        } else {
            System.out.println("Invalid filename");
        }




		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {

				String weight = "INF";

				if(dist[i][j] != null)
					weight = dist[i][j] + "";

				//write result to output file.
				output.println(weight);

			}

			output.println("====");
		}

	}

	public void init() {

		//Get the number of vertices.
		n = adjacencyList.size();

		//create a distance matrix with size of N x N
		dist = new Integer[n][n];

		// Load the D^0 of the distance map using adjacencyList.
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

		/**
		 * Test all k value and find if that any path from
		 * i to k + any path from k to j produces a better cost path.
		 */
		for (int k = 0; k < n; k++) {

			//Test it for all starting-ending pair in the distance matrix.
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {


					/**
					 * The null value here determines infinity, infinity + any should result in infinity,
					 * therefore, the distance matrix will not be updated.
					 * If the original path is infinity, then replace it with what ever the new path is.
					 * This will keep the distance matrix unchanged if we replacing an infinity with an infinity.
					 * If no null value present, then only update the matrix if there is a better cost path detected.
					 */
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
		double sortTimeInSeconds = (double) sortTimeInNano / Math.pow(10, 9);
    System.out.printf("RUN_TIME %.1f\n", sortTimeInSeconds);

	}

	public void result() {

		System.err.println("COMPARISONS " + comp);

	}

    public static void main(String[] args) {
        new Floydwarshall(args[0]);
    }
}
