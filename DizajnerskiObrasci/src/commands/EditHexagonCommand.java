package commands;

import geometry.AdapterHexagon;

public class EditHexagonCommand implements GenericCommand {
	
	private AdapterHexagon oldState;
	private AdapterHexagon newState;
	private AdapterHexagon original = new AdapterHexagon(0,0,0, false, null, null);

	public EditHexagonCommand(AdapterHexagon oldState,
			AdapterHexagon newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void forward() {
		original = oldState.clone();
		oldState.setR(newState.getR());
		oldState.setBorderColor(newState.getBorderColor());
		oldState.setX(newState.getX());
		oldState.setY(newState.getY());
		oldState.setFillColor(newState.getFillColor());
		oldState.setSelected(true);

	}

	@Override
	public void backward() {
		oldState.setR(original.getR());
		oldState.setBorderColor(original.getBorderColor());
		oldState.setX(original.getX());
		oldState.setY(original.getY());
		oldState.setFillColor(original.getFillColor());
		oldState.setSelected(true);
	}
	
	@Override
	public String toString() {
		return "Modified Hexagon " + "[" + oldState + "]" + " to [" + newState + "]";
	}

}
