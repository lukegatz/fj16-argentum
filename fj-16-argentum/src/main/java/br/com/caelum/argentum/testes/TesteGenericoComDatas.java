package br.com.caelum.argentum.testes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TesteGenericoComDatas {
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		Date datum = c.getTime();
		
		String padrao = "dd/MM/YYYY";
		SimpleDateFormat sdf = new SimpleDateFormat(padrao);
		String data = sdf.format(datum);
		
		System.out.println(data);
	}

}
