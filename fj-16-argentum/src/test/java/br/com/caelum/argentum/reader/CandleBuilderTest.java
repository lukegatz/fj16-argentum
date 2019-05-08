package br.com.caelum.argentum.reader;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import br.com.caelum.argentum.Candlestick;

public class CandleBuilderTest {

	@Test(expected=IllegalStateException.class)
	public void geracaoDeCandleDeveTerTodosOsDadosNecessarios() {
		// valores em BigDecimal
		BigDecimal ap = BigDecimal.valueOf(42.567);
		BigDecimal fc = BigDecimal.valueOf(36.789);
		BigDecimal mn = BigDecimal.valueOf(12.314);
		BigDecimal mx = BigDecimal.valueOf(74.817);
		BigDecimal vl = BigDecimal.valueOf(0.0); // sem cálculo
		Calendar data = Calendar.getInstance();

		// teste do Builder (retire qualquer um dos termos para obter a exceção)
		new CandleBuilder().comAbertura(ap)
			.comFechamento(fc).comMinimo(mn).comMaximo(mx).comVolume(vl)
			.geraCandle();
		
		// por exemplo
//		new CandleBuilder()
//		.comFechamento(fc).comMinimo(mn).comMaximo(mx).comVolume(vl)
//		.comData(data).geraCandle();
	}

}
