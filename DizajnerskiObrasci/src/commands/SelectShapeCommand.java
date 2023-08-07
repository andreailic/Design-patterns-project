package commands;

import geometry.Shape;
import mvc.Model;

public class SelectShapeCommand implements GenericCommand {

	private Shape shape;
	private Model model;
	
	public SelectShapeCommand(Shape shape, Model model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void forward() {
		shape.setSelected(true);
	}

	@Override
	public void backward() {
		shape.setSelected(false);
	}
	
	@Override
	public String toString() {
		return "Select " + shape.getClass().getSimpleName() + " on index " + model.getIndexOfShape(shape);
	}

}
