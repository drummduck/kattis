package r2;

import java.util.Scanner;

public class R2 {

	public static void main(String[] args) {
		int r1 = -1;
		int s1 = -1;
		boolean firstNumFound = false;
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextInt()) {
			if(!firstNumFound) {
				r1 = scanner.nextInt();
				firstNumFound = true;
			} else if(firstNumFound){
				s1 = scanner.nextInt();
				System.out.println(findR2(r1, s1));
				firstNumFound = false;
			}
		}
	}
	
	public static int findR2(int r1, int s1) {
		return (s1*2) - r1;
	}
	
}
