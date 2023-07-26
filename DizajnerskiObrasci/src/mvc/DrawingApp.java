package mvc;

import javax.swing.JFrame;

public class DrawingApp {

	public static void main(String[] args) {
		Model model = new Model();
		Frame frame = new Frame();
		frame.getView().setModel(model);
		Controller controller = new Controller(model, frame);
		frame.setController(controller);
		controller.addObserver(frame);
		
		frame.setSize(950, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
