package view;

import model.Command;
import model.OrtsListe;

public class CommandAnsicht implements Command {
	OrtsListe ort;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		ort.getListeVonOrten();
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		ort.getListeVonOrten();
	}

}
