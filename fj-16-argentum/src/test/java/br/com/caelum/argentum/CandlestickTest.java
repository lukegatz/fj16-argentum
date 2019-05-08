package br.com.caelum.argentum;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.junit.Test;

import br.com.caelum.argentum.reader.CandlestickFactory;

public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo() {
		BigDecimal abertura = BigDecimal.valueOf(0);
		BigDecimal fechamento = BigDecimal.valueOf(0);
		BigDecimal maximo = BigDecimal.valueOf(0);
		BigDecimal minimo = BigDecimal.valueOf(10);
		BigDecimal volume = BigDecimal.valueOf(0);
		Calendar hoje = Calendar.getInstance();

		new Candlestick(abertura, fechamento, 
				minimo, maximo, volume, hoje);
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoCriaCandleComDataNula() {
		BigDecimal abertura = BigDecimal.valueOf(0);
		BigDecimal fechamento = BigDecimal.valueOf(0);
		BigDecimal maximo = BigDecimal.valueOf(0);
		BigDecimal minimo = BigDecimal.valueOf(0);
		BigDecimal volume = BigDecimal.valueOf(0);
		Calendar hoje = null;

		new Candlestick(abertura, fechamento, 
				minimo, maximo, volume, hoje);
	}

	@Test(expected=IllegalArgumentException.class)
	public void naoCriaCandleComValoresMonetariosNegativos() {
		// qualquer um dos valores (apenas um é o bastante)
		BigDecimal abertura = BigDecimal.valueOf(0);
		BigDecimal fechamento = BigDecimal.valueOf(0);
		BigDecimal maximo = BigDecimal.valueOf(-1);
		BigDecimal minimo = BigDecimal.valueOf(0);
		BigDecimal volume = BigDecimal.valueOf(0);
		Calendar hoje = Calendar.getInstance();

		new Candlestick(abertura, fechamento, 
				minimo, maximo, volume, hoje);
	}

	@Test
	public void negociosEmOrdemCrescente() {
		CandlestickFactory fabrica = new CandlestickFactory();
		Calendar hoje = Calendar.getInstance();
		Negocio negocio1 = new Negocio(BigDecimal.valueOf(20), 100, hoje);
		Negocio negocio2 = new Negocio(BigDecimal.valueOf(30), 100, hoje);
		Negocio negocio3 = new Negocio(BigDecimal.valueOf(40), 100, hoje);
		Negocio negocio4 = new Negocio(BigDecimal.valueOf(50), 100, hoje);

		List<Negocio> negocios = 
				Arrays.asList(negocio1, negocio2, negocio3, negocio4);

		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		assertEquals(20, candle.getAbertura().doubleValue(), 0.001);
		assertEquals(50, candle.getFechamento().doubleValue(), 0.001);
		assertEquals(20, candle.getMinimo().doubleValue(), 0.001);
		assertEquals(50, candle.getMaximo().doubleValue(), 0.001);
		assertEquals(14000, candle.getVolume().doubleValue(), 0.001);
	}

	@Test
	public void negociosEmOrdemDecrescente() {
		CandlestickFactory fabrica = new CandlestickFactory();
		Calendar hoje = Calendar.getInstance();
		Negocio negocio1 = new Negocio(BigDecimal.valueOf(50), 100, hoje);
		Negocio negocio2 = new Negocio(BigDecimal.valueOf(40), 100, hoje);
		Negocio negocio3 = new Negocio(BigDecimal.valueOf(30), 100, hoje);
		Negocio negocio4 = new Negocio(BigDecimal.valueOf(20), 100, hoje);

		List<Negocio> negocios = 
				Arrays.asList(negocio1, negocio2, negocio3, negocio4);

		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		assertEquals(50, candle.getAbertura().doubleValue(), 0.001);
		assertEquals(20, candle.getFechamento().doubleValue(), 0.001);
		assertEquals(20, candle.getMinimo().doubleValue(), 0.001);
		assertEquals(50, candle.getMaximo().doubleValue(), 0.001);
		assertEquals(14000, candle.getVolume().doubleValue(), 0.001);
	}

	@Test
	public void negociosEmGrandeQuantidade() {
		CandlestickFactory fabrica = new CandlestickFactory();
		Calendar hoje = Calendar.getInstance();
		List<Negocio> negocios = new ArrayList<Negocio>();
		// calculado com 10 mil negócios
		for (Integer i=0; i <= 10000; i++) { 
			Negocio neg1 = new Negocio(BigDecimal.valueOf(10 + i), 100, hoje);
			negocios.add(neg1);
		}
		
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		assertEquals(10, candle.getAbertura().doubleValue(), 0.001);
		assertEquals(10010, candle.getFechamento().doubleValue(), 0.001);
		assertEquals(10, candle.getMinimo().doubleValue(), 0.001);
		assertEquals(10010, candle.getMaximo().doubleValue(), 0.001);
		assertEquals(5.010501E9, candle.getVolume().doubleValue(), 0.001);
	}
	
	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {
		CandlestickFactory fabrica = new CandlestickFactory();
		Calendar hoje = Calendar.getInstance();
		Negocio negocio1 = new Negocio(BigDecimal.valueOf(10), 100, hoje);
		Negocio negocio2 = new Negocio(BigDecimal.valueOf(40), 100, hoje);
		Negocio negocio3 = new Negocio(BigDecimal.valueOf(30), 100, hoje);
		Negocio negocio4 = new Negocio(BigDecimal.valueOf(10), 100, hoje);

		List<Negocio> negocios = 
				Arrays.asList(negocio1, negocio2, negocio3, negocio4);

		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		assertEquals(10, candle.getAbertura().doubleValue(), 0.001);
		assertEquals(10, candle.getFechamento().doubleValue(), 0.001);
		assertTrue(candle.isAlta());
	}
	
	@Test
	public void testIsAlta() {
		CandlestickFactory fabrica = new CandlestickFactory();
		Calendar hoje = Calendar.getInstance();
		Negocio negocio1 = new Negocio(BigDecimal.valueOf(10), 100, hoje);
		Negocio negocio2 = new Negocio(BigDecimal.valueOf(40), 100, hoje);
		Negocio negocio3 = new Negocio(BigDecimal.valueOf(30), 100, hoje);
		// qualquer valor maior ou igual ao de abertura (no caso, era 10)
		Negocio negocio4 = new Negocio(BigDecimal.valueOf(10), 100, hoje);

		List<Negocio> negocios = 
				Arrays.asList(negocio1, negocio2, negocio3, negocio4);

		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		assertTrue(candle.isAlta());
	}

	@Test
	public void testIsBaixa() {
		CandlestickFactory fabrica = new CandlestickFactory();
		Calendar hoje = Calendar.getInstance();
		Negocio negocio1 = new Negocio(BigDecimal.valueOf(10), 100, hoje);
		Negocio negocio2 = new Negocio(BigDecimal.valueOf(40), 100, hoje);
		Negocio negocio3 = new Negocio(BigDecimal.valueOf(30), 100, hoje);
		// qualquer valor menor ao de abertura (no caso, era 10)
		Negocio negocio4 = new Negocio(BigDecimal.valueOf(9.5), 100, hoje);

		List<Negocio> negocios = 
				Arrays.asList(negocio1, negocio2, negocio3, negocio4);

		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

		assertTrue(candle.isBaixa());
	}

}
