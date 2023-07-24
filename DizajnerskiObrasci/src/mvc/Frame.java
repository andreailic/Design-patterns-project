package mvc;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;

@SuppressWarnings("deprecation")
public class Frame extends JFrame implements Observer {
	
	private static final long serialVersionUID = 1L;
	private View panelForDrawing = new View();
	private Controller controller;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnNewToggleButton;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnHexagon;
	private JToggleButton tglbtnSelect;
	
	private JButton btnUp;
	private JButton btnDown;
	private JButton btnToFront;
	private JButton btnToBack;
	
	private JButton btnBorderColor;
	private JButton btnInsideColor;
	
	private JButton btnUndo;
	private JButton btnRedo;
	
	private JButton btnEdit;
	private JButton btnDelete;
	private DefaultListModel<String> defaultListModel;
	
	public Frame() {
		setTitle("Design patterns");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		ButtonGroup toggleGroup = new ButtonGroup(); 
		
		panelForDrawing = new View();
		getContentPane().add(panelForDrawing, BorderLayout.CENTER);
		
		JPanel leftToolbarPanel = new JPanel();
		getContentPane().add(leftToolbarPanel, BorderLayout.WEST);
		leftToolbarPanel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolbarShapes = new JToolBar();
		toolbarShapes.setOrientation(SwingConstants.VERTICAL);
		leftToolbarPanel.add(toolbarShapes, BorderLayout.NORTH);
		
		tglbtnPoint = new JToggleButton("     Point    ");
		toolbarShapes.add(tglbtnPoint);
		toggleGroup.add(tglbtnPoint);
		
		tglbtnNewToggleButton = new JToggleButton("Rectangle");
		toolbarShapes.add(tglbtnNewToggleButton);
		toggleGroup.add(tglbtnNewToggleButton);
		
		tglbtnLine = new JToggleButton("      Line     ");
		toolbarShapes.add(tglbtnLine);
		toggleGroup.add(tglbtnLine);
		
		tglbtnCircle = new JToggleButton("    Circle    ");
		toolbarShapes.add(tglbtnCircle);
		toggleGroup.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("    Donut    ");
		toolbarShapes.add(tglbtnDonut);
		toggleGroup.add(tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton(" Hexagon ");
		toolbarShapes.add(tglbtnHexagon);
		toggleGroup.add(tglbtnHexagon);
		
		tglbtnSelect = new JToggleButton("    Select   ");
		tglbtnSelect.setBackground(Color.YELLOW);
		tglbtnSelect.setEnabled(false);
		toolbarShapes.add(tglbtnSelect);
		toggleGroup.add(tglbtnSelect);
		
		JPanel centerPartOfLeftToolbarPanel = new JPanel();
		centerPartOfLeftToolbarPanel.setLayout(new BorderLayout());
		leftToolbarPanel.add(centerPartOfLeftToolbarPanel, BorderLayout.CENTER);
		
		JToolBar yAxisActions = new JToolBar();
		yAxisActions.setOrientation(SwingConstants.VERTICAL);
		centerPartOfLeftToolbarPanel.add(yAxisActions, BorderLayout.NORTH);
		
		btnUp = new JButton("       Up       ");
		btnUp.setEnabled(false);
		btnUp.setBackground(Color.GREEN);
		yAxisActions.add(btnUp);
		
		btnDown = new JButton("    Down    ");
		btnDown.setEnabled(false);
		btnDown.setBackground(Color.GREEN);
		yAxisActions.add(btnDown);
		
		btnToFront = new JButton("  To Front ");
		btnToFront.setEnabled(false);
		btnToFront.setBackground(Color.GREEN);
		yAxisActions.add(btnToFront);
		
		btnToBack = new JButton("  To Back ");
		btnToBack.setEnabled(false);
		btnToBack.setBackground(Color.GREEN);
		yAxisActions.add(btnToBack);
		
		JToolBar colorActions = new JToolBar();
		colorActions.setOrientation(SwingConstants.VERTICAL);
		centerPartOfLeftToolbarPanel.add(colorActions, BorderLayout.CENTER);
		
		btnBorderColor = new JButton("    Border  ");
		btnBorderColor.setBackground(Color.BLACK);
		colorActions.add(btnBorderColor);
		
		btnInsideColor = new JButton("    Inside    ");
		btnInsideColor.setBackground(Color.WHITE);
		colorActions.add(btnInsideColor);
		
		JToolBar toolBarActions = new JToolBar();
		toolBarActions.setOrientation(SwingConstants.VERTICAL);
		leftToolbarPanel.add(toolBarActions, BorderLayout.SOUTH);
		
		JToolBar undoRedoActions = new JToolBar();
		undoRedoActions.setOrientation(SwingConstants.VERTICAL);
		centerPartOfLeftToolbarPanel.add(undoRedoActions, BorderLayout.SOUTH);
		
		btnUndo = new JButton("    Undo    ");
		btnUndo.setEnabled(false);
		btnUndo.setBackground(Color.WHITE);
		undoRedoActions.add(btnUndo);
		
		btnRedo = new JButton("    Redo    ");
		btnRedo.setEnabled(false);
		btnRedo.setBackground(Color.WHITE);
		undoRedoActions.add(btnRedo);
		
		btnEdit = new JButton("      Edit     ");
		btnEdit.setEnabled(false);
		btnEdit.setBackground(Color.YELLOW);
		toolBarActions.add(btnEdit);
		
		btnDelete = new JButton("   Delete   ");
		btnDelete.setBackground(Color.RED);
		toolBarActions.add(btnDelete);
		
		JPanel logsContainer = new JPanel();
		logsContainer.setLayout(new BorderLayout());
		logsContainer.setMinimumSize(new Dimension(200, 0));
		getContentPane().add(logsContainer, BorderLayout.EAST);
		
		defaultListModel = new DefaultListModel<String>();
		JList<String> logsList = new JList<String>(defaultListModel);
		logsList.setVisibleRowCount(6);
		JScrollPane jScrollPane = new JScrollPane(logsList);
		JLabel logsTitle = new JLabel("App logs");
		logsContainer.add(logsTitle, BorderLayout.NORTH);
		logsContainer.add(jScrollPane, BorderLayout.CENTER);
		
		/* Listeners */
		panelForDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.mouseClicked(arg0);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.edit();
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.delete();
			}
		});
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.up();
			}
		});
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.down();
			}
		});
		
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toFront();
			}
		});
		
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.toBack();
			}
		});
		
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(null, "Border color", btnBorderColor.getBackground());
				if(color != null) {
					 btnBorderColor.setBackground(color);
				}
			}
		});
		
		btnInsideColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color color = JColorChooser.showDialog(null, "Inside color", btnInsideColor.getBackground());
				if(color != null) {
					 btnInsideColor.setBackground(color);
				}
			}
		});
		
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.undo();
			}
		});
		
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.redo();
			}
		});
	}

	public View getView() {
		return panelForDrawing;
	}

	public void setView(View view) {
		this.panelForDrawing = view;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public JToggleButton getTglbtnNewToggleButton() {
		return tglbtnNewToggleButton;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}
	
	public Color getBorderColor() {
		return btnBorderColor.getBackground();
	}

	public Color getInsideColor() {
		return btnInsideColor.getBackground();
	}
	
	public void setBorderColor(Color borderColor) {
		btnBorderColor.setBackground(borderColor);
	}

	public void setInsideColor(Color insideColor) {
		btnInsideColor.setBackground(insideColor);
	}
	
	public void logCommand(String command) {
		defaultListModel.addElement(command);
	}

	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		List<Boolean> flags = (List<Boolean>) arg;
		btnEdit.setEnabled(flags.get(0));
		tglbtnSelect.setEnabled(flags.get(1));
		btnDelete.setEnabled(flags.get(2));
		btnUp.setEnabled(flags.get(3));
		btnDown.setEnabled(flags.get(4));
		btnToFront.setEnabled(flags.get(5));
		btnToBack.setEnabled(flags.get(6));
		btnUndo.setEnabled(flags.get(7));
		btnRedo.setEnabled(flags.get(8));
	}
	
}
