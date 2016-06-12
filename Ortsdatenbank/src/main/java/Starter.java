import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import model.IOrtsListe;
import view.OrtsListenAnsicht;

@SuppressWarnings("restriction")
public class Starter extends Application {

	public static void main(String[] args) {
		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ApplicationContext context = new FileSystemXmlApplicationContext("springjavapersistence.xml");
		
		IOrtsListe ortsListe = context.getBean(IOrtsListe.class);
		OrtsListenAnsicht ortsListenAnsicht = new OrtsListenAnsicht(ortsListe);
		ortsListenAnsicht.show(primaryStage);
	}
}