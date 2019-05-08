package br.com.caelum.argentum.reader;

import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.Negocio;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmNegocioEmListaUnitaria() {
		String xmlDeTeste = 
				"<list>" +
				"    <negocio>" +
				"        <preco>43.5</preco>" +
				"        <quantidade>1000</quantidade>" +
				"        <data>" +
				"          <time>1322233344455</time>" +
				"        </data>" +
				"    </negocio>" +
				"</list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = 
				leitor.carrega(new StringReader(xmlDeTeste));
	}
	
	@Test
	public void carregaXmlComZeroNegocioEmListaUnitaria() {
		// qualquer XML será lido
		// esse aqui, contudo, gera erros de validação na classe Negocio
		String xmlDeTeste = 
				"<list>" +
				"    <negocio>" +
				"    </negocio>" +
				"</list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = 
				leitor.carrega(new StringReader(xmlDeTeste));
	}
	
	@Test
	public void confereOsValoresDoXmlNaListaDeNegocios() {
		String xmlDeTeste = 
				"<list>" +
				"    <negocio>" +
				"        <preco>43.5</preco>" +
				"        <quantidade>1000</quantidade>" +
				"        <data>" +
				"          <time>1322233344455</time>" +
				"        </data>" +
				"    </negocio>" +
				"</list>";
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = 
				leitor.carrega(new StringReader(xmlDeTeste));
		assertEquals(43.5, negocios.get(0).getPreco().doubleValue(), 0.001);
		assertEquals(1000, negocios.get(0).getQuantidade());
		assertEquals(1, negocios.size());
	}

}
