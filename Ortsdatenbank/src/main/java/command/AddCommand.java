package command;

import model.AbstractOrt;
import model.OrtsListe;

public class AddCommand implements AbstractCommand {
	
	private OrtsListe ortsListe;
	private AbstractOrt abstractOrt;

	public AddCommand() {}
	
	public AddCommand(OrtsListe ortsListe, AbstractOrt abstractOrt) {
		this.ortsListe = ortsListe;
		this.abstractOrt = abstractOrt;
	}
	
	@Override
	public void execute() {
		ortsListe.addOrt(abstractOrt);
	}

	@Override
	public void undo() {
		ortsListe.removeOrt(abstractOrt);
	}

}
