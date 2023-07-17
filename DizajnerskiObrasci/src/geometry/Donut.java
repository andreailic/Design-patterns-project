package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	
	private static final long serialVersionUID = 1L;
	private int innerRadius;

	public Donut() {

	}

	public Donut(Point center, int outerRadius, int innerRadius) {
		super(center, outerRadius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int outerRadius, int innerRadius, boolean selected, Color borderColor, Color fillColor) {
		this(center, outerRadius, innerRadius);
		setSelected(selected);
		setBorderColor(borderColor);
		setFillColor(fillColor);
	}
	
	public boolean contains(int x, int y) {
		return super.contains(x, y) && center.distance(x, y) > innerRadius;
	}

	public double area() {
		return super.area() - (innerRadius * innerRadius * Math.PI);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getR() == d.getR()
					&& this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void draw(Graphics g) {
		Ellipse2D outerCircle =new Ellipse2D.Double(
				this.getCenter().getX() - this.getR(),
				this.getCenter().getY() - this.getR(),
				this.getR()*2, 
				this.getR()*2);
		
		Ellipse2D innerCircle=new Ellipse2D.Double(
				this.getCenter().getX() - this.getInnerRadius(),
				this.getCenter().getY() - this.getInnerRadius(),
				this.getInnerRadius()*2,
				this.getInnerRadius()*2);
		
		Area donut=new Area(outerCircle);
		donut.subtract(new Area(innerCircle));
		
		Graphics2D graphics2D =(Graphics2D)g;
		graphics2D.setColor(getFillColor());
		graphics2D.fill(donut);
		graphics2D.setColor(getBorderColor());
		graphics2D.draw(donut);
		
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getX() - r - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() + r - 3, center.getY() - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() - r - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() + r - 3, 6, 6);
			g.drawRect(center.getX() - 3, center.getY() - 3, 6, 6);
			
		}
	}
	

	public Donut clone() {
		Donut cloneDonut = new Donut();
		cloneDonut.setBorderColor(this.getBorderColor());
		cloneDonut.setFillColor(this.getFillColor());
		cloneDonut.setCenter(this.getCenter().clone());
		cloneDonut.setInnerRadius(this.getInnerRadius());
		cloneDonut.setR(this.getR());
		cloneDonut.setSelected(this.isSelected());
		return cloneDonut;
	}

	public String toString() {
		return center + ", radius: " + r + ", innerRadius: " + innerRadius + ", borderColor= " + this.getBorderColor().getRGB() + ", fillColor= " + this.getFillColor().getRGB();
	}

	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		
		this.innerRadius = innerRadius;
	}

}
