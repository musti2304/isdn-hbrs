package command;

import model.AbstractOrt;

public interface AbstractCommand {
	public void execute(AbstractOrt abstractOrt);
	public void undo();
}
