package command;

import model.IAbstractOrt;
import model.IOrtsListe;

public class DeleteCommand implements Command {

	private IOrtsListe ortsListe;
	private IAbstractOrt abstractOrt;

	public DeleteCommand() {}
	
	public DeleteCommand(IOrtsListe ortsListe, IAbstractOrt abstractOrt) {
		this.ortsListe = ortsListe;
		this.abstractOrt = abstractOrt;
	}
	
	@Override
	public void execute(IAbstractOrt abstractOrt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

}
