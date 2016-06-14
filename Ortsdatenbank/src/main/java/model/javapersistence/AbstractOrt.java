package model.javapersistence;

import java.io.Serializable;

import javax.persistence.Column;

import model.IAbstractOrt;

public abstract class AbstractOrt implements IAbstractOrt, Serializable {

	private static final long serialVersionUID = 7367028818548018245L;

	private String name;
	private String anschrift;

	public AbstractOrt() {
	}

	public AbstractOrt(String name, String anschrift) {
		this.name = name;
		this.anschrift = anschrift;
	}

    @Column(name="ORTS_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    @Column(name="ORTS_ANSCHRIFT")
	public String getAnschrift() {
		return anschrift;
	}

	public void setAnschrift(String anschrift) {
		this.anschrift = anschrift;
	}

	@Override
	public String toString() {
		return name + ", " + anschrift;
	}
}