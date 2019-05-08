package br.com.caelum.argentum.reader;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.Candlestick;
import br.com.caelum.argentum.Negocio;

public class CandlestickFactory {
	
	public Candlestick constroiCandleParaData(Calendar data, 
			List<Negocio> negocios) {
		
		BigDecimal abertura = BigDecimal.ZERO;
		BigDecimal fechamento = BigDecimal.ZERO;
		BigDecimal maximo = negocios.isEmpty() ? BigDecimal.ZERO :
				negocios.get(0).getPreco();
		BigDecimal minimo = negocios.isEmpty() ? BigDecimal.ZERO :
				negocios.get(0).getPreco();
		BigDecimal volume = BigDecimal.ZERO;
		
		for (Negocio negocio : negocios) {
			volume = volume.add(negocio.getVolume());
			
			if(maximo.compareTo(negocio.getPreco())==-1) {
				maximo = negocio.getPreco();
			} else if(minimo.compareTo(negocio.getPreco())==1) {
				minimo = negocio.getPreco();
			}
				
		}
		
		if(!negocios.isEmpty()) {
			abertura = negocios.get(0).getPreco();
			fechamento = negocios.get(negocios.size()-1).getPreco();
		}
		
		return new CandleBuilder()
				.comAbertura(abertura)
				.comFechamento(fechamento)
				.comMinimo(minimo)
				.comMaximo(maximo)
				.comVolume(volume)
				.comData(data)
				.geraCandle();
	}
}
