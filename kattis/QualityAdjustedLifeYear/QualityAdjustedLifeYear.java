package qualityAdjustedLifeYear;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class QualityAdjustedLifeYear {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double totalQualityOfLife = 0;
		int numOfPeriods = 0;
		
		if(scanner.hasNextInt()) {
			numOfPeriods = scanner.nextInt();
		} else {
			System.out.println("You suck!");
			return;
		}
		
		for(int i = 0; i < numOfPeriods; i++) {
			double qualityOfLife = 0;
			double numOfYears = 0;
			
			if(scanner.hasNextDouble()) {
				qualityOfLife = scanner.nextDouble();
			} else {
				System.out.println("You suck!");
				return;
			}
			
			if(scanner.hasNextDouble()) {
				numOfYears = scanner.nextDouble();
			} else {
				System.out.println("You suck!");
				return;
			}
			
			totalQualityOfLife += numOfYears * qualityOfLife;
		}
		
		System.out.println(totalQualityOfLife);
		
	}

}
