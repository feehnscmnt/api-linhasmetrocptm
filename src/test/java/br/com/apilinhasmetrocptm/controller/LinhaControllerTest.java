package br.com.apilinhasmetrocptm.controller;

import br.com.apilinhasmetrocptm.component.AdministradoraComponent;
import br.com.apilinhasmetrocptm.entity.AdministradoraEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import br.com.apilinhasmetrocptm.component.LinhaComponent;
import br.com.apilinhasmetrocptm.model.ResponseMessages;
import br.com.apilinhasmetrocptm.entity.LinhaEntity;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.ResponseEntity;
import br.com.apilinhasmetrocptm.dto.LinhaDTO;
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
 * Classe test para implementação dos testes unitários dos métodos de cadastro e consulta de linhas.
 * 
 * @author Felipe Nascimento
 * 
 */

class LinhaControllerTest implements Serializable {
	private static final long serialVersionUID = -8573895064438276907L;
	
	@Mock
	private AdministradoraComponent administradoraComponent;
	
	@InjectMocks
	private LinhaController linhaController;
	
	@Mock
	private LinhaComponent linhaComponent;
	
	/**
	 * Método responsável pela inicialização dos mocks.
	 */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#saveLinha(String nomeAdministradora, LinhaDTO linhaDTO)}
     * quando já existir a linha informada cadastrada.
     */
    @Test
    void testSaveLinhaJaExiste() {
    	String nomeAdministradora = "nomeAdministradora";
    	LinhaDTO linhaDTO = new LinhaDTO();
    	linhaDTO.setNumeroLinha("numeroLinha");
    	
    	when(linhaComponent.existsByNumeroLinha(linhaDTO.getNumeroLinha())).thenReturn(true);
    	
    	ResponseEntity<Object> responseSaveLinha = linhaController.saveLinha(nomeAdministradora, linhaDTO);
    	
    	verify(linhaComponent).save(any(LinhaEntity.class));
        assertEquals(HttpStatus.OK, responseSaveLinha.getStatusCode());
        assertEquals(new ResponseMessages("Já existe uma linha cadastrada com este número.", HttpStatus.CONFLICT, HttpStatus.CONFLICT.value()), responseSaveLinha.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#saveLinha(String nomeAdministradora, LinhaDTO linhaDTO)} com sucesso.
     */
    @Test
    void testSaveLinhaComSucesso() {
    	String nomeAdministradora = "nomeAdministradora";
    	LinhaDTO linhaDTO = new LinhaDTO();
    	linhaDTO.setNumeroLinha("numeroLinha");
    	
    	when(linhaComponent.existsByNumeroLinha(linhaDTO.getNumeroLinha())).thenReturn(false);
    	
    	ResponseEntity<Object> responseSaveLinha = linhaController.saveLinha(nomeAdministradora, linhaDTO);
    	
    	verify(linhaComponent).save(any(LinhaEntity.class));
        assertEquals(HttpStatus.OK, responseSaveLinha.getStatusCode());
        assertEquals(new ResponseMessages("Linha cadastrada com sucesso.", HttpStatus.CREATED, HttpStatus.CREATED.value()), responseSaveLinha.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#listTodasLinhas(String nomeAdministradora)}
     * quando a administradora não for encontrada.
     */
    @Test
    void testListTodasLinhasAdminNaoEncontrada() {
    	String nomeAdminInexistente = "nomeAdminInexistente";
    	
    	when(administradoraComponent.findByNomeAdministradora(nomeAdminInexistente)).thenReturn(Optional.empty());
    	
    	ResponseEntity<Object> responseListTodasLinhas = linhaController.listTodasLinhas(nomeAdminInexistente);
    	
    	assertEquals(HttpStatus.OK, responseListTodasLinhas.getStatusCode());
    	assertTrue(responseListTodasLinhas.getBody() instanceof ResponseMessages);
    	assertEquals(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), responseListTodasLinhas.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#listTodasLinhas(String nomeAdministradora)}
     * quando a lista de linhas retornar vazia.
     */
    @Test
    void testListTodasLinhasNaoEncontrada() {
    	String nomeAdministradora = "nomeAdministradora";
    	AdministradoraEntity administradoraEntity = new AdministradoraEntity();
    	
    	when(administradoraComponent.findByNomeAdministradora(nomeAdministradora)).thenReturn(Optional.of(administradoraEntity));
    	when(linhaComponent.findLinhasByNomeAdministradora(nomeAdministradora)).thenReturn(Collections.emptyList());
    	
    	ResponseEntity<Object> responseListTodasLinhas = linhaController.listTodasLinhas(nomeAdministradora);
    	
    	assertEquals(HttpStatus.OK, responseListTodasLinhas.getStatusCode());
        assertTrue(responseListTodasLinhas.getBody() instanceof ResponseMessages);
        assertEquals(new ResponseMessages("Nenhuma linha foi encontrada.", HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.value()), responseListTodasLinhas.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#listTodasLinhas(String nomeAdministradora)}
     * quando a lista de linhas não retornar vazia.
     */
    @Test
    void testListTodasLinhasEncontrada() {
    	String nomeAdministradora = "nomeAdministradora";
    	AdministradoraEntity administradoraEntity = new AdministradoraEntity();
        LinhaEntity linhaEntity = new LinhaEntity();
        
        when(administradoraComponent.findByNomeAdministradora(nomeAdministradora)).thenReturn(Optional.of(administradoraEntity));
    	when(linhaComponent.findLinhasByNomeAdministradora(nomeAdministradora)).thenReturn(Collections.singletonList(linhaEntity));
    	
    	ResponseEntity<Object> responseListTodasLinhas = linhaController.listTodasLinhas(nomeAdministradora);
    	
    	assertEquals(HttpStatus.OK, responseListTodasLinhas.getStatusCode());
    	assertTrue(responseListTodasLinhas.getBody() instanceof List);
    	assertEquals(1, ((List<?>) responseListTodasLinhas.getBody()).size());
    	assertTrue(((List<?>) responseListTodasLinhas.getBody()).get(0) instanceof LinhaEntity);
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#listLinhaPorNome(String nomeAdministradora, String nome)}
     * quando a administradora não for encontrada.
     */
    @Test
    void testListLinhaPorNomeAdminNaoEncontrada() {
    	String nomeAdminInexistente = "nomeAdminInexistente";
    	String nome = "nomeLinha";
    	
    	when(administradoraComponent.findByNomeAdministradora(nomeAdminInexistente)).thenReturn(Optional.empty());
    	
    	ResponseEntity<Object> responseListLinha = linhaController.listLinhaPorNome(nomeAdminInexistente, nome);
    	
    	assertEquals(HttpStatus.OK, responseListLinha.getStatusCode());
    	assertTrue(responseListLinha.getBody() instanceof ResponseMessages);
    	assertEquals(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), responseListLinha.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#listLinhaPorNome(String nomeAdministradora, String nome)}
     * quando a linha não for encontrada.
     */
    @Test
    void testListLinhaPorNomeNaoEncontrada() {
    	String nomeAdministradora = "nomeAdministradora";
    	String nomeLinhaInexistente = "nomeLinhaInexistente";
    	AdministradoraEntity administradoraEntity = new AdministradoraEntity();
    	
    	when(administradoraComponent.findByNomeAdministradora(nomeAdministradora)).thenReturn(Optional.of(administradoraEntity));
    	
    	ResponseEntity<Object> responseListLinha = linhaController.listLinhaPorNome(nomeAdministradora, nomeLinhaInexistente);
    	
    	assertEquals(HttpStatus.OK, responseListLinha.getStatusCode());
    	assertTrue(responseListLinha.getBody() instanceof ResponseMessages);
    	assertEquals(new ResponseMessages("Nenhuma linha foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), responseListLinha.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#listLinhaPorNome(String nomeAdministradora, String nome)}
     * quando a linha for encontrada.
     */
    @Test
    void testListLinhaPorNomeEncontrada() {
    	String nomeAdministradora = "nomeAdministradora";
    	String nome = "nomeLinha";
    	AdministradoraEntity administradoraEntity = new AdministradoraEntity();
    	LinhaEntity linhaEntity = new LinhaEntity();
    	
    	when(administradoraComponent.findByNomeAdministradora(nomeAdministradora)).thenReturn(Optional.of(administradoraEntity));
    	when(linhaComponent.findLinhaPorNomeByNomeAdministradora(nomeAdministradora, nome)).thenReturn(Optional.of(linhaEntity));
    	
    	ResponseEntity<Object> responseListLinha = linhaController.listLinhaPorNome(nomeAdministradora, nome);
    	
    	assertEquals(HttpStatus.OK, responseListLinha.getStatusCode());
    	assertEquals(linhaEntity, (LinhaEntity) responseListLinha.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#listLinhaPorCor(String nomeAdministradora, String cor)}
     * quando a administradora não for encontrada.
     */
    @Test
    void testListLinhaPorCorAdminNaoEncontrada() {
    	String nomeAdminInexistente = "nomeAdminInexistente";
    	String cor = "corLinha";
    	
    	when(administradoraComponent.findByNomeAdministradora(nomeAdminInexistente)).thenReturn(Optional.empty());
    	
    	ResponseEntity<Object> responseListLinha = linhaController.listLinhaPorCor(nomeAdminInexistente, cor);
    	
    	assertEquals(HttpStatus.OK, responseListLinha.getStatusCode());
    	assertTrue(responseListLinha.getBody() instanceof ResponseMessages);
    	assertEquals(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), responseListLinha.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#listLinhaPorCor(String nomeAdministradora, String cor)}
     * quando a linha não for encontrada.
     */
    @Test
    void testListLinhaPorCorNaoEncontrada() {
    	String nomeAdministradora = "nomeAdministradora";
    	String corLinhaInexistente = "corLinhaInexistente";
    	AdministradoraEntity administradoraEntity = new AdministradoraEntity();
    	
    	when(administradoraComponent.findByNomeAdministradora(nomeAdministradora)).thenReturn(Optional.of(administradoraEntity));
    	
    	ResponseEntity<Object> responseListLinha = linhaController.listLinhaPorCor(nomeAdministradora, corLinhaInexistente);
    	
    	assertEquals(HttpStatus.OK, responseListLinha.getStatusCode());
    	assertTrue(responseListLinha.getBody() instanceof ResponseMessages);
    	assertEquals(new ResponseMessages("Nenhuma linha foi encontrada com esta cor.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), responseListLinha.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link LinhaController#listLinhaPorCor(String nomeAdministradora, String cor)}
     * quando a linha for encontrada.
     */
    @Test
    void testListLinhaPorCorEncontrada() {
    	String nomeAdministradora = "nomeAdministradora";
    	String cor = "corLinha";
    	AdministradoraEntity administradoraEntity = new AdministradoraEntity();
    	LinhaEntity linhaEntity = new LinhaEntity();
    	
    	when(administradoraComponent.findByNomeAdministradora(nomeAdministradora)).thenReturn(Optional.of(administradoraEntity));
    	when(linhaComponent.findLinhaPorCorByNomeAdministradora(nomeAdministradora, cor)).thenReturn(Optional.of(linhaEntity));
    	
    	ResponseEntity<Object> responseListLinha = linhaController.listLinhaPorCor(nomeAdministradora, cor);
    	
    	assertEquals(HttpStatus.OK, responseListLinha.getStatusCode());
    	assertEquals(linhaEntity, (LinhaEntity) responseListLinha.getBody());
    }
    
}