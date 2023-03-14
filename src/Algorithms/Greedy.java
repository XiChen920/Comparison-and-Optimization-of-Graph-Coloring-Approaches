package Algorithms;

import java.util.ArrayList;

/**
 * Greedy algorithm
 * @author GROUP 29
 *
 */
public class Greedy extends Algorithm{
    
	/**
	 *chromatic number the algortihm produces
	 */
	private int amountcolors;
	/**
	 *array storing the colors of each vertex
	 */
	private int[] colors;
	
	private ArrayList<ArrayList<Integer>> neigh;
	/**
	 *Order of the graph ( number of vertices )
	 */
	private int n;
    /**
	*Current index we're looking at
	*/
	private int currentindex;
	/**
	* Current highest degree vertex
	*/
	private int degree;
	/**
	* Measures efficiency 
	*/
	private int efficiency;

	@Override
	public int chromaticNumber() {
		name = "HeuristicPhase2";
		degree = propertyDetection.getHighestDegree();
		n = propertyDetection.getOrder();
		neigh = propertyDetection.getGraph();
		currentindex = 0;
		amountcolors = 0;
		colors = new int[n];
		firstColor();
		giveColor();
		return amountcolors;
	}
	
	/**
	 * gives the first vertex the first color and increases the amount of colors
	 * used. this is step 1.
	 */
	private void firstColor() {
		if (amountcolors == 0) {
			colors[getIndex()] = 1;
			amountcolors++;
			efficiency++;
		}
	}

	
	/**
	 * This method is the main part of the logic of the chromatic number algorithm.
	 * It contains the following steps: 2. Set the current color to 1 and select the
	 * vertex with the next highest degree. 3. a. If at least one of the adjacent
	 * vertices uses that color, increase the color by one and go back to 3. i. If
	 * we looked for the last possible color and this was used, increase the amount
	 * of used colors and give this vertex that new color. b. If none of the
	 * adjacent vertices use that color, use it to color the current vertex. 4.
	 * Repeat from 2. for every vertex. 5. Return the number of colors used as the
	 * chromatic number.
	 */
	private boolean giveColor() {
		for (int i = 1; i < n; i++) { // 4. Repeat from 2. for every vertex with the index i.
			efficiency++;
			int currentvertex = getIndex();// 2. select the vertex with the next highest degree.
			for (int j = 1; j <= amountcolors; j++) {// 2. Set the current color to 1.
				for (int k = 0; k < neigh.get(currentvertex).size(); k++) { //Loops through the neighbors
					efficiency++;
					if (colors[neigh.get(currentvertex).get(k)] == j) { // 3. a. checks if adjacent vertex uses color
						efficiency++;
						if (j == amountcolors) {// 3. b. If we are looking at the last possible color.
							colors[currentvertex] = ++amountcolors;  // increase the amount of colors used by 1 and give
							                                         // this vertex the new color.
							j = amountcolors + 1;
						}
						break;
					} else if (k == neigh.get(currentvertex).size() - 1) { // check if we are checking the last possible
						efficiency++;                                             // vertex 
						colors[currentvertex] = j;
						j = amountcolors + 1;
					} else {
						efficiency++;
					}
				}
			}
		}
		return true;
	}

	
	
	/**
	 * This method gives back the index of the vertex we want to color using the
	 * following steps: a. Starts at vertex 1 and loops through all vertices,
	 * looking for the max. b. if it finds a vertex with the max it returns that
	 * index, and increases the current index so that it looks at the remaining
	 * vertices for the max. i. if the vertex we found is the last vertex we reset
	 * the current index and decrease the max c. if it does not find a vertex with
	 * the max and checked the last vertex it decreases the max we want to look for,
	 * and resets the current index back to 1
	 * @return returns the index of the vertex, if the code would happen to not work
	 *         it returns -1.
	 *         */
	private int getIndex() {
		while (currentindex < n) {
			efficiency++;
			if (neigh.get(currentindex).size() == degree) {
				int result = currentindex;
				if (currentindex == neigh.size() - 1) {
					currentindex = -1;
					degree--;
				}
				
				// increases the current index so that it will start at the next index the next
				// time and wont give back the same index.
				currentindex++;
				return result;
			} else if (currentindex == neigh.size() - 1) {
				currentindex = -1;
				degree--;
			}
			currentindex++;
		}
		return -1;
	}
	
}
