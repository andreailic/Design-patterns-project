package mvc;

import java.awt.event.MouseEvent;

import commands.AddShapeCommand;
import commands.GenericCommand;
import geometry.Point;

public class Controller {

	private Model model;
	private Frame frame;
	
	public Controller(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
	}
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public Frame getFrame() {
		return frame;
	}
	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public void mouseClicked(MouseEvent clickedPoint) {
		
		if (frame.getTglbtnPoint().isSelected()) {
			Point point = new Point(clickedPoint.getX(), clickedPoint.getY());
			GenericCommand commmand = new AddShapeCommand(point, model);
			commmand.forward();
			
			frame.repaint();
		} else if (frame.getTglbtnNewToggleButton().isSelected()) {
			
		} else if (frame.getTglbtnLine().isSelected()) {
			
		} else if (frame.getTglbtnCircle().isSelected()) {
			
		}
		
	}
	
	
	
}
