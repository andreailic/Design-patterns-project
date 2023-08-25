package commands;

import geometry.Rectangle;

public class EditRectangleCommand implements GenericCommand {

	private Rectangle oldState;
	private Rectangle newState;
	private Rectangle original;
	
	public EditRectangleCommand(
			Rectangle oldState,
			Rectangle newState) {
		this.oldState=oldState;
		this.newState=newState;
	}
	
	@Override
	public void forward() {
		original=oldState.clone();
		
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setFillColor(newState.getFillColor());
		oldState.setHeight(newState.getHeight());
		oldState.getUpperLeftPoint().setX(newState.getUpperLeftPoint().getX());
		oldState.getUpperLeftPoint().setY(newState.getUpperLeftPoint().getY());
		oldState.setWidth(newState.getWidth());
		oldState.setSelected(true);
	}

	@Override
	public void backward() {
		oldState.setBorderColor(original.getBorderColor());
		oldState.setFillColor(original.getFillColor());
		oldState.setHeight(original.getHeight());
		oldState.setUpperLeftPoint(original.getUpperLeftPoint());
		oldState.setWidth(original.getWidth());
		oldState.setSelected(true);
	}
	
	@Override
	public String toString() {
		return "Modified Rectangle " + "[" + oldState + "]" + " to [" + newState + "]";
	}
	
}