package br.com.caelum.argentum.testes;

import java.math.BigDecimal;

public class TesteComBigDecimal {

	public static void main(String[] args) {
		BigDecimal bd = new BigDecimal(1.51834800076543210987654321);
		String s = bd.toString();
		// toString
		System.out.println(s);
		// toPlain
		String p = bd.toPlainString();
		System.out.println(p);
		// to Engineering
		String x = bd.toEngineeringString();
		System.out.println(x);
		// Arredondar
		bd = bd.setScale(4, BigDecimal.ROUND_HALF_EVEN);
		System.out.println(bd);
	}

}
