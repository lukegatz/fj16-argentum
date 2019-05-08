package br.com.caelum.argentum;

import java.math.BigDecimal;
import java.util.Calendar;

public final class Negocio {
	private final BigDecimal preco;
	private final int quantidade;
	private final Calendar data;

	public Negocio(BigDecimal preco, int quantidade, Calendar data) {
		if(data == null) {
			throw new IllegalArgumentException("Data não pode ser nula!");
		}
		if(quantidade <= 0) {
			throw new 
				IllegalArgumentException("Quantidade não pode ser negativa ou nula!");
		}
		this.preco = preco.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.quantidade = quantidade;
		this.data = data;
	}

	// volume de dinheiro transferido naquela negociação
	public BigDecimal getVolume() {
		return preco.multiply(BigDecimal.valueOf(quantidade));
	}

	// getters
	public BigDecimal getPreco() {
		return preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public Calendar getData() {
		return (Calendar)this.data.clone();
	}

}
