package model;

import java.util.List;
import model.javapersistence.AbstractOrt;
import model.javapersistence.OrtsListe;

public interface IOrtsListe {
	public List<IAbstractOrt> getListeVonOrten();
	public boolean addOrt(IAbstractOrt abstractOrt);
	public void load();
	public boolean removeOrt(IAbstractOrt abstractOrt);

}