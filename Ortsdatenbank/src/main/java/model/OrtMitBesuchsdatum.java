package model;

import java.io.Serializable;
import java.util.Date;

public class OrtMitBesuchsdatum extends AbstractOrt implements Serializable {
	
	private static final long serialVersionUID = 7367028818548018245L;
	
	Date datumDesBesuchs = new Date();
	
	public OrtMitBesuchsdatum() {}
	
	public OrtMitBesuchsdatum(Date datum) {
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
        return getName() + ", " + getAnschrift();
    }

}