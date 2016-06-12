package strategy;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.javapersistence.AbstractOrt;
import model.javapersistence.OrtMitBesuchsdatum;

public class PrivatModusStrategy implements Strategy {

	@Override
	public void ausgabeDerOrte(List<AbstractOrt> listeVonOrten) {

		Date date = new Date().from(Instant.now());

		Calendar calender = Calendar.getInstance();

		calender.setTime(date);
		calender.add(Calendar.MONTH, -2);
		date = calender.getTime();

		for (AbstractOrt abstractOrt : listeVonOrten) {
			if (abstractOrt instanceof OrtMitBesuchsdatum) {
				OrtMitBesuchsdatum ortMitBesuchsdatum = (OrtMitBesuchsdatum) abstractOrt;
				if (ortMitBesuchsdatum.getDatumDesBesuchs().after(date)) {
					System.out.println(abstractOrt.toString());
				}
			}
		}
	}
}