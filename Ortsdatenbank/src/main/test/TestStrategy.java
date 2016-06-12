import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.hibernate.AbstractOrt;
import model.hibernate.Ort;
import model.hibernate.OrtMitBesuchsdatum;
import model.hibernate.OrtsListe;
import strategy.OeffentlicherModusStrategy;
import strategy.PrivatModusStrategy;
import strategy.Strategy;

public class TestStrategy {

	private Strategy strategy;
	private List<AbstractOrt> listeVonOrten;
	private OrtsListe ortsListe;
	Strategy publicStrategy = new OeffentlicherModusStrategy();
	Strategy privateStrategy = new PrivatModusStrategy();
	
	@Before
	public void setUp() {
		listeVonOrten = new ArrayList<AbstractOrt>();
		ortsListe = (OrtsListe) OrtsListe.getInstance();
		initPlaces();	
	}
	
	public void initPlaces() {
		listeVonOrten.add(new Ort("HBRS Sankt Augustin", "Grantham-Allee 20, 53757 Sankt Augustin"));
		listeVonOrten.add(new Ort("HBRS Rheinbach", "Von-Liebig-Straﬂe 20, 53359 Rheinbach"));
		listeVonOrten.add(new Ort("HBRS Hennef", "Zum Steimelsberg 7, 53773 Hennef"));
		listeVonOrten.add(new OrtMitBesuchsdatum("CI Mobile Minds GmbH", "Marie-Curie-Straﬂe 10, 51103 Kˆln", new Date().from(Instant.now())));
		listeVonOrten.add(new OrtMitBesuchsdatum("Testort", "Teststraﬂe 99", new Date(2016, 02, 20)));	
	}

	@Test
	public void testCountOfAllPlaces() {
		Assert.assertThat(listeVonOrten.size(), CoreMatchers.is(5));
	}
	
	@Test
	public void showPublicPlaces() {
		// TODO
	}
	
	@Test
	public void showPrivatePlaces() {
		// TODO
	}

}