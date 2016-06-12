package command;

import model.javapersistence.AbstractOrt;
import model.javapersistence.OrtsListe;

public class DeleteCommand implements Command {

	private OrtsListe ortsListe;
	private AbstractOrt abstractOrt;

	public DeleteCommand() {}
	
	public DeleteCommand(OrtsListe ortsListe, AbstractOrt abstractOrt) {
		this.ortsListe = ortsListe;
		this.abstractOrt = abstractOrt;
	}
	
	@Override
	public void execute(AbstractOrt abstractOrt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
