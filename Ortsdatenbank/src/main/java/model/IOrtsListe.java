package model;

import java.util.List;
import model.javapersistence.AbstractOrt;
import model.javapersistence.OrtsListe;

public interface IOrtsListe {
	public List<AbstractOrt> getListeVonOrten();
	public boolean addOrt(AbstractOrt abstractOrt);
	public void load();
	public boolean removeOrt(AbstractOrt abstractOrt);

}