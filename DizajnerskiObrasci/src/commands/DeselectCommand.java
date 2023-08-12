package commands;

import java.util.List;
import java.util.ListIterator;

import geometry.Shape;
import mvc.Model;

public class DeselectCommand implements GenericCommand {

	private List<Shape> shapes;
	private Model model;
	
	public DeselectCommand(List<Shape> shapes, Model model) {
		this.shapes = shapes;
		this.model = model;
	}
	
	@Override
	public void forward() {
		ListIterator<geometry.Shape> it = shapes.listIterator();
		while (it.hasNext()) {
			it.next().setSelected(false);
		}
	}

	@Override
	public void backward() {
		ListIterator<geometry.Shape> it = shapes.listIterator();
		while (it.hasNext()) {
			it.next().setSelected(true);
		}		
	}
	
	@Override
	public String toString() {
		String indices = "";
		for (Shape shape : shapes ) {
			indices += model.getIndexOfShape(shape) + ",";
		}
		
		indices = indices.substring(0, indices.length() - 1);
		return "Deselect shapes on indices: " + indices;
	}

}
