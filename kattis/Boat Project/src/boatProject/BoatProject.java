package boatProject;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

public class BoatProject {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int numBoatParts = -1;
		int daysInSeason = -1;
		boolean avoided = true;
		HashSet<String> parts = new HashSet<String>();
		int dayPassed = 0;
		int uniqueParts = 0;
		while(in.hasNext()) {
			String part = "";
			if(numBoatParts == -1) {
				numBoatParts = Integer.parseInt(in.next());
			}
			else if(daysInSeason == -1) {
				daysInSeason = Integer.parseInt(in.next());
			}
			else {	
				part = in.next();
				dayPassed++;
				if(!parts.contains(part)) {
					uniqueParts++;
					if(uniqueParts == numBoatParts) {
						System.out.println(dayPassed);
						avoided = false;
						break;
					}
					parts.add(part);
				}
				else continue;
			}
		}
		
		if(avoided) System.out.println("paradox avoided");
		in.close();
	}

}
