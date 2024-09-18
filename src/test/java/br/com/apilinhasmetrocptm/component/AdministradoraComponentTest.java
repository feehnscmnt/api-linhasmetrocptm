package br.com.apilinhasmetrocptm.component;

import br.com.apilinhasmetrocptm.repository.AdministradoraRepository;
import br.com.apilinhasmetrocptm.entity.AdministradoraEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import java.io.Serializable;
import java.util.Optional;
import org.mockito.Mock;
import java.util.List;

/**
 * Classe test para implementação dos testes unitários dos métodos
 * de validação, cadastro e consulta de administradoras.
 * 
 * @author Felipe Nascimento
 * 
 */

class AdministradoraComponentTest implements Serializable {
	private static final long serialVersionUID = -99003111871843229L;
	
	@Mock
	private AdministradoraRepository administradoraRepository;
	
	@InjectMocks
	private AdministradoraComponent administradoraComponent;
	
	/**
	 * Método responsável pela inicialização dos mocks.
	 */
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraComponent#existsByNomeAdministradora(String nomeAdministradora)}.
	 */
	@Test
	void testExistsByNomeAdministradora() {
		String nomeAdministradora = "nomeAdministradora";
		when(administradoraRepository.existsByNomeAdministradora(nomeAdministradora)).thenReturn(true);
		assertTrue(administradoraComponent.existsByNomeAdministradora(nomeAdministradora));
        verify(administradoraRepository).existsByNomeAdministradora(nomeAdministradora);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraComponent#save(AdministradoraEntity administradoraEntity)}.
	 */
	@Test
    void testSave() {
		AdministradoraEntity administradoraEntity = new AdministradoraEntity();
		when(administradoraRepository.save(administradoraEntity)).thenReturn(administradoraEntity);
        assertEquals(administradoraEntity, administradoraComponent.save(administradoraEntity));
        verify(administradoraRepository).save(administradoraEntity);
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraComponent#findByNomeAdministradora(String nomeAdministradora)}
	 * quando o nome da administradora for encontrado.
	 */
	@Test
	void testFindByNomeAdministradoraEncontrado() {
		String nomeAdministradora = "nomeAdministradora";
		AdministradoraEntity administradoraEntity = new AdministradoraEntity();
		when(administradoraRepository.findByNomeAdministradora(nomeAdministradora)).thenReturn(Optional.of(administradoraEntity));
		Optional<AdministradoraEntity> optionalAdminEntity = administradoraComponent.findByNomeAdministradora(nomeAdministradora);
        assertTrue(optionalAdminEntity.isPresent());
        assertEquals(administradoraEntity, optionalAdminEntity.get());
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link AdministradoraComponent#findByNomeAdministradora(String nomeAdministradora)}
	 * quando o nome da administradora não for encontrado.
	 */
	@Test
	void testFindByNomeAdministradoraNaoEncontrado() {
		String nomeAdminInexistente = "nomeAdminInexistente";
		when(administradoraRepository.findByNomeAdministradora(nomeAdminInexistente)).thenReturn(Optional.empty());
        Optional<AdministradoraEntity> optionalAdminEntity = administradoraComponent.findByNomeAdministradora(nomeAdminInexistente);
        assertFalse(optionalAdminEntity.isPresent());
	}
	
	/**
	 * Método responsável pelo teste unitário do metodo {@link AdministradoraComponent#findAll(Pageable pageable)}.
	 */
	@Test
    void testFindAll() {
		Pageable pageable = Pageable.unpaged();
		
		AdministradoraEntity administradoraEntity1 = new AdministradoraEntity();
		AdministradoraEntity administradoraEntity2 = new AdministradoraEntity();
		Page<AdministradoraEntity> page = new PageImpl<>(List.of(administradoraEntity1, administradoraEntity2));
		
		when(administradoraRepository.findAll(pageable)).thenReturn(page);
        assertEquals(page, administradoraComponent.findAll(pageable));
        verify(administradoraRepository).findAll(pageable);
	}
	
}