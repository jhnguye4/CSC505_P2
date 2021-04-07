import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Floydwarshall {

	int n;
	ArrayList<MyList> adjacencyList;
	Integer[][] shortestPath;
	Integer[][] dist;

	public Floydwarshall() {

		// TODO: readdata to the adjacencyList;

	}

	public void init() {
		// Initialize the distance map.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				ListNode tar = adjacencyList.get(i).search(j);

				if (i == j) {
					dist[i][j] = 0;
				} else if (tar != null) {
					dist[i][j] = tar.weight;
				} else {
					dist[i][j] = null;
				}

			}
		}

		// Initialize the shortest path map;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {

				if (i != j && dist[i][j] != null)
					shortestPath[i][j] = i;

			}
		}
	}

	public void run() {

		// Run algorithm.
		for (int k = 0; k < n; k++) {

			Integer[][] dist_k = new Integer[n][n];
			Integer[][] shortestPath_k = new Integer[n][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {

					if (dist[i][k] == null || dist[k][j] == null) {

						dist_k[i][j] = dist[i][j];
						shortestPath_k[i][j] = shortestPath[i][j];

					} else if (dist[i][j] == null) {

						dist_k[i][j] = dist[i][k] + dist[k][j];
						shortestPath_k[i][j] = shortestPath[k][j];

					} else if (dist[i][j] <= dist[i][k] + dist[k][j]) {

						dist_k[i][j] = dist[i][j];
						shortestPath_k[i][j] = shortestPath[i][j];

					} else {

						dist_k[i][j] = dist[i][k] + dist[k][j];
						shortestPath_k[i][j] = shortestPath[k][j];

					}

				}
			}

			dist = dist_k;
			shortestPath = shortestPath_k;

		}

	}
	
	public void result() {
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				shortestPathToReadable(i,j);
			}
		}
		
	}

	private void shortestPathToReadable(int start, int end) {
		
		if(end == start)
			return;
		
		Integer pred = shortestPath[start][end];
		
		//ToDo something
		
		shortestPathToReadable(start,pred);
	}
	
}
