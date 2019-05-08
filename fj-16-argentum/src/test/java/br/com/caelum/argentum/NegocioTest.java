package br.com.caelum.argentum;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;

import junit.framework.Assert;

import org.junit.Test;

public class NegocioTest implements Cloneable {

	@Test
	public void dataDoNegocioEhImutavel() {
		// se criar um negocio no dia 15
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negocio n = 
				new Negocio(BigDecimal.valueOf(40.5), 100, (Calendar) c.clone());
		
		// ainda que eu tente mudar a data para dia 20
		c.set(Calendar.DAY_OF_MONTH, 20);
		
		// ele continua no dia 15.
		assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegocioComDataNula() {
		new Negocio(BigDecimal.valueOf(0),100,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegocioComQuantidadeNegativa() {
		Calendar hoje = Calendar.getInstance();
		new Negocio(BigDecimal.valueOf(0),-1,hoje);
	}

}
