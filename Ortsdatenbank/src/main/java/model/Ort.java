package model;

import java.io.Serializable;

public class Ort implements Serializable {

    private static final long serialVersionUID = 7367028818548018245L;

    private String name;
    private String anschrift;
    
    public Ort ()  {}

    public Ort(String name, String anschrift) {
        this.name = name;
        this.anschrift = anschrift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
