package model.javapersistence;

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

import model.IAbstractOrt;
import model.IOrtsListe;
import view.OrtsListenAnsicht;

public class OrtsListe extends Observable implements IOrtsListe, Serializable {

	private static final long serialVersionUID = 4573262376065633086L;
	OrtsListenAnsicht ortsListenAnsicht = new OrtsListenAnsicht();
	private List<IAbstractOrt> listeVonOrten = new ArrayList<IAbstractOrt>();
	private static IOrtsListe instance = new OrtsListe();

	
	private OrtsListe() {
		// Uncomment below to test the Save/Load feature
//		listeVonOrten = new ArrayList<IAbstractOrt>();
//		listeVonOrten.add(new Ort("HBRS Sankt Augustin", "Grantham-Allee 20, 53757 Sankt Augustin"));
//		listeVonOrten.add(new Ort("HBRS Rheinbach", "Von-Liebig-Stra�e 20, 53359 Rheinbach"));
//		listeVonOrten.add(new Ort("HBRS Hennef", "Zum Steimelsberg 7, 53773 Hennef"));
//		listeVonOrten.add(new OrtMitBesuchsdatum("CI Mobile Minds GmbH", "Marie-Curie-Stra�e 10, 51103 K�ln",
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
		OrtsListe ortsListe = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("myPlaces.txt");
			in = new ObjectInputStream(fis);
			instance = (OrtsListe) in.readObject();
			System.out.println("Data load");
			this.listeVonOrten = instance.getListeVonOrten();
			// instance.notifyObservers(listeVonOrten);
			ortsListenAnsicht.update();// ortsListe, this
			in.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		// instance.notifyObservers(listeVonOrten);

	}

	public void save() {
		FileOutputStream fos;
		ObjectOutputStream out;
		try {
			fos = new FileOutputStream("myPlaces.txt");
			out = new ObjectOutputStream(fos);
			out.writeObject(OrtsListe.getInstance());
			System.out.println("Data saved successfully");
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}