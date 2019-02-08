package timing;

import java.awt.List;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Fort {
	
	private ArrayList<Path> paths = new ArrayList<Path>();
	private double forces;
	private double forcesComing;
	private double forcesLeaving;
	
	public Fort(double forces) {
		this.forces = forces;
		forcesComing = 0;
		forcesLeaving = 0;
	}
	
	public void addPath(Path path) {
		path.setForces(path.getForcePercentage() * forces);
		paths.add(path);
	}
	
	public ArrayList<Path> getPaths(){
		return paths;
	}
	
	public void calculateLeavingForces(){
		forcesLeaving = 0;
		for(Path path : paths) {
			forcesLeaving = forcesLeaving + (forces * path.getForcePercentage());
			path.setForces(forcesLeaving);
		}
	}
	
	public void releaseForces() {
		forces -= forcesLeaving;
	}
	
	public double addForces(double inForces) {
		return forces += inForces;
	}
	
	public double getForces() {
		return forces;
	}
	
	public void sendForces(HashMap<Integer, Fort> forts) {
		for(Path path : paths) {
			forts.get(path.getTo()).addForces(path.getForces());
		}
	}
	
	public boolean hasPathToFort(int fortNumber) {
		for(Path path : paths) {
			if(path.getTo() == fortNumber) return true; 
		}
		return false;
	}
	
	public double getForcesFromSentForts(HashMap<Integer, Fort> forts) {
		double forcesFromSent = 0;
		for(Path p: paths) {
			forcesFromSent += forts.get(p.getTo()).getForces();
		}
		
		return forcesFromSent;
	}
}
