package command;

import model.javapersistence.AbstractOrt;
import model.javapersistence.OrtsListe;

public class EditCommand implements Command {

	private AbstractOrt abstractOrt;

	public EditCommand() {}
	
	public EditCommand(AbstractOrt abstractOrt) {
		this.abstractOrt = abstractOrt;
	}
	
	@Override
	public void execute(AbstractOrt abstractOrt) {
		OrtsListe.getInstance().addOrt(abstractOrt);
	}

	@Override
	public void undo() {
		OrtsListe.getInstance().getListeVonOrten(); //TODO
	}

}
