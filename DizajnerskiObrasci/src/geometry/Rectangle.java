package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends AreaShape {
	
	private static final long serialVersionUID = 1L;
	protected Point upperLeftPoint;
	protected int width;
	protected int height;

	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color borderColor, Color fillColor) {
		this(upperLeftPoint, width, height);
		setSelected(selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}
	
	public Rectangle clone() {
		
		Rectangle cloneRectangle = new Rectangle();
		cloneRectangle.setHeight(this.getHeight());
		cloneRectangle.setWidth(this.getWidth());
		cloneRectangle.setUpperLeftPoint(upperLeftPoint.clone());
		cloneRectangle.setBorderColor(this.getBorderColor());
		cloneRectangle.setFillColor(this.getFillColor());
		cloneRectangle.setSelected(this.isSelected());
		return cloneRectangle;
	}
	
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(o instanceof Rectangle)
			return this.area() - ((Rectangle)o).area();
		return 0;
	}

	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		upperLeftPoint.moveBy(x, y);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle r = (Rectangle) obj;
			if (this.upperLeftPoint.equals(r.getUpperLeftPoint()) && this.height == r.getHeight()
					&& this.width == r.getWidth()) {
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
		g.fillRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		g.setColor(getBorderColor());
		g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), width, height);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(upperLeftPoint.getX() + width - 3, upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(upperLeftPoint.getX() - 3, upperLeftPoint.getY() + height - 3, 6, 6);
			g.drawRect(upperLeftPoint.getX() + width  - 3, upperLeftPoint.getY() + height - 3, 6, 6);
			
		}
	}
	
	public boolean contains(int x, int y) {
		return (x >= upperLeftPoint.getX() &&
			x <= upperLeftPoint.getX() + width &&
			y >= upperLeftPoint.getY() &&
			y <= upperLeftPoint.getY() + height);
	}
	
	public boolean contains(Point p) {
		return this.contains(p.getX(), p.getY());
	}

	public String toString() {
		return upperLeftPoint+", width: "+width+", height: "+height + ", borderColor= " + this.getBorderColor().getRGB() + ", fillColor= " + this.getFillColor().getRGB();
	}

	public int area() {
		return width * height;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {

		this.width = width;
	}

	public int getHeight() {
		
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
