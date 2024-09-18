package br.com.apilinhasmetrocptm.controller;

import br.com.apilinhasmetrocptm.component.AdministradoraComponent;
import br.com.apilinhasmetrocptm.component.EstacaoComponent;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import br.com.apilinhasmetrocptm.component.LinhaComponent;
import br.com.apilinhasmetrocptm.model.ResponseMessages;
import br.com.apilinhasmetrocptm.entity.EstacaoEntity;
import br.com.apilinhasmetrocptm.entity.LinhaEntity;
import br.com.apilinhasmetrocptm.dto.EstacaoDTO;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.http.ResponseEntity;
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
 * Classe test para implementação dos testes unitários dos métodos de cadastro e consulta de estações.
 * 
 * @author Felipe Nascimento
 * 
 */

class EstacaoControllerTest implements Serializable {
	private static final long serialVersionUID = 1563214661313732365L;
	
	@Mock
	private AdministradoraComponent administradoraComponent;
	
	@InjectMocks
	private EstacaoController estacaoController;
	
	@Mock
	private EstacaoComponent estacaoComponent;
	
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
     * Método responsável pelo teste unitário do método {@link EstacaoController#saveEstacao(String nomeAdministradora, String numeroLinha, EstacaoDTO estacaoDTO)} com sucesso.
     */
    @Test
    void testSaveEstacaoComSucesso() {
    	String nomeAdministradora = "nomeAdministradora";
    	String numeroLinha = "numeroLinha";
    	EstacaoDTO estacaoDTO = new EstacaoDTO();
    	estacaoDTO.setNomeEstacao("nomeEstacao");
    	
    	ResponseEntity<Object> responseSaveEstacao = estacaoController.saveEstacao(nomeAdministradora, numeroLinha, estacaoDTO);
    	
    	verify(estacaoComponent).save(any(EstacaoEntity.class));
        assertEquals(HttpStatus.OK, responseSaveEstacao.getStatusCode());
        assertEquals(new ResponseMessages("Estação cadastrada com sucesso.", HttpStatus.CREATED, HttpStatus.CREATED.value()), responseSaveEstacao.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link EstacaoController#listTodasEstacoes(String numeroLinha)}
     * quando a linha não for encontrada.
     */
    @Test
    void testListTodasEstacoesLinhaNaoEncontrada() {
    	String numeroLinhaInexistente = "numeroLinhaInexistente";
    	
    	when(linhaComponent.findByNumeroLinha(numeroLinhaInexistente)).thenReturn(Optional.empty());
    	
    	ResponseEntity<Object> responseListTodasEstacoes = estacaoController.listTodasEstacoes(numeroLinhaInexistente);
    	
    	assertEquals(HttpStatus.OK, responseListTodasEstacoes.getStatusCode());
    	assertTrue(responseListTodasEstacoes.getBody() instanceof ResponseMessages);
    	assertEquals(new ResponseMessages("Nenhuma linha foi encontrada com este número para esta administradora.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), responseListTodasEstacoes.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link EstacaoController#listTodasEstacoes(String numeroLinha)}
     * quando a lista de estações retornar vazia.
     */
    @Test
    void testListTodasEstacoesNaoEncontrada() {
    	String numeroLinha = "numeroLinha";
    	LinhaEntity linhaEntity = new LinhaEntity();
    	
    	when(linhaComponent.findByNumeroLinha(numeroLinha)).thenReturn(Optional.of(linhaEntity));
    	when(estacaoComponent.findEstacoesByNumeroLinha(numeroLinha)).thenReturn(Collections.emptyList());
    	
    	ResponseEntity<Object> responseListTodasEstacoes = estacaoController.listTodasEstacoes(numeroLinha);
    	
    	assertEquals(HttpStatus.OK, responseListTodasEstacoes.getStatusCode());
        assertTrue(responseListTodasEstacoes.getBody() instanceof ResponseMessages);
        assertEquals(new ResponseMessages("Nenhuma estação foi encontrada.", HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.value()), responseListTodasEstacoes.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link EstacaoController#listTodasEstacoes(String numeroLinha)}
     * quando a lista de estações não retornar vazia.
     */
    @Test
    void testListTodasEstacoesEncontrada() {
    	String numeroLinha = "numeroLinha";
        LinhaEntity linhaEntity = new LinhaEntity();
        EstacaoEntity estacaoEntity = new EstacaoEntity();
        
        when(linhaComponent.findByNumeroLinha(numeroLinha)).thenReturn(Optional.of(linhaEntity));
        when(estacaoComponent.findEstacoesByNumeroLinha(numeroLinha)).thenReturn(Collections.singletonList(estacaoEntity));
        
        ResponseEntity<Object> responseListTodasEstacoes = estacaoController.listTodasEstacoes(numeroLinha);
        
        assertEquals(HttpStatus.OK, responseListTodasEstacoes.getStatusCode());
        assertTrue(responseListTodasEstacoes.getBody() instanceof List);
        assertEquals(1, ((List<?>) responseListTodasEstacoes.getBody()).size());
        assertTrue(((List<?>) responseListTodasEstacoes.getBody()).get(0) instanceof EstacaoEntity);
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link EstacaoController#listEstacao(String nomeAdministradora, String numeroLinha)}
     * quando a linha não for encontrada.
     */
    @Test
    void testListEstacaoLinhaNaoEncontrada() {
    	String numeroLinhaInexistente = "numeroLinhaInexistente";
    	
    	when(linhaComponent.findByNumeroLinha(numeroLinhaInexistente)).thenReturn(Optional.empty());
    	
    	ResponseEntity<Object> responseListEstacao = estacaoController.listEstacao(numeroLinhaInexistente);
    	
    	assertEquals(HttpStatus.OK, responseListEstacao.getStatusCode());
    	assertTrue(responseListEstacao.getBody() instanceof ResponseMessages);
    	assertEquals(new ResponseMessages("Nenhuma linha foi encontrada com este número para esta administradora.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), responseListEstacao.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link EstacaoController#listEstacao(String nomeEstacao)}
     * quando o nome da estação não for encontrado.
     */
    @Test
    void testListEstacaoNaoEncontrada() {
    	String nomeEstacao = "nomeEstacao";
        
        when(estacaoComponent.findByNomeEstacao(nomeEstacao)).thenReturn(Optional.empty());
        
        ResponseEntity<Object> responseListEstacao = estacaoController.listEstacao(nomeEstacao);
        
        assertEquals(HttpStatus.OK, responseListEstacao.getStatusCode());
        assertTrue(responseListEstacao.getBody() instanceof ResponseMessages);
        assertEquals(new ResponseMessages("Nenhuma estação foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()), responseListEstacao.getBody());
    }
    
    /**
     * Método responsável pelo teste unitário do método {@link EstacaoController#listEstacao(String nomeEstacao)}
     * quando o nome da estação for encontrado.
     */
    @Test
    void testListEstacaoEncontrada() {
    	String nomeEstacao = "nomeEstacao";
        EstacaoEntity estacaoEntity = new EstacaoEntity();
        
        when(estacaoComponent.findByNomeEstacao(nomeEstacao)).thenReturn(Optional.of(estacaoEntity));
        
        ResponseEntity<Object> responseListEstacao = estacaoController.listEstacao(nomeEstacao);
        
        assertEquals(HttpStatus.OK, responseListEstacao.getStatusCode());
        assertEquals(estacaoEntity, (EstacaoEntity) responseListEstacao.getBody());
    }
    
}