package sibice;

import java.util.Scanner;

public class Sibice {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		//Just to skip num of matches
		scanner.nextInt();
		int widthOfBox = scanner.nextInt();
		int heightOfBox = scanner.nextInt();
		double diagonal = Math.sqrt(Math.pow((double)widthOfBox, 2.0) + Math.pow((double)heightOfBox, 2.0));
		
		while(scanner.hasNext()) {
			int matchLength = scanner.nextInt();
			if((matchLength <= widthOfBox) || (matchLength <= heightOfBox) || (matchLength <= diagonal)) {
				System.out.println("DA");
			} else {
				System.out.println("NE");
			}
		}
		scanner.close();
	}
}
