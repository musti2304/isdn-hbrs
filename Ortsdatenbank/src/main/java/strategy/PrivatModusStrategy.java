package strategy;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.IAbstractOrt;
import model.IOrtMitBesuchsdatum;

public class PrivatModusStrategy implements Strategy {

	@Override
	public void ausgabeDerOrte(List<IAbstractOrt> listeVonOrten) {

		Date date = new Date().from(Instant.now());

		Calendar calender = Calendar.getInstance();

		calender.setTime(date);
		calender.add(Calendar.MONTH, -2);
		date = calender.getTime();

		for (IAbstractOrt abstractOrt : listeVonOrten) {
			if (abstractOrt instanceof IOrtMitBesuchsdatum) {
				IOrtMitBesuchsdatum ortMitBesuchsdatum = (IOrtMitBesuchsdatum) abstractOrt;
				if (ortMitBesuchsdatum.getDatumDesBesuchs().after(date)) {
					System.out.println(abstractOrt.toString());
				}
			}
		}
	}
}