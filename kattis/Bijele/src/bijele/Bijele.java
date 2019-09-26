package bijele;

import java.util.Scanner;

/**
 * Mirko has found an old chessboard and a set of pieces in his attic. 
 * Unfortunately, the set contains only white pieces, and apparently an 
 * incorrect number of them. A set of pieces should contain:
 * 
 * One king
 * 
 * One queen
 * 
 * Two rooks
 * 
 * Two bishops
 * 
 * Two knights
 * 
 * Eight pawns
 * 
 * Mirko would like to know how many pieces of each type he should add 
 * or remove to make a valid set.
 * 
 * Input
 * The input consists of 6 integers on a single line, each between 0 and 10 (inclusive). 
 * The numbers are, in order, the numbers of kings, queens, rooks, bishops, knights and 
 * pawns in the set Mirko found.
 * 
 * Output
 * Output should consist of 6 integers on a single line; the number of pieces of each 
 * type Mirko should add or remove. If a number is positive, Mirko needs to add that 
 * many pieces. If a number is negative, Mirko needs to remove pieces.
 * 
 * @author ndonaldson
 */

public class Bijele {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int kings = -1;
		int queens = -1;
		int rooks = -1;
		int bishops = -1;
		int knights = -1;
		int pawns = -1;
		String output = "";
		Pieces pieces;
		
		while(in.hasNext()) {
			if(kings == -1) {
				kings = Integer.parseInt(in.next());
				output += getDifference(kings, Pieces.KINGS.getValue()) + " ";
			}
			else if(queens == -1) {
				queens = Integer.parseInt(in.next());
				output += getDifference(queens, Pieces.QUEENS.getValue()) + " ";
			}
			else if(rooks == -1) {
				rooks = Integer.parseInt(in.next());
				output += getDifference(rooks, Pieces.ROOKS.getValue()) + " ";
			}
			else if(bishops == -1) {
				bishops = Integer.parseInt(in.next());
				output += getDifference(bishops, Pieces.BISHOPS.getValue()) + " ";
			}
			else if(knights == -1) {
				knights = Integer.parseInt(in.next());
				output += getDifference(knights, Pieces.KNIGHTS.getValue()) + " ";
			}
			else if(pawns == -1) {
				pawns = Integer.parseInt(in.next());
				output += getDifference(pawns, Pieces.PAWNS.getValue()) + " ";
			}
		}
		System.out.println(output);
	}
	
	private enum Pieces{
		KINGS(1),
		QUEENS(1),
		ROOKS(2),
		BISHOPS(2),
		KNIGHTS(2),
		PAWNS(8);
		
		private final int value;
		
		Pieces(int value){
			this.value = value;
		}

	    public int getValue() { return value; }
	}
	
	private static String getDifference(int current, int needed) {
		int difference = needed - current;
		
		return String.valueOf(difference);
	}
}
