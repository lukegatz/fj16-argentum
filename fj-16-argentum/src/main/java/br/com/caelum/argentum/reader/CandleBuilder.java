package br.com.caelum.argentum.reader;

import java.math.BigDecimal;
import java.util.Calendar;

import br.com.caelum.argentum.Candlestick;

public class CandleBuilder {
	private BigDecimal abertura;
	private BigDecimal fechamento;
	private BigDecimal minimo;
	private BigDecimal maximo;
	private BigDecimal volume;
	private Calendar data;
	// flags para os setters
	private boolean flagAbertura;
	private boolean flagFechamento;
	private boolean flagMinimo;
	private boolean flagMaximo;
	private boolean flagVolume;
	private boolean flagData;
	
	// setters
	public CandleBuilder comAbertura(BigDecimal abertura) {
		flagAbertura = true;
		this.abertura = abertura.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return this;
	}

	public CandleBuilder comFechamento(BigDecimal fechamento) {
		flagFechamento = true;
		this.fechamento = fechamento.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return this;
	}

	public CandleBuilder comMinimo(BigDecimal minimo) {
		flagMinimo = true;
		this.minimo = minimo.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return this;
	}

	public CandleBuilder comMaximo(BigDecimal maximo) {
		flagMaximo = true;
		this.maximo = maximo.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return this;
	}

	public CandleBuilder comVolume(BigDecimal volume) {
		flagVolume = true;
		this.volume = volume.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return this;
	}

	public CandleBuilder comData(Calendar data) {
		flagData = true;
		this.data = data;
		return this;
	}
	
	// método gerador de Candlestick
	public Candlestick geraCandle() {
		if(flagAbertura==false || flagFechamento==false || flagMinimo==false
				|| flagMaximo==false || flagVolume==false || flagData==false) {
			throw new IllegalStateException("O builder necessita de todos os " +
					"dados para ser executado!!");
		}
		return new Candlestick(abertura, fechamento, minimo, maximo,
				volume, data);
	}

}
