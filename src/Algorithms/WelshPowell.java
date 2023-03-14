package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Welsh Powell algorithm
 * @author GROUP 29
 *
 */
public class WelshPowell extends Algorithm{

	/** 
	 * n is the number of vertices in the graph.
	 */
	private int order;
	/**
	 * max is the highest degree of the graph.
	 */
	private int max;
	/**
	 * Declaring an array that saves the color of every vertex.
	 */
	private int[] color;
	/**
	 * The index of the vertex we are currently looking at.
	 */
	private int currentIndex;

	private boolean checkAllNeighbors;

	private boolean colorAreColored = true;


	//! e will contain the edges of the graph.
	ArrayList<ArrayList<Integer>> graph;

	private int[][] degreeSequence;
	
	private int lastHighestDegreeIndex;

	// How to copy all info from propertyDetection.getGraph() to array e
	// How to get the number of vertices 




	public int chromaticNumber() {
		name = "Welsh-Powell";
		order = propertyDetection.getOrder();
		color = new int[order];
		for(int i : color) {
			color[i] = 0;
		}
		currentIndex = 0;
		colorAreColored = true;
		chromaticNumber = 0;
		graph = propertyDetection.getGraph();
		degreeSequence = propertyDetection.getDegreeSequence();
		int chromaticNumber = welshPowell(propertyDetection.getDegreeSequence()[0][0]);
		lastHighestDegreeIndex = 0;

		return chromaticNumber;
	}


	/**
	 * This method is the main part of the logic of the Welsh Powell algorithm
	 * This algorithm begins with using the vertex with the highest degree, give it a color  
	 * Then it will search for vertices which are not connected to the first vertex and give them the same color.
	 * After coloring all vertices that can be colored by color 1, repeat the same steps with the second highest degree vertex
	 * Repeat the process until all vertices have been colored.  
	 * The algorithm is considered to be an upper bound algorithm
	 * 
	 * @param currentHighestdegree integer storing the current highest degree vertex
	 */
	public int welshPowell(int currentHighestdegree) {
		//1. Assign a color to the current highest degree vertex that is uncolored
		color[currentHighestdegree] = chromaticNumber;
		//2. Go through all the vertices one by one
		for(int i = 0; i < order; i++) {
			efficiency++;
			if(color[i] == 0) {
				//3. Look at each vertex�s neighbor
				ArrayList<Integer> neighbor = graph.get(i);
				boolean canColor = true;
				for(int n = 0; n < neighbor.size(); n++) {
					efficiency++;
					if(color[neighbor.get(n)] == chromaticNumber) {
						canColor = false;
						break;
					}
				}
				//4. If none of the neighbors are colored with this color, color the current vertex
				if(canColor) {
					color[i] = chromaticNumber;
				} 
				//5. If at least one of the neighbors has this color, skip the current vertex we�re looking at without giving it a color
			}
			
			

		}
		
		
		//6. Find the next uncolored highest degree vertex
		currentHighestdegree = -1;
		for(int i = lastHighestDegreeIndex + 1; i < order; i++) {
			efficiency++;
			int cur = degreeSequence[i][0];
			if(color[cur] == 0) {
				currentHighestdegree = cur;
				lastHighestDegreeIndex = i;
				
				//7. Increase chromatic number
				chromaticNumber++;
				break;
			}
		}
		//all vertices colored, return chromaticNumber
		if(currentHighestdegree == -1) {
			return chromaticNumber;
		} else {
			//8. Repeat the process until we color all vertices
			return welshPowell(currentHighestdegree);
		}
	}
}
