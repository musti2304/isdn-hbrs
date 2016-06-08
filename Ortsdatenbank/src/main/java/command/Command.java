package command;

import model.AbstractOrt;

public interface Command {
	public void execute(AbstractOrt abstractOrt);
	public void undo();
}
