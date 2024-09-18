package br.com.apilinhasmetrocptm.dto;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.Serializable;
import java.util.UUID;

/**
 * Classe test para implementação dos testes unitários dos métodos equals, hashCode, toString e compare.
 * 
 * @author Felipe Nascimento
 * 
 */

class EstacaoDTOTest implements Serializable {
	private static final long serialVersionUID = 7082594789449494330L;
	private EstacaoDTO estacaoDTO1;
	private EstacaoDTO estacaoDTO2;
	private EstacaoDTO estacaoDTO3;
	
	/**
	 * Método responsável pela inicialização dos atributos.
	 */
	@BeforeEach
	void setUp() {
		estacaoDTO1 = new EstacaoDTO();
		estacaoDTO1.setId(UUID.randomUUID());
		estacaoDTO1.setNomeEstacao("Amador Bueno");
		estacaoDTO1.setCidadeEstacao("Itapevi");
		
		estacaoDTO2 = new EstacaoDTO();
		estacaoDTO2.setId(UUID.randomUUID());
		estacaoDTO2.setNomeEstacao("Amador Bueno");
		estacaoDTO2.setCidadeEstacao("Itapevi");
		
		estacaoDTO3 = new EstacaoDTO();
		estacaoDTO3.setId(UUID.randomUUID());
		estacaoDTO3.setNomeEstacao("Tucuruvi");
		estacaoDTO3.setCidadeEstacao("São Paulo");
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoDTO#equals(Object object)}.
	 */
	@Test
	void testEquals() {
		assertEquals(estacaoDTO1, estacaoDTO2);
        assertNotEquals(estacaoDTO1, estacaoDTO3);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoDTO#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertEquals(estacaoDTO1.hashCode(), estacaoDTO2.hashCode());
        assertNotEquals(estacaoDTO1.hashCode(), estacaoDTO3.hashCode());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoDTO#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals(estacaoDTO1.toString(), estacaoDTO2.toString());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoDTO#compare(EstacaoDTO estacaoDTO1, EstacaoDTO estacaoDTO2)}.
	 */
	@Test
    void testCompare() {
		assertTrue(new EstacaoDTO().compare(estacaoDTO1, estacaoDTO3) < 0);
	}
	
}