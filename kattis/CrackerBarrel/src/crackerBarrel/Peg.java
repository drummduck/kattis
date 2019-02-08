package crackerBarrel;

import java.awt.Color;
import java.text.ParsePosition;

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
}
