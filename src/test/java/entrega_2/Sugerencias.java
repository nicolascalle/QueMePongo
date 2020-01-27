package entrega_2;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Period;

import org.joda.time.Days;
import org.junit.Test;

public class Sugerencias {

	@Test
	public void test() {
		LocalDate _fecha =LocalDate.now();
		LocalDate _fecha2 =LocalDate.of(2019, 7 , 11) ;
		assertEquals(5,Period.between(LocalDate.now(),_fecha2).getDays());
	}

}
