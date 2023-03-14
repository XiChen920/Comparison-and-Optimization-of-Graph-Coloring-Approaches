package main;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.text.WrappedPlainView;

import Util.Partitions;
import Util.ReadGraph;
import Util.Util;

/**
 * Detects the following properties in a graph:
 * order, size, highest degree, degree sequence, if it is trivial, if it is null,
 * if it is a cycle graph (odd/ even length), if it contains cycles (odd/ even length),
 * if it is complete and calculates probabilities for the clique number.
 * @author GROUP 29
 *
 */
public class PropertyDetection {
	public static final String EXIT_STATEMENT = "Exiting program... \nGoodbye!";
	public static final double STOP_CYCLE_DETECTION = 10000.0;//10 seconds

	private ArrayList<ArrayList<Integer>> graph;
	private String[][] graphS;
	private int order;
	private int size;
	private int highestDegree;
	private int[][] degreeSequence;
	private int[] degrees;
	private boolean isTrivial;
	private double density;
	private int largestCliqueSize;
	private double[] cliqueProbabilities;
	private int chromaticNumber;
	private int lowerBound;
	private int oldLowerBound;
	private int upperBound;
	private boolean isNullGraph;
	private boolean isComplete;
	private String output;
	private boolean debug;
	private boolean isKRegular;
	private double timer;
	private int efficiencyMeasure;
	private boolean isCycleGraphOdd, isCycleGraphEven;
	private boolean hasCycles;
	private boolean hasOddLengthCycles;
	private double timeTaken;
	
	/**
	 * Constructor starts property detection
	 * @param location
	 */
	public PropertyDetection(String location){
		output = "";
		debug = false;
		efficiencyMeasure = 0;
		loadGraph(location);
	}
	
