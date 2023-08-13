package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
import commands.SelectShapeCommand;
import commands.YAxisCommand;
import drawingDialogs.CircleDialog;
import drawingDialogs.DonutDialog;
import drawingDialogs.HexagonDialog;
import drawingDialogs.RectangleDialog;
import files.LogParser;
import files.SaveDrawing;
import files.SaveLog;
import files.SaveManager;
import geometry.AdapterHexagon;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import modificationDialogs.CircleModificationDialog;
import modificationDialogs.DonutModificationDialog;
import modificationDialogs.HexagonModificationDialog;
import modificationDialogs.LineModificationDialog;
import modificationDialogs.PointModificationDialog;
import modificationDialogs.RectangleModificationDialog;

@SuppressWarnings("deprecation")
public class Controller extends Observable {

	private Model model;
	private Frame frame;
	private Point firstClickedPointOfLine = null;
	private Stack<GenericCommand> commandsForUndo = new Stack<GenericCommand>();
	private Stack<GenericCommand> commandsForRedo = new Stack<GenericCommand>();
	private List<String> loadedLogs = new ArrayList<String>();
	private LogParser parser;

	public Controller(Model model, Frame frame) {
		this.model = model;
		this.frame = frame;
		this.parser = new LogParser(model);
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
	
	public void newDrawing() {
		model.clearShapes();
		frame.clearLogs();
		commandsForUndo.clear();
		commandsForRedo.clear();
		firstClickedPointOfLine = null;
		
		frame.getView().repaint();
		frame.hideLoadNextButton();
		notifyAllObservers();
	}
	
	public void loadNextLog() {
		String log = loadedLogs.get(0);
		GenericCommand parsedCommand = parser.parse(log);
		loadedLogs.remove(0);
		frame.getView().repaint();
		frame.logCommand(log);
		commandsForUndo.add(parsedCommand);
		notifyAllObservers();
		if (loadedLogs.size() == 0) {
			frame.hideLoadNextButton();
		}
	}
	
	public void loadLogs() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select a file");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			
			try {
				BufferedReader buffer = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getAbsolutePath()));
				newDrawing();
				loadedLogs = new ArrayList<String>();
				String text = "";
				while((text = buffer.readLine()) != null) {
					loadedLogs.add(text);
				}
				
