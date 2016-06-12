package model;

import java.util.List;

public interface IOrtsListe {
	public List<IAbstractOrt> getListeVonOrten();
	public boolean addOrt(IAbstractOrt abstractOrt);
	public void load();
	public boolean removeOrt(IAbstractOrt abstractOrt);
}