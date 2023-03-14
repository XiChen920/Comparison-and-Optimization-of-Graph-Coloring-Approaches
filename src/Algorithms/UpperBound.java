package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Upper bound algorithm is a greedy variant
 * @author GROUP 29
 *
 */
public class UpperBound extends Algorithm{
	
	private int efficiency;
	public int find(ArrayList<ArrayList<Integer>> e) {
		efficiency = 0;
		
		//amount of vertices
		int n = propertyDetection.getOrder();
		
		
		//the array to store the coloring situation
		//0 if uncolored
		int[]vertexColor= new int[n];
		for(int i=0;i<vertexColor.length;i++) {
			vertexColor[i]=0;
		}	
		//color the vertex
		int c;
		for(c = 0; c < n; c++) {
			for(int i = 0; i < n; i++) {
				if(vertexColor[i] == 0) {
					ArrayList<Integer> neighbor = e.get(i);//get vertex i's neighbor
					boolean sameColor = true;
					for(int j = 0; j < neighbor.size(); j++) {
						if(vertexColor[neighbor.get(j)] == c) {
							efficiency++;//efficiency step
							sameColor = false;
							break;
						}
					}
					if(sameColor) {
						efficiency++;//efficiency step
						vertexColor[i] = c;
					}
				}
			}
			if(!hasZero(vertexColor))
				return c;
		}
		return c;//the number of color it uses	
	}
	
	
	//to check if a array contains 0(from position array[1])
	//i.e. this is the method to check if all is colored
	public boolean hasZero(int[]arr) {
		for(int i = 0; i < arr.length; i++) {
			efficiency++;//efficiency step
			if(arr[i] == 0) {
				return true;
			}
		}
		return false;
	}
	
	

	@Override
	public int chromaticNumber() {
		name = "UpperBound";
		return find(propertyDetection.getGraph());

	}
	
	
}