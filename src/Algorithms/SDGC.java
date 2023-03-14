package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * SImple Decentralized Graph Colouring (SDGC) algorithm
 * @author filre
 *
 */
public class SDGC extends Algorithm {
	// algorithm 1
	/**
	 * color of every vertex
	 */
	private int[] colors;
	/**
	 * random order of the vertices
	 */
	private Integer[] order;
	/**
	 * order of the graph
	 */
	private int n;
	/**
	 * list with neighbours
	 */
	private ArrayList<ArrayList<Integer>> neigh;

	/**
	 * Initiates the variables and runs the algorithms
	 * 
	 * @param graph arrayList with all neighbours
	 * @return the chromatic number
	 */
	public int chromaticNumber(String[][] graph) {
		name = "Decentral";
		Algorithm a = new DSatur();
		a.setPropertyDetection(propertyDetection);
		int x = a.chromaticNumber();
		n = propertyDetection.getOrder();
		neigh = propertyDetection.getGraph();
		colors = new int[n];
		order = new Integer[n];
		// create an order list
		for (int i = 0; i < n; i++) {
			order[i] = i;
		}
		// temporary placeholder
		int temp = x;

		// run algorithm 1 a 1000 times
		for (int i = 0; i < 1000; i++) {
			// if the algorithm returns the amount of colors
			if (sdgc(temp, 1000) == temp) {
				// set this as the new cn
				x = temp;
				System.out.println("NEW BEST UPPER BOUND = " + x);
				// reduce the amount you want to test
				if (temp != 2) {
					i = 0;
					temp--;
				} else
					break;
			}

		}
		return x;
	}

	@Override
	public int chromaticNumber() {
		return chromaticNumber(propertyDetection.getGraphS());
	}

	/**
	 * Takes in a amount of colors and tries to color the graph using this amount
	 * 
	 * @param k  amount of colors you want to test
	 * @param it amount of iterations
	 * @return input k if it could color the graph or 0 if it didnt work
	 */
	private int sdgc(int k, int it) {
		// random object
		Random rand = new Random();
		// loop through the colors
		for (int i = 0; i < colors.length; i++) {
			// give each vertex a random color
			colors[i] = rand.nextInt(k) + 1;
		}
		// loop the amount of iterations
		for (int j = 0; j < it; j++) {
			// randomize the order in which you go through the vertices
			List<Integer> l = Arrays.asList(order);
			Collections.shuffle(l);
			// loop through the vertices
			for (int q = 0; q < n; q++) {
				// loop through the neighbours
				for (int m = 0; m < neigh.get(order[q]).size(); m++) {
					// check if there is a neighbour with the same color
					if (colors[neigh.get(order[q]).get(m)] == colors[order[q]]) {
						// if there is give the vertex the color decided in minconflicts()
						colors[order[q]] = minconflicts(order[q], k);
					}
				}

			}
			// check if there are still conflicts
			if (noConflicts()) {
				return k;
			}
		}
		return 0;
	}

	/**
	 * Look at the neighbours and count the amount of times each color is used
	 * 
	 * @param vertex the vertex you want to color
	 * @param k      the amount of colors we can use
	 * @return the color that was used the least
	 */
	private int minconflicts(int vertex, int k) {
		// random object
		Random rand = new Random();
		// the used colors
		int[] colorsUsed = new int[k + 1];
		int amount = n;
		// list containing the colors used the least
		ArrayList<Integer> leastColors = new ArrayList<Integer>(n);
		// loop through all neighbours
		for (int i = 0; i < neigh.get(vertex).size(); i++) {
			// increase the amount of times a color is used based on how many neighbours use
			// it
			colorsUsed[colors[neigh.get(vertex).get(i)]]++;
		}
		// loop through the colors
		for (int j = 1; j < colorsUsed.length; j++) {
			// if a color is found that is used less than the minimum
			if (colorsUsed[j] < amount) {
				// clear the leastcolors list
				leastColors.clear();
				// set this amount as the minimum
				amount = colorsUsed[j];
				// add this color to the list
				leastColors.add(j);
				// else if the color is used the same amount as the minimum also put it in the
				// list
			} else if (colorsUsed[j] == amount) {
				leastColors.add(j);
			}
		}
		// return a random color chosen from the least colors list
		return leastColors.get(rand.nextInt(leastColors.size()));
	}
	/**
	 * checks if there are no conflicts
	 * 
	 * @return true if there are no conflicts, false if there are
	 */
	private boolean noConflicts() {
		// loop through the vertices
		for (int i = 0; i < neigh.size(); i++) {
			// loop through the neighbours
			for (int j = 0; j < neigh.get(i).size(); j++) {
				// check if there are conflicts
				if (colors[i] == colors[neigh.get(i).get(j)] && colors[i] != 0) {
					return false;
				}
			}
		}
		return true;
	}
}