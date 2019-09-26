package timing;

import java.util.HashMap;
import java.util.Set;

public class Path {
	private double forcePercentage;
	private int to;
	private double forces;
	
	public Path(int to, double forcePercentage) {
		this.to = to;
		this.forcePercentage = forcePercentage;
		forces = 0;
	}
	
	public int getTo() {
		return to;
	}
	
	public void setTo(int to) {
		this.to = to;
	}
	
	public double getForcePercentage() {
		return forcePercentage;
	}
	
	public void setForcePercentage(double forcePercentage) {
		this.forcePercentage = forcePercentage;
	}
	
	public void setForces(double forces) {
		this.forces = forces;
	}
	
	public void resetForces() {
		forces = 0;
	}
	
	public double getForces() {
		return forces;
	}
}
