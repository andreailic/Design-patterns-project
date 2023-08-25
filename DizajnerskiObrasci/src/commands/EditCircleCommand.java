package commands;

import geometry.Circle;

public class EditCircleCommand implements GenericCommand {
	
	private Circle oldState;
	private Circle newState;
	private Circle original = new Circle();

	public EditCircleCommand(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void forward() {
		original = oldState.clone();
		oldState.setR(newState.getR());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setFillColor(newState.getFillColor());
		oldState.setSelected(true);

	}

	@Override
	public void backward() {
		oldState.setR(original.getR());
		oldState.setBorderColor(original.getBorderColor());
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		oldState.setFillColor(original.getFillColor());
		oldState.setSelected(true);
	}
	
	@Override
	public String toString() {
		return "Modified Circle " + "[" + oldState + "]" + " to [" + newState + "]";
	}
	
}