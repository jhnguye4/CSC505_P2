# CSC505_P1
CSC 505 Project 2: All-Pairs Shortest Paths

## Learning Objective
Gain deeper understanding of shortest paths algorithms and graph algorithm implementation; design experiments to test hypotheses about algorithm behavior; write a compelling comparative analysis based on experimental results.

Learn two ways of finding shortest paths between all pairs of vertices for undirected graphs with positive edge weights.

* n iterations of Dijkstra’s single source algorithm, one iteration per start node
* the Floyd-Warshall algorithm

We will implement each of these algorithms and do experiments reporting both runtime and number of edge-weight comparisons. Both algorithms have logic that requires comparing weights of two edges. 


## Specifications
Programs must run from the command line and read input from standard input in gph format

Every output line contains a single integer - all weights are integers and there are exactly n**2 lines. Lines d_i_i will be 0 and the output matrix will be symmetric, i.e., d_i_j = d_j_i. The -1’s are there to make the output more readable when debugging. There is not one at the very end. Because of the output size you will want to suppress it when experimenting with larger graphs by sending it to /dev/null on the command line.

Additional output giving statistics about the run should be sent to standard error. Format of the additional output is:

runtime     SECONDS
comparisons NUMBER_OF_COMPARISONS

The runtime should not include the time it takes to read the input or produce the output. All three programming languages have functions/methods that facilitate access to runtime during execution. All runtimes should be at least 1/10 of a second and should be rounded to the nearest 1/10 of a second.


## Running Program
Clone repository and maintain file structure.

In order to run Dijkstras with input files:

* javac Dijkstras.java 
* java Dijkstras
* Program will prompt "Enter a filename or Q to quit:"
* Type in file name
* Example: ./inputs/dual_06_09.gph

In order to run Floyd Warshall with input files:

* javac Floydwarshall.java 
* java Floydwarshall
* Program will prompt "Enter a filename or Q to quit:"
* Type in file name
* Example: ./inputs/dual_06_09.gph

A file will be created that outputs all shortest path values of each node. With the correct outputs being in the outputs folder to check during testing.


## Team Members

* Jonathan Nguyen
* Justin Kirscher
* Yi Qiu
* Rahul Ramakrishnan