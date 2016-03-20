import javafx.application.Application;
import javafx.stage.Stage;
import model.OrtsListe;
import view.OrtsListenAnsicht;

public class Starter extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        OrtsListenAnsicht ortsListenAnsicht = new OrtsListenAnsicht(new OrtsListe());
        ortsListenAnsicht.show(primaryStage);
    }
}