package commands;

public class RedoCommand implements GenericCommand {

	private GenericCommand command;
	
	public RedoCommand(GenericCommand command) {
		this.command = command;
	}
	
	@Override
	public void forward() {
		command.forward();
	}

	@Override
	public void backward() {
		command.backward();
	}

}