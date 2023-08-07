package commands;

import geometry.Shape;
import mvc.Model;

public class AddShapeCommand implements GenericCommand {

	private Shape shape;
	private Model model;
	
	public AddShapeCommand(Shape shape, Model model) {
		this.shape = shape;
		this.model = model;
	}
	
	@Override
	public void forward() {
		model.addShape(shape);
	}

	@Override
	public void backward() {
		model.removeShape(shape);
	}
	
	@Override
	public String toString() {
		return "Add " + shape.getClass().getSimpleName() + " " + shape.toString();
	}

}
