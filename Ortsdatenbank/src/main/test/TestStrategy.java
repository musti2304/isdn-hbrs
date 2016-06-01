import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import model.AbstractOrt;
import model.Ort;
import model.OrtMitBesuchsdatum;
import model.OrtsListe;
import model.Strategy;


public class TestStrategy {
	
	private Strategy strategy;
    private List<AbstractOrt> listeVonOrten;
	
	@Before
	public void setUp() {
    	listeVonOrten = new ArrayList<AbstractOrt>();
    	listeVonOrten.add(new Ort("Campus St. Augustin Hochschule Bonn-Rhein-Sieg", "Grantham-Allee 20, 53757 Sankt Augustin"));
        listeVonOrten.add(new Ort("Campus Rheinbach Hochschule Bonn-Rhein-Sieg", "von-Liebig-Straße 20, 53359 Rheinbach"));
        listeVonOrten.add(new Ort("Campus Hennef Hochschule Bonn-Rhein-Sieg", "Zum Steimelsberg 7, 53773 Hennef"));
        listeVonOrten.add(new OrtMitBesuchsdatum("CI Mobile Minds GmbH", "Marie-Curie-Straße 10, 51103 Köln", new Date().from(Instant.now())));

	}
	
	@Test
	public void testCountOfPlaces() {
		Assert.assertThat(listeVonOrten.size(), CoreMatchers.is(4));
	}

}
