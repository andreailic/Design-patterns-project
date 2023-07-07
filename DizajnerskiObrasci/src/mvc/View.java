package mvc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;

import javax.swing.JPanel;

public class View extends JPanel {

	private static final long serialVersionUID = 1L;

	public View() {
		setBackground(Color.white);
	}

	private Model model;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void paint(Graphics g) {

		super.paint(g);
		ListIterator<geometry.Shape> it = model.getShapes().listIterator();
		while (it.hasNext()) {
			it.next().draw(g);
		}
	}

}
