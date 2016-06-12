package model.javapersistence;

public class Ort extends AbstractOrt implements Cloneable {

	private static final long serialVersionUID = 7367028818548018245L;

	public Ort() {
	}

	public Ort(String name, String anschrift) {
		super(name, anschrift);
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return super.getName() + ", " + super.getAnschrift();
	}

}
