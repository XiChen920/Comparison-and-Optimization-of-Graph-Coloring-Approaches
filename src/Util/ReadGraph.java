package Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadGraph {	
	/** 
	 * n is the number of vertices in the graph.
	 */
	public static int n = -1;
	/**
	 * The index of the vertex we are currently looking at.
	 */
	public static int currentIndex = 0;
	public final static boolean DEBUG = false;
	
	public final static String COMMENT = "//";
	/**
	 * Main method of the class ReadGraph, when executed the program calculates the chromatic number of 
	 * the graph specified in location and outputs the results of the calculation to the console. 
	 * 
	 * @param args
	 */
	public static String[][] loadGraph(int number){
		String s = "graph";
		if(number < 10)
			s+="0"+number+"_2020.txt";
		else
			s+=number+"_2020.txt";
		return loadGraph(s);
	}
	public static String[][] loadGraph(String location){
		if(location.startsWith("C:")) {
			return loadGraphAbsolutePath(location);
		}
		String[][] graph = null;
		//insert code to read a graph from a file into a 2-dimensional string array
		if(location.isBlank()){
			System.out.println("Error! No filename specified.");
			return null;
		} else {
			File file = new File(location);
			
			//! m is the number of edges in the graph.
			int m = -1;
			currentIndex = 0;
			
			//! e will contain the edges of the graph.
			ArrayList<ArrayList<Integer>> e= null;
			
			try 	{ 
			    	FileReader fr = new FileReader(file);
			        BufferedReader br = new BufferedReader(fr);
	
			        String record = new String();
					
					//! The first few lines of the file are allowed to be comments, staring with a // symbol.
					//! These comments are only allowed at the top of the file.
					
					//! -----------------------------------------
			        while ((record = br.readLine()) != null)
						{
						if( record.startsWith("//") ) continue;
						break; // Saw a line that did not start with a comment -- time to start reading the data in!
						}
	
					if( record.startsWith("VERTICES = ") )
						{
						n = Integer.parseInt( record.substring(11) );			
						if(DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
						}
					record = br.readLine();
					
					if( record.startsWith("EDGES = ") )
						{
						m = Integer.parseInt( record.substring(8) );					
						if(DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
						}
					graph = new String[m+2][2];
					graph[0] = new String[]{"VERTICES = ", n+""};
					graph[1] = new String[]{"EDGES = ", m+""};		
					for( int d=0; d<m; d++)
						{
						if(DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
						record = br.readLine();
						String data[] = record.split(" ");
						if( data.length != 2 )//only two data points
								{
								System.out.println("Error! Malformed edge line: "+record);
								System.exit(0);
								}
						int from = Integer.parseInt(data[0]), to = Integer.parseInt(data[1]);//from = u; to = v
						graph[d+2][0] = from+"";
						graph[d+2][1] = to+"";
						
						if(DEBUG) System.out.println(COMMENT + " Edge: "+ from +" "+to);
				
						}
									
					String surplus = br.readLine();
					if( surplus != null )
						{
						if( surplus.length() >= 2 ) if(DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");						
						}
					
					}
			catch (IOException ex)
				{ 
		        // catch possible io errors from readLine()
			    System.out.println("Error! Problem reading file "+location);
				System.exit(0);
				}
	
				//
				return graph;
		}

		
	
	}
	public static String[][] loadGraphAbsolutePath(String absolutePath){
		String[][] graph = null;
		//insert code to read a graph from a file into a 2-dimensional string array
		if(absolutePath.isBlank()){
			System.out.println("Error! No filename specified.");
			return null;
		} else {
			File file = new File(absolutePath);
			
			//! m is the number of edges in the graph.
			int m = -1;
			currentIndex = 0;
			
			//! e will contain the edges of the graph.
			ArrayList<ArrayList<Integer>> e= null;
			
			try 	{ 
			    	FileReader fr = new FileReader(file);
			        BufferedReader br = new BufferedReader(fr);
	
			        String record = new String();
					
					//! The first few lines of the file are allowed to be comments, staring with a // symbol.
					//! These comments are only allowed at the top of the file.
					
					//! -----------------------------------------
			        while ((record = br.readLine()) != null)
						{
						if( record.startsWith("//") ) continue;
						break; // Saw a line that did not start with a comment -- time to start reading the data in!
						}
	
					if( record.startsWith("VERTICES = ") )
						{
						n = Integer.parseInt( record.substring(11) );			
						if(DEBUG) System.out.println(COMMENT + " Number of vertices = "+n);
						}
					record = br.readLine();
					
					if( record.startsWith("EDGES = ") )
						{
						m = Integer.parseInt( record.substring(8) );					
						if(DEBUG) System.out.println(COMMENT + " Expected number of edges = "+m);
						}
					graph = new String[m+2][2];
					graph[0] = new String[]{"VERTICES = ", n+""};
					graph[1] = new String[]{"EDGES = ", m+""};		
					for( int d=0; d<m; d++)
						{
						if(DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));
						record = br.readLine();
						String data[] = record.split(" ");
						if( data.length != 2 )//only two data points
								{
								System.out.println("Error! Malformed edge line: "+record);
								System.exit(0);
								}
						int from = Integer.parseInt(data[0]), to = Integer.parseInt(data[1]);//from = u; to = v
						graph[d+2][0] = from+"";
						graph[d+2][1] = to+"";
						
						if(DEBUG) System.out.println(COMMENT + " Edge: "+ from +" "+to);
				
						}
									
					String surplus = br.readLine();
					if( surplus != null )
						{
						if( surplus.length() >= 2 ) if(DEBUG) System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '"+surplus+"'");						
						}
					
					}
			catch (IOException ex)
				{ 
		        // catch possible io errors from readLine()
			    System.out.println("Error! Problem reading file "+absolutePath);
				System.exit(0);
				}
	
				//
				return graph;
		}

		
	
	}

}
