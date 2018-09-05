package cold_Puter_Science;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * We’re not going to sugar-coat it: Chicago’s winters can be rough. The temperatures sometimes dip to uncomfortable levels and, 
 * after last year’s “polar vortex”, the University of Chicago Weather Service wants to find out exactly how bad the winter was. 
 * More specifically, they are interested in knowing the total number of days in which the temperature was below zero degrees Celsius.
 * 
 * Input
 * The input is composed of two lines. The first line contains a single positive integer n (1≤n≤100) that specifies the number of temperatures collected by the University of Chicago Weather Service. The second line contains n temperatures, each separated by a single space. Each temperature is represented by an integer t (−1000000≤t≤1000000)
 * 
 * Output
 * You must print a single integer: the number of temperatures strictly less than zero.
 * 
 * @author ndonaldson
 */

public class ColdPuterScience {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int totalTemps = -1;
		ArrayList<Integer> temps = new ArrayList<Integer>();
		int tempsBelowZero = 0;
		while(sc.hasNext()) {
			if(totalTemps == -1) {
				totalTemps = Integer.parseInt(sc.next());
			}
			else if(Integer.parseInt(sc.next()) < 0) tempsBelowZero++;
		}
		System.out.println(tempsBelowZero);
		sc.close();
	}

}
