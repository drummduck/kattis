package crackerBarrel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.Stack;

public class CrackerBarrel {

	private final static int BOARD_SIZE = 5;
//	private static HashSet<ArrayList<Peg>> failedLayouts;

	public static void main(String[] args) {
//		failedLayouts = new HashSet<ArrayList<Peg>>();
		Scanner in = new Scanner(System.in);
		String targetColor = null;
		ArrayList<Peg> board = new ArrayList<Peg>();
		int numOfPegs = 0;
		int rowsRead = 0;

		while (in.hasNextLine()) {
			String text = in.nextLine();
			if (text.equals("**")) {
				break;
			} else if (targetColor == null) {
				targetColor = text;
			} else {
				numOfPegs += setupRow(text, board);
				rowsRead++;
			}
			if (rowsRead == BOARD_SIZE) {
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
		if (makeMoves(board.get(0), 0, numOfPegs - 1, board, moves, 0, targetColor, 0)) {
			while (!moves.isEmpty()) {
				movesString = moves.pop().toString() + movesString;
			}
			System.out.println("Possible");
			return true;
		} else {
			System.out.println("Impossible");
			return false;
		}
	}

	public static boolean makeMoves(Peg peg, int index, int maxRemoves, ArrayList<Peg> board, Stack<Move> moves,
			int pegsRemoved, String targetColor, int noMoveCount) {
		
//		if(failedLayouts.contains(board)) {
//			return false;
//		}
		
		int removed = pegsRemoved;

		int actualRemoved = 0;

		if (noMoveCount == 15) {
//			ArrayList<Peg> boardCopy = new ArrayList<Peg>(board);
//			failedLayouts.add(boardCopy);
			return false;
		}

		if (removed == maxRemoves) {
			if (peg.getColor().equals(targetColor)) {
				return true;
			} else
				return false;
		}

		if (peg == null) {
			if (index == 14) {
				return makeMoves(board.get(0), 0, maxRemoves, board, moves, removed, targetColor, noMoveCount + 1);
			} else {
				return makeMoves(board.get(index + 1), index + 1, maxRemoves, board, moves, removed, targetColor,
						noMoveCount + 1);
			}
		}

		switch (index) {
		case 0: {
			if (board.get(1) != null && board.get(3) == null) {
				removed++;
				makeMove(0, 1, 3, board, moves, peg);
				if (makeMoves(peg, 3, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(0, 1, 3, board, moves);
				}
			}
			if (board.get(2) != null && board.get(5) == null) {
				removed++;
				makeMove(0, 2, 5, board, moves, peg);
				if (makeMoves(peg, 5, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(0, 2, 5, board, moves);
				}
			}
			break;
		}
		case 1: {
			if (board.get(3) != null && board.get(6) == null) {
				removed++;
				makeMove(1, 3, 6, board, moves, peg);
				if (makeMoves(peg, 6, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(1, 3, 6, board, moves);
				}
			}
			if (board.get(4) != null && board.get(8) == null) {
				removed++;
				makeMove(1, 4, 8, board, moves, peg);
				if (makeMoves(peg, 8, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(1, 4, 8, board, moves);
				}
			}
			break;
		}
		case 2: {
			if (board.get(4) != null && board.get(7) == null) {
				removed++;
				makeMove(2, 4, 7, board, moves, peg);
				if (makeMoves(peg, 7, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(2, 4, 7, board, moves);
				}
			}
			if (board.get(5) != null && board.get(9) == null) {
				removed++;
				makeMove(2, 5, 9, board, moves, peg);
				if (makeMoves(peg, 9, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(2, 5, 9, board, moves);
				}
			}
			break;
		}
		case 3: {
			if (board.get(1) != null && board.get(0) == null) {
				removed++;
				makeMove(3, 1, 0, board, moves, peg);
				if (makeMoves(peg, 0, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(3, 1, 0, board, moves);
				}
			}
			if (board.get(4) != null && board.get(5) == null) {
				removed++;
				makeMove(3, 4, 5, board, moves, peg);
				if (makeMoves(peg, 5, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(3, 4, 5, board, moves);
				}
			}
			if (board.get(7) != null && board.get(12) == null) {
				removed++;
				makeMove(3, 7, 12, board, moves, peg);
				if (makeMoves(peg, 12, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(3, 7, 12, board, moves);
				}
			}
			if (board.get(6) != null && board.get(10) == null) {
				removed++;
				makeMove(3, 6, 10, board, moves, peg);
				if (makeMoves(peg, 10, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(3, 6, 10, board, moves);
				}
			}
			break;
		}
		case 4: {
			if (board.get(7) != null && board.get(11) == null) {
				removed++;
				makeMove(4, 7, 11, board, moves, peg);
				if (makeMoves(peg, 11, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(4, 7, 11, board, moves);
				}
			}
			if (board.get(8) != null && board.get(13) == null) {
				removed++;
				makeMove(4, 8, 13, board, moves, peg);
				if (makeMoves(peg, 13, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(4, 8, 13, board, moves);
				}
			}
			break;
		}
		case 5: {
			if (board.get(2) != null && board.get(0) == null) {
				removed++;
				makeMove(5, 2, 0, board, moves, peg);
				if (makeMoves(peg, 0, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(5, 2, 0, board, moves);
				}
			}
			if (board.get(4) != null && board.get(3) == null) {
				removed++;
				makeMove(5, 4, 3, board, moves, peg);
				if (makeMoves(peg, 3, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(5, 4, 3, board, moves);
				}
			}
			if (board.get(8) != null && board.get(12) == null) {
				removed++;
				makeMove(5, 8, 12, board, moves, peg);
				if (makeMoves(peg, 12, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(5, 8, 12, board, moves);
				}
			}
			if (board.get(9) != null && board.get(14) == null) {
				removed++;
				makeMove(5, 9, 14, board, moves, peg);
				if (makeMoves(peg, 14, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(5, 9, 14, board, moves);
				}
			}
			break;
		}
		case 6: {
			if (board.get(3) != null && board.get(1) == null) {
				removed++;
				makeMove(6, 3, 1, board, moves, peg);
				if (makeMoves(peg, 1, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(6, 3, 1, board, moves);

				}
			}
			if (board.get(7) != null && board.get(8) == null) {
				removed++;
				makeMove(6, 7, 8, board, moves, peg);
				if (makeMoves(peg, 8, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(6, 7, 8, board, moves);
				}
			}
			break;
		}
		case 7: {
			if (board.get(4) != null && board.get(2) == null) {
				removed++;
				makeMove(7, 4, 2, board, moves, peg);
				if (makeMoves(peg, 2, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(7, 4, 2, board, moves);
				}
			}
			if (board.get(8) != null && board.get(9) == null) {
				removed++;
				makeMove(7, 8, 9, board, moves, peg);
				if (makeMoves(peg, 9, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(7, 8, 9, board, moves);
				}
			}
			break;
		}
		case 8: {
			if (board.get(4) != null && board.get(1) == null) {
				removed++;
				makeMove(8, 4, 1, board, moves, peg);
				if (makeMoves(peg, 1, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(8, 4, 1, board, moves);
				}
			}
			if (board.get(7) != null && board.get(6) == null) {
				removed++;
				makeMove(8, 7, 6, board, moves, peg);
				if (makeMoves(peg, 6, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(8, 7, 6, board, moves);
				}
			}
			break;
		}
		case 9: {
			if (board.get(5) != null && board.get(2) == null) {
				removed++;
				makeMove(9, 5, 2, board, moves, peg);
				if (makeMoves(peg, 2, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(9, 5, 2, board, moves);
				}
			}
			if (board.get(8) != null && board.get(7) == null) {
				removed++;
				makeMove(9, 8, 7, board, moves, peg);
				if (makeMoves(peg, 7, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(9, 8, 7, board, moves);
				}
			}
			break;
		}
		case 10: {
			if (board.get(6) != null && board.get(3) == null) {
				removed++;
				makeMove(10, 6, 3, board, moves, peg);
				if (makeMoves(peg, 3, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(10, 6, 3, board, moves);
				}
			}
			if (board.get(11) != null && board.get(12) == null) {
				removed++;
				makeMove(10, 11, 12, board, moves, peg);
				if (makeMoves(peg, 12, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(10, 11, 12, board, moves);
				}
			}
			break;
		}
		case 11: {
			if (board.get(7) != null && board.get(4) == null) {
				removed++;
				makeMove(11, 7, 4, board, moves, peg);
				if (makeMoves(peg, 4, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(11, 7, 4, board, moves);
				}
			}
			if (board.get(12) != null && board.get(13) == null) {
				removed++;
				makeMove(11, 12, 13, board, moves, peg);
				if (makeMoves(peg, 13, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(11, 12, 13, board, moves);
				}
			}
			break;
		}
		case 12: {
			if (board.get(7) != null && board.get(3) == null) {
				removed++;
				makeMove(12, 7, 3, board, moves, peg);
				if (makeMoves(peg, 3, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(12, 7, 3, board, moves);
				}
			}
			if (board.get(8) != null && board.get(5) == null) {
				removed++;
				makeMove(12, 8, 5, board, moves, peg);
				if (makeMoves(peg, 5, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(12, 8, 5, board, moves);
				}
			}
			if (board.get(11) != null && board.get(10) == null) {
				removed++;
				makeMove(12, 11, 10, board, moves, peg);
				if (makeMoves(peg, 10, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(12, 11, 10, board, moves);
				}
			}
			if (board.get(13) != null && board.get(14) == null) {
				removed++;
				makeMove(12, 13, 14, board, moves, peg);
				if (makeMoves(peg, 14, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(12, 13, 14, board, moves);
				}
			}
			break;
		}
		case 13: {
			if (board.get(12) != null && board.get(11) == null) {
				removed++;
				makeMove(13, 12, 11, board, moves, peg);
				if (makeMoves(peg, 11, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(13, 12, 11, board, moves);
				}
			}
			if (board.get(8) != null && board.get(4) == null) {
				removed++;
				makeMove(13, 8, 4, board, moves, peg);
				if (makeMoves(peg, 4, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(13, 8, 4, board, moves);
				}
			}
			break;
		}
		case 14: {
			if (board.get(13) != null && board.get(12) == null) {
				removed++;
				makeMove(14, 13, 12, board, moves, peg);
				if (makeMoves(peg, 12, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(14, 13, 12, board, moves);
				}
			}
			if (board.get(9) != null && board.get(5) == null) {
				removed++;
				makeMove(14, 9, 5, board, moves, peg);
				if (makeMoves(peg, 5, maxRemoves, board, moves, removed, targetColor, 0)) {
					return true;
				} else {
					removed--;
					reverseMove(14, 9, 5, board, moves);
				}
			}
			break;
		}

		}

		if (index == 14) {
			return makeMoves(board.get(0), 0, maxRemoves, board, moves, removed, targetColor, noMoveCount + 1);
		}

		return makeMoves(board.get(index + 1), index + 1, maxRemoves, board, moves, removed, targetColor,
				noMoveCount + 1);
	}

	public static int setupRow(String rowInfo, ArrayList<Peg> originalBoard) {
		boolean buildingPeg = false;
		boolean buildingNull = false;
		int numOfPegs = 0;
		StringBuilder builder = new StringBuilder();
		for (Character c : rowInfo.toCharArray()) {
			if (buildingPeg) {
				builder.append(c);
				originalBoard.add(new Peg(builder.toString()));
				numOfPegs++;
				buildingPeg = false;
				builder = new StringBuilder();
			} else if (buildingNull) {
				originalBoard.add(null);
				builder = new StringBuilder();
				buildingNull = false;
			} else if (c.equals('_')) {
				buildingNull = true;
			} else if (!c.equals(' ')) {
				buildingPeg = true;
				builder.append(c);
			}
		}

		return numOfPegs;
	}

	public static void makeMove(int from, int removed, int to, ArrayList<Peg> board, Stack<Move> moves, Peg peg) {
		try {
			moves.add(new Move(from, removed, to, board.get(from), board.get(removed)));
			board.set(to, peg);
			board.set(removed, null);
			board.set(from, null);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	public static void reverseMove(int from, int removed, int to, ArrayList<Peg> board, Stack<Move> moves) {
		try {
			Move move = moves.pop();
			board.set(from, move.getFromPeg());
			board.set(removed, move.getRemovedPeg());
			board.set(to, null);
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}
