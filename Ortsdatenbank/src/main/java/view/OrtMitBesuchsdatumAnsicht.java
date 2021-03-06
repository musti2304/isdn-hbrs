package view;

import java.time.ZoneId;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import model.IAbstractOrt;
import model.IOrtMitBesuchsdatum;
import model.IOrtsListe;
import model.javapersistence.OrtsListe;

@SuppressWarnings("restriction")
public class OrtMitBesuchsdatumAnsicht extends AbstractOrtsAnsicht {

	private IAbstractOrt abstractOrt;
	private IOrtsListe ortsListe;
	private OrtsListenAnsicht ortsListenAnsicht;
	private IOrtMitBesuchsdatum ortMitBesuchsdatum;

	final Label besuchsDatumLabel = new Label("Letztes Besuchsdatum");
	final DatePicker besuchsdatumDatePicker = new DatePicker();

	public OrtMitBesuchsdatumAnsicht(IAbstractOrt abstractOrt, IOrtsListe ortsListe,
			OrtsListenAnsicht ortsListenAnsicht) {
		this.abstractOrt = abstractOrt;
		this.ortsListe = ortsListe;
		this.ortsListenAnsicht = ortsListenAnsicht;
	}

	@Override
	public void initEingabeFelder() {

		vBox.getChildren().addAll(besuchsDatumLabel, besuchsdatumDatePicker);

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				abstractOrt.setName(nameTextField.getText());
				abstractOrt.setAnschrift(anschriftTextField.getText());
				((IOrtMitBesuchsdatum) abstractOrt).setDatumDesBesuchs(
						Date.from(besuchsdatumDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
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