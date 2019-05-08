package br.com.caelum.argentum.testes;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.reader.CandleBuilder;

public class TestaCandleBuilder {

	// teste da classe AQUI!!
	public static void main(String[] args) {
		// valores em BigDecimal
		BigDecimal ap = BigDecimal.valueOf(42.567);
		BigDecimal fc = BigDecimal.valueOf(36.789);
		BigDecimal mn = BigDecimal.valueOf(12.314);
		BigDecimal mx = BigDecimal.valueOf(74.817);
		BigDecimal vl = BigDecimal.valueOf(0.0); // sem cálculo
		Calendar data = Calendar.getInstance();

		// teste do Builder
		Candlestick candle = new CandleBuilder().comAbertura(ap).
				comFechamento(fc).comMinimo(mn).comMaximo(mx).comVolume(vl)
				.comData(data).geraCandle();
		System.out.println(candle);
		
		// outro teste (para testar a exceção personalizada)
		// o método só funciona se os outros seis tiverem sido invocados antes!
		Candlestick candle1 = new CandleBuilder().comAbertura(ap).
				comFechamento(fc).comMinimo(mn).comMaximo(mx).comVolume(vl)
				.comData(data).geraCandle();
	}

}
