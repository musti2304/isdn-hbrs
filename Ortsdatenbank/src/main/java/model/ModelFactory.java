package model;

import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import view.OrtsListenAnsicht;

public class ModelFactory {

	public void create(Stage primaryStage) {
		ApplicationContext context = new FileSystemXmlApplicationContext("springjavapersistence.xml");

		IOrtsListe ortsListe = context.getBean(IOrtsListe.class);
		OrtsListenAnsicht ortsListenAnsicht = new OrtsListenAnsicht(ortsListe);
		ortsListenAnsicht.show(primaryStage);
	}
}
