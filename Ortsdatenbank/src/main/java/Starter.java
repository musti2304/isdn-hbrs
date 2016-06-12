import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelFactory;

@SuppressWarnings("restriction")
public class Starter extends Application {

	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ModelFactory modelFactory = new ModelFactory();
		modelFactory.create(primaryStage);
	}
}