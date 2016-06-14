package model.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import model.IAbstractOrt;

@Entity
@DiscriminatorColumn(name="Typ")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class AbstractOrt implements IAbstractOrt, Serializable {

	private static final long serialVersionUID = 7367028818548018245L;
	
	private String name;
	

	private String anschrift;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long id;

	@Column(name="ORT_ID")
	public long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public AbstractOrt() {
	}

	public AbstractOrt(String name, String anschrift) {
		this.name = name;
		this.anschrift = anschrift;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	@Column(name="ANSCHRIFT")	
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