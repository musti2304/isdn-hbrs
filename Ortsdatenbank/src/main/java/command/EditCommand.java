package command;

import model.IAbstractOrt;
import model.IOrtsListe;
import model.hibernate.OrtsListe;

public class EditCommand implements Command {

	private IAbstractOrt abstractOrt;
	private IOrtsListe ortsListe;

	public EditCommand() {}
	
	public EditCommand(IAbstractOrt abstractOrt) {
		this.abstractOrt = abstractOrt;
	}
	
	@Override
	public void execute(IAbstractOrt abstractOrt) {
		OrtsListe.getInstance().addOrt(abstractOrt);
	}

	@Override
	public void undo() {
		OrtsListe.getInstance().getListeVonOrten();
	}

}
