package model.hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import model.IOrtMitBesuchsdatum;

@Entity
@DiscriminatorValue("OrtMitBesuchsdatum")
@Table(name="ORT_MIT_DATUM")
public class OrtMitBesuchsdatum extends AbstractOrt implements IOrtMitBesuchsdatum, Serializable {

	private static final long serialVersionUID = 7367028818548018245L;
	
	private Date datumDesBesuchs = new Date();

	
	public OrtMitBesuchsdatum() {
	}

	public OrtMitBesuchsdatum(String name, String anschrift, Date datum) {
		super(name, anschrift);
		this.datumDesBesuchs = datum;
	}

	@Column(name="ORTS_DATUM")
	public Date getDatumDesBesuchs() {
		return datumDesBesuchs;
	}

	public void setDatumDesBesuchs(Date datumDesBesuchs) {
		this.datumDesBesuchs = datumDesBesuchs;
	}

	@Override
	public String toString() {
		return super.getName() + ", " + super.getAnschrift() + ", zuletzt besucht am " + getDatumDesBesuchs();
	}

}