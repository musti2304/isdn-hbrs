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
import model.Ort;
import model.OrtMitBesuchsdatum;
import model.OrtsListe;

public class OrtMitBesuchsdatumAnsicht extends AbstractOrtsAnsicht {

	/**
	 * TODO gemeinsames verhalten in oberklasse auskapseln
	 */

	private Ort ort;
	private OrtsListe ortsListe;
	private OrtsListenAnsicht ortsListenAnsicht;
	private OrtMitBesuchsdatum ortMitBesuchsdatum;

	public OrtMitBesuchsdatumAnsicht(OrtMitBesuchsdatum ortMitBesuchsdatum, OrtsListe ortsListe,
			OrtsListenAnsicht ortsListenAnsicht) {
		this.ortsListe = ortsListe;
		this.ortsListenAnsicht = ortsListenAnsicht;
		this.ortMitBesuchsdatum = ortMitBesuchsdatum;
	}

	public void show(Stage owner) {
		final Stage stage = new Stage();

		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(owner);

		stage.setTitle("Ort mit Datum - Editor");

		BorderPane border = new BorderPane();

		final Label nameLabel = new Label("Name");
		final TextField nameTextField = new TextField();

		final Label anschriftLabel = new Label("Anschrift");
		final TextField anschriftTextField = new TextField();

		final Label besuchsdatumLabel = new Label("Letztes Besuchsdatum");
		final DatePicker besuchsdatumDatePicker = new DatePicker();

		VBox vBox = new VBox();
		vBox.setPadding(new Insets(15, 12, 15, 12));
		vBox.setSpacing(10);

		// Die VBox: Komponente, die zwei Labels und zwei TextFields als Leafs
		// enthält.
		vBox.getChildren().addAll(nameLabel, nameTextField, anschriftLabel, anschriftTextField, besuchsdatumLabel,
				besuchsdatumDatePicker);

		border.setCenter(vBox);

		Button btnAdd = new Button("Hinzufügen");
		Button btnCancel = new Button("Abbrechen");

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: linear-gradient(#CBED63, #72C227);");

		// Die HBox: Komponente, die zwei Buttons als Leafs enthält.
		hbox.getChildren().addAll(btnAdd, btnCancel);

		btnAdd.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ortMitBesuchsdatum.setName(nameTextField.getText());
				ortMitBesuchsdatum.setAnschrift(anschriftTextField.getText());
				ortMitBesuchsdatum.setDatumDesBesuchs(
						Date.from(besuchsdatumDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				ortsListe.addOrt(ortMitBesuchsdatum);
				ortsListenAnsicht.update(ortsListe, this);
				stage.close();
			}
		});

		btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				ort = null;
				stage.close();
			}
		});

		border.setBottom(hbox);

		// Die Stage: Erhält eine Scene, diese eine BorderPane. Außerdem ein
		// Icon
		Scene scene = new Scene(border, 420, 260);
		stage.setScene(scene);
		stage.getIcons().add(new Image(OrtsAnsicht.class.getResourceAsStream("icon.png")));
		stage.show();
	}

	/*
	 * public void initEingabeFelder() {
	 * 
	 * VBox inputFields = new VBox();
	 * 
	 * final Label nameLabel = new Label("Name"); final TextField nameTextField
	 * = new TextField();
	 * 
	 * final Label anschriftLabel = new Label("Anschrift"); final TextField
	 * anschriftTextField = new TextField();
	 * 
	 * final Label besuchsdatumLabel = new Label("Letztes Besuchsdatum"); final
	 * DatePicker besuchsdatumDatePicker = new DatePicker();
	 * 
	 * inputFields.getChildren().addAll(besuchsdatumLabel,
	 * besuchsdatumDatePicker);
	 * 
	 * btnAdd.setOnAction(new EventHandler<ActionEvent>() { public void
	 * handle(ActionEvent e) {
	 * ortMitBesuchsdatum.setName(nameTextField.getText());
	 * ortMitBesuchsdatum.setAnschrift(anschriftTextField.getText());
	 * ortMitBesuchsdatum.setDatumDesBesuchs(
	 * Date.from(besuchsdatumDatePicker.getValue()
	 * .atStartOfDay(ZoneId.systemDefault()).toInstant()));
	 * ortsListe.addOrt(ortMitBesuchsdatum); getStage().close(); } });
	 * 
	 * }
	 */

}