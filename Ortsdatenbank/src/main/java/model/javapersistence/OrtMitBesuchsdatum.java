package model.javapersistence;

import java.util.Date;

public class OrtMitBesuchsdatum extends AbstractOrt {

	private static final long serialVersionUID = 7367028818548018245L;

	private Date datumDesBesuchs = new Date();

	public OrtMitBesuchsdatum() {
	}

	public OrtMitBesuchsdatum(String name, String anschrift, Date datum) {
		super(name, anschrift);
		this.datumDesBesuchs = datum;
	}

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