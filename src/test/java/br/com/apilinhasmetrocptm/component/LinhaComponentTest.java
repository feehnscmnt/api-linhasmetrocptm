package br.com.apilinhasmetrocptm.component;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import br.com.apilinhasmetrocptm.repository.LinhaRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import br.com.apilinhasmetrocptm.entity.LinhaEntity;
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
 * de validação, cadastro e consulta de linhas.
 * 
 * @author Felipe Nascimento
 * 
 */

class LinhaComponentTest implements Serializable {
	private static final long serialVersionUID = 8800569799329042271L;
	
	@Mock
	private LinhaRepository linhaRepository;
	
	@InjectMocks
	private LinhaComponent linhaComponent;
	
	/**
	 * Método responsável pela inicialização dos mocks.
	 */
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaComponent#existsByNumeroLinha(String numeroLinha)}.
	 */
	@Test
	void testExistsByNumeroLinha() {
		String numeroLinha = "numeroLinha";
		when(linhaRepository.existsByNumeroLinha(numeroLinha)).thenReturn(true);
		assertTrue(linhaComponent.existsByNumeroLinha(numeroLinha));
        verify(linhaRepository).existsByNumeroLinha(numeroLinha);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaComponent#save(LinhaEntity linhaEntity)}.
	 */
	@Test
    void testSave() {
		LinhaEntity linhaEntity = new LinhaEntity();
		when(linhaRepository.save(linhaEntity)).thenReturn(linhaEntity);
        assertEquals(linhaEntity, linhaComponent.save(linhaEntity));
        verify(linhaRepository).save(linhaEntity);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaComponent#findLinhasByNomeAdministradora(String nomeAdministradora)}.
	 */
	@Test
    void testFindLinhasByNomeAdministradora() {
		LinhaEntity linhaEntity = new LinhaEntity();
		List<LinhaEntity> colecLinhaEntity = Collections.singletonList(linhaEntity);
		String nomeAdministradora = "nomeAdministradora";
		
		when(linhaRepository.findLinhasByNomeAdministradora(nomeAdministradora)).thenReturn(colecLinhaEntity);
		
		List<LinhaEntity> listaLinhaEntity = linhaComponent.findLinhasByNomeAdministradora(nomeAdministradora);
		
		assertNotNull(listaLinhaEntity);
        assertEquals(1, listaLinhaEntity.size());
        assertEquals(linhaEntity, listaLinhaEntity.get(0));
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaComponent#findLinhaPorNomeByNomeAdministradora(String nomeAdministradora, String nome)}
	 * quando o nome da linha for encontrado.
	 */
	@Test
	void testFindLinhaPorNomeByNomeAdministradoraEncontrado() {
		LinhaEntity linhaEntity = new LinhaEntity();
		String nomeAdministradora = "nomeAdministradora";
		String nome = "nome";
		when(linhaRepository.findLinhaPorNomeByNomeAdministradora(nomeAdministradora, nome)).thenReturn(Optional.of(linhaEntity));
		Optional<LinhaEntity> optionalLinhaEntity = linhaComponent.findLinhaPorNomeByNomeAdministradora(nomeAdministradora, nome);
		assertFalse(optionalLinhaEntity.isPresent());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaComponent#findLinhaPorNomeByNomeAdministradora(String nomeAdministradora, String nome)}
	 * quando o nome da linha não for encontrado.
	 */
	@Test
	void testFindLinhaPorNomeByNomeAdministradoraNaoEncontrado() {
		String nomeAdministradora = "nomeAdministradora";
		String nomeInexistente = "nomeInexistente";
		when(linhaRepository.findLinhaPorNomeByNomeAdministradora(nomeAdministradora, nomeInexistente)).thenReturn(Optional.empty());
		Optional<LinhaEntity> optionalLinhaEntity = linhaComponent.findLinhaPorNomeByNomeAdministradora(nomeAdministradora, nomeInexistente);
		assertFalse(optionalLinhaEntity.isPresent());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaComponent#findLinhaPorCorByNomeAdministradora(String nomeAdministradora, String cor)}
	 * quando a cor da linha for encontrada.
	 */
	@Test
	void testFindLinhaPorCorByNomeAdministradoraEncontrado() {
		LinhaEntity linhaEntity = new LinhaEntity();
		String nomeAdministradora = "nomeAdministradora";
		String cor = "cor";
		when(linhaRepository.findLinhaPorCorByNomeAdministradora(nomeAdministradora, cor)).thenReturn(Optional.of(linhaEntity));
		Optional<LinhaEntity> optionalLinhaEntity = linhaComponent.findLinhaPorCorByNomeAdministradora(nomeAdministradora, cor);
		assertFalse(optionalLinhaEntity.isPresent());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaComponent#findLinhaPorCorByNomeAdministradora(String nomeAdministradora, String cor)}
	 * quando a cor da linha não for encontrada.
	 */
	@Test
	void testFindLinhaPorCorByNomeAdministradoraNaoEncontrada() {
		String nomeAdministradora = "nomeAdministradora";
		String corInexistente = "corInexistente";
		when(linhaRepository.findLinhaPorCorByNomeAdministradora(nomeAdministradora, corInexistente)).thenReturn(Optional.empty());
		Optional<LinhaEntity> optionalLinhaEntity = linhaComponent.findLinhaPorCorByNomeAdministradora(nomeAdministradora, corInexistente);
		assertFalse(optionalLinhaEntity.isPresent());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link LinhaComponent#findAll(Pageable pageable)}.
	 */
	@Test
    void testFindAll() {
		Pageable pageable = Pageable.unpaged();
		
		LinhaEntity linhaEntity1 = new LinhaEntity();
		LinhaEntity linhaEntity2 = new LinhaEntity();
		Page<LinhaEntity> page = new PageImpl<>(List.of(linhaEntity1, linhaEntity2));
		
		when(linhaRepository.findAll(pageable)).thenReturn(page);
        assertEquals(page, linhaComponent.findAll(pageable));
        verify(linhaRepository).findAll(pageable);
	}
	
}