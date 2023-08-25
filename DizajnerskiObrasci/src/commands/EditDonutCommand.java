package commands;

import geometry.Donut;

public class EditDonutCommand implements GenericCommand {
	
	private Donut oldState;
	private Donut newState;
	private Donut original;
	
	public EditDonutCommand(Donut oldState,Donut newState) {
		this.oldState=oldState;
		this.newState=newState;
	}
	
	@Override
	public void forward() {
		original=oldState.clone();
		oldState.setR(newState.getR());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setCenter(newState.getCenter());
		oldState.getCenter().setX(newState.getCenter().getX());
		oldState.getCenter().setY(newState.getCenter().getY());
		oldState.setFillColor(newState.getFillColor());
		oldState.setInnerRadius(newState.getInnerRadius());
		oldState.setSelected(true);
	}

	@Override
	public void backward() {
		oldState.setR(original.getR());
		oldState.setBorderColor(original.getBorderColor());
		oldState.getCenter().setX(original.getCenter().getX());
		oldState.getCenter().setY(original.getCenter().getY());
		oldState.setFillColor(original.getFillColor());
		oldState.setInnerRadius(original.getInnerRadius());
		oldState.setSelected(true);
	}
	
	@Override
	public String toString() {
		return "Modified Donut " + "[" + oldState + "]" + " to [" + newState + "]";
	}
}
