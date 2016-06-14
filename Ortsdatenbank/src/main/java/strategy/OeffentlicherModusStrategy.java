package strategy;

import java.util.List;

import model.IAbstractOrt;

public class OeffentlicherModusStrategy implements Strategy {

	@Override
	public void ausgabeDerOrte(List<IAbstractOrt> listeVonOrten) {
		for (IAbstractOrt abstractOrt : listeVonOrten) {
			System.out.println(abstractOrt.toString());
		}
	}

}
