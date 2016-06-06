package command;

import model.AbstractOrt;
import model.OrtsListe;

public class EditCommand implements AbstractCommand {

	private OrtsListe ortsListe;
	private AbstractOrt abstractOrt;

	public EditCommand() {}
	
	public EditCommand(OrtsListe ortsListe, AbstractOrt abstractOrt) {
		this.ortsListe = ortsListe;
		this.abstractOrt = abstractOrt;
	}
	
	@Override
	public void execute() {
		// TODO
	}

	@Override
	public void undo() {
		// TODO
	}

}
