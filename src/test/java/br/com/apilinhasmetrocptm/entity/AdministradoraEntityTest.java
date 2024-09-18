package br.com.apilinhasmetrocptm.entity;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.io.Serializable;
import java.util.UUID;

/**
 * Classe test para implementação dos testes unitários dos métodos equals, hashCode, toString e compare.
 * 
 * @author Felipe Nascimento
 * 
 */

class AdministradoraEntityTest implements Serializable {
	private static final long serialVersionUID = 4810769066606318944L;
	private AdministradoraEntity administradoraEntity1;
	private AdministradoraEntity administradoraEntity2;
	private AdministradoraEntity administradoraEntity3;
	
	/**
	 * Método responsável pela inicialização dos atributos.
	 */
	@BeforeEach
	void setUp() {
		administradoraEntity1 = new AdministradoraEntity();
		administradoraEntity1.setId(UUID.randomUUID());
		administradoraEntity1.setNomeAdministradora("CPTM");
		administradoraEntity1.setLinhas(Collections.singletonList(new LinhaEntity()));
		
		administradoraEntity2 = new AdministradoraEntity();
		administradoraEntity2.setId(UUID.randomUUID());
		administradoraEntity2.setNomeAdministradora("CPTM");
		administradoraEntity2.setLinhas(Collections.singletonList(new LinhaEntity()));
		
		administradoraEntity3 = new AdministradoraEntity();
		administradoraEntity3.setId(UUID.randomUUID());
		administradoraEntity3.setNomeAdministradora("Metrô");
		administradoraEntity3.setLinhas(Collections.singletonList(new LinhaEntity()));
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraEntity#equals(Object object)}.
	 */
	@Test
	void testEquals() {
		assertEquals(administradoraEntity1, administradoraEntity2);
        assertNotEquals(administradoraEntity1, administradoraEntity3);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraEntity#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertEquals(administradoraEntity1.hashCode(), administradoraEntity2.hashCode());
        assertNotEquals(administradoraEntity1.hashCode(), administradoraEntity3.hashCode());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraEntity#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals(administradoraEntity1.toString(), administradoraEntity2.toString());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraEntity#compare(AdministradoraEntity administradoraEntity1, AdministradoraEntity administradoraEntity2)}.
	 */
	@Test
    void testCompare() {
		assertTrue(new AdministradoraEntity().compare(administradoraEntity1, administradoraEntity3) < 0);
	}
	
}