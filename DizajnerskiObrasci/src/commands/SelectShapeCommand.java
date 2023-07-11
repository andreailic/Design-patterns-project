package commands;

import geometry.Shape;

public class SelectShapeCommand implements GenericCommand {

	private Shape shape;
	
	public SelectShapeCommand(Shape shape) {
		this.shape = shape;
	}
	
	@Override
	public void forward() {
		shape.setSelected(true);
	}

	@Override
	public void backward() {
		shape.setSelected(false);
	}

}