				buffer.close();
				notifyAllObservers();
				frame.showLoadNextButton();
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void saveLogs(List<String> logs) {

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save logs");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	
		if(fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			
			SaveManager manager = new SaveManager();
			SaveLog saveLog = new SaveLog();
			
			String logToSave = "";
			
			for (String log : logs) {
				logToSave = logToSave + log;
			}
			
			saveLog.setLog(logToSave);
			manager.setStrategy(saveLog);
			manager.save(fileChooser.getSelectedFile().getAbsolutePath() + ".log");
			
			JOptionPane.showMessageDialog(null, "Successfully saved", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	public void openDrawing() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify the painting you want to open");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			
			try {
				newDrawing();
				
				FileInputStream fis = new FileInputStream(fileChooser.getSelectedFile().getAbsoluteFile());
				ObjectInputStream ois = new ObjectInputStream(fis);
				List<Shape> loadedShapes = ((List<Shape>)ois.readObject());
				for (Shape s: loadedShapes) {
					model.addShape(s);
				}
				
				ois.close();
				frame.getView().repaint();
				notifyAllObservers();
				
			} catch (Exception ex) {
				
				JOptionPane.showMessageDialog(null, "Drawing failed to load!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void saveDrawing() {
		if (model.getShapes().size() == 0) {
			JOptionPane.showMessageDialog(null, "Drawing is empty!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save drawing");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		if(fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			
			SaveManager manager = new SaveManager();
			SaveDrawing strategy = new SaveDrawing();
			manager.setStrategy(strategy);
			
			strategy.setShapes(model.getShapes());
			String path = fileChooser.getSelectedFile().getAbsolutePath() + ".bin";
			manager.save(path);
			JOptionPane.showMessageDialog(null, "Successfully saved", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void undo() {
		if (commandsForUndo.isEmpty()) {
			return;
		}
		
		GenericCommand command = commandsForUndo.pop();
		command.backward();
		frame.getView().repaint();
		frame.logCommand("UNDO");
		commandsForRedo.push(command);
		notifyAllObservers();
		frame.hideLoadNextButton();
	}
	
	public void redo() {
		if (commandsForRedo.isEmpty()) {
			return;
		}
		
		GenericCommand command = commandsForRedo.pop();
		command.forward();
		frame.getView().repaint();
		frame.logCommand("REDO");
		commandsForUndo.push(command);
		notifyAllObservers();
		frame.hideLoadNextButton();
	}
	
	public void up() {
		Shape shape = model.getOneSelectedShape();
		int currentIndex = model.getIndexOfShape(shape);
		int nextIndex = currentIndex + 1;
		YAxisCommand command = new YAxisCommand(currentIndex, nextIndex, model);
		command.forward();
		commandsForUndo.push(command);
		commandsForRedo.clear();
		frame.getView().repaint();
		frame.logCommand("Up " + command.toString());
		notifyAllObservers();
		frame.hideLoadNextButton();
	}

	public void down() {
		Shape shape = model.getOneSelectedShape();
		int currentIndex = model.getIndexOfShape(shape);
		int nextIndex = currentIndex - 1;
		YAxisCommand command = new YAxisCommand(currentIndex, nextIndex, model);
		command.forward();
		commandsForUndo.push(command);
		commandsForRedo.clear();
		frame.getView().repaint();
		frame.logCommand("Down " + command.toString());
		notifyAllObservers();
		frame.hideLoadNextButton();
		
	}

	public void toFront() {
		Shape shape = model.getOneSelectedShape();
		int currentIndex = model.getIndexOfShape(shape);
		int nextIndex = model.getShapes().size() - 1;
		YAxisCommand command = new YAxisCommand(currentIndex, nextIndex, model);
		command.forward();
		commandsForUndo.push(command);
		commandsForRedo.clear();
		frame.getView().repaint();
		frame.logCommand("Front " + command.toString());
		notifyAllObservers();
		frame.hideLoadNextButton();
		
	}

	public void toBack() {
		Shape shape = model.getOneSelectedShape();
		int currentIndex = model.getIndexOfShape(shape);
		int nextIndex = 0;
		YAxisCommand command = new YAxisCommand(currentIndex, nextIndex, model);
		command.forward();
		commandsForUndo.push(command);
		commandsForRedo.clear();
		frame.getView().repaint();
		frame.logCommand("Back " + command.toString());
		notifyAllObservers();
		frame.hideLoadNextButton();
		
	}
	
	public void delete() {
		List<Shape> shapes = model.getSelectedShapes();
		DeleteCommand command = new DeleteCommand(shapes, model);
		command.forward();
		commandsForUndo.push(command);
		commandsForRedo.clear();
		frame.getView().repaint();
		frame.logCommand(command.toString());
		notifyAllObservers();
		frame.hideLoadNextButton();
	}

	public void mouseClicked(MouseEvent clickedPoint) {

		if (frame.getTglbtnSelect().isSelected()) {

			for (int i = model.getShapes().size() - 1; i >= 0; i--) {

				if (model.getShapes().get(i).contains(clickedPoint.getX(), clickedPoint.getY())
						&& !model.getShapes().get(i).isSelected()) {

					SelectShapeCommand command = new SelectShapeCommand(model.getShapes().get(i), model);
					command.forward();
					commandsForUndo.push(command);
					commandsForRedo.clear();
					frame.logCommand(command.toString());
					frame.getView().repaint();
					notifyAllObservers();
					frame.hideLoadNextButton();
					return;
				} else if (model.getShapes().get(i).contains(clickedPoint.getX(), clickedPoint.getY())
						&& model.getShapes().get(i).isSelected()) {

					List<Shape> helpList = new ArrayList<Shape>();
					helpList.add(model.getShapes().get(i));
					DeselectCommand command = new DeselectCommand(helpList, model);
					command.forward();
					commandsForUndo.push(command);
					commandsForRedo.clear();
					frame.logCommand(command.toString());
					frame.getView().repaint();
					notifyAllObservers();
					frame.hideLoadNextButton();
					return;
				}
			}

			DeselectCommand command = new DeselectCommand(model.getSelectedShapes(), model);
			command.forward();
			commandsForUndo.push(command);
			commandsForRedo.clear();
			frame.logCommand(command.toString());
		} else if (frame.getTglbtnPoint().isSelected()) {
			Point point = new Point(clickedPoint.getX(), clickedPoint.getY());
			point.setBorderColor(frame.getBorderColor());
			GenericCommand command = new AddShapeCommand(point, model);
			command.forward();
			commandsForUndo.push(command);
			commandsForRedo.clear();
			frame.logCommand(command.toString());
		} else if (frame.getTglbtnNewToggleButton().isSelected()) {
			RectangleDialog dialog = new RectangleDialog();
			if (dialog.isConfirmed()) {
				int height = Integer.parseInt(dialog.getTxtHeight().getText().toString());
				int width = Integer.parseInt(dialog.getTxtWidth().getText().toString());

				Rectangle rectangle = new Rectangle(new Point(clickedPoint.getX(), clickedPoint.getY()), width, height);
				rectangle.setBorderColor(frame.getBorderColor());
				rectangle.setFillColor(frame.getInsideColor());
				GenericCommand command = new AddShapeCommand(rectangle, model);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		} else if (frame.getTglbtnLine().isSelected()) {
			if (firstClickedPointOfLine == null)
				firstClickedPointOfLine = new Point(clickedPoint.getX(), clickedPoint.getY());
			else {
				Line line = new Line(firstClickedPointOfLine, new Point(clickedPoint.getX(), clickedPoint.getY()));
				line.setBorderColor(frame.getBorderColor());
				GenericCommand command = new AddShapeCommand(line, model);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				firstClickedPointOfLine = null;
				frame.logCommand(command.toString());
			}
		} else if (frame.getTglbtnCircle().isSelected()) {
			CircleDialog dialog = new CircleDialog();
			if (dialog.isConfirmed()) {
				int radius = Integer.parseInt(dialog.getTxtRadius().getText().toString());

				Circle circle = new Circle(new Point(clickedPoint.getX(), clickedPoint.getY()), radius);
				circle.setBorderColor(frame.getBorderColor());
				circle.setFillColor(frame.getInsideColor());
				
				GenericCommand command = new AddShapeCommand(circle, model);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		} else if (frame.getTglbtnDonut().isSelected()) {
			DonutDialog dialog = new DonutDialog();
			if (dialog.isConfirmed()) {
				int radius = Integer.parseInt(dialog.getTxtRadius().getText().toString());
				int innerRadius = Integer.parseInt(dialog.getTxtInnerRadius().getText().toString());
				Donut donut = new Donut(new Point(clickedPoint.getX(), clickedPoint.getY()), radius, innerRadius);
				donut.setBorderColor(frame.getBorderColor());
				donut.setFillColor(frame.getInsideColor());
				
				GenericCommand command = new AddShapeCommand(donut, model);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		} else if (frame.getTglbtnHexagon().isSelected()) {
			HexagonDialog dialog = new HexagonDialog();
			if (dialog.isConfirmed()) {
				int radius = Integer.parseInt(dialog.getTxtRadius().getText().toString());
				AdapterHexagon hexagon = new AdapterHexagon(clickedPoint.getX(), clickedPoint.getY(), radius);
				hexagon.setBorderColor(frame.getBorderColor());
				hexagon.setFillColor(frame.getInsideColor());
				
				GenericCommand command = new AddShapeCommand(hexagon, model);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		}
		
		notifyAllObservers();
		frame.repaint();
		frame.hideLoadNextButton();
	}
	
	public void edit() {
		Shape selectedShape = model.getOneSelectedShape();
		
		if (selectedShape instanceof Point) {
			Point point = (Point)selectedShape;
			PointModificationDialog dialog = new PointModificationDialog();
			dialog.setTxtX(String.valueOf(point.getX()));
			dialog.setTxtY(String.valueOf(point.getY()));
			dialog.setColor(point.getBorderColor());
			
			dialog.setVisible(true);
			
			if (dialog.isConfirmed()) {
				
				int x = Integer.parseInt(dialog.getTxtX());
				int y = Integer.parseInt(dialog.getTxtY());
				Color color = dialog.getColor();
				
				Point editedPoint = new Point(x, y);
				editedPoint.setBorderColor(color);
				
				EditPointCommand command = new EditPointCommand(point, editedPoint);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		} else if (selectedShape instanceof Line) {
			Line line = (Line) selectedShape;
			LineModificationDialog dialog = new LineModificationDialog();
			dialog.setTxtStartX(String.valueOf(line.getStartPoint().getX()));
			dialog.setTxtStartY(String.valueOf(line.getStartPoint().getY()));
			dialog.setTxtEndX(String.valueOf(line.getEndPoint().getX()));
			dialog.setTxtEndY(String.valueOf(line.getEndPoint().getY()));
			dialog.setColor(line.getBorderColor());
			
			dialog.setVisible(true);
			
			if (dialog.isConfirmed()) {
				int xStart = Integer.parseInt(dialog.getTxtStartX());
				int yStart = Integer.parseInt(dialog.getTxtStartY());
				int xEnd = Integer.parseInt(dialog.getTxtEndX());
				int yEnd = Integer.parseInt(dialog.getTxtEndY());
				Color color = dialog.getColor();
				
				Line editedLine = new Line(new Point (xStart, yStart), new Point(xEnd, yEnd));
				editedLine.setBorderColor(color);
				EditLineCommand command = new EditLineCommand(line, editedLine);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		} else if (selectedShape instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) selectedShape;
			
			RectangleModificationDialog dialog = new RectangleModificationDialog();
			dialog.setTxtStartX(String.valueOf(rectangle.getUpperLeftPoint().getX()));
			dialog.setTxtStartY(String.valueOf(rectangle.getUpperLeftPoint().getY()));
			dialog.setTxtHeight(String.valueOf(rectangle.getHeight()));
			dialog.setTxtWidth(String.valueOf(rectangle.getWidth()));
			dialog.setBorderColor(rectangle.getBorderColor());
			dialog.setAreaColor(rectangle.getFillColor());
			
			dialog.setVisible(true);
			
			if (dialog.isConfirmed()) {
				int x = Integer.parseInt(dialog.getTxtStartX());
				int y = Integer.parseInt(dialog.getTxtStartY());
				int width = Integer.parseInt(dialog.getTxtWidth());
				int height = Integer.parseInt(dialog.getTxtHeight());
				Color borderColor = dialog.getBorderColor();
				Color areaColor = dialog.getAreaColor();
				
				Rectangle editedRectangle = new Rectangle();
				editedRectangle.setUpperLeftPoint(new Point (x, y));
				editedRectangle.setHeight(height);
				editedRectangle.setWidth(width);
				editedRectangle.setBorderColor(borderColor);
				editedRectangle.setFillColor(areaColor);
				
				EditRectangleCommand command = new EditRectangleCommand(rectangle, editedRectangle);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		} else if (selectedShape instanceof Donut) {
			Donut donut = (Donut) selectedShape;
			
			DonutModificationDialog dialog = new DonutModificationDialog();
			dialog.setTxtStartX(String.valueOf(donut.getCenter().getX()));
			dialog.setTxtStartY(String.valueOf(donut.getCenter().getY()));
			dialog.setTxtRadius(String.valueOf(donut.getR()));
			dialog.setTxtInnerRadius(String.valueOf(donut.getInnerRadius()));
			dialog.setBorderColor(donut.getBorderColor());
			dialog.setAreaColor(donut.getFillColor());
			
			dialog.setVisible(true);
			
			if (dialog.isConfirmed()) {
				int x = Integer.parseInt(dialog.getTxtStartX());
				int y = Integer.parseInt(dialog.getTxtStartY());
				int radius = Integer.parseInt(dialog.getTxtRadius());
				int innerRadius = Integer.parseInt(dialog.getTxtInnerRadius());
				Color borderColor = dialog.getBorderColor();
				Color areaColor = dialog.getAreaColor();
				
				Donut editedDonut = new Donut();
				editedDonut.setCenter(new Point(x,y));
				editedDonut.setR(radius);
				editedDonut.setInnerRadius(innerRadius);
				editedDonut.setBorderColor(borderColor);
				editedDonut.setFillColor(areaColor);
				
				EditDonutCommand command = new EditDonutCommand(donut, editedDonut);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		} else if (selectedShape instanceof Circle) {
			Circle circle = (Circle) selectedShape;
			
			CircleModificationDialog dialog = new CircleModificationDialog();
			dialog.setTxtStartX(String.valueOf(circle.getCenter().getX()));
			dialog.setTxtStartY(String.valueOf(circle.getCenter().getY()));
			dialog.setTxtRadius(String.valueOf(circle.getR()));
			dialog.setBorderColor(circle.getBorderColor());
			dialog.setAreaColor(circle.getFillColor());
			
			dialog.setVisible(true);
			
			if (dialog.isConfirmed()) {
				int x = Integer.parseInt(dialog.getTxtStartX());
				int y = Integer.parseInt(dialog.getTxtStartY());
				int radius = Integer.parseInt(dialog.getTxtRadius());
				Color borderColor = dialog.getBorderColor();
				Color areaColor = dialog.getAreaColor();
				
				Circle editedCircle = new Circle();
				editedCircle.setCenter(new Point(x,y));
				editedCircle.setR(radius);
				editedCircle.setBorderColor(borderColor);
				editedCircle.setFillColor(areaColor);
				
				EditCircleCommand command = new EditCircleCommand(circle, editedCircle);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		}  else if (selectedShape instanceof AdapterHexagon) {
			AdapterHexagon hexagon = (AdapterHexagon) selectedShape;
			
			HexagonModificationDialog dialog = new HexagonModificationDialog();
			dialog.setTxtStartX(String.valueOf(hexagon.getX()));
			dialog.setTxtStartY(String.valueOf(hexagon.getY()));
			dialog.setTxtRadius(String.valueOf(hexagon.getR()));
			dialog.setBorderColor(hexagon.getBorderColor());
			dialog.setAreaColor(hexagon.getFillColor());
			
			dialog.setVisible(true);
			
			if (dialog.isConfirmed()) {
				int x = Integer.parseInt(dialog.getTxtStartX());
				int y = Integer.parseInt(dialog.getTxtStartY());
				int radius = Integer.parseInt(dialog.getTxtRadius());
				Color borderColor = dialog.getBorderColor();
				Color areaColor = dialog.getAreaColor();
				
				AdapterHexagon editedHexagon = new AdapterHexagon(x, y, radius, false, borderColor, areaColor);
				
				EditHexagonCommand command = new EditHexagonCommand(hexagon, editedHexagon);
				command.forward();
				commandsForUndo.push(command);
				commandsForRedo.clear();
				frame.logCommand(command.toString());
			}
		}
		
		notifyAllObservers();
		frame.repaint();
		frame.hideLoadNextButton();
	}

	public void notifyAllObservers() {
		
		List<Boolean> flags = new ArrayList<>();
		boolean editEnabled = model.getSelectedShapes().size() == 1;
		boolean selectEnabled = model.getShapes().size() > 0;
		boolean deleteEnabled = model.getSelectedShapes().size() > 0;
		
		boolean upEnabled = false;
		boolean downEnabled = false;
		boolean toFrontEnabled = false;
		boolean toBackEnabled = false;
		
		boolean undoEnabled = commandsForUndo.size() > 0;
		boolean redoEnabled = commandsForRedo.size() > 0;
		
		if (model.getSelectedShapes().size() == 1) {
			Shape selectedShape = model.getOneSelectedShape();
			int numberOfShapes = model.getShapes().size();
			
			upEnabled = toFrontEnabled = model.getIndexOfShape(selectedShape) < numberOfShapes - 1;
			downEnabled = toBackEnabled = model.getIndexOfShape(selectedShape) > 0;
		}
		
		boolean loadNextCommandEnabled = loadedLogs.size() > 0; 
		
		flags.add(0, editEnabled);
		flags.add(1, selectEnabled);
		flags.add(2, deleteEnabled);
		flags.add(3, upEnabled);
		flags.add(4, downEnabled);
		flags.add(5, toFrontEnabled);
		flags.add(6, toBackEnabled);
		flags.add(7, undoEnabled);
		flags.add(8, redoEnabled);
		flags.add(9, loadNextCommandEnabled);
		
		setChanged();
		notifyObservers(flags);
	}

}
