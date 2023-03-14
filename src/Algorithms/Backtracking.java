package Algorithms;

import java.util.ArrayList;

/**
 * Backtracking algorithm
 * @author GROUP 29
 *
 */
public class Backtracking extends Algorithm {
    private static final long STOP = 100000;
    private long start = System.currentTimeMillis();
    public int[] x;
    private int chromNum;
    private int testchrom;
    private int n;
    private ArrayList<ArrayList<Integer>> neigh;

    @Override
    public int chromaticNumber() {
        name = "Backtracking";
        n = propertyDetection.getOrder();
        Algorithm a = new DSatur();
        a.setPropertyDetection(propertyDetection);
        chromNum = Math.min(propertyDetection.getUpperBound(), a.chromaticNumber());
        testchrom = chromNum;
        neigh = propertyDetection.getGraph();
        // initialize array x, used to store the colors in algorithm 2
        x = new int[n];
        // runs algorithm 2
        // while the algorithm returns true
        while (testChrom(0)) {
            // resets the colors for algortihm 2
            for (int i = 0; i < x.length; i++) {
                x[i] = 0;
            }
            // set the chromatic number to the found tested chromatic number
            chromNum = testchrom;
            // decrease the chromatic number you want to test
            testchrom--;
        }
        return chromNum;
    }

    /**
     * A recursive method that tries to color the graph with a certain amount of
     * colors. first checks if the time passed is above 30 because then it stops the
     * algortihm then it checks if the last vertex has been colored and if so
     * returns true loops through all colors, checks if the current vertex can be
     * colored with color c if so recursive call this method with the next vertex,
     * also check if this returns true, if not reset the color of the current vertex
     * because it did not lead to a solution
     * 
     * @param vertex vertex you want to color
     * @return
     */
    private boolean testChrom(int vertex) {
        // don�t need to check coloring lower than lowerbound
        if (testchrom < propertyDetection.getLowerBound())
            return false;
        // checks current time and if the time passed is above 3O seconds stop algorithm
        long finish = System.currentTimeMillis();
        if (finish - start > STOP - propertyDetection.getTimeTaken()) {
            return false;
        }
        // if last vertex has been colored return true
        efficiency++;
        if (vertex >= n) {
            return true;
        }
        // loops through all colors
        for (int c = 1; c <= testchrom; c++) {
            // checks if the vertex can be colored with color c
            efficiency++;
            if (canBeColored(vertex, c)) {
                x[vertex] = c; // if so color it
                efficiency++;
                if (testChrom(vertex + 1)) { // recursive call this method with the next vertex
                    // if it returns true this also returns true
                    return true;
                } else {
                    efficiency++;
                    // if not reset this vertex because this color did not end up in a anwser
                    x[vertex] = 0;
                }
            }
        }

        return false;
    }

    /**
     * checks if a certain vertex can be colored with a certain color
     * 
     * @param vertex vertex you want to color
     * @param c      color you want to test
     * @return
     */
    private boolean canBeColored(int vertex, int c) {
        // loops through the neighbours
        for (int k = 0; k < neigh.get(vertex).size(); k++) {
            efficiency++;
            // if a neighbour has the same color return false
            if (x[neigh.get(vertex).get(k)] == c) {
                return false;
            }
        }
        // if no neighbour has that color return true
        return true;
    }

}
