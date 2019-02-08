package crackerBarrel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class CrackerBarrel {
	
	private final static int BOARD_SIZE = 5;
	private static int movesCount = 0;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String targetColor = null;
		ArrayList<Peg> board = new ArrayList<Peg>(); 
		int numOfPegs = 0;
		int rowsRead = 0;
	
		while(in.hasNextLine()) {
			String text = in.nextLine();
			if(text.equals("**")) {
				break;
			} else if (targetColor == null) {
				targetColor = text;
			} else {
				numOfPegs += setupRow(text, board);
				rowsRead++;
			}
			if(rowsRead == BOARD_SIZE) {
				calculatePossibility(targetColor, board, numOfPegs);
				targetColor = null;
				board.clear();
				numOfPegs = 0;
				rowsRead = 0;
			}
		}
		
		in.close();
	}
	
	public static boolean calculatePossibility(String targetColor, ArrayList<Peg> board, int numOfPegs) {
		Stack<Move> moves = new Stack<Move>();
		String movesString = "";
		if (makeMoves(board.get(0), 0, numOfPegs-1, board, moves, 0, targetColor, 0)) {
			while(!moves.isEmpty()) {
				movesString = moves.pop().toString() + movesString;
			}
//			System.out.println("MOVES: " + movesCount);
//			System.out.print(movesString);
			System.out.println("Possible");
			return true;
		}
		else {
//			System.out.println("MOVES: " + movesCount);
//			System.out.print(movesString);
			System.out.println("Impossible");
			return false;
		}
	}
	
	public static boolean makeMoves(Peg peg, int index, int maxRemoves, ArrayList<Peg> board, Stack<Move> moves, int pegsRemoved, String targetColor, int noMoveCount) {	
		
		movesCount++;
		
		int removed = pegsRemoved;
		
		int actualRemoved = 0;
		
		for(int i = 0; i <= board.size()-1; i++) {
			if(board.get(i) == null) actualRemoved++;
		}
		
		if(noMoveCount == 15) {
			return false;
		}
		
		if(removed == maxRemoves) {
			if(peg.getColor().equals(targetColor)) {
				return true;
			} 
			else 
				return false;
		} 
		
//		if(index == 15) {
//			return makeMoves(board.get(0), 0, maxRemoves, board, moves, removed, targetColor, noMoveCount + 1); 
//		}
		
		if(peg == null) {
			if(index == 14) {
				return makeMoves(board.get(0), 0, maxRemoves, board, moves, removed, targetColor, noMoveCount + 1);
			} else {
				return makeMoves(board.get(index + 1), index + 1, maxRemoves, board, moves, removed, targetColor, noMoveCount + 1);
			}
		}
				
		switch(index) {
			case 0: {
				if(board.get(1) != null && board.get(3) == null) {
					removed++;
					moves.add(new Move(0, 1, 3, board.get(0), board.get(1)));
					board.set(1, null);
					board.set(3, peg);
					board.set(0, null);
					if (makeMoves(peg, 3, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(0, move.getFromPeg());
						board.set(3, null);
						board.set(1, move.getRemovedPeg());
					}
				}
				if(board.get(2) != null && board.get(5) == null) {
					removed++;
					moves.add(new Move(0, 2, 5, board.get(0), board.get(2)));
					board.set(2, null);
					board.set(5, peg);
					board.set(0,  null);
					if(makeMoves(peg, 5, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(0, move.getFromPeg());
						board.set(5, null);
						board.set(2, move.getRemovedPeg());
					}
				} 
				break;
			}
			case 1: {
				if(board.get(3) != null && board.get(6) == null) {
					moves.add(new Move(1, 3, 6, board.get(1), board.get(3)));
					removed++;
					board.set(3, null);
					board.set(6, peg);
					board.set(1,  null);
					if (makeMoves(peg, 6, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(1, move.getFromPeg());
						board.set(6, null);
						board.set(3, move.getRemovedPeg());
					}
				}
				if(board.get(4) != null && board.get(8) == null) {
					moves.add(new Move(1, 4, 8, board.get(1), board.get(4)));
					removed++;
					board.set(4, null);
					board.set(8, peg);
					board.set(1,  null);
					if(makeMoves(peg, 8, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(1, move.getFromPeg());
						board.set(8, null);
						board.set(4, move.getRemovedPeg());
					}
				} 
				break;
			}
			case 2: {
				if(board.get(4) != null && board.get(7) == null) {
					moves.add(new Move(2, 4, 7, board.get(2), board.get(4)));
					removed++;
					board.set(4, null);
					board.set(7, peg);
					board.set(2,  null);
					if(makeMoves(peg, 7, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(2, move.getFromPeg());
						board.set(7, null);
						board.set(4, move.getRemovedPeg());
					}
				}
				if(board.get(5) != null && board.get(9) == null) {
					moves.add(new Move(2, 5, 9, board.get(2), board.get(5)));
					removed++;
					board.set(5, null);
					board.set(9, peg);
					board.set(2,  null);
					if(makeMoves(peg, 9, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(2, move.getFromPeg());
						board.set(9, null);
						board.set(5, move.getRemovedPeg());
					}
				}
				break;
			}	
			case 3: {
				if(board.get(1) != null && board.get(0) == null) {
					moves.add(new Move(3, 1, 0, board.get(3), board.get(1)));
					removed++;
					board.set(1, null);
					board.set(0, peg);
					board.set(3,  null);
					if(makeMoves(peg, 0, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(3, move.getFromPeg());
						board.set(0, null);
						board.set(1, move.getRemovedPeg());
					}
				}
				if(board.get(4) != null && board.get(5) == null) {
					moves.add(new Move(3, 4, 5, board.get(3), board.get(4)));
					removed++;
					board.set(4, null);
					board.set(5, peg);
					board.set(3,  null);
					if(makeMoves(peg, 5, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(3, move.getFromPeg());
						board.set(5, null);
						board.set(4, move.getRemovedPeg());
					}
				}
				if(board.get(7) != null && board.get(12) == null) {
					moves.add(new Move(3, 7, 12, board.get(3), board.get(7)));
					removed++;
					board.set(7, null);
					board.set(12, peg);
					board.set(3,  null);
					if(makeMoves(peg, 12, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(3, move.getFromPeg());
						board.set(12, null);
						board.set(7, move.getRemovedPeg());
					}
				}
				if(board.get(6) != null && board.get(10) == null) {
					moves.add(new Move(3, 6, 10, board.get(3), board.get(6)));
					removed++;
					board.set(6, null);
					board.set(10, peg);
					board.set(3, null);
					if(makeMoves(peg, 10, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(3, move.getFromPeg());
						board.set(10, null);
						board.set(6, move.getRemovedPeg());
					}
				}
				break;
			}
			case 4: {
				if(board.get(7) != null && board.get(11) == null) {
					moves.add(new Move(4, 7, 11, board.get(4), board.get(7)));
					removed++;
					board.set(7, null);
					board.set(11, peg);
					board.set(4,  null);
					if(makeMoves(peg, 11, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(4, move.getFromPeg());
						board.set(11, null);
						board.set(7, move.getRemovedPeg());
					}
				}
				if(board.get(8) != null && board.get(13) == null) {
					moves.add(new Move(4, 8, 13, board.get(4), board.get(8)));
					removed++;
					board.set(8, null);
					board.set(13, peg);
					board.set(4, null);
					if(makeMoves(peg, 13, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(4, move.getFromPeg());
						board.set(13, null);
						board.set(8, move.getRemovedPeg());
					}
				}
				break;
			}
			case 5: {
				if(board.get(2) != null && board.get(0) == null) {
					moves.add(new Move(5, 2, 0, board.get(5), board.get(2)));
					removed++;
					board.set(2, null);
					board.set(0, peg);
					board.set(5,  null);
					if(makeMoves(peg, 0, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(5, move.getFromPeg());
						board.set(0, null);
						board.set(2, move.getRemovedPeg());
					}
				}
				if(board.get(4) != null && board.get(3) == null) {
					moves.add(new Move(5, 4, 3, board.get(5), board.get(4)));
					removed++;
					board.set(4, null);
					board.set(3, peg);
					board.set(5,  null);
					if(makeMoves(peg, 3, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(5, move.getFromPeg());
						board.set(3, null);
						board.set(4, move.getRemovedPeg());
					}
				}
				if(board.get(8) != null && board.get(12) == null) {
					moves.add(new Move(5, 8, 12, board.get(5), board.get(8)));
					removed++;
					board.set(8, null);
					board.set(12, peg);
					board.set(5,  null);
					if(makeMoves(peg, 12, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(5, move.getFromPeg());
						board.set(12, null);
						board.set(8, move.getRemovedPeg());
					}
				}
				if(board.get(9) != null && board.get(14) == null) {
					moves.add(new Move(5, 9, 14, board.get(5), board.get(9)));
					removed++;
					board.set(9, null);
					board.set(14, peg);
					board.set(5,  null);
					if(makeMoves(peg, 14, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(5, move.getFromPeg());
						board.set(14, null);
						board.set(9, move.getRemovedPeg());
					}
				}
				break;
			}
			case 6: {
				if(board.get(3) != null && board.get(1) == null) {
					moves.add(new Move(6, 3, 1, board.get(6), board.get(3)));
					removed++;
					board.set(3, null);
					board.set(1, peg);
					board.set(6,  null);
					if(makeMoves(peg, 1, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(6, move.getFromPeg());
						board.set(1, null);
						board.set(3, move.getRemovedPeg());
					}
				}
				if(board.get(7) != null && board.get(8) == null) {
					moves.add(new Move(6, 7, 8, board.get(6), board.get(7)));
					removed++;
					board.set(7, null);
					board.set(8, peg);
					board.set(6,  null);
					if(makeMoves(peg, 8, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(6, move.getFromPeg());
						board.set(8, null);
						board.set(7, move.getRemovedPeg());
					}
				}
				break;
			}
			case 7: {
				if(board.get(4) != null && board.get(2) == null) {
					moves.add(new Move(7, 4, 2, board.get(7), board.get(4)));
					removed++;
					board.set(4, null);
					board.set(2, peg);
					board.set(7,  null);
					if(makeMoves(peg, 2, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(7, move.getFromPeg());
						board.set(2, null);
						board.set(4, move.getRemovedPeg());
					}
				}
				if(board.get(8) != null && board.get(9) == null) {
					moves.add(new Move(7, 8, 9, board.get(7), board.get(8)));
					removed++;
					board.set(8, null);
					board.set(9, peg);
					board.set(7,  null);
					if(makeMoves(peg, 9, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(7, move.getFromPeg());
						board.set(9, null);
						board.set(8, move.getRemovedPeg());
					}
				}
				break;
			}
			case 8: {
				if(board.get(4) != null && board.get(1) == null) {
					moves.add(new Move(8, 4, 1, board.get(8), board.get(4)));
					removed++;
					board.set(4, null);
					board.set(1, peg);
					board.set(8,  null);
					if(makeMoves(peg, 1, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(8, move.getFromPeg());
						board.set(1, null);
						board.set(4, move.getRemovedPeg());
					}
				}
			if (board.get(7) != null && board.get(6) == null) {
					moves.add(new Move(8, 7, 6, board.get(8), board.get(7)));
					removed++;
					board.set(7, null);
					board.set(6, peg);
					board.set(8,  null);
					if(makeMoves(peg, 6, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(8, move.getFromPeg());
						board.set(6, null);
						board.set(7, move.getRemovedPeg());
					}
				}
				break;
			}
			case 9: {
				if(board.get(5) != null && board.get(2) == null) {
					moves.add(new Move(9, 5, 2, board.get(9), board.get(5)));
					removed++;
					board.set(5, null);
					board.set(2, peg);
					board.set(9,  null);
					if(makeMoves(peg, 2, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(9, move.getFromPeg());
						board.set(2, null);
						board.set(5, move.getRemovedPeg());
					}
				}
				if(board.get(8) != null && board.get(7) == null) {
					moves.add(new Move(9, 8, 7, board.get(9), board.get(8)));
					removed++;
					board.set(8, null);
					board.set(7, peg);
					board.set(9,  null);
					if(makeMoves(peg, 7, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(9, move.getFromPeg());
						board.set(7, null);
						board.set(8, move.getRemovedPeg());
					}
				}
				break;
			}
			case 10: {
				if(board.get(6) != null && board.get(3) == null) {
					moves.add(new Move(10, 6, 3, board.get(10), board.get(6)));
					removed++;
					board.set(6, null);
					board.set(3, peg);
					board.set(10,  null);
					if(makeMoves(peg, 3, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(10, move.getFromPeg());
						board.set(3, null);
						board.set(6, move.getRemovedPeg());
					}
				}
				if(board.get(11) != null && board.get(12) == null) {
					moves.add(new Move(10, 11, 12, board.get(10), board.get(11)));
					removed++;
					board.set(11, null);
					board.set(12, peg);
					board.set(10,  null);
					if(makeMoves(peg, 12, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(10, move.getFromPeg());
						board.set(12, null);
						board.set(11, move.getRemovedPeg());
					}
				}
				break;
			}
			case 11: {
				if(board.get(7) != null && board.get(4) == null) {
					moves.add(new Move(11, 7, 4, board.get(11), board.get(7)));
					removed++;
					board.set(7, null);
					board.set(4, peg);
					board.set(11,  null);
					if(makeMoves(peg, 4, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(11, move.getFromPeg());
						board.set(4, null);
						board.set(7, move.getRemovedPeg());
					}
				}
				if(board.get(12) != null && board.get(13) == null) {
					moves.add(new Move(11, 12, 13, board.get(11), board.get(12)));
					removed++;
					board.set(12, null);
					board.set(13, peg);
					board.set(11,  null);
					if(makeMoves(peg, 13, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(11, move.getFromPeg());
						board.set(13, null);
						board.set(12, move.getRemovedPeg());
					}
				}
				break;
			}
			case 12: {
				if(board.get(7) != null && board.get(3) == null) {
					moves.add(new Move(12, 7, 3, board.get(12), board.get(7)));
					removed++;
					board.set(7, null);
					board.set(3, peg);
					board.set(12,  null);
					if(makeMoves(peg, 3, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(12, move.getFromPeg());
						board.set(3, null);
						board.set(7, move.getRemovedPeg());
					}
				}
				if(board.get(8) != null && board.get(5) == null) {
					moves.add(new Move(12, 8, 5, board.get(12), board.get(8)));
					removed++;
					board.set(8, null);
					board.set(5, peg);
					board.set(12,  null);
					if(makeMoves(peg, 5, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(12, move.getFromPeg());
						board.set(5, null);
						board.set(8, move.getRemovedPeg());
					}
				}
				if(board.get(11) != null && board.get(10) == null) {
					moves.add(new Move(12, 11, 10, board.get(12), board.get(11)));
					removed++;
					board.set(11, null);
					board.set(10, peg);
					board.set(12,  null);
					if(makeMoves(peg, 10, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(12, move.getFromPeg());
						board.set(10, null);
						board.set(11, move.getRemovedPeg());
					}
				}
				if(board.get(13) != null && board.get(14) == null) {
					moves.add(new Move(12, 13, 14, board.get(12), board.get(13)));
					removed++;
					board.set(13, null);
					board.set(14, peg);
					board.set(12,  null);
					if(makeMoves(peg, 14, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(12, move.getFromPeg());
						board.set(14, null);
						board.set(13, move.getRemovedPeg());
					}
				}
				break;
			}
			case 13: {
				if(board.get(12) != null && board.get(11) == null) {
					moves.add(new Move(13, 12, 11, board.get(13), board.get(12)));
					removed++;
					board.set(12, null);
					board.set(11, peg);
					board.set(13,  null);
					if(makeMoves(peg, 11, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(13, move.getFromPeg());
						board.set(11, null);
						board.set(12, move.getRemovedPeg());
					}
				}
				if(board.get(8) != null && board.get(4) == null) {
					moves.add(new Move(13, 8, 4, board.get(13), board.get(8)));
					removed++;
					board.set(8, null);
					board.set(4, peg);
					board.set(13,  null);
					if(makeMoves(peg, 4, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(13, move.getFromPeg());
						board.set(4, null);
						board.set(8, move.getRemovedPeg());
					}
				}
				break;
			}
			case 14: {
				if(board.get(13) != null && board.get(12) == null) {
					moves.add(new Move(14, 13, 12, board.get(14), board.get(13)));
					removed++;
					board.set(13, null);
					board.set(12, peg);
					board.set(14,  null);
					if(makeMoves(peg, 12, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(14, move.getFromPeg());
						board.set(12, null);
						board.set(13, move.getRemovedPeg());
					}
				}
				if(board.get(9) != null && board.get(5) == null) {
					moves.add(new Move(14, 9, 5, board.get(14), board.get(9)));
					removed++;
					board.set(9, null);
					board.set(5, peg);
					board.set(14,  null);
					if(makeMoves(peg, 5, maxRemoves, board, moves, removed, targetColor, 0)) {
						return true;
					} else {
						removed--;
						Move move = moves.pop();
						board.set(14, move.getFromPeg());
						board.set(5, null);
						board.set(9, move.getRemovedPeg());
					}
				} 
				break;
			}
		
		}
		
		if(index == 14) {
			return makeMoves(board.get(0), 0, maxRemoves, board, moves, removed, targetColor, noMoveCount + 1);
		}

		
		return makeMoves(board.get(index + 1), index + 1, maxRemoves, board, moves, removed, targetColor, noMoveCount + 1);
	}
	
	public static int setupRow(String rowInfo, ArrayList<Peg> originalBoard) {
		boolean buildingPeg = false;
		boolean buildingNull = false;
		int numOfPegs = 0;
		StringBuilder builder = new StringBuilder();
		for(Character c : rowInfo.toCharArray()) {
			if(buildingPeg) {
				builder.append(c);
				originalBoard.add(new Peg(builder.toString()));
				numOfPegs++;
				buildingPeg = false;
				builder = new StringBuilder();
			}
			else if(buildingNull) {
				originalBoard.add(null);
				builder = new StringBuilder();
				buildingNull = false;
			}
			else if (c.equals('_')) {
				buildingNull = true;
			} 
			else if(!c.equals(' ')){
				buildingPeg = true;
				builder.append(c);
			}
		}
		
		return numOfPegs;
	}
	
	public static void makeMove(int from, int to, int removed, ArrayList<Peg> board, Stack<Move> moves, Peg peg) {
		moves.add(new Move(from, removed, to, board.get(from), board.get(to)));
		removed++;
		board.set(to, peg);
		board.set(removed, null);
		board.set(from,  null);
	}
	
	public static void reverseMove(int from, int to, int removed, ArrayList<Peg> board, Stack<Move> moves) {
		Move move = moves.pop();
		board.set(from, move.getFromPeg());
		board.set(removed, move.getRemovedPeg());
		board.set(to, null);
	}
}
