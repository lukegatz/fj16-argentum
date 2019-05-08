package br.com.caelum.argentum.testes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;
import br.com.caelum.argentum.reader.CandlestickFactory;

public class TestaCandlestickFactory {

	public static void main(String[] args) {
		int incremento = 1;
		Calendar hoje = Calendar.getInstance();
		Negocio negocio1 = new Negocio(BigDecimal.valueOf(40.5), 100, hoje);
		Negocio negocio2 = new Negocio(BigDecimal.valueOf(45), 100, hoje);
		Negocio negocio3 = new Negocio(BigDecimal.valueOf(39.8), 100, hoje);
		Negocio negocio4 = new Negocio(BigDecimal.valueOf(42.3), 100, hoje);

		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, 
				negocio3, negocio4);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);
		System.out.println();
		System.out.println(incremento++ + ")");
		System.out.println(candle);

		// testes
		// apenas um negócio
		Negocio negocio6 = new Negocio(BigDecimal.valueOf(40.5), 100, hoje);
		List <Negocio> negs = Arrays.asList(negocio6);
		Candlestick candle1 = fabrica.constroiCandleParaData(hoje, negs);
		System.out.println();
		System.out.println(incremento++ + ")");
		System.out.println(candle1);

		// sem negócios
		List <Negocio> empty = Arrays.asList();
		Candlestick candle2 = fabrica.constroiCandleParaData(hoje, empty);
		System.out.println();
		System.out.println(incremento++ + ")");
		System.out.println(candle2);

		// em ordem crescente
		Negocio negocio11 = new Negocio(BigDecimal.valueOf(40.5), 100, hoje);
		Negocio negocio12 = new Negocio(BigDecimal.valueOf(42.5), 100, hoje);
		Negocio negocio13 = new Negocio(BigDecimal.valueOf(44.8), 100, hoje);
		Negocio negocio14 = new Negocio(BigDecimal.valueOf(64.312), 100, hoje);

		List<Negocio> negociosCresc = Arrays.asList(negocio11, negocio12, 
				negocio13, negocio14);

		Candlestick candle3 = fabrica.constroiCandleParaData(hoje, negociosCresc);
		System.out.println();
		System.out.println(incremento++ + ")");
		System.out.println(candle3);

		// em ordem decrescente
		Calendar dataNula = null;
		// obs. Negócios com data nula?
		Negocio negocio21 = new Negocio(BigDecimal.valueOf(4), -100, dataNula);
		Negocio negocio22 = new Negocio(BigDecimal.valueOf(3), 100, dataNula);
		Negocio negocio23 = new Negocio(BigDecimal.valueOf(2), 100, dataNula);
		Negocio negocio24 = new Negocio(BigDecimal.valueOf(1), 100, dataNula);

		List<Negocio> negociosDCresc = Arrays.asList(negocio21, negocio22, 
				negocio23, negocio24);

		Candlestick candle4 = fabrica.constroiCandleParaData(hoje, negociosDCresc);
		System.out.println();
		System.out.println(incremento++ + ")");
		System.out.println(candle4);
		System.out.println(candle4.isBaixa());
		
		//System.out.println(candle4.getData());
		
	}

}
