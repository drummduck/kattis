package crackerBarrel;

public class Move {
	private int mFrom;
	private int mRemoved;
	private int mTo;
	private Peg mFromPeg;
	private Peg mRemovedPeg;

	public Move(int from, int removed, int to, Peg fromPeg, Peg removedPeg) {
		mFrom = from;
		mRemoved = removed;
		mTo = to;
		mFromPeg = new Peg(fromPeg);
		mRemovedPeg = new Peg(removedPeg);
	}
	
	public int getFrom() {
		return mFrom;
	}
	
	public int getTo() {
		return mTo;
	}
	
	public int getRemoved() {
		return mRemoved;
	}
	
	public Peg getFromPeg() {
		return mFromPeg;
	}
	
	public Peg getRemovedPeg() {
		return mRemovedPeg;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Move peg at " + mFrom + " to slot " + mTo + ", removing peg at " + mRemoved + "\n");
		return builder.toString();
	}
}
