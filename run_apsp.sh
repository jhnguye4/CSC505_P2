# Runs the specified algorithm based on argument
# d - Dijkstra's algorithm
# fw - Floyd-Warshall algorithm

algo=$1

if [ $algo = "d" ];
then
	echo "Running Dijkstra's Algorithm"
	java ./src/bin/Dijkstras
elif [ $algo = "fw" ];
then
	echo "Running Floyd-Warshall Algorithm"
	java ./src/bin/Floydwarshall
else
	echo "Invalid argument"
fi
