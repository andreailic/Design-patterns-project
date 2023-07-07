package mvc;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	private View view;
	private Controller controller;

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
}
