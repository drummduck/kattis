package sodaSurpler;

import java.util.Scanner;

public class SodaSurpler {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int startingEmpties = -1;
		int found = -1;
		int cost = -1;
		while(in.hasNext()) {
			if(startingEmpties == -1) {
				startingEmpties = Integer.parseInt(in.next());
			}
			else if(found == -1) {
				found = Integer.parseInt(in.next());
			}
			else if(cost == -1) {
				cost = Integer.parseInt(in.next());
				break;
			}
		}
		System.out.println(bottlesDrank(startingEmpties + found, cost, 0));
		in.close();
	}
	
	public static int bottlesDrank(int hasLeft, int needsForOne, int drank) {
		
		if(hasLeft < needsForOne) {
			return 0;
		}
		
		int leftOver = (hasLeft % needsForOne);
		
		if(leftOver != 0) {
			drank = (hasLeft / needsForOne);
			hasLeft = (hasLeft / needsForOne) + leftOver;
		}
		else {
			drank = (hasLeft / needsForOne);
			hasLeft = (hasLeft / needsForOne);
		}
		
		return drank + bottlesDrank(hasLeft, needsForOne, drank);
	}

}
