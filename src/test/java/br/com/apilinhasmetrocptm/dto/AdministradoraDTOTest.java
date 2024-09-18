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

class AdministradoraDTOTest implements Serializable {
	private static final long serialVersionUID = 7237275855120750110L;
	private AdministradoraDTO administradoraDTO1;
	private AdministradoraDTO administradoraDTO2;
	private AdministradoraDTO administradoraDTO3;
	
	/**
	 * Método responsável pela inicialização dos atributos.
	 */
	@BeforeEach
	void setUp() {
		administradoraDTO1 = new AdministradoraDTO();
		administradoraDTO1.setId(UUID.randomUUID());
		administradoraDTO1.setNomeAdministradora("CPTM");
		
		administradoraDTO2 = new AdministradoraDTO();
		administradoraDTO2.setId(UUID.randomUUID());
		administradoraDTO2.setNomeAdministradora("CPTM");
		
		administradoraDTO3 = new AdministradoraDTO();
		administradoraDTO3.setId(UUID.randomUUID());
		administradoraDTO3.setNomeAdministradora("Metrô");
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraDTO#equals(Object object)}.
	 */
	@Test
	void testEquals() {
		assertEquals(administradoraDTO1, administradoraDTO2);
        assertNotEquals(administradoraDTO1, administradoraDTO3);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraDTO#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertEquals(administradoraDTO1.hashCode(), administradoraDTO2.hashCode());
        assertNotEquals(administradoraDTO1.hashCode(), administradoraDTO3.hashCode());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraDTO#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals(administradoraDTO1.toString(), administradoraDTO2.toString());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraDTO#compare(AdministradoraDTO administradoraDTO1, AdministradoraDTO administradoraDTO2)}.
	 */
	@Test
    void testCompare() {
		assertTrue(new AdministradoraDTO().compare(administradoraDTO1, administradoraDTO3) < 0);
	}
	
}