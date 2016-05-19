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

public abstract class AbstractOrtsAnsicht {
    
//	// References 
//	private Ort ort;
//    private OrtsListe ortsListe;
//    private OrtsListenAnsicht ortsListenAnsicht;
	
    
	public void show(Stage owner) {
		final Stage stage = new Stage();

		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(owner);

//		stage.setTitle("Ort mit Besuchsdatum - Editor");

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 12, 15, 12));
        vBox.setSpacing(10);

		BorderPane border = new BorderPane();
		
		border.setCenter(vBox);
		
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: linear-gradient(#CBED63, #72C227);");
        
        border.setBottom(hbox);

        // Die Stage: Erh‰lt eine Scene, diese eine BorderPane. Auﬂerdem ein Icon
        Scene scene = new Scene(border, 300, 195);
        stage.setScene(scene);
        stage.getIcons().add(new Image(OrtMitBesuchsdatumAnsicht.class.getResourceAsStream("icon.png")));
        stage.show();
		
	}
    
}