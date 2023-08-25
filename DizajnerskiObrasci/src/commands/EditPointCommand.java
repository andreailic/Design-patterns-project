package commands;

import geometry.Point;

public class EditPointCommand implements GenericCommand {

	private Point oldState;
	private Point newState;
	private Point original;
	
	public EditPointCommand (Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	@Override
	public void forward() {
		original = oldState.clone();
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setSelected(true);
	}

	@Override
	public void backward() {
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setSelected(true);
	}
	
	@Override
	public String toString() {
		return "Modified Point " + "[" + oldState + "]" + " to [" + newState + "]";
	}

}
