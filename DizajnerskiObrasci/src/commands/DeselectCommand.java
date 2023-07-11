package commands;

import java.util.List;
import java.util.ListIterator;

import geometry.Shape;

public class DeselectCommand implements GenericCommand {

	private List<Shape> shapes;
	
	public DeselectCommand(List<Shape> shapes) {
		this.shapes = shapes;
	}
	
	@Override
	public void forward() {
		ListIterator<geometry.Shape> it = shapes.listIterator();
		while (it.hasNext()) {
			it.next().setSelected(false);
		}
	}

	@Override
	public void backward() {
		ListIterator<geometry.Shape> it = shapes.listIterator();
		while (it.hasNext()) {
			it.next().setSelected(true);
		}		
	}

}
