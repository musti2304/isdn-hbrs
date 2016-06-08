package command;

import java.util.Stack;

import model.AbstractOrt;

public class Invoker {

	Command[] commands;
	Command noCommand;
	Stack<Command> undoCommands = new Stack<>();

	public Invoker() {
		commands = new Command[10];
		noCommand = new NoCommand();

		for (int i = 0; i < 10; i++) {
			commands[i] = noCommand;
		}
		undoCommands.push(noCommand);
	}

	public void setCommand(Command command, int position) {
		commands[position] = command;
	}
	
	public void hitButton(AbstractOrt abstractOrt, int position) {
		commands[position].execute(abstractOrt);
		undoCommands.push(commands[position]);
	}
	
	public void hitUndo() {
		Command command = undoCommands.pop();
		command.undo();
	}
}
