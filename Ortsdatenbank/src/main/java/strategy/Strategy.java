package strategy;

import java.util.List;

import model.javapersistence.AbstractOrt;

public interface Strategy {

	void ausgabeDerOrte(List<AbstractOrt> listeVonOrten);

}
