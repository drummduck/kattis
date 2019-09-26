package crackerBarrel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class CrackerBarrel {

    private final static int BOARD_SIZE = 5;
    private static HashSet<Long> checkedBoards = new HashSet<Long>();

    public static void main(String[] args) {
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
                if(calculatePossibility(targetColor, board, numOfPegs)) {
        			System.out.println("Possible");
                } else {
                    System.out.println("Impossible");
                }
                targetColor = null;
                board.clear();
                checkedBoards.clear();
                numOfPegs = 0;
                rowsRead = 0;
            }
        }

        in.close();
    }

    public static boolean calculatePossibility(String targetColor, ArrayList<Peg> board, int numOfPegs) {
        Stack<Move> moves = new Stack<Move>();
        
        if(numOfPegs == 0 && targetColor.equals("__")) {
    		return true;
    	}
        
        if(numOfPegs == 0 || numOfPegs == 15) {
        	return false;
        }
        
        if(numOfPegs == 1) {
        	boolean possible = false;
        	if(targetColor.equals("__")) {
        		return false;
        	}
        	for(Peg p: board) {
        		if(p != null && p.getColor() != null && p.getColor().equals(targetColor)){
        			possible = true;
        		}
        	}
        	return possible;
        }
        if (makeMoves(board.get(0), 0, numOfPegs - 1, board, moves, 0, targetColor)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean makeMoves(Peg peg, int index, int maxRemoves, ArrayList<Peg> board, Stack<Move> moves,
            int pegsRemoved, String targetColor) {
 
    	int beginningIndex = index;       
        int removed = pegsRemoved;
        int moveCount = 0;
               
        
        if (removed == maxRemoves) {
        	if(targetColor.equals("__")) {
        		return false;
        	} 
        	else if (peg != null && peg.getColor() != null && peg.getColor().equals(targetColor)){
        		return true;
        	}
            else {
                return false;
            }
        }
        
        long encodedBoard = encodeBoard(board, index, pegsRemoved);
        
        if(checkedBoards.contains(encodedBoard))
        	return false;
        	
        
	while(moveCount != 15) {
			if(peg == null) {
		        if(index == 14) {
		        	index = 0;
		        }else {
		        	index++;
		        }
		        peg = board.get(index);
		        moveCount++;
		        continue;
	    	}
	        switch (index) {
		        case 0: {
		            if (board.get(1) != null && board.get(3) == null) {
		                removed++;
		                makeMove(0, 1, 3, board, moves, peg);
		                if (makeMoves(board.get(3), 3, maxRemoves, board, moves, removed, targetColor)) {
		                    return true;
		                } else {
		                    removed--;
		                    reverseMove(0, 1, 3, board, moves);
		                }
		            }
		            if (board.get(2) != null && board.get(5) == null) {
		                removed++;
		                makeMove(0, 2, 5, board, moves, peg);
		                if (makeMoves(board.get(5), 5, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(6), 6, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(1, 3, 6, board, moves);
		                }
		            }
		            if (board.get(4) != null && board.get(8) == null) {
		                removed++;
		                makeMove(1, 4, 8, board, moves, peg);
		                if (makeMoves(board.get(8), 8, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(7), 7, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(2, 4, 7, board, moves);
		                }
		            }
		            if (board.get(5) != null && board.get(9) == null) {
		                removed++;
		                makeMove(2, 5, 9, board, moves, peg);
		                if (makeMoves(board.get(9), 9, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(0), 0, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(3, 1, 0, board, moves);
		                }
		            }
		            if (board.get(4) != null && board.get(5) == null) {
		                removed++;
		                makeMove(3, 4, 5, board, moves, peg);
		                if (makeMoves(board.get(5), 5, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(3, 4, 5, board, moves);
		                }
		            }
		            if (board.get(7) != null && board.get(12) == null) {
		                removed++;
		                makeMove(3, 7, 12, board, moves, peg);
		                if (makeMoves(board.get(12), 12, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(3, 7, 12, board, moves);
		                }
		            }
		            if (board.get(6) != null && board.get(10) == null) {
		                removed++;
		                makeMove(3, 6, 10, board, moves, peg);
		                if (makeMoves(board.get(10), 10, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(11), 11, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(4, 7, 11, board, moves);
		                }
		            }
		            if (board.get(8) != null && board.get(13) == null) {
		                removed++;
		                makeMove(4, 8, 13, board, moves, peg);
		                if (makeMoves(board.get(13), 13, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(0), 0, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(5, 2, 0, board, moves);
		                }
		            }
		            if (board.get(4) != null && board.get(3) == null) {
		                removed++;
		                makeMove(5, 4, 3, board, moves, peg);
		                if (makeMoves(board.get(3), 3, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(5, 4, 3, board, moves);
		                }
		            }
		            if (board.get(8) != null && board.get(12) == null) {
		                removed++;
		                makeMove(5, 8, 12, board, moves, peg);
		                if (makeMoves(board.get(12), 12, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(5, 8, 12, board, moves);
		                }
		            }
		            if (board.get(9) != null && board.get(14) == null) {
		                removed++;
		                makeMove(5, 9, 14, board, moves, peg);
		                if (makeMoves(board.get(14), 14, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(1), 1, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(6, 3, 1, board, moves);
		
		                }
		            }
		            if (board.get(7) != null && board.get(8) == null) {
		                removed++;
		                makeMove(6, 7, 8, board, moves, peg);
		                if (makeMoves(board.get(8), 8, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(2), 2, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(7, 4, 2, board, moves);
		                }
		            }
		            if (board.get(8) != null && board.get(9) == null) {
		                removed++;
		                makeMove(7, 8, 9, board, moves, peg);
		                if (makeMoves(board.get(9), 9, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(1), 1, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(8, 4, 1, board, moves);
		                }
		            }
		            if (board.get(7) != null && board.get(6) == null) {
		                removed++;
		                makeMove(8, 7, 6, board, moves, peg);
		                if (makeMoves(board.get(6), 6, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(2), 2, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(9, 5, 2, board, moves);
		                }
		            }
		            if (board.get(8) != null && board.get(7) == null) {
		                removed++;
		                makeMove(9, 8, 7, board, moves, peg);
		                if (makeMoves(board.get(7), 7, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(3), 3, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(10, 6, 3, board, moves);
		                }
		            }
		            if (board.get(11) != null && board.get(12) == null) {
		                removed++;
		                makeMove(10, 11, 12, board, moves, peg);
		                if (makeMoves(board.get(12), 12, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(4), 4, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(11, 7, 4, board, moves);
		                }
		            }
		            if (board.get(12) != null && board.get(13) == null) {
		                removed++;
		                makeMove(11, 12, 13, board, moves, peg);
		                if (makeMoves(board.get(13), 13, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(3), 3, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(12, 7, 3, board, moves);
		                }
		            }
		            if (board.get(8) != null && board.get(5) == null) {
		                removed++;
		                makeMove(12, 8, 5, board, moves, peg);
		                if (makeMoves(board.get(5), 5, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(12, 8, 5, board, moves);
		                }
		            }
		            if (board.get(11) != null && board.get(10) == null) {
		                removed++;
		                makeMove(12, 11, 10, board, moves, peg);
		                if (makeMoves(board.get(10), 10, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(12, 11, 10, board, moves);
		                }
		            }
		            if (board.get(13) != null && board.get(14) == null) {
		                removed++;
		                makeMove(12, 13, 14, board, moves, peg);
		                if (makeMoves(board.get(14), 14, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(11), 11, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(13, 12, 11, board, moves);
		                }
		            }
		            if (board.get(8) != null && board.get(4) == null) {
		                removed++;
		                makeMove(13, 8, 4, board, moves, peg);
		                if (makeMoves(board.get(4), 4, maxRemoves, board, moves, removed, targetColor)) {
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
		                if (makeMoves(board.get(12), 12, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(14, 13, 12, board, moves);
		                }
		            }
		            if (board.get(9) != null && board.get(5) == null) {
		                removed++;
		                makeMove(14, 9, 5, board, moves, peg);
		                if (makeMoves(board.get(5), 5, maxRemoves, board, moves, removed, targetColor)) {
		                	return true;
		                } else {
		                    removed--;
		                    reverseMove(14, 9, 5, board, moves);
		                }
		            }
		            break;
		        }
		        default: break;
	        }
	        moveCount++;
	        if(index == 14) {
	        	index = 0;
	        } else {
	        	index++;
	        }
	        peg = board.get(index);
		}
    
        checkedBoards.add(encodeBoard(board, beginningIndex, removed));
        
        return false;
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
    
    public static long encodeBoard(ArrayList<Peg> board, int index, int pegsRemoved) {
    	long value = 0;
    	for(Peg p: board) {
    		if(p == null) value = value * BOARD_SIZE + Character.getNumericValue('0');
    		else if(p.getColor() == null) value = value * BOARD_SIZE + Character.getNumericValue('X'); 
    		else value = value * BOARD_SIZE + Character.getNumericValue(p.getColor().charAt(0));
    	}
    	return value;
    }
}