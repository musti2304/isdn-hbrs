package model;

import java.io.Serializable;

public class Ort extends AbstractOrt implements Serializable {
	
	private static final long serialVersionUID = 7367028818548018245L;

	public Ort() {}

	public Ort(String name, String anschrift) {
		super(name, anschrift);
	}
	
	@Override
    public String toString() {
        return getName() + ", " + getAnschrift();
    }
}
