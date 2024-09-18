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

class LinhaDTOTest implements Serializable {
	private static final long serialVersionUID = -2313058965006042965L;
	private LinhaDTO linhaDTO1;
	private LinhaDTO linhaDTO2;
	private LinhaDTO linhaDTO3;
	
	/**
	 * Método responsável pela inicialização dos atributos.
	 */
	@BeforeEach
	void setUp() {
		linhaDTO1 = new LinhaDTO();
		linhaDTO1.setId(UUID.randomUUID());
		linhaDTO1.setNumeroLinha("1");
		linhaDTO1.setNome("Linha 1 Azul");
		linhaDTO1.setNomeIngles("Line 1 Blue");
		linhaDTO1.setCor("Azul");
		linhaDTO1.setCorHexadecimal("#0000FF");
		
		linhaDTO2 = new LinhaDTO();
		linhaDTO2.setId(UUID.randomUUID());
		linhaDTO2.setNumeroLinha("1");
		linhaDTO2.setNome("Linha 1 Azul");
		linhaDTO2.setNomeIngles("Line 1 Blue");
		linhaDTO2.setCor("Azul");
		linhaDTO2.setCorHexadecimal("#0000FF");
		
		linhaDTO3 = new LinhaDTO();
		linhaDTO3.setId(UUID.randomUUID());
		linhaDTO3.setNumeroLinha("2");
		linhaDTO3.setNome("Linha 2 Verde");
		linhaDTO3.setNomeIngles("Line 2 Green");
		linhaDTO3.setCor("Green");
		linhaDTO3.setCorHexadecimal("#008000");
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaDTO#equals(Object object)}.
	 */
	@Test
	void testEquals() {
		assertEquals(linhaDTO1, linhaDTO2);
        assertNotEquals(linhaDTO1, linhaDTO3);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaDTO#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertEquals(linhaDTO1.hashCode(), linhaDTO2.hashCode());
        assertNotEquals(linhaDTO1.hashCode(), linhaDTO3.hashCode());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaDTO#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals(linhaDTO1.toString(), linhaDTO2.toString());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaDTO#compare(LinhaDTO linhaDTO1, LinhaDTO linhaDTO2)}.
	 */
	@Test
    void testCompare() {
		assertTrue(new LinhaDTO().compare(linhaDTO1, linhaDTO3) < 0);
	}
	
}