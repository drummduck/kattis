package timing;

import java.util.HashMap;
import java.util.Scanner;

public class Timing {
	
	private static int numTestCases;
	private static int numOfForts;
	private static int numOfLinks;
	private static int timeToAttack;
	private static HashMap<Integer, Fort> forts = new HashMap<Integer, Fort>();
	
	public static void main(String[] args) {

		
		Scanner scanner = new Scanner(System.in);
		

			numTestCases = Integer.parseInt(scanner.next());
			
			for(int i = 0; i < numTestCases; i++) {
				forts.clear();
				
				numOfForts = Integer.parseInt(scanner.next());
				numOfLinks = Integer.parseInt(scanner.next());
				timeToAttack = Integer.parseInt(scanner.next());
				
				//if(timeToAttack == 0) timeToAttack = 1;
				
				
				for(int j = 0; j < numOfForts; j++) {
					forts.put(j, new Fort(Double.parseDouble(scanner.next())));
				}
				
				
				for(int j = 0; j < numOfLinks; j++) {
					int fortNumber = Integer.parseInt(scanner.next());
					Fort fort = forts.get(fortNumber);
					fort.addPath(new Path(Integer.parseInt(scanner.next()), Double.parseDouble(scanner.next())));
				}
				
				System.out.println((float)startTestCase());
			}
		
		
		scanner.close();
	}
	
	public static double startTestCase() {
		
		for(int i = 0; i < timeToAttack; i++) {
			for(Integer fortKey : forts.keySet()) {
				Fort fort = forts.get(fortKey);
				fort.calculateLeavingForces();
				fort.releaseForces();		
			}
			for(Integer fortKey : forts.keySet()) {
				Fort fort = forts.get(fortKey);
				fort.sendForces(forts);
			}
		}
		
		return findLeastFortified();
	}
	
	public static double findLeastFortified() {
		double lowestFortValue = -1;
		double tempLowestValue = 0;
		
		for(Integer fortKey : forts.keySet()) {
			Fort fort = forts.get(fortKey);
			tempLowestValue = fort.getForces();
			tempLowestValue += fort.getForcesFromSentForts(forts);
			for(Integer fortKey2 : forts.keySet()) {
				Fort fort2 = forts.get(fortKey2);
				if(fort2.hasPathToFort(fortKey)) {
					tempLowestValue += fort2.getForces();
				}
			}
			if(tempLowestValue < lowestFortValue || lowestFortValue == -1) {
				lowestFortValue = tempLowestValue;
			}
		}
		
		return lowestFortValue;
	}
}
