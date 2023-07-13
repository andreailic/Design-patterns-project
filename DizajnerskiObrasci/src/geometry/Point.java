package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {

	
	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, boolean selected, Color borderColor) {
		this(x,y);
		setSelected(selected);
		setBorderColor(borderColor);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Point)
			return (int) (this.distance(0, 0) - ((Point) o).distance(0, 0));
		return 0;
	}
	
	@Override
	public void moveBy(int x, int y) {
		this.x = this.x + x;
		this.y += y;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(this.getBorderColor());
		g.drawLine(x-2, y, x+2, y);
		g.drawLine(x, y-2, x, y+2);
		
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(x-3, y-3, 6, 6);	
		}
	}

	public boolean contains(int x, int y) {
		return this.distance(x, y) <= 3;
	}

	public String toString() {
		if (this.getBorderColor() != null) {
			return "(x, y)-->" + "("+x+","+this.y+")" + ", borderColor= " + this.getBorderColor().getRGB() ;
		}else
			return "(x, y)-->" + "("+x+","+this.y+")";
	}

	public boolean equals(Object obj) {
		Point temp;
		if(obj instanceof Point) {
			temp = (Point) obj;
			if (this.x == temp.x && y == temp.y)
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public double distance(int x, int y) {
		int dX = this.x - x;
		int dY = this.y - y;
		double d = Math.sqrt(dX * dX + dY * dY);
		return d;
	}
	
	public Point clone() {
		Point clonePoint = new Point(this.getX(), this.getY(), this.isSelected(), this.getBorderColor());
		return clonePoint;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
