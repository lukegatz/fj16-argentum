package br.com.caelum.argentum;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class Candlestick {
	private final BigDecimal abertura;
	private final BigDecimal fechamento;
	private final BigDecimal minimo;
	private final BigDecimal maximo;
	private final BigDecimal volume;
	private final Calendar data;
	
	public Candlestick(BigDecimal abertura, BigDecimal fechamento, 
			BigDecimal minimo, BigDecimal maximo, BigDecimal volume, 
			Calendar data) {
		if(maximo.compareTo(minimo)==-1) {
			throw new 
				IllegalArgumentException("Valor máximo não pode ser menor " +
						"que o mínimo!!");
		}
		if(data == null) {
			throw new 
				IllegalArgumentException("A data do Candlestick " +
						"não pode ser nula!!");
		}
		if(abertura.compareTo(BigDecimal.ZERO)==-1
				|| fechamento.compareTo(BigDecimal.ZERO)==-1
				|| minimo.compareTo(BigDecimal.ZERO)==-1
				|| maximo.compareTo(BigDecimal.ZERO)==-1
				|| volume.compareTo(BigDecimal.ZERO)==-1) {
			throw new 
				IllegalArgumentException("Valor não pode ser negativo!!");
		}
		this.abertura = abertura.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.fechamento = fechamento.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.minimo = minimo.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.maximo = maximo.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.volume = volume.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.data = data;
	}
	
	// Verifica se o candle está em alta (fechamento > abertura)
	public boolean isAlta() {
		@SuppressWarnings("unused")
		int i;
		return (i = this.abertura.compareTo(fechamento))<=0 ? true : false;
	}
	
	// Verifica se o candle está em alta (fechamento < abertura)
	public boolean isBaixa() {
		@SuppressWarnings("unused")
		int i;
		return (i = this.fechamento.compareTo(abertura))==-1 ? true : false;
	}
	
	/**
	 * Método toString sobrescrito para a classe Candlestick
	 */
	@Override
	public String toString() {
		String saidaCandle;
		saidaCandle = "[Abertura " + abertura + ", Fechamento " + fechamento +
				", Mínimo " + minimo + ", Máximo " + maximo + ", Volume " +
				volume;
		
		// Tratamento da data
		Calendar hoje = data;
		Date datum = hoje.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
		String dataCompleta = sdf.format(datum);
		
		//String formatada completa
		saidaCandle += ", Data " + dataCompleta + "]";
	
		return saidaCandle;
	}
	
	// getters
	public BigDecimal getAbertura() {
		return abertura;
	}

	public BigDecimal getFechamento() {
		return fechamento;
	}

	public BigDecimal getMinimo() {
		return minimo;
	}

	public BigDecimal getMaximo() {
		return maximo;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
	
	
}
