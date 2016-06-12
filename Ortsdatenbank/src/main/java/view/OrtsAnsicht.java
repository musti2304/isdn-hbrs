package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.javapersistence.AbstractOrt;
import model.javapersistence.OrtsListe;

@SuppressWarnings("restriction")
public class OrtsAnsicht extends AbstractOrtsAnsicht {

	private AbstractOrt abstractOrt;
	private OrtsListe ortsListe;
	private OrtsListenAnsicht ortsListenAnsicht;

	public OrtsAnsicht(AbstractOrt abstractOrt, OrtsListe ortsListe, OrtsListenAnsicht ortsListenAnsicht) {
		this.abstractOrt = abstractOrt;
		this.ortsListe = ortsListe;
		this.ortsListenAnsicht = ortsListenAnsicht;
	}

	@Override
	public void initEingabeFelder() {

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				abstractOrt.setName(nameTextField.getText());
				abstractOrt.setAnschrift(anschriftTextField.getText());
				if(!ortsListe.getListeVonOrten().contains(abstractOrt)) {
					ortsListe.addOrt(abstractOrt);					
				}
				OrtsListenAnsicht.update(); //ortsListe, this
				stage.close();
			}
		});

		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				abstractOrt = null;
				stage.close();
			}
		});

		btnDel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ortsListe.removeOrt(abstractOrt);
				OrtsListenAnsicht.update(); //ortsListe, this
				stage.close();
			}
		});

	}
}