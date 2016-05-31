package view;

import java.time.ZoneId;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.AbstractOrt;
import model.Ort;
import model.OrtMitBesuchsdatum;
import model.OrtsListe;

public class OrtMitBesuchsdatumAnsicht extends AbstractOrtsAnsicht {

	private AbstractOrt abstractOrt;
	private OrtsListe ortsListe;
	private OrtsListenAnsicht ortsListenAnsicht;
	private OrtMitBesuchsdatum ortMitBesuchsdatum;
	
	final Label besuchsDatumLabel = new Label("Letztes Besuchsdatum");
	final DatePicker besuchsdatumDatePicker = new DatePicker();

	
	public OrtMitBesuchsdatumAnsicht(AbstractOrt abstractOrt, OrtsListe ortsListe, OrtsListenAnsicht ortsListenAnsicht) {
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
                ((OrtMitBesuchsdatum) abstractOrt).setDatumDesBesuchs(Date.from(besuchsdatumDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                ortsListe.addOrt(abstractOrt);
                ortsListenAnsicht.update(ortsListe, this);
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
        			abstractOrt = null;
        			stage.close();
        		}
	});
	}
}