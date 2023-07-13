package geometry;

import java.awt.Color;
import java.awt.Graphics;


public class Circle extends AreaShape {
	
	private static final long serialVersionUID = 1L;
	protected Point center;
	protected int r;
	

	public Circle() {

	}

	public Circle(Point center, int r) {
		this.center = center;
		this.r = r;
	}

	public Circle(Point center, int r, boolean selected, Color borderColor, Color fillColor) {
		this(center, r);
		setSelected(selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	} 
	

	public Circle clone() {
		
		Circle cloneCircle = new Circle();
		cloneCircle.setCenter(this.getCenter().clone());
		cloneCircle.setBorderColor(this.getBorderColor());
		cloneCircle.setFillColor(this.getFillColor());
		cloneCircle.setSelected(this.isSelected());
		cloneCircle.setR(this.getR());
		return cloneCircle;
				
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Circle)
			return (int) (this.area() - ((Circle)o).area());
		return 0;
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		center.moveBy(x, y);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle c = (Circle) obj;
			if (this.center.equals(c.getCenter()) && this.r == c.getR()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(this.getFillColor());
		g.fillOval(center.getX()-r, center.getY()-r, 2*r, r+r);
		g.setColor(getBorderColor());
		g.drawOval(center.getX()-r, center.getY()-r, 2*r, r+r);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - r - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() + r - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() - r - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() + r - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() - 3, 6, 6);
			
			
		}
	}
	
	public boolean contains(int x, int y) {
		double temp = center.distance(x, y);
		return temp <= r;
	}
	
	public boolean contains(Point p) {
		return this.contains(p.getX(), p.getY());
	}

	// redefinisati toString()
	// (xCenter,yCenter), radius: r
	public String toString() {
		return center+", radius: "+r+", borderColor= " + this.getBorderColor().getRGB() + ", fillColor= " + this.getFillColor().getRGB();
	}
	// redefinisati equals tako da poredi krugove po radijusu

	public double area() {
		return r * r * Math.PI;
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

}
