package command;

import model.IAbstractOrt;

public interface Command {
	public void execute(IAbstractOrt abstractOrt);
	public void undo();
}
