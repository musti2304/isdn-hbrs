package model;

import java.util.List;

public class OeffentlicherModusStrategy implements Strategy {

	@Override
	public void ausgabeDerOrte(List<AbstractOrt> listeVonOrten) {
		// TODO Add correct behavior to show the public places
		System.out.println("Ich bin oeffentlich");
	}

}

