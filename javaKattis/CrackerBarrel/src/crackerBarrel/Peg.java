package crackerBarrel;

//import java.awt.Color;
//import java.text.ParsePosition;
public class Peg {
	private String color;
	
	public Peg(String color) {
		this.color = color;
	}
	
	public Peg(Peg peg) {
		color = peg.color;
	}
	
	public String getColor() {
		return this.color;
	}
	
//	@Override
//	public boolean equals(Object o) {
//		
//		if (o == this) return true;
//		
//		if(!(o instanceof Peg))return false;
//		
//		Peg peg = (Peg) o;  
//		
//		if((peg.getColor() != null && this.color != null)) return true;
//		else return false;
//	}
}
