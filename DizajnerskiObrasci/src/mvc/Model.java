package mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
	
	public List<Shape> getSelectedShapes() {
		List<Shape> helpList = new ArrayList<Shape>();
		
		ListIterator<geometry.Shape> it = shapes.listIterator();
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape.isSelected())
				helpList.add(helpShape);
		}
		
		return helpList;
	}
	
	public Shape getOneSelectedShape() {
		ListIterator<geometry.Shape> it = shapes.listIterator();
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape.isSelected())
				return helpShape;
		}
		
		return null;
	}
}
