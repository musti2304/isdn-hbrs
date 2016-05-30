package model;

import java.util.List;

public class PrivatModusStrategy implements Strategy {

	@Override
	public void ausgabeDerOrte(List<AbstractOrt> listeVonOrten) {
		// TODO Add correct behavior to show the private places
		System.out.println("Ich bin privat");
	}

}
