package Util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;

public class Util {
	public static BigDecimal nCk(int n, int k) {
		BigDecimal nFactorial = factorial(n);
		BigDecimal kFactorial = factorial(k);
		BigDecimal nminuskFactorial = factorial(n - k);
		return nFactorial.divide(kFactorial.multiply(nminuskFactorial));
	}
	public static BigDecimal pow(BigDecimal n, int power) {
		if(n.compareTo(new BigDecimal(0.5)) == -1)
			return new BigDecimal(0);
		if(power > 4001)
			return pow(n.pow(4000), power-4000);
		else
			return n.pow(power);
	}
	
	public static BigDecimal factorial(int n) {
		if(n == 1) {
			return new BigDecimal(1);
		}
		return new BigDecimal(n).multiply(factorial(n-1));
	}
	public static void sort(int[][] a) {
		int max;
		int maxInd;
		for(int i = 0; i < a.length; i++) {
			max = Integer.MIN_VALUE;
			maxInd = -1;
			for(int j = i; j < a.length; j++) {
				if(a[j][1] > max) {
					max = a[j][1];
					maxInd = j;
				}
			}
			if(maxInd > -1) {
				int[] tmp = a[i];
				a[i] = a[maxInd];
				a[maxInd] = tmp;
			}
		}
	}
	

	public static <T> boolean contains(T t, T[] a) {
		for(T s : a) {
			if(s.equals(t))
				return true;
		}
		return false;
	}
	public static boolean isOdd(int i) {
		return i % 2 == 1;
	}
	public static <T> ArrayList<T> cloneArrayList(ArrayList<T> al){
		ArrayList<T> al2 = new ArrayList<T>();
		for(T t : al) {
			al2.add(t);
		}
		return al2;
	}
	public static <T> void cloneArrayList(ArrayList<T> source, ArrayList<T> dest){
		for(T t : source) {
			dest.add(t);
		}
	}
	public static <T> ArrayList<ArrayList<T>> sortArrayListsByLength(ArrayList<ArrayList<T>> al) {
		Collections.sort(al, new Comparator<ArrayList>(){
		    public int compare(ArrayList al, ArrayList al1) {
		        return al.size() - al1.size(); // assumes you want biggest to smallest
		    }
		});
		return al;
	}

	public static void getLowestNotContained(int upperbound, ArrayList<Integer> al) {
		Collections.sort(al);
	}
	
	public static <T> boolean contains(ArrayList<ArrayList<T>> al, ArrayList<T> t) {
		boolean contains = true;
		for(int i = 0; i < al.size(); i++) {
			ArrayList<T> tmp = al.get(i);
			if(tmp.size() == t.size()) {
				contains = true;
				for(int j = 0; j < t.size(); j++) {
					if(!tmp.contains(t.get(j))) {
						contains = false;
						break;
					}
				}
				if(contains)
					return true;
			}
		}
		return false;
	}
	public static <T> boolean contains(ArrayList<ArrayList<T>> al, ArrayList<T> t, T t1) {
		boolean contains = true;
		for(int i = 0; i < al.size(); i++) {
			ArrayList<T> tmp = al.get(i);
			if(tmp.size() == t.size()) {
				contains = true;
				for(int j = 0; j < t.size(); j++) {
					if(!tmp.contains(t.get(j))) {
						contains = false;
						break;
					}
				}
				if(!tmp.contains(t1)) {
					contains = false;
				}
				if(contains)
					return true;
			}
		}
		return false;
	}


	public static boolean isSame(ArrayList<Integer> curPar, ArrayList<Integer> curPar2) {
		for(int i = 0; i < curPar.size(); i++) {
			if(curPar.get(i) != curPar2.get(i))
				return false;
		}
		return true;
	}
	public static double round(double d) {
		DecimalFormat df = new DecimalFormat("0.00");
		String s = df.format(d);
		return Double.parseDouble(s);
	}
	public static double toPercentage(double d) {
		DecimalFormat df = new DecimalFormat("00.00");
		String s = df.format(d*100.0);
		d = Double.parseDouble(s);
		return d;
	}
	public static ArrayList<Integer> assignAllValue(int value, int length){
		ArrayList<Integer> al = new ArrayList<Integer>();
		for(int i = 0; i < length; i++) {
			al.add(value);
		}
		return al;
	}
}
