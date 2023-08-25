package commands;

import geometry.Line;

public class EditLineCommand implements GenericCommand {

	private Line oldState;
	private Line newState;
	private Line original;
	
	public EditLineCommand (Line oldState, Line newState) {
		this.oldState = oldState;
		this.newState = newState;
	}
	
	@Override
	public void forward() {
		original=oldState.clone();
		
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setStartPoint(newState.getStartPoint());
		oldState.setEndPoint(newState.getEndPoint());
		oldState.setSelected(true);
	}

	@Override
	public void backward() {
		oldState.setBorderColor(original.getBorderColor());
		oldState.setStartPoint(original.getStartPoint());
		oldState.setEndPoint(original.getEndPoint());
		oldState.setSelected(true);
	}
	
	@Override
	public String toString() {
		return "Modified Line " + "[" + oldState + "]" + " to [" + newState + "]";
	}

}
