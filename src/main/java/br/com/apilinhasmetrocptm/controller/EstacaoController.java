package br.com.apilinhasmetrocptm.controller;

import br.com.apilinhasmetrocptm.component.AdministradoraComponent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.apilinhasmetrocptm.entity.AdministradoraEntity;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.apilinhasmetrocptm.component.EstacaoComponent;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.apilinhasmetrocptm.component.LinhaComponent;
import br.com.apilinhasmetrocptm.model.ResponseMessages;
import br.com.apilinhasmetrocptm.entity.EstacaoEntity;
import br.com.apilinhasmetrocptm.entity.LinhaEntity;
import br.com.apilinhasmetrocptm.dto.EstacaoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Optional;
import java.util.List;

/**
 * Classe controller responsável pelo cadastro e pela consulta de estações.
 * 
 * @author Felipe Nascimento
 *
 */

@RestController
@RequestMapping("/v1/estacao")
public class EstacaoController implements Serializable {
	private static final long serialVersionUID = -8202473186058540200L;
	private transient AdministradoraComponent administradoraComponent;
	private transient EstacaoComponent estacaoComponent;
	private transient LinhaComponent linhaComponent;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param administradoraComponent - {@link AdministradoraComponent} - serviço de administradoras
	 * @param estacaoComponent - {@link EstacaoComponent} - serviço de estações
	 * @param linhaComponent - {@link LinhaComponent} - serviço de linhas
	 * 
	 */
	public EstacaoController(AdministradoraComponent administradoraComponent, EstacaoComponent estacaoComponent, LinhaComponent linhaComponent) {
		this.administradoraComponent = administradoraComponent;
		this.estacaoComponent = estacaoComponent;
		this.linhaComponent = linhaComponent;
	}
	
	/**
	 * Método responsável pelo cadastramento das estações no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link PathVariable} / {@link String} - nome da administradora que a linha será atrelada
	 * @param numeroLinha - {@link PathVariable} / {@link String} - número da linha que a estação será atrelada
	 * @param estacaoDTO - {@link RequestBody} / {@link Valid} / {@link EstacaoDTO} - dados da estação
	 * 
	 * @return dados da estação em json em caso de sucesso
	 * 
	 */
	@PostMapping("/cadastrar-estacao/{nomeAdministradora}/{numeroLinha}")
	public ResponseEntity<Object> saveEstacao(@PathVariable String nomeAdministradora, @PathVariable String numeroLinha, @RequestBody @Valid EstacaoDTO estacaoDTO) {
		
		Optional<AdministradoraEntity> optionalAdminEntity = administradoraComponent.findByNomeAdministradora(nomeAdministradora);
		
		if (!optionalAdminEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		Optional<LinhaEntity> optionalLinhaEntity = linhaComponent.findByNumeroLinha(numeroLinha);
		
		if (!optionalLinhaEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma linha foi encontrada com este número.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		EstacaoEntity estacaoEntity = new EstacaoEntity();
		BeanUtils.copyProperties(estacaoDTO, estacaoEntity);
		estacaoEntity.setNumeroLinha(optionalLinhaEntity.get());
		estacaoComponent.save(estacaoEntity);
		
		return ResponseEntity.status(HttpStatus.OK)
			.body(new ResponseMessages("Estação cadastrada com sucesso.", HttpStatus.CREATED, HttpStatus.CREATED.value()));
		
	}
	
	/**
	 * Método responsável pela busca de todas as estações atreladas
	 * a uma determinada linha no banco de dados.
	 * 
	 * @param numeroLinha - {@link PathVariable} / {@link String} - número da linha que a estação está atrelada
	 * 
	 * @return dados de todas as estações em json
	 * 
	 */
	@GetMapping("/listar-estacoes/{numeroLinha}")
	public ResponseEntity<Object> listTodasEstacoes(@PathVariable String numeroLinha) {
		
		Optional<LinhaEntity> optionalLinhaEntity = linhaComponent.findByNumeroLinha(numeroLinha);
		
		if (!optionalLinhaEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma linha foi encontrada com este número.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		List<EstacaoEntity> listaEstacaoEntity = estacaoComponent.findEstacoesByNumeroLinha(numeroLinha);
		
		if (listaEstacaoEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma estação foi encontrada.", HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.value()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listaEstacaoEntity);
		
	}
	
	/**
	 * Método responsável pela busca de uma estação pelo nome.
	 * 
	 * @param nomeEstacao - {@link String} - nome da estação que será buscada
	 * 
	 * @return dados de todas as estações em json
	 * 
	 */
	@GetMapping("/listar-estacao/{nomeEstacao}")
	public ResponseEntity<Object> listEstacao(@PathVariable String nomeEstacao) {
		
		Optional<EstacaoEntity> optionalEstacaoEntity = estacaoComponent.findByNomeEstacao(nomeEstacao);
		
		if (!optionalEstacaoEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma estação foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(optionalEstacaoEntity);
		
	}
	
}