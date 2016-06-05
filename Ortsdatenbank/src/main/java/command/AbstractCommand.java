package command;

public interface AbstractCommand {
	public void execute();
	public void undo();
}
