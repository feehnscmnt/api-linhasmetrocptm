package br.com.apilinhasmetrocptm.component;

import br.com.apilinhasmetrocptm.repository.EstacaoRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import br.com.apilinhasmetrocptm.entity.EstacaoEntity;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import java.util.Collections;
import java.io.Serializable;
import java.util.Optional;
import org.mockito.Mock;
import java.util.List;

/**
 * Classe test para implementação dos testes unitários dos métodos
 * de validação, cadastro e consulta de estações.
 * 
 * @author Felipe Nascimento
 * 
 */

class EstacaoComponentTest implements Serializable {
	private static final long serialVersionUID = 2453774442967728134L;
	
	@Mock
	private EstacaoRepository estacaoRepository;
	
	@InjectMocks
	private EstacaoComponent estacaoComponent;
	
	/**
	 * Método responsável pela inicialização dos mocks.
	 */
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoComponent#save(EstacaoEntity estacaoEntity)}.
	 */
	@Test
    void testSave() {
		EstacaoEntity estacaoEntity = new EstacaoEntity();
		when(estacaoRepository.save(estacaoEntity)).thenReturn(estacaoEntity);
        assertEquals(estacaoEntity, estacaoComponent.save(estacaoEntity));
        verify(estacaoRepository).save(estacaoEntity);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoComponent#findEstacoesByNumeroLinha(String numeroLinha)}.
	 */
	@Test
	void testFindEstacoesByNumeroLinha() {
		EstacaoEntity estacaoEntity = new EstacaoEntity();
		List<EstacaoEntity> colecEstacaoEntity = Collections.singletonList(estacaoEntity);
		String numeroLinha = "numeroLinha";
		
		when(estacaoRepository.findEstacoesByNumeroLinha(numeroLinha)).thenReturn(colecEstacaoEntity);
		
		List<EstacaoEntity> listaEstacaoEntity = estacaoComponent.findEstacoesByNumeroLinha(numeroLinha);
        
        assertNotNull(listaEstacaoEntity);
        assertEquals(1, listaEstacaoEntity.size());
        assertEquals(estacaoEntity, listaEstacaoEntity.get(0));
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoComponent#findByNomeEstacao(String nomeEstacao)}
	 * quando o nome da estação for encontrado.
	 */
	@Test
	void testFindByNomeEstacaoEncontrado() {
		String nomeEstacao = "nomeEstacao";
		EstacaoEntity estacaoEntity = new EstacaoEntity();
		when(estacaoRepository.findByNomeEstacao(nomeEstacao)).thenReturn(Optional.of(estacaoEntity));
		Optional<EstacaoEntity> optionalEstacaoEntity = estacaoComponent.findByNomeEstacao(nomeEstacao);
		assertTrue(optionalEstacaoEntity.isPresent());
        assertEquals(estacaoEntity, optionalEstacaoEntity.get());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoComponent#findByNomeEstacao(String nomeEstacao)}
	 * quando o nome da estação não for encontrado.
	 */
	@Test
	void testFindByNomeEstacaoNaoEncontrado() {
		String nomeEstacaoInexistente = "nomeEstacaoInexistente";
		when(estacaoRepository.findByNomeEstacao(nomeEstacaoInexistente)).thenReturn(Optional.empty());
		Optional<EstacaoEntity> optionalEstacaoEntity = estacaoComponent.findByNomeEstacao(nomeEstacaoInexistente);
		assertFalse(optionalEstacaoEntity.isPresent());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link EstacaoComponent#findAll(Pageable pageable)}.
	 */
	@Test
    void testFindAll() {
		Pageable pageable = Pageable.unpaged();
		
		EstacaoEntity estacaoEntity1 = new EstacaoEntity();
		EstacaoEntity estacaoEntity2 = new EstacaoEntity();
		Page<EstacaoEntity> page = new PageImpl<>(List.of(estacaoEntity1, estacaoEntity2));
		
		when(estacaoRepository.findAll(pageable)).thenReturn(page);
        assertEquals(page, estacaoComponent.findAll(pageable));
        verify(estacaoRepository).findAll(pageable);
	}
	
}