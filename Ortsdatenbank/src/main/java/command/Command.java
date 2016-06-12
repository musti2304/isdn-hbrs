package command;

import model.javapersistence.AbstractOrt;

public interface Command {
	public void execute(AbstractOrt abstractOrt);
	public void undo();
}
