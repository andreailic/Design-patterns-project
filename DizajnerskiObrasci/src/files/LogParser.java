package files;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import commands.AddShapeCommand;
import commands.DeleteCommand;
import commands.DeselectCommand;
import commands.EditCircleCommand;
import commands.EditDonutCommand;
import commands.EditHexagonCommand;
import commands.EditLineCommand;
import commands.EditPointCommand;
import commands.EditRectangleCommand;
import commands.GenericCommand;
import commands.RedoCommand;
import commands.SelectShapeCommand;
import commands.UndoCommand;
import commands.YAxisCommand;
import geometry.AdapterHexagon;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.Model;


public class LogParser {
	
	private Model model;
	private List<GenericCommand> undoList = new ArrayList<GenericCommand>();
	private List<GenericCommand> redoList = new ArrayList<GenericCommand>();
	
	public LogParser(Model model) {
		this.model = model;
	}

	public GenericCommand parse(String log) {
		String[] line = log.split("[] (),]+");
		if(line[0].equals("Add")) {
			if(line[1].equals("Point")) {
				Point p = new Point(
						Integer.parseInt(line[5]),
						Integer.parseInt(line[6]), 
						false, 
						(Integer.parseInt(line[8]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[8]))
								));
				AddShapeCommand add = new AddShapeCommand(p, model);
				add.forward();
				undoList.add(add);
				redoList.clear();
				return add;
			}
			else if(line[1].equals("Line")) {
				Point p1 = new Point(
						Integer.parseInt(line[5]),
						Integer.parseInt(line[6]), 
						false, 
						new Color(Integer.parseInt(line[14])));
				
				Point p2 = new Point(
						Integer.parseInt(line[11]),
						Integer.parseInt(line[12]), 
						false, 
						new Color(Integer.parseInt(line[12])));
				
				Line l = new Line(
						p1, 
						p2, 
						false, 
						(Integer.parseInt(line[14]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[14]))
								));
				
				AddShapeCommand add = new AddShapeCommand(l, model);
				add.forward();
				undoList.add(add);
				redoList.clear();
				return add;
			}
			else if(line[1].equals("Rectangle")) {
				Point p = new Point(
						Integer.parseInt(line[5]),
						Integer.parseInt(line[6]),
						false,
						Color.BLACK);
				
				int height = Integer.parseInt(line[10]);
				int width = Integer.parseInt(line[8]);
				
				Rectangle r = new Rectangle(
						p, 
						width,
						height, 
						false, 
						new Color(Integer.parseInt(line[12])), new Color(Integer.parseInt(line[14]))
						);
				
				AddShapeCommand add = new AddShapeCommand(r, model);
				add.forward();
				undoList.add(add);
				redoList.clear();
				return add;
			}
			else if(line[1].equals("Circle")) {
				Point p = new Point(
						Integer.parseInt(line[5]), 
						Integer.parseInt(line[6]), 
						false, 
						Color.BLACK
						);
				int radius = Integer.parseInt(line[8]);
				
				Circle c = new Circle(
						p, 
						radius, 
						false, 
						(Integer.parseInt(line[10]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[10]))),
						(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))));
				
				AddShapeCommand add = new AddShapeCommand(c, model);
				add.forward();
				undoList.add(add);
				redoList.clear();
				return add;
			}
			else if(line[1].equals("Donut")) {
				Point p = new Point(
						Integer.parseInt(line[5]), 
						Integer.parseInt(line[6]), 
						false, 
						Color.BLACK
						);
				
				int radius = Integer.parseInt(line[8]);
				int innerRadius = Integer.parseInt(line[10]);
				
				Donut d = new Donut(
						p, 
						radius, 
						innerRadius, 
						false, 
						(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12]))), 
						(Integer.parseInt(line[14]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[14])))
						);
				
				AddShapeCommand add = new AddShapeCommand(d, model);
				add.forward();
				undoList.add(add);
				redoList.clear();
				return add;
			}
			else if(line[1].equals("AdapterHexagon")) {
				
				int radius = Integer.parseInt(line[8]);
				AdapterHexagon h = new AdapterHexagon(
						Integer.parseInt(line[5]),
						Integer.parseInt(line[6]),
						radius,
						false, 
						(Integer.parseInt(line[10]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[10]))), 
						(Integer.parseInt(line[12]) == 0 ? new Color(0, 0, 0, 0) : new Color(Integer.parseInt(line[12])))
						);
				
				AddShapeCommand add = new AddShapeCommand(h, model);
				add.forward();
				undoList.add(add);
				redoList.clear();
				return add;
			} else {
				return null;
			}
		} else if(line[0].equals("Select")) {
			
			int index = Integer.parseInt(line[4]);
			
			Shape shape = model.getShapes().get(index);
			SelectShapeCommand command = new SelectShapeCommand(shape, model);
			command.forward();
			undoList.add(command);
			redoList.clear();
			return command;
		}
		else if(line[0].equals("Deselect")) {
			List<Shape> shapes = new ArrayList<Shape>(); 
			for (int i = 4; i < line.length; i++) {
				shapes.add(model.getShapes().get(Integer.parseInt(line[i])));
			}
			
			DeselectCommand command = new DeselectCommand(shapes, model);
			command.forward();
			undoList.add(command);
			redoList.clear();
			return command;
		} else if (line[0].equals("Modified")) {
			
			Shape selected = model.getSelectedShapes().get(0);
			if(line[1].equals("Point")) {
				
				Point oldState = (Point)selected;
				Point newState = new Point(
						Integer.parseInt(line[15]),
						Integer.parseInt(line[16]),
						true,
						new Color(Integer.parseInt(line[18])));
				
				EditPointCommand cmdUpdate = new EditPointCommand(oldState, newState);
				cmdUpdate.forward();
				undoList.add(cmdUpdate);
				redoList.clear();
				return cmdUpdate;
			} else if (line[1].equals("Line")) {
				
				Point p1 = new Point(
						Integer.parseInt(line[21]),
						Integer.parseInt(line[22]), 
						false, 
						Color.BLACK
						);
				
				Point p2 = new Point(
						Integer.parseInt(line[27]),
						Integer.parseInt(line[28]), 
						false, 
						Color.BLACK
						);
				
				Line oldState = (Line)selected;
				Line newState = new Line(
						p1,
						p2,
						true,
						new Color(Integer.parseInt(line[30]))
						);
				
				EditLineCommand cmdUpdate = new EditLineCommand(oldState, newState);
				cmdUpdate.forward();
				undoList.add(cmdUpdate);
				redoList.clear();
				return cmdUpdate;
			} else if (line[1].equals("Rectangle")) {
				Point p1 = new Point(
						Integer.parseInt(line[21]),
						Integer.parseInt(line[22]), 
						false, 
						Color.BLACK
						);
				
				Rectangle oldState = (Rectangle)selected;
				Rectangle newState = new Rectangle(
						p1,
						Integer.parseInt(line[24]), 
						Integer.parseInt(line[26]),
						true, 
						new Color(Integer.parseInt(line[28])),
						new Color(Integer.parseInt(line[30]))
						);
				
				EditRectangleCommand cmdUpdate = new EditRectangleCommand(oldState, newState);
				cmdUpdate.forward();
				undoList.add(cmdUpdate);
				redoList.clear();
				return cmdUpdate;
			} else if (line[1].equals("Circle")) {
				Point p1 = new Point(
						Integer.parseInt(line[19]),
						Integer.parseInt(line[20]), 
						false, 
						Color.BLACK
						);
				
				Circle oldState = (Circle)selected;
				Circle newState = new Circle(
						p1, 
						Integer.parseInt(line[22]), 
						true,
						new Color(Integer.parseInt(line[24])), 
						new Color(Integer.parseInt(line[26]))
						);
				
				EditCircleCommand cmdUpdate = new EditCircleCommand(oldState, newState);
				cmdUpdate.forward();
				undoList.add(cmdUpdate);
				redoList.clear();
				return cmdUpdate;
			} else if (line[1].equals("Donut")) {
				for (int i = 0; i < 27; i++) {
					System.out.println(line[i]);
				}
				Point p1 = new Point(
						Integer.parseInt(line[21]),
						Integer.parseInt(line[22]), 
						false, 
						Color.BLACK
						);
				
				Donut oldState = (Donut)selected;
				Donut newState = new Donut(
						p1,
						Integer.parseInt(line[24]), 
						Integer.parseInt(line[26]), 
						true, 
						new Color(Integer.parseInt(line[28])), 
						new Color(Integer.parseInt(line[30]))
						);
				EditDonutCommand cmdUpdate = new EditDonutCommand(oldState, newState);
				cmdUpdate.forward();
				undoList.add(cmdUpdate);
				redoList.clear();
				return cmdUpdate;
			} else if (line[1].equals("Hexagon")) {
				
				AdapterHexagon oldState = (AdapterHexagon)selected;
				AdapterHexagon newState = new AdapterHexagon(
						Integer.parseInt(line[19]), 
						Integer.parseInt(line[20]), 
						Integer.parseInt(line[22]), 
						true, 
						new Color(Integer.parseInt(line[24])), 
						new Color(Integer.parseInt(line[26]))
						);
				EditHexagonCommand cmdUpdate = new EditHexagonCommand(oldState, newState);
				cmdUpdate.forward();
				undoList.add(cmdUpdate);
				redoList.clear();
				return cmdUpdate;
			} else {
				return null;
			}
		} else if(line[0].equals("Delete")) {
			List<Shape> shapes = model.getSelectedShapes();
			DeleteCommand command = new DeleteCommand(shapes, model);
			command.forward();
			undoList.add(command);
			redoList.clear();
			return command;
		} else if(line[0].equals("UNDO")) {
			GenericCommand command = undoList.get(undoList.size() - 1);
			UndoCommand undoCommand = new UndoCommand(command);
			undoCommand.forward();
			redoList.add(command);
			undoList.remove(undoList.size() - 1);
			return undoCommand;
		} else if(line[0].equals("REDO")) {
			GenericCommand command = redoList.get(redoList.size() - 1);
			RedoCommand redoCommand = new RedoCommand(command);
			redoCommand.forward();
			undoList.add(command);
			redoList.remove(redoList.size() - 1);
			return redoCommand;
		} else if(line[0].equals("Up")) {
			Shape selectedShape = model.getOneSelectedShape();
			int index = model.getIndexOfShape(selectedShape);
			YAxisCommand command = new YAxisCommand(index, index + 1, model);
			command.forward();
			undoList.add(command);
			redoList.clear();
			return command;
		} else if(line[0].equals("Down")) {
			Shape selectedShape = model.getOneSelectedShape();
			int index = model.getIndexOfShape(selectedShape);
			YAxisCommand command = new YAxisCommand(index, index - 1, model);
			command.forward();
			undoList.add(command);
			redoList.clear();
			return command;
		} else if(line[0].equals("Front")) {
			Shape selectedShape = model.getOneSelectedShape();
			int index = model.getIndexOfShape(selectedShape);
			YAxisCommand command = new YAxisCommand(index, model.getShapes().size() - 1, model);
			command.forward();
			undoList.add(command);
			redoList.clear();
			return command;
		} else if(line[0].equals("Back")) {
			Shape selectedShape = model.getOneSelectedShape();
			int index = model.getIndexOfShape(selectedShape);
			YAxisCommand command = new YAxisCommand(index, 0, model);
			command.forward();
			undoList.add(command);
			redoList.clear();
			return command;
		} else {
			return null;
		}
	}
}
