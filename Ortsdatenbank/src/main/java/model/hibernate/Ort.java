package model.hibernate;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.IOrt;

@Entity
@DiscriminatorValue("Ort")
@Table(name="ORT")
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