package mvc;

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
	
	
	
}
