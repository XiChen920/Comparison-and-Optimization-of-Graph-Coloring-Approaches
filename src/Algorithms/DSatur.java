package Algorithms;

import java.util.ArrayList;
import java.util.Random;

/**
 * DSatur algorithm
 * @author GROUP 29
 *
 */
public class DSatur extends Algorithm {
	private int n;
	private ArrayList<ArrayList<Integer>> neighbors;
	private int highestDegree;
	private ArrayList<ArrayList<Integer>> saturated;
	private int[] colours;
	private ArrayList<Integer> uncoloured;
	private int amountOfColors;

	long start;
	int count1 = 0;
	int count2 = 0;
	int count3 = 0;
	int count4 = 0;
	int count5 = 0;

	/**
	 * Method to calculate the chromatic number using the DSatur algorithm
	 */
	public int chromaticNumber() {
		name = "DSatur";
		start = System.currentTimeMillis();
		n = propertyDetection.getOrder();
		neighbors = propertyDetection.getGraph();
		highestDegree = propertyDetection.getHighestDegree();
		colours = new int[n];
		uncoloured = new ArrayList<Integer>(n);
		amountOfColors = 1;
		saturated = new ArrayList<>(n);

		// Set default values to the array that stores the saturation
		for (int i = 0; i < n; i++) {
			saturated.add(new ArrayList());
			saturated.get(i).add(0);
		}

		// Set default values to the uncoloured array
		for (int k = 0; k < n; k++) {
			uncoloured.add(k, k);
		}

		int vertex = nextVertex();

		// Colour the first vertex
		colour(vertex);

		// Continue untill all vertices have been coloured
		while (uncoloured.size() != 0) {
			vertex = nextVertex();
			// Loop through all the current colors used
			for (int i = 1; i <= amountOfColors; i++) {
				efficiency++;
				// Loop through all the neighbors
				for (int j = 0; j < neighbors.get(vertex).size(); j++) {
					efficiency++;
					count1++;
					// In case their is vertex with the same colour as the neighbour, assign it a new colour
					// Remove the recently coloured vertex from the uncoloured list
					// Update the saturation as well
					if (colours[neighbors.get(vertex).get(j)] == i) {
						if (i == amountOfColors) {
							colours[vertex] = ++amountOfColors;
							i = amountOfColors + 1;
							uncoloured.remove(new Integer(vertex));
							updateSat(vertex);
						}
						break;
					// Check whether it is possible to assign a recently assigned colour
					// Remove the recently coloured vertex from the uncoloured list
					// Update the saturation as well
					} else if (j == neighbors.get(vertex).size() - 1) {
						colours[vertex] = i;
						i += amountOfColors;
						uncoloured.remove(new Integer(vertex));
						updateSat(vertex);
					}
				}
			}
			// In case the algorithm takes longer than 100 ms, stop it
			long finish = System.currentTimeMillis();
			if (finish - start > 1000) {
				break;
			}
		}
		System.out.println("NEW BEST UPPER BOUND = "+amountOfColors);
		// Return the chromatic number
		return amountOfColors;
	}

	/**
	 * Method to update the saturation degrees when a vertex has been coloured
	 *
	 * @param vertex position of the vertex which just has been coloured
	 */
	private void updateSat(int vertex) {
		int color = colours[vertex];
		// Loop through all the neighbors of the recently coloured vertex
		for (int i = 0; i < neighbors.get(vertex).size(); i++) {
			int j = neighbors.get(vertex).get(i);
				// In case the method is called for the first time, only update the saturation of the first coloured vertex
			if (saturated.get(j).size() - 1 == 0) {
				efficiency++;
				count2++;
				saturated.get(j).add(color);
			// Else update saturation degree and update it to the colours used
			} else {
				for (int k = 0; k < saturated.get(j).size() - 1; k++) {
					efficiency++;
					count2++;
					if (saturated.get(j).get(k) == color) {
						break;
					} else if (k == saturated.get(j).size() - 1 - 1) {
						saturated.get(j).add(color);
					}
				}
			}

		}
	}

	/**
	 * Method to colour the first vertex with color one
	 * Then, remove it from the list which still has to be coloured
	 * Also update the saturation
	 *
	 * @param n position of the vertex with the highest degree
	 */
	private void colour(int n) {
		colours[n] = 1;
		uncoloured.remove(n);
		updateSat(n);
	}

	/**
	 * Method to decide which vertex has to be coloured next
	 */
	private int nextVertex() {
		int max = 0;
		ArrayList<Integer> highestSat = new ArrayList<Integer>(n);
		// In case the method is called for the first time, return the vertex with the highest degree
		if (uncoloured.size() == n) {
			return findHighestDegree();
		}
		// Then loop through all the vertices that did not receive any colour yet
		for (int i = 0; i < uncoloured.size(); i++) {
			efficiency++;
			count3++;
			int j = uncoloured.get(i);
			// Find the vertex with the highest saturation
			if (saturated.get(j).size() - 1 > max) {
				highestSat.clear();
				max = saturated.get(j).size() - 1;
				highestSat.add(j);
			// In case there is another vertex with the same highest saturation
			} else if (saturated.get(j).size() - 1 == max) {
				highestSat.add(j);
			}
		}
		// Return the vertex with the highest saturation degree
		if (highestSat.size() == 1) {
			return highestSat.get(0);
		} else {
			// Call the highestDegreeNeigh method to decide which vertex to colour next
			return highestDegreeNeigh(highestSat);
		}
	}

	/**
	 * Method to find the next vertex to colour in case there are ties in the saturation
	 *
	 * @param ties array containing all the vertices with the same amount saturation
	 */
	private int highestDegreeNeigh(ArrayList<Integer> ties) {
		int max = 0;
		ArrayList<Integer> HighestDegree = new ArrayList<Integer>(n);
		// Decide which vertex has the highest degree
		for (int i = 0; i < ties.size(); i++) {
			efficiency++;
			count4++;
			int j = ties.get(i);
			if (neighbors.get(j).size() > max) {
				HighestDegree.clear();
				max = neighbors.get(j).size();
				HighestDegree.add(j);
			} else if (neighbors.get(j).size() == max) {
				HighestDegree.add(j);
			}
		}
		// In case there is only one vertex with the highest degree, return it
		if (HighestDegree.size() == 1) {
			return HighestDegree.get(0);
		} else {
			// In case there is a tie in the degrees and saturation, return a random vertex to colour next
			Random rand = new Random();
			return HighestDegree.get(rand.nextInt(HighestDegree.size()));
		}
	}

	/**
	 * Find the vertex with the highest degree to start the algorithm
	 */
	private int findHighestDegree() {
		// Loop through the array containing all neighbors
		for (int i = 0; i < neighbors.size(); i++) {
			efficiency++;
			count5++;
			// Check whether a vertex is found with the highest degree and return the position
			if (neighbors.get(i).size() == highestDegree) {
				return i;
			}
		}
		return -1;
	}

}
