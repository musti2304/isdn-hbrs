import javafx.application.Application;
import javafx.stage.Stage;
import model.javapersistence.OrtsListe;
import view.OrtsListenAnsicht;

@SuppressWarnings("restriction")
public class Starter extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		OrtsListenAnsicht ortsListenAnsicht = new OrtsListenAnsicht(OrtsListe.getInstance());
		ortsListenAnsicht.show(primaryStage);
	}
}