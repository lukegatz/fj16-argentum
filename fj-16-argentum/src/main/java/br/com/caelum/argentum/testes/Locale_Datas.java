package br.com.caelum.argentum.testes;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Locale_Datas{

	public static void main(String[] args) throws ParseException {
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();

		Locale brasil = new Locale("pt", "BR"); //Retorna do país e a língua
		Locale eua = Locale.US;
		Locale italia = Locale.ITALIAN;

		DateFormat f2 = DateFormat.getDateInstance(DateFormat.FULL, brasil);
		System.out.println("Data e hora brasileira: "+f2.format(data));

		DateFormat f3 = DateFormat.getDateInstance(DateFormat.FULL, eua);
		System.out.println("Data e hora americana: "+f3.format(data));

		DateFormat f4 = DateFormat.getDateInstance(DateFormat.FULL, italia);
		System.out.println("Data e hora italiana: "+f4.format(data));

	}
}
