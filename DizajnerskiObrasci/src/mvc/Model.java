package mvc;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;

public class Model {

	private List<Shape> shapes;
	
	public Model() {
		shapes = new ArrayList<Shape>();
	}
	
	public void addShape(Shape shape) {
		shapes.add(shape);
	}
	
	public List<Shape> getShapes() {
		return shapes;
	}
}