	/**
	 * This method detects the following properties in a graph:
	 * order, size, highest degree, degree sequence, if it is trivial, if it is null,
	 * if it is a cycle graph (odd/ even length), if it contains cycles (odd/ even length),
	 * if it is complete and calculates probabilities for the clique number. 
	 * @param location of the graph
	 */
	private void loadGraph(String location) {
		double startTime = System.currentTimeMillis();
		// 1. load graph
		graph = new ArrayList<ArrayList<Integer>>();
		String[][] g = ReadGraph.loadGraph(location);
		graphS = g;
		// 2. retrieving the order of the graph
		order = Integer.parseInt(g[0][1]);
		// initiating chromatic number
		chromaticNumber = order;
		lowerBound = 0;
		oldLowerBound = 0;
		upperBound = order;
		// 3. checking if graph is trivial
		if (order > 1) {
			isTrivial = false;
		}
		if (order == 1) {
			isTrivial = true;
			chromaticNumber = 1;
			return;
		} else if (order < 1) {
			return;
		}
		// 4. retrieving the size of the graph
		size = Integer.parseInt(g[1][1]);
		// 5. checking if graph is a null graph
		if (size > 0) {
			isNullGraph = false;
		} else if (size == 0) {
			isNullGraph = true;
			chromaticNumber = 1;
			return;
		} else if (size < 0) {
			return;
		}
		// 6. creating adjacency list
		for (int i = 0; i < order; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 2; i < g.length; i++) {
			int u = Integer.parseInt(g[i][0]) - 1;// first vertex the edge is incident to
			int v = Integer.parseInt(g[i][1]) - 1;// second vertex the edge is incident to
			// adding all edge to incidence list
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		// 7. calculate the degrees of each vertex
		degreeSequence = new int[order][2];
		for (int i = 0; i < order; i++) {
			degreeSequence[i][0] = i;
			degreeSequence[i][1] = graph.get(i).size();
			efficiencyMeasure++;
			/*
			 * the adjacency list holds all adjacent vertices to v_i, therefore the size of
			 * the list for v_i is its degree
			 */
		}
		degrees = Arrays.copyOf(degreeSequence[1], degreeSequence.length);
		// sort the degreeSequence descendingly
		Util.sort(degreeSequence);
		// 8. a. checking if graph is complete
		if (size == order * (order - 1) / 2) {// 8. a. i. size of a complete graph
			isComplete = true;
			chromaticNumber = order;// a complete graph has a chromatic number equal to its order
		} else {
			isComplete = false;
		}
		// 9. retrieving highest degree
		highestDegree = degreeSequence[0][1];
		// 9. a. highest degree forms an upper bound on the chromatic number
		updateUpperBound(highestDegree + 1);
		// 10. retrieving lowest degree
		//11. cycle detection
		//implementing bollobas theorem
		int threshhold = (int) Math.pow(order, 2);
		threshhold /= 4;
		if(size > threshhold) {
			updateLowerBound(3);
		} else {
			timer = System.currentTimeMillis();
			hasCycles = cycleDetection();
			if(isCycleGraphOdd) {
				chromaticNumber = 3;
			} else if(isCycleGraphEven) {
				chromaticNumber = 2;
			}
			if(hasOddLengthCycles) {
				updateLowerBound(3);//chromatic number of an odd length cycle is 3
			} else {
				updateLowerBound(2);//chromatic number of an even lengt cycle is 2
			}

		}
		// 12. Clique detection
		cliqueDetection();
		// 13. Brooks Theorem
		if (!isCycleGraphOdd && !isComplete) {
			updateUpperBound(highestDegree);
		}
		if(lowerBound == upperBound) {
			chromaticNumber = lowerBound;
		}
	}
	/**
	 * This method detects cycles using breadth first search.
	 * @return
	 */
	private boolean cycleDetection() {
		hasCycles = false;
		hasOddLengthCycles = false;
		int[] cycles = new int[order];
		for (int i = 0; i < order; i++) {
			// stops after time
			if (System.currentTimeMillis() - timer > STOP_CYCLE_DETECTION) {
				return hasCycles;
			}
			cycles[i] = cycleDetection(i, i, 0, new ArrayList<Integer>());
			if (cycles[i] > 0) {
				hasCycles = true;
				// checking if G is a cycle graph
				boolean isOdd = Util.isOdd(cycles[i]);
				if (order == cycles[i]) {
					if (isOdd) {
						isCycleGraphOdd = true;
					} else {
						isCycleGraphEven = true;
						return hasCycles;
					}
				}
				if (isOdd) {// found a cycle of odd length
					hasOddLengthCycles = true;
					return hasCycles;
				}
			}
		}
		return hasCycles;
	}
	
	/**
	 * Recursive breadth first search
	 * @param startingVertex
	 * @param currentVertex
	 * @param length
	 * @param alreadyVisited
	 * @return length of cycle found
	 */
	private int cycleDetection(int startingVertex, int currentVertex, int length, ArrayList<Integer> alreadyVisited) {

		// recursive anchor
		if (currentVertex == startingVertex && length > 2) {
			return length;
		}
		if (alreadyVisited.size() == order) {// visited all vertices but no cycle found
			return 0;
		}
		// recursive step
		alreadyVisited.add(currentVertex);
		ArrayList<Integer> currentNeighbors = graph.get(currentVertex);
		if (currentNeighbors.size() > 0) {
			for (int i = 0; i < currentNeighbors.size(); i++) {
				int currentNeighbor = currentNeighbors.get(i);
				efficiencyMeasure++;
				if (!alreadyVisited.contains(currentNeighbor) || (currentNeighbor == startingVertex && length > 1)) {
					return cycleDetection(startingVertex, currentNeighbor, length + 1,
							Util.cloneArrayList(alreadyVisited));
				}
			}
		} else {
			Util.getLowestNotContained(order, alreadyVisited);
		}

		return 0;
	}
	
	/**
	 * Computes probabilities for highest clique number
	 */
	private void cliqueDetection() {
		ArrayList<BigDecimal> cliqueProbabilities = new ArrayList<BigDecimal>();// calculates the probabilities for
																				// cliques of size i + 3
		// Bollobas theorem
		int j = 0;
		if (size > (Math.pow(order, 2) / 4)) {
			j = 1;
			cliqueProbabilities.add(new BigDecimal(1));
		}
		// since cliques of size 1 and 2 make no sense
		// calculate probabilities of cliques of size i + 3, until one probability is
		// below 0.999
		BigDecimal twoE = new BigDecimal(size).multiply(new BigDecimal(2));
		BigDecimal nTimesnminus1 = new BigDecimal(order).multiply(new BigDecimal(order - 1));
		BigDecimal p = twoE.divide(nTimesnminus1, 10, RoundingMode.HALF_UP);
		density = p.doubleValue();
		BigDecimal minimum = new BigDecimal(0.1).pow(14);
		efficiencyMeasure += 4;
		for (int i = j; i <= highestDegree - 3; i++) {
			BigDecimal xChoose2 = Util.nCk(i + 3, 2);
			BigDecimal q = p.pow(xChoose2.intValue());// pToTheXChoose2
			efficiencyMeasure += 2;
			if (q.compareTo(minimum) == 1) {
				BigDecimal r = new BigDecimal(1).subtract(q);// OneMinuspToTheXChoose2
				BigDecimal s = Util.nCk(order, i + 3);// order choose x
				efficiencyMeasure += 2;
				// underflow protection
				BigDecimal t = new BigDecimal(Math.pow(r.doubleValue(), s.doubleValue()));
				efficiencyMeasure++;
				cliqueProbabilities.add(new BigDecimal(1).subtract(t));
				if (cliqueProbabilities.get(i).compareTo(new BigDecimal(0.999)) == 1) {
					largestCliqueSize = i + 3;
				}
			} else {
				break;
			}
		}
		// round clique probabilities
		this.cliqueProbabilities = new double[cliqueProbabilities.size()];
		for (int i = 0; i < cliqueProbabilities.size(); i++) {
			this.cliqueProbabilities[i] = Util.toPercentage(cliqueProbabilities.get(i).doubleValue());
		}

		// make statement about lowerbound
		updateLowerBound(largestCliqueSize);// -2 because it is the second to last value, but + 3 because the size of
											// the clique is i+3 makes length+1
	}
	private void updateLowerBound(int newLowerBound) {
		if (lowerBound < newLowerBound) {
			oldLowerBound = lowerBound;
			lowerBound = newLowerBound;
		}
	}

	private void updateUpperBound(int newUpperBound) {
		if (upperBound > newUpperBound) {
			upperBound = newUpperBound;
		}
	}
	public ArrayList<ArrayList<Integer>> getGraph() {
		return graph;
	}

	public void setGraph(ArrayList<ArrayList<Integer>> graph) {
		this.graph = graph;
	}

	public String[][] getGraphS() {
		return graphS;
	}

	public void setGraphS(String[][] graphS) {
		this.graphS = graphS;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getHighestDegree() {
		return highestDegree;
	}

	public void setHighestDegree(int highestDegree) {
		this.highestDegree = highestDegree;
	}

	public int[][] getDegreeSequence() {
		return degreeSequence;
	}

	public void setDegreeSequence(int[][] degreeSequence) {
		this.degreeSequence = degreeSequence;
	}

	public boolean isTrivial() {
		return isTrivial;
	}

	public void setTrivial(boolean isTrivial) {
		this.isTrivial = isTrivial;
	}

	public int getLargestCliqueSize() {
		return largestCliqueSize;
	}

	public void setLargestCliqueSize(int largestCliqueSize) {
		this.largestCliqueSize = largestCliqueSize;
	}

	public int getChromaticNumber() {
		return chromaticNumber;
	}

	public void setChromaticNumber(int chromaticNumber) {
		this.chromaticNumber = chromaticNumber;
	}

	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public boolean isNullGraph() {
		return isNullGraph;
	}

	public void setNullGraph(boolean isNullGraph) {
		this.isNullGraph = isNullGraph;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public boolean isKRegular() {
		return isKRegular;
	}

	public void setKRegular(boolean isKRegular) {
		this.isKRegular = isKRegular;
	}

	public int getEfficiencyMeasure() {
		return efficiencyMeasure;
	}

	public void setEfficiencyMeasure(int efficiencyMeasure) {
		this.efficiencyMeasure = efficiencyMeasure;
	}

	public int[] getDegrees() {
		return degrees;
	}

	public void setDegrees(int[] degrees) {
		this.degrees = degrees;
	}

	public double getTimeTaken() {
		return timeTaken;
	}

	public int getOldLowerBound() {
		return oldLowerBound;
	}

	public void setOldLowerBound(int oldLowerBound) {
		this.oldLowerBound = oldLowerBound;
	}
	
}
