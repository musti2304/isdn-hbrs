package model;

import java.util.List;

import view.OrtsListenAnsicht;

public class OeffentlicherModusStrategy implements Strategy {
	
	
	@Override
	public void ausgabeDerOrte(List<AbstractOrt> listeVonOrten) {
		// TODO Add correct behavior to show the public places
		System.out.println("Ich bin oeffentlich");
		System.out.println(listeVonOrten.toString());		
	}

}

