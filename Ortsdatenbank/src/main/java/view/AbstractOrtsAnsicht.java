package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import model.OrtsListe;

public abstract class AbstractOrtsAnsicht {
    
	private AbstractOrt abstractOrt;
    private OrtsListe ortsListe;
    private OrtsListenAnsicht ortsListenAnsicht;
	
    public abstract void initEingabeFelder();

    final Stage stage = new Stage();

    final Label nameLabel = new Label("Name");
    final TextField nameTextField = new TextField();
    
    final Label anschriftLabel = new Label("Anschrift");
    final TextField anschriftTextField = new TextField();
	
    VBox vBox = new VBox();

    BorderPane border = new BorderPane();

    Button btnAdd = new Button("Hinzufügen");
    Button btnCancel = new Button("Abbrechen");

    HBox hbox = new HBox();

    Scene scene = new Scene(border, 500, 300);

    public void show(Stage owner) {
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(owner);
		stage.setTitle("Orts-Editor");

        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(nameLabel, nameTextField, anschriftLabel, anschriftTextField);
        
		border.setCenter(vBox);
		
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: linear-gradient(#CBED63, #72C227);");        
        hbox.getChildren().addAll(btnAdd, btnCancel);
        
        border.setBottom(hbox);
        
        initEingabeFelder();

        stage.setScene(scene);
        stage.getIcons().add(new Image(OrtMitBesuchsdatumAnsicht.class.getResourceAsStream("icon.png")));
        stage.show();
		
	}
}