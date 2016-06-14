package model.hibernate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import model.IAbstractOrt;
import model.IOrtsListe;
import model.ModelFactory;
import view.OrtsListenAnsicht;

public class OrtsListe extends Observable implements IOrtsListe, Serializable {

	
	private List<IAbstractOrt> listeVonOrten = new ArrayList<IAbstractOrt>();
	OrtsListenAnsicht ortsListenAnsicht = new OrtsListenAnsicht();
	private static final long serialVersionUID = 4573262376065633086L;
	private static IOrtsListe instance = new OrtsListe();

	
	private OrtsListe() {
		// Uncomment below to test the Save/Load feature
//		listeVonOrten = new ArrayList<IAbstractOrt>();
//		listeVonOrten.add(new Ort("HBRS Sankt Augustin", "Grantham-Allee 20, 53757 Sankt Augustin"));
//		listeVonOrten.add(new Ort("HBRS Rheinbach", "Von-Liebig-Straße 20, 53359 Rheinbach"));
//		listeVonOrten.add(new Ort("HBRS Hennef", "Zum Steimelsberg 7, 53773 Hennef"));
//		listeVonOrten.add(new OrtMitBesuchsdatum("CI Mobile Minds GmbH", "Marie-Curie-Straße 10, 51103 Köln",
//				new Date().from(Instant.now())));
	}

	public List<IAbstractOrt> getListeVonOrten() {
		return listeVonOrten;
	}

	public static IOrtsListe getInstance() {
		return instance;
	}

	@Override
	public boolean addOrt(IAbstractOrt abstractOrt) {
		notifyObservers(listeVonOrten);
		return listeVonOrten.add(abstractOrt);
	}
	
	@Override
	public boolean removeOrt(IAbstractOrt abstractOrt) {
		notifyObservers(listeVonOrten);
		return listeVonOrten.remove(abstractOrt);
	}

	public void load() {
		listeVonOrten = new ModelFactory(new FileSystemXmlApplicationContext("springhibernate.xml")).getOrtsDAO().getOrte();
		ortsListenAnsicht.update();
	}

	public void save() {
		ModelFactory modelFactory = new ModelFactory(new FileSystemXmlApplicationContext("springhibernate.xml"));
		for(IAbstractOrt abstractOrt : listeVonOrten) {
			modelFactory.getOrtsDAO().saveOrt((IAbstractOrt) abstractOrt); 
		}
	}
}