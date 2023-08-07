package commands;

import geometry.Shape;
import mvc.Model;

public class YAxisCommand implements GenericCommand {

	private int previousIndex;
	private int nextIndex;
	private Model model;
	
	public YAxisCommand(int previousIndex, 
			int nextIndex, 
			Model model) {
		this.previousIndex = previousIndex;
		this.nextIndex = nextIndex;
		this.model = model;
	}
	
	@Override
	public void forward() {
		Shape shape = model.getOneSelectedShape();
		model.removeShape(shape);
		model.addShapeOnIndex(shape, nextIndex);
	}
	
	@Override
	public void backward() {
		Shape shape = model.getOneSelectedShape();
		model.removeShape(shape);
		model.addShapeOnIndex(shape, previousIndex);
	}
	
	@Override
	public String toString() {
		return previousIndex + "," + nextIndex;
	}

}
