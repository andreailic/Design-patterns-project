package geometry;

import java.awt.Color;

public abstract class AreaShape extends Shape {

	private static final long serialVersionUID = 1L;
	private Color fillColor;
	
	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

}
