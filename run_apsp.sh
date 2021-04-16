# Runs the specified algorithm based on argument
# d - Dijkstra's algorithm
# fw - Floyd-Warshall algorithm

algo=$1


# Set class path 
if [[ "$OSTYPE" == "msys" ]]; then
  class_path=".;./java/lib/gson-2.8.6.jar;src/bin"
else
	  class_path=".:./java/lib/gson-2.8.6.jar:src/bin"
fi


if [[ $algo = "d" ]];
then
	echo "Running Dijkstra's Algorithm"
	java -cp $class_path Dijkstras
elif [[ $algo = "fw" ]];
then
	echo "Running Floyd-Warshall Algorithm"
	java -cp $class_path Floydwarshall
else
	echo "Invalid argument"
	echo "Argument list: "
	echo "d  - Dijkstra's algorithm"
	echo "fw - Floyd-Warshall algorithm"
fi
