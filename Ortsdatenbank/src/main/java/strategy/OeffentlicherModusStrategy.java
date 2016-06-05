package strategy;

import java.util.List;

import model.AbstractOrt;

public class OeffentlicherModusStrategy implements Strategy {

	@Override
	public void ausgabeDerOrte(List<AbstractOrt> listeVonOrten) {
		for (AbstractOrt abstractOrt : listeVonOrten) {
			System.out.println(abstractOrt.toString());
		}
	}

}
