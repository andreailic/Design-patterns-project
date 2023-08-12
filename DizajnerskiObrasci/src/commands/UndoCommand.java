package commands;

public class UndoCommand implements GenericCommand {

	private GenericCommand command;
	
	public UndoCommand(GenericCommand command) {
		this.command = command;
	}
	
	@Override
	public void forward() {
		command.backward();
	}

	@Override
	public void backward() {
		command.forward();
	}

}
