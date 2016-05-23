package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class OrtsListe extends Observable implements Serializable {

    private static final long serialVersionUID = 4573262376065633086L;
    private List<AbstractOrt> listeVonOrten;

    public OrtsListe() { 	
    	listeVonOrten = new ArrayList<AbstractOrt>();
    	listeVonOrten.add(new Ort("Campus St. Augustin Hochschule Bonn-Rhein-Sieg", "Grantham-Allee 20, 53757 Sankt Augustin"));
        listeVonOrten.add(new Ort("Campus Rheinbach Hochschule Bonn-Rhein-Sieg", "von-Liebig-Straﬂe 20, 53359 Rheinbach"));
        listeVonOrten.add(new Ort("Campus Hennef Hochschule Bonn-Rhein-Sieg", "Zum Steimelsberg 7, 53773 Hennef"));
    }
    
    public List<AbstractOrt> getListeVonOrten() {
    	return listeVonOrten;
    }
    
    public boolean addOrt(AbstractOrt abstractOrt) {
    	notifyObservers(listeVonOrten);
    	return listeVonOrten.add(abstractOrt);
    }
    
    public boolean removeOrt(AbstractOrt abstractOrt) {
    	notifyObservers(listeVonOrten);
    	return listeVonOrten.remove(abstractOrt);
    }
}
