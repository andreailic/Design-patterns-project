package helper;

import geometry.Shape;

public class ShapeIndex {

	private Shape shape;
	private int index;
	
	
	public ShapeIndex(Shape shape, int index) {
		super();
		this.shape = shape;
		this.index = index;
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
}
