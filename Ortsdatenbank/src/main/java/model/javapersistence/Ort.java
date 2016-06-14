package model.javapersistence;

import java.io.Serializable;

import model.IOrt;

public class Ort extends AbstractOrt implements IOrt, Serializable, Cloneable {

	private static final long serialVersionUID = 7367028818548018245L;

	public Ort() {
	}

	public Ort(String name, String anschrift) {
		super(name, anschrift);
	}

	@Override
	public String toString() {
		return super.getName() + ", " + super.getAnschrift();
	}

}
