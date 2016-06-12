import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import model.IOrtsListe;
import model.ModelFactory;
import view.OrtsListenAnsicht;

@SuppressWarnings("restriction")
public class Starter extends Application {

	private static OrtsListenAnsicht ortsListenAnsicht;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		ApplicationContext context = new FileSystemXmlApplicationContext("springjavapersistence.xml");
		ortsListenAnsicht = new OrtsListenAnsicht();
		IOrtsListe ortsListe = (IOrtsListe) context.getBean("OrtsListe");
		ortsListenAnsicht.show(primaryStage);
	}
}