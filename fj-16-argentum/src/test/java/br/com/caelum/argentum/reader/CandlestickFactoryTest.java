package br.com.caelum.argentum.reader;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactoryTest {

	Calendar hoje = Calendar.getInstance();
	Negocio negocio1;
	Negocio negocio2;
	Negocio negocio3;
	Negocio negocio4;
	List<Negocio> negocios;
	CandlestickFactory fabrica = new CandlestickFactory();
	Candlestick candle;

	@Test
	public void sequenciaSimplesDeNegocios() {
	
		negocio1 = new Negocio(BigDecimal.valueOf(40.5), 100, hoje);
		negocio2 = new Negocio(BigDecimal.valueOf(45), 100, hoje);
		negocio3 = new Negocio(BigDecimal.valueOf(39.8), 100, hoje);
		negocio4 = new Negocio(BigDecimal.valueOf(42.3), 100, hoje);

		negocios = 
				Arrays.asList(negocio1,negocio2,negocio3,negocio4);
	
		candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		assertEquals(40.5, candle.getAbertura().doubleValue(), 0.001);
		assertEquals(42.3, candle.getFechamento().doubleValue(), 0.001);
		assertEquals(39.8, candle.getMinimo().doubleValue(), 0.001);
		assertEquals(45.0, candle.getMaximo().doubleValue(), 0.001);
		assertEquals(16760.0, candle.getVolume().doubleValue(), 0.001);	
	}

	@Test
	public void semNegociosGeraCandleComZeros() {
		
		negocios = Arrays.asList();
		
		candle = fabrica.constroiCandleParaData(hoje, negocios);

		assertEquals(0, candle.getAbertura().doubleValue(), 0.001);
		assertEquals(0, candle.getFechamento().doubleValue(), 0.001);
		assertEquals(0, candle.getMinimo().doubleValue(), 0.001);
		assertEquals(0, candle.getMaximo().doubleValue(), 0.001);
		assertEquals(0, candle.getVolume().doubleValue(), 0.001);
	}
	
	@Test
	public void apenasUmNegocioGeraCandleComValoresIguais() {
		
		negocio1 = new Negocio(BigDecimal.valueOf(60.5), 100, hoje);
		negocios = Arrays.asList(negocio1);
		candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		assertEquals(60.5, candle.getAbertura().doubleValue(), 0.001);
		assertEquals(60.5, candle.getFechamento().doubleValue(), 0.001);
		assertEquals(60.5, candle.getMinimo().doubleValue(), 0.001);
		assertEquals(60.5, candle.getMaximo().doubleValue(), 0.001);
		assertEquals(6050, candle.getVolume().doubleValue(), 0.001);
	}
	
	@Test
	public void negociosEmOrdemCrescenteDeValor() {
		negocio1 = new Negocio(BigDecimal.valueOf(40.5), 100, hoje);
		negocio2 = new Negocio(BigDecimal.valueOf(42.5), 100, hoje);
		negocio3 = new Negocio(BigDecimal.valueOf(44.5), 100, hoje);
		negocio4 = new Negocio(BigDecimal.valueOf(46.5), 100, hoje);

		negocios = 
				Arrays.asList(negocio1,negocio2,negocio3,negocio4);
	
		candle = fabrica.constroiCandleParaData(hoje, negocios);
		
		assertEquals(40.5, candle.getAbertura().doubleValue(), 0.001);
		assertEquals(46.5, candle.getFechamento().doubleValue(), 0.001);
		assertEquals(40.5, candle.getMinimo().doubleValue(), 0.001);
		assertEquals(46.5, candle.getMaximo().doubleValue(), 0.001);
		assertEquals(17400.0, candle.getVolume().doubleValue(), 0.001);
	}
}
