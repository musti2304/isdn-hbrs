package command;

import model.AbstractOrt;
import model.OrtsListe;

public class DeleteCommand implements AbstractCommand {

	private OrtsListe ortsListe;
	private AbstractOrt abstractOrt;

	public DeleteCommand() {}
	
	public DeleteCommand(OrtsListe ortsListe, AbstractOrt abstractOrt) {
		this.ortsListe = ortsListe;
		this.abstractOrt = abstractOrt;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
