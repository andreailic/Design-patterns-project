package geometry;

import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;

public class AdapterHexagon extends AreaShape {

	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	
	public AdapterHexagon(int x, int y, int r) {
		this.hexagon = new Hexagon(x, y, r);
		
	}

	public AdapterHexagon(int x, int y, int r, boolean selected, Color borderColor, Color fillColor) {
		this(x,y,r);
		this.setSelected(selected);
		super.setBorderColor(borderColor);
		super.setFillColor(fillColor);
		this.hexagon.setAreaColor(fillColor);
		this.hexagon.setBorderColor(borderColor);
	}
	
	public AdapterHexagon clone() {
		AdapterHexagon cloneHexagon = new AdapterHexagon(this.getHexagon().getX(), 
				this.getHexagon().getY(), 
				this.getHexagon().getR(), 
				this.getHexagon().isSelected(), 
				this.getHexagon().getBorderColor(), 
				this.getHexagon().getAreaColor());
		return cloneHexagon;
	}

	@Override
	public int compareTo(Object arg0) {
		if (arg0 instanceof AdapterHexagon) {
			return (int) (6 * ((this.hexagon.getR() * Math.sqrt(3)) / 4)
					- 6 * ((((AdapterHexagon) arg0).hexagon.getR() * Math.sqrt(3)) / 4));
		}
		return 0;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof AdapterHexagon) {
			AdapterHexagon c = (AdapterHexagon) obj;
			if (getR() == (c.getR()) && getX() == c.getX() && getY() == c.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(int x, int y) {
		return this.hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		this.hexagon.paint(g);
		
	}
	
	public Hexagon getHexagon() {
		return hexagon;
	}

	public void setHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		this.hexagon.setSelected(selected);
	}
	
	public String toString() {
		Point p = new Point(hexagon.getX(), hexagon.getY());

		return  p + ", radius= " + hexagon.getR() + ", borderColor= " + getBorderColor().getRGB()
				+ ", fillColor= " + getFillColor().getRGB();
		
	}
	public int getX() {
		return hexagon.getX();
	}

	public int getY() {
		return hexagon.getY();
	}

	public int getR() {
		return hexagon.getR();
	}
	
	public void setX(int x) {
		hexagon.setX(x);
	}

	public void setY( int y) {
		hexagon.setY(y);
	}

	public void setR(int r) {
		hexagon.setR(r);
	}
	
	@Override
	public void setBorderColor(Color borderColor) {
		this.hexagon.setBorderColor(borderColor);
	}
	
	@Override
	public void setFillColor(Color fillColor) {
		this.hexagon.setAreaColor(fillColor);
	}
	
	@Override
	public Color getBorderColor() {
		return this.hexagon.getBorderColor();
	}
	
	@Override
	public Color getFillColor() {
		return this.hexagon.getAreaColor();
	}
	
}
