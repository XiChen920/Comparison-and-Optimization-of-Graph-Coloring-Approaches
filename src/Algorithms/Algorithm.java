package Algorithms;

import main.PropertyDetection;

/**
 * Super class to all algorithms
 * @author GROUP 29
 *
 */
public abstract class Algorithm {
	/**
	 * Chromatic number computed
	 */
	protected int chromaticNumber;
	/**
	 * Efficiency measure, counts amount of computations
	 */
	protected long efficiency;
	/**
	 * Name of the algorithm
	 */
	protected String name;
	/**
	 * To detect properties
	 */
	protected PropertyDetection propertyDetection;
	/**
	 * Computes chromatic number
	 * @return chromatic number
	 */
	public abstract int chromaticNumber();
	
	//GETTERS AND SETTERS
	public void setPropertyDetection(PropertyDetection pa) {
		propertyDetection = pa;
	}
	public long getEfficiency() {
		return efficiency;
	}
	public String getName() {
		return name;
	}
}
