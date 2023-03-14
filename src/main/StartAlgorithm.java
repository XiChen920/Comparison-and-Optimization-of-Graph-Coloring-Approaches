package main;

import Algorithms.*;

/**
 * This class is responsible to lead the program through the chromatic number estimation.
 * @author GROUP 29
 *
 */
public class StartAlgorithm {
	/**
	 * Object responsible for upper bound
	 */
	private Algorithm a;
	/**
	 * Constructor that starts the estimation progress with the file, 
	 * of which the file name is given. It has to be in the same directory as the code.
	 * @param fileName name of graph
	 */
	public StartAlgorithm(String fileName) {
		a = new SDGC();
		loadGraph(fileName);
	}

	/**
	 * Loads the graph from the desired location and runs our algorithm on it
	 * 
	 * @param location graph file location
	 */
	public void loadGraph(String location) {
		//1. detects properties such as cycles, highest degree, cliques, etc.
		PropertyDetection pa = new PropertyDetection(location);
		a.setPropertyDetection(pa);
		System.out.println("NEW BEST UPPER BOUND = "+pa.getChromaticNumber());
		System.out.println("NEW BEST UPPER BOUND = "+pa.getUpperBound());
		System.out.println("NEW BEST LOWER BOUND = "+pa.getOldLowerBound());
		//2. start the upper bound estimation first with DSatur and then with SDGC
		int cn = a.chromaticNumber();
		System.out.println("NEW BEST UPPER BOUND = " + cn);
		if(pa.getOldLowerBound() == cn) {
			System.out.println("CHROMATIC NUMBER = " + cn);
		}
		if(pa.getLowerBound() <= cn) {
			System.out.println("NEW BEST LOWER BOUND = "+pa.getLowerBound());
			if(pa.getLowerBound() == cn) {
				System.out.println("CHROMATIC NUMBER = " + cn);
			}
		}
	}
}
