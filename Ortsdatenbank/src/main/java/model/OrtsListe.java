package model;

import java.io.Serializable;
import java.util.ArrayList;

public class OrtsListe extends ArrayList<Ort> implements Serializable {

    private static final long serialVersionUID = 4573262376065633086L;

    public OrtsListe() {
        this.add(new Ort("Campus St. Augustin Hochschule Bonn-Rhein-Sieg", "Grantham-Allee 20, 53757 Sankt Augustin"));
        this.add(new Ort("Campus Rheinbach Hochschule Bonn-Rhein-Sieg", "von-Liebig-Straﬂe 20, 53359 Rheinbach"));
        this.add(new Ort("Campus Hennef Hochschule Bonn-Rhein-Sieg", "Zum Steimelsberg 7, 53773 Hennef"));
    }
}
