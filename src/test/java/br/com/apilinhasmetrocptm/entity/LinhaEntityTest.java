package br.com.apilinhasmetrocptm.entity;

import br.com.apilinhasmetrocptm.component.AdministradoraComponent;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.io.Serializable;
import java.util.Optional;
import org.mockito.Mock;
import java.util.UUID;

/**
 * Classe test para implementação dos testes unitários dos métodos equals, hashCode, toString e compare.
 * 
 * @author Felipe Nascimento
 * 
 */

class LinhaEntityTest implements Serializable {
	private static final long serialVersionUID = 5409581686438536057L;
	
	@Mock
	private AdministradoraComponent administradoraComponent;
	
	private LinhaEntity linhaEntity1;
	private LinhaEntity linhaEntity2;
	private LinhaEntity linhaEntity3;
	
	/**
	 * Método responsável pela inicialização dos atributos e do mock.
	 */
	@BeforeEach
	void setUp() {
		String nomeAdministradora = "nomeAdministradora";
		
		Optional<AdministradoraEntity> optionalAdminEntity = administradoraComponent.findByNomeAdministradora(nomeAdministradora);
		
		linhaEntity1 = new LinhaEntity();
		linhaEntity1.setId(UUID.randomUUID());
		linhaEntity1.setNomeAdministradora(optionalAdminEntity.get());
		linhaEntity1.setNumeroLinha("1");
		linhaEntity1.setNome("Linha 1 Azul");
		linhaEntity1.setNomeIngles("Line 1 Blue");
		linhaEntity1.setCor("Azul");
		linhaEntity1.setCorHexadecimal("#0000FF");
		linhaEntity1.setEstacoes(Collections.singletonList(new EstacaoEntity()));
		
		linhaEntity2 = new LinhaEntity();
		linhaEntity2.setId(UUID.randomUUID());
		linhaEntity2.setNomeAdministradora(optionalAdminEntity.get());
		linhaEntity2.setNumeroLinha("1");
		linhaEntity2.setNome("Linha 1 Azul");
		linhaEntity2.setNomeIngles("Line 1 Blue");
		linhaEntity2.setCor("Azul");
		linhaEntity2.setCorHexadecimal("#0000FF");
		linhaEntity2.setEstacoes(Collections.singletonList(new EstacaoEntity()));
		
		linhaEntity3 = new LinhaEntity();
		linhaEntity3.setId(UUID.randomUUID());
		linhaEntity3.setNomeAdministradora(optionalAdminEntity.get());
		linhaEntity3.setNumeroLinha("2");
		linhaEntity3.setNome("Linha 2 Verde");
		linhaEntity3.setNomeIngles("Line 2 Green");
		linhaEntity3.setCor("Green");
		linhaEntity3.setCorHexadecimal("#008000");
		linhaEntity3.setEstacoes(Collections.singletonList(new EstacaoEntity()));
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaEntity#equals(Object object)}.
	 */
	@Test
	void testEquals() {
		assertEquals(linhaEntity1, linhaEntity2);
        assertNotEquals(linhaEntity1, linhaEntity3);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaEntity#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertEquals(linhaEntity1.hashCode(), linhaEntity2.hashCode());
        assertNotEquals(linhaEntity1.hashCode(), linhaEntity3.hashCode());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaEntity#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals(linhaEntity1.toString(), linhaEntity2.toString());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaEntity#compare(LinhaEntity linhaEntity1, LinhaEntity linhaEntity2)}.
	 */
	@Test
    void testCompare() {
		assertTrue(new LinhaEntity().compare(linhaEntity1, linhaEntity3) < 0);
	}
	
}