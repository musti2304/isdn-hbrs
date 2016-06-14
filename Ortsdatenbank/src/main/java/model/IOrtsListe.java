package model;

import java.util.List;

import model.javapersistence.OrtsListe;

public interface IOrtsListe {
	public List<IAbstractOrt> getListeVonOrten();
	public boolean addOrt(IAbstractOrt abstractOrt);
	public boolean removeOrt(IAbstractOrt abstractOrt);
	public void save();
	public void load();
}