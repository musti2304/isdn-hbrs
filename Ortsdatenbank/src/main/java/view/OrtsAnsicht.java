package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.IAbstractOrt;
import model.IOrtsListe;
import model.hibernate.OrtsListe;

@SuppressWarnings("restriction")
public class OrtsAnsicht extends AbstractOrtsAnsicht {

	private IAbstractOrt abstractOrt;
	private IOrtsListe ortsListe;
	private OrtsListenAnsicht ortsListenAnsicht;

	public OrtsAnsicht(IAbstractOrt abstractOrt, IOrtsListe ortsListe, OrtsListenAnsicht ortsListenAnsicht) {
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
				ortsListenAnsicht.update(); //ortsListe, this
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
				ortsListenAnsicht.update(); //ortsListe, this
				stage.close();
			}
		});

	}
}