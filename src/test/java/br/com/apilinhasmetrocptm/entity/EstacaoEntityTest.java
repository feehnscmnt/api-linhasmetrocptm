package br.com.apilinhasmetrocptm.entity;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import br.com.apilinhasmetrocptm.component.LinhaComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

class EstacaoEntityTest implements Serializable {
	private static final long serialVersionUID = -7216457527618017445L;
	
	@Mock
	private LinhaComponent linhaComponent;
	
	private EstacaoEntity estacaoEntity1;
	private EstacaoEntity estacaoEntity2;
	private EstacaoEntity estacaoEntity3;
	
	/**
	 * Método responsável pela inicialização dos atributos e do mock.
	 */
	@BeforeEach
	void setUp() {
		String numeroLinha = "numeroLinha";
		
		Optional<LinhaEntity> optionalLinhaEntity = linhaComponent.findByNumeroLinha(numeroLinha);
		
		estacaoEntity1 = new EstacaoEntity();
		estacaoEntity1.setId(UUID.randomUUID());
		estacaoEntity1.setNumeroLinha(optionalLinhaEntity.get());
		estacaoEntity1.setNomeEstacao("Amador Bueno");
		estacaoEntity1.setCidadeEstacao("Itapevi");
		
		estacaoEntity2 = new EstacaoEntity();
		estacaoEntity2.setId(UUID.randomUUID());
		estacaoEntity2.setNumeroLinha(optionalLinhaEntity.get());
		estacaoEntity2.setNomeEstacao("Amador Bueno");
		estacaoEntity2.setCidadeEstacao("Itapevi");
		
		estacaoEntity3 = new EstacaoEntity();
		estacaoEntity3.setId(UUID.randomUUID());
		estacaoEntity3.setNumeroLinha(optionalLinhaEntity.get());
		estacaoEntity3.setNomeEstacao("Tucuruvi");
		estacaoEntity3.setCidadeEstacao("São Paulo");
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoEntity#equals(Object object)}.
	 */
	@Test
	void testEquals() {
		assertEquals(estacaoEntity1, estacaoEntity2);
        assertNotEquals(estacaoEntity1, estacaoEntity3);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoEntity#hashCode()}.
	 */
	@Test
	void testHashCode() {
		assertEquals(estacaoEntity1.hashCode(), estacaoEntity2.hashCode());
        assertNotEquals(estacaoEntity1.hashCode(), estacaoEntity3.hashCode());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoEntity#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals(estacaoEntity1.toString(), estacaoEntity2.toString());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoEntity#compare(EstacaoEntity estacaoEntity1, EstacaoEntity estacaoEntity2)}.
	 */
	@Test
    void testCompare() {
		assertTrue(new EstacaoEntity().compare(estacaoEntity1, estacaoEntity3) < 0);
	}
	
}