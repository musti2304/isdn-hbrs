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
import model.Ort;
import model.OrtsListe;

public class OrtsAnsicht {

    private Ort ort;
    private OrtsListe ortsListe;
    private OrtsListenAnsicht ortsListenAnsicht;

    public OrtsAnsicht(Ort ort, OrtsListe ortsListe, OrtsListenAnsicht ortsListenAnsicht) {
        this.ort = ort;
        this.ortsListe = ortsListe;
        this.ortsListenAnsicht = ortsListenAnsicht;
    }

    public void show(Stage owner) {
        final Stage stage = new Stage();

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);

        stage.setTitle("Ort von Interesse - Editor");

        BorderPane border = new BorderPane();

        final Label nameLabel = new Label("Name");
        final TextField nameTextField = new TextField();
        
        final Label anschriftLabel = new Label("Anschrift");
        final TextField anschriftTextField = new TextField();
        
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setSpacing(10);
        
        // Die VBox: Komponente, die zwei Labels und zwei TextFields als Leafs enthält.
        vBox.getChildren().addAll(nameLabel, nameTextField, anschriftLabel, anschriftTextField);
        
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
                ort.setName(nameTextField.getText());
                ort.setAnschrift(anschriftTextField.getText());
                ortsListe.addOrt(ort);
                ortsListenAnsicht.updateDisplayedList();
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

        // Die Stage: Erhält eine Scene, diese eine BorderPane. Außerdem ein Icon
        Scene scene = new Scene(border, 300, 195);
        stage.setScene(scene);
        stage.getIcons().add(new Image(OrtsAnsicht.class.getResourceAsStream("icon.png")));
        stage.show();
    }
}