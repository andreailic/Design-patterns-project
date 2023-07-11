package mvc;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private View panelForDrawing;
	private Controller controller;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnNewToggleButton;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnSelect;
	
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
		
		tglbtnPoint = new JToggleButton("Point");
		toolbarShapes.add(tglbtnPoint);
		toggleGroup.add(tglbtnPoint);
		
		tglbtnNewToggleButton = new JToggleButton("Rectangle");
		toolbarShapes.add(tglbtnNewToggleButton);
		toggleGroup.add(tglbtnNewToggleButton);
		
		tglbtnLine = new JToggleButton("Line");
		toolbarShapes.add(tglbtnLine);
		toggleGroup.add(tglbtnLine);
		
		tglbtnCircle = new JToggleButton("Circle");
		toolbarShapes.add(tglbtnCircle);
		toggleGroup.add(tglbtnCircle);
		
		tglbtnDonut = new JToggleButton("Donut");
		toolbarShapes.add(tglbtnDonut);
		toggleGroup.add(tglbtnDonut);
		
		tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.setBackground(Color.YELLOW);
		toolbarShapes.add(tglbtnSelect);
		toggleGroup.add(tglbtnSelect);
		
		JToolBar toolBarActions = new JToolBar();
		toolBarActions.setOrientation(SwingConstants.VERTICAL);
		leftToolbarPanel.add(toolBarActions, BorderLayout.SOUTH);
		
		JButton btnEdit = new JButton("Edit");
		toolBarActions.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		toolBarActions.add(btnDelete);
		
		/* Listeners */
		panelForDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.mouseClicked(arg0);
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

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}
	
}
