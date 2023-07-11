package mvc;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import commands.AddShapeCommand;
import commands.DeselectCommand;
import commands.GenericCommand;
import commands.SelectShapeCommand;
import drawingDialogs.CircleDialog;
import drawingDialogs.DonutDialog;
import drawingDialogs.RectangleDialog;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class Controller {

	private Model model;
	private Frame frame;
	private Point firstClickedPointOfLine = null;

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

		if (frame.getTglbtnSelect().isSelected()) {

			for (int i = model.getShapes().size() - 1; i >= 0; i--) {

				if (model.getShapes().get(i).contains(clickedPoint.getX(), clickedPoint.getY())
						&& !model.getShapes().get(i).isSelected()) {

					SelectShapeCommand command = new SelectShapeCommand(model.getShapes().get(i));
					command.forward();
					frame.getView().repaint();
					return;
				} else if (model.getShapes().get(i).contains(clickedPoint.getX(), clickedPoint.getY())
						&& model.getShapes().get(i).isSelected()) {

					List<Shape> helpList = new ArrayList<Shape>();
					helpList.add(model.getShapes().get(i));
					DeselectCommand command = new DeselectCommand(helpList);
					command.forward();
					frame.getView().repaint();
					return;
				}
			}

			frame.getView().repaint();
			DeselectCommand command = new DeselectCommand(model.getSelectedShapes());
			command.forward();
			frame.getView().repaint();
			return;

		}

		if (frame.getTglbtnPoint().isSelected()) {
			Point point = new Point(clickedPoint.getX(), clickedPoint.getY());
			GenericCommand commmand = new AddShapeCommand(point, model);
			commmand.forward();

			frame.repaint();
		} else if (frame.getTglbtnNewToggleButton().isSelected()) {
			RectangleDialog dialog = new RectangleDialog();
			if (dialog.isConfirmed()) {
				int height = Integer.parseInt(dialog.getTxtHeight().getText().toString());
				int width = Integer.parseInt(dialog.getTxtWidth().getText().toString());

				Rectangle rectangle = new Rectangle(new Point(clickedPoint.getX(), clickedPoint.getY()), width, height);

				GenericCommand commmand = new AddShapeCommand(rectangle, model);
				commmand.forward();

				frame.repaint();
			}
		} else if (frame.getTglbtnLine().isSelected()) {
			if (firstClickedPointOfLine == null)
				firstClickedPointOfLine = new Point(clickedPoint.getX(), clickedPoint.getY());
			else {
				Line line = new Line(firstClickedPointOfLine, new Point(clickedPoint.getX(), clickedPoint.getY()));
				GenericCommand commmand = new AddShapeCommand(line, model);
				commmand.forward();

				frame.repaint();
			}
		} else if (frame.getTglbtnCircle().isSelected()) {
			CircleDialog dialog = new CircleDialog();
			if (dialog.isConfirmed()) {
				int radius = Integer.parseInt(dialog.getTxtRadius().getText().toString());

				Circle circle = new Circle(new Point(clickedPoint.getX(), clickedPoint.getY()), radius);

				GenericCommand commmand = new AddShapeCommand(circle, model);
				commmand.forward();

				frame.repaint();
			}
		} else if (frame.getTglbtnDonut().isSelected()) {
			DonutDialog dialog = new DonutDialog();
			if (dialog.isConfirmed()) {
				int radius = Integer.parseInt(dialog.getTxtRadius().getText().toString());
				int innerRadius = Integer.parseInt(dialog.getTxtInnerRadius().getText().toString());
				System.out.println(radius);
				System.out.println(innerRadius);

				Donut donut = new Donut(new Point(clickedPoint.getX(), clickedPoint.getY()), radius, innerRadius);

				GenericCommand commmand = new AddShapeCommand(donut, model);
				commmand.forward();

				frame.repaint();
			}
		}

	}

}
