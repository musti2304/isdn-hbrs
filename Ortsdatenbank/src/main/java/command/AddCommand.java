package command;

import model.AbstractOrt;
import model.OrtsListe;

public class AddCommand implements Command {
	
	// Solution 1
	private AbstractOrt abstractOrt;
	private int index;

	public AddCommand() {}
	
	public AddCommand(OrtsListe ortsListe, AbstractOrt abstractOrt) {
//		this.ortsListe = ortsListe;
		this.abstractOrt = abstractOrt;
	}
	
	@Override
	public void execute(AbstractOrt abstractOrt) {
		OrtsListe.getInstance().addOrt(abstractOrt);
		index = OrtsListe.getInstance().getListeVonOrten().indexOf(abstractOrt);
		OrtsListe.getInstance().notifyObservers(/*OrtsListe.getInstance().getListeVonOrten()*/);
	}

	@Override
	public void undo() {
		(OrtsListe.getInstance()).removeOrt(OrtsListe.getInstance().getListeVonOrten().remove(index));
		
	}
	
	
/*	
  	// Solution 2
	private OrtsListe ortsListe;
	private AbstractOrt abstractOrt;
	private int index;

	public AddCommand() {}
	
	public AddCommand(OrtsListe ortsListe, AbstractOrt abstractOrt) {
		this.ortsListe = ortsListe;
		this.abstractOrt = abstractOrt;
	}
	
	@Override
	public void execute(AbstractOrt abstractOrt) {
		ortsListe.addOrt(abstractOrt);
		index = ortsListe.getListeVonOrten().indexOf(abstractOrt);
		ortsListe.notifyObservers(OrtsListe.getInstance().getListeVonOrten());
	}

	@Override
	public void undo() {
		ortsListe.removeOrt(ortsListe.getListeVonOrten().remove(index));
		
	}
	
*/

}