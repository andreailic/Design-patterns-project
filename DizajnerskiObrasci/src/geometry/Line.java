package geometry;

import java.awt.Color;
import java.awt.Graphics;


public class Line extends Shape{
	
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color borderColor) {
		this(startPoint, endPoint);
		setSelected(selected);
		setBorderColor(borderColor);
	}

	public Line clone() {
		Line cloneLine = new Line(this.getStartPoint(), this.getEndPoint(), this.isSelected(), this.getBorderColor());
		/*Line cloneLine = new Line(new Point(), new Point());
		cloneLine.getStartPoint().setX(this.getStartPoint().getX());
		cloneLine.getStartPoint().setY(this.getStartPoint().getY());
		cloneLine.getEndPoint().setX(this.getEndPoint().getX());
		cloneLine.getEndPoint().setY(this.getEndPoint().getY());
		cloneLine.setBorderColor(this.getBorderColor());*/
		return cloneLine;
	}
	
	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		startPoint.moveBy(x, y);
		endPoint.moveBy(x, y);
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(this.getBorderColor());
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if(isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(startPoint.getX()-3, startPoint.getY()-3, 6, 6);
			g.drawRect(endPoint.getX()-3, endPoint.getY()-3, 6, 6);
			
		}
	}
	
	public boolean contains(int x, int y) {
		double temp = startPoint.distance(x, y) + endPoint.distance(x, y);
		return temp - this.length() <= 3;
	}
	
	@Override
	public int compareTo(Object o) {
		if(o instanceof Line)
			return startPoint.compareTo(endPoint);
		return 0;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line l = (Line) obj;
			if (this.startPoint.equals(l.getStartPoint()) &&
					this.endPoint.equals(l.getEndPoint())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	

	public String toString() {
		return startPoint + "-->" + endPoint + ", borderColor= " + this.getBorderColor().getRGB();
	}
	
	
	public double length() {
		double length = startPoint.distance(endPoint.getX(), endPoint.getY());
		return length;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public Point getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
}
