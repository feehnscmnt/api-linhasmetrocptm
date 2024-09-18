package br.com.apilinhasmetrocptm.controller;

import br.com.apilinhasmetrocptm.component.AdministradoraComponent;
import br.com.apilinhasmetrocptm.entity.AdministradoraEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import br.com.apilinhasmetrocptm.model.ResponseMessages;
import br.com.apilinhasmetrocptm.dto.AdministradoraDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
 * Classe test para implementação dos testes unitários dos métodos de cadastro e consulta de administradoras.
 * 
 * @author Felipe Nascimento
 * 
 */

class AdministradoraControllerTest implements Serializable {
	private static final long serialVersionUID = -5023124338128457183L;
	
	@InjectMocks
	private AdministradoraController administradoraController;
	
	@Mock
	private AdministradoraComponent administradoraComponent;
	
	/**
	 * Método responsável pela inicialização dos mocks.
	 */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link AdministradoraController#saveAdministradora(AdministradoraDTO administradoraDTO)}
     * quando já existir a administradora informada cadastrada.
     */
    @Test
    void testSaveAdministradoraJaExiste() {
    	AdministradoraDTO administradoraDTO = new AdministradoraDTO();
    	administradoraDTO.setNomeAdministradora("nomeAdminExistente");
    	
    	when(administradoraComponent.existsByNomeAdministradora(administradoraDTO.getNomeAdministradora())).thenReturn(true);
    	
    	ResponseEntity<Object> responseSaveAdmin = administradoraController.saveAdministradora(administradoraDTO);
    	
    	verify(administradoraComponent).save(any(AdministradoraEntity.class));
        assertEquals(HttpStatus.OK, responseSaveAdmin.getStatusCode());
        assertEquals(new ResponseMessages("Já existe uma administradora cadastrada com este nome.", HttpStatus.CONFLICT, HttpStatus.CONFLICT.value()), responseSaveAdmin.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link AdministradoraController#saveAdministradora(AdministradoraDTO administradoraDTO)} com sucesso.
     */
    @Test
    void testSaveAdministradoraComSucesso() {
    	AdministradoraDTO administradoraDTO = new AdministradoraDTO();
    	administradoraDTO.setNomeAdministradora("nomeAdminExistente");
    	
    	when(administradoraComponent.existsByNomeAdministradora(administradoraDTO.getNomeAdministradora())).thenReturn(false);
    	
    	ResponseEntity<Object> responseSaveAdmin = administradoraController.saveAdministradora(administradoraDTO);
    	
    	verify(administradoraComponent).save(any(AdministradoraEntity.class));
        assertEquals(HttpStatus.OK, responseSaveAdmin.getStatusCode());
        assertEquals(new ResponseMessages("Administradora cadastrada com sucesso.", HttpStatus.CREATED, HttpStatus.CREATED.value()), responseSaveAdmin.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link AdministradoraController#listAdministradoras(Pageable pageable)}
     * quando a lista retornar vazia.
     */
    @Test
    void testListAdministradorasEmpty() {
    	Pageable pageable = PageRequest.of(0, 10);
    	Page<AdministradoraEntity> page = new PageImpl<>(Collections.emptyList());
    	
    	when(administradoraComponent.findAll(pageable)).thenReturn(page);
    	
    	ResponseEntity<Object> responseListAdministradoras = administradoraController.listAdministradoras(pageable);
    	
    	assertEquals(HttpStatus.OK, responseListAdministradoras.getStatusCode());
        assertEquals(new ResponseMessages("Nenhuma administradora foi encontrada.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), responseListAdministradoras.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link AdministradoraController#listAdministradoras(Pageable pageable)}
     * quando a lista não retornar vazia.
     */
    @Test
    void testListAdministradorasNotEmpty() {
    	Pageable pageable = PageRequest.of(0, 10);
        AdministradoraEntity administradoraEntity = new AdministradoraEntity();
        List<AdministradoraEntity> listaEntity = List.of(administradoraEntity);
        Page<AdministradoraEntity> page = new PageImpl<>(listaEntity);

        when(administradoraComponent.findAll(pageable)).thenReturn(page);

        ResponseEntity<Object> responseListAdministradoras = administradoraController.listAdministradoras(pageable);

        assertEquals(HttpStatus.OK, responseListAdministradoras.getStatusCode());
        assertEquals(listaEntity, responseListAdministradoras.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link AdministradoraController#listAdministradoraPorNome(String nomeAdministradora)}
     * quando o nome da administradora for encontrado.
     */
    @Test
    void testListAdministradoraPorNomeEncontrado() {
    	String nomeAdministradora = "nomeAdministradora";
        AdministradoraEntity administradoraEntity = new AdministradoraEntity();
        
        when(administradoraComponent.findByNomeAdministradora(nomeAdministradora)).thenReturn(Optional.of(administradoraEntity));

        ResponseEntity<Object> responseListAdministradora = administradoraController.listAdministradoraPorNome(nomeAdministradora);

        assertEquals(HttpStatus.OK, responseListAdministradora.getStatusCode());
        assertEquals(administradoraEntity, responseListAdministradora.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link AdministradoraController#listAdministradoraPorNome(String nomeAdministradora)}
     * quando o nome da administradora não for encontrado.
     */
    @Test
    void testListAdministradoraPorNomeNaoEncontrado() {
    	String nomeAdminInexistente = "nomeAdminInexistente";
        
        when(administradoraComponent.findByNomeAdministradora(nomeAdminInexistente)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = administradoraController.listAdministradoraPorNome(nomeAdminInexistente);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), response.getBody());
    }
    
}