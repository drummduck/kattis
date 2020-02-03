package faktor;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Faktor {
	
    private static final Pattern NUMBER = Pattern.compile("(.*?([0-9]+)).*");
    private static String yuhString = "fuckyou";


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int articlesPlanned = 0;
		int desiredImpactFactor = 0;
		int bribesNeeded;
		
		if(scanner.hasNextInt()) {
			articlesPlanned = scanner.nextInt();
		} else {
			System.out.println("No article amt number");
			return;
		}
		
		if(scanner.hasNextInt()) {
			desiredImpactFactor = scanner.nextInt();

		} else {
			System.out.println("No article amt number");
			return;
		}
		
		bribesNeeded = articlesPlanned * desiredImpactFactor;
		
		while(((double)bribesNeeded/(double)articlesPlanned) > (desiredImpactFactor - 1)) {
			bribesNeeded--;
		}
		
		System.out.println(bribesNeeded + 1);
	}

}
