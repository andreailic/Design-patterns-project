package commands;

import java.util.ArrayList;
import java.util.List;

import geometry.Shape;
import helper.ShapeIndex;
import mvc.Model;

public class DeleteCommand implements GenericCommand {

	private List<ShapeIndex> shapesIndices = new ArrayList<ShapeIndex>();
	private Model model;
	private List<Shape> shapesBackup = new ArrayList<Shape>();
	
	public DeleteCommand(List<Shape> shapes,
			Model model) {
		
		for (Shape s : shapes) {
			int index = model.getIndexOfShape(s);
			ShapeIndex shapeIndex = new ShapeIndex(s, index);
			shapesIndices.add(shapeIndex);
			shapesBackup.add(s);
		}
		
		this.model = model;
	}
	
	@Override
	public void forward() {
		for (ShapeIndex s : shapesIndices) {
			model.removeShape(s.getShape());
		}
		
	}

	@Override
	public void backward() {
		for (ShapeIndex s : shapesIndices) {
			model.addShapeOnIndex(s.getShape(), s.getIndex());
		}
	}
	
	@Override
	public String toString() {
		String shapesString = "";
		for (Shape s : shapesBackup) {
			shapesString += s.toString() + ";";
		}
		
		shapesString = shapesString.substring(0, shapesString.length() - 1);
		
		return "Delete " + shapesString;
	}

}
