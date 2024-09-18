package br.com.apilinhasmetrocptm.controller;

import br.com.apilinhasmetrocptm.component.AdministradoraComponent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.apilinhasmetrocptm.entity.AdministradoraEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.apilinhasmetrocptm.component.LinhaComponent;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.apilinhasmetrocptm.model.ResponseMessages;
import br.com.apilinhasmetrocptm.entity.LinhaEntity;
import org.springframework.http.ResponseEntity;
import br.com.apilinhasmetrocptm.dto.LinhaDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Optional;
import java.util.List;

/**
 * Classe controller responsável pelo cadastro e pela consulta de linhas.
 * 
 * @author Felipe Nascimento
 *
 */

@RestController
@RequestMapping("/v1/linha")
public class LinhaController implements Serializable {
	private static final long serialVersionUID = -7225113896873434488L;
	private transient AdministradoraComponent administradoraComponent;
	private transient LinhaComponent linhaComponent;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param administradoraComponent - {@link AdministradoraComponent} - serviço de administradoras
	 * @param linhaComponent - {@link LinhaComponent} - serviço de linhas
	 * 
	 */
	public LinhaController(AdministradoraComponent administradoraComponent, LinhaComponent linhaComponent) {
		this.administradoraComponent = administradoraComponent;
		this.linhaComponent = linhaComponent;
	}
	
	/**
	 * Método responsável pelo cadastramento das linhas no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link PathVariable} / {@link String} - nome da administradora que a linha será atrelada
	 * @param linhaDTO - {@link RequestBody} / {@link Valid} / {@link LinhaDTO} - dados da linha
	 * 
	 * @return dados da linha em json em caso de sucesso
	 * 
	 */
	@PostMapping("/cadastrar-linha/{nomeAdministradora}")
	public ResponseEntity<Object> saveLinha(@PathVariable String nomeAdministradora, @RequestBody @Valid LinhaDTO linhaDTO) {
		
		Optional<AdministradoraEntity> optionalAdminEntity = administradoraComponent.findByNomeAdministradora(nomeAdministradora);
		
		if (!optionalAdminEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		if (linhaComponent.existsByNumeroLinha(linhaDTO.getNumeroLinha())) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Já existe uma linha cadastrada com este número.", HttpStatus.CONFLICT, HttpStatus.CONFLICT.value()));
		}
		
		LinhaEntity linhaEntity = new LinhaEntity();
		BeanUtils.copyProperties(linhaDTO, linhaEntity);
		linhaEntity.setNomeAdministradora(optionalAdminEntity.get());
		linhaComponent.save(linhaEntity);
		
		return ResponseEntity.status(HttpStatus.OK)
			.body(new ResponseMessages("Linha cadastrada com sucesso.", HttpStatus.CREATED, HttpStatus.CREATED.value()));
		
	}
	
	/**
	 * Método responsável pela busca de todas as linhas atreladas
	 * a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link PathVariable} / {@link String} - nome da administradora que as linhas estão atreladas
	 * 
	 * @return dados de todas as linhas em json
	 * 
	 */
	@GetMapping("/listar-linhas/{nomeAdministradora}")
	public ResponseEntity<Object> listTodasLinhas(@PathVariable String nomeAdministradora) {
		
		Optional<AdministradoraEntity> optionalAdminEntity = administradoraComponent.findByNomeAdministradora(nomeAdministradora);
		
		if (!optionalAdminEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		List<LinhaEntity> listaLinhaEntity = linhaComponent.findLinhasByNomeAdministradora(optionalAdminEntity.get().getNomeAdministradora());
		
		if (listaLinhaEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma linha foi encontrada.", HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.value()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listaLinhaEntity);
		
	}
	
	/**
	 * Método responsável pela busca de uma linha por nome atrelada a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link PathVariable} / {@link String} - nome da administradora que a linha está atrelada
	 * @param nome - {@link PathVariable} / {@link String} - nome da linha que será buscada
	 * 
	 * @return dados da linha em json
	 * 
	 */
	@GetMapping("/listar-por-nome/{nomeAdministradora}/{nome}")
	public ResponseEntity<Object> listLinhaPorNome(@PathVariable String nomeAdministradora, @PathVariable String nome) {
		
		Optional<AdministradoraEntity> optionalAdminEntity = administradoraComponent.findByNomeAdministradora(nomeAdministradora);
		
		if (!optionalAdminEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		Optional<LinhaEntity> optionalLinhaEntity = linhaComponent.findLinhaPorNomeByNomeAdministradora(optionalAdminEntity.get().getNomeAdministradora(), nome);
		
		if (!optionalLinhaEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma linha foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(optionalLinhaEntity);
		
	}
	
	/**
	 * Método responsável pela busca de uma linha por cor atrelada a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link PathVariable} / {@link String} - nome da administradora que a linha está atrelada
	 * @param cor - {@link PathVariable} / {@link String} - cor da linha que será buscada
	 * 
	 * @return dados da linha em json
	 * 
	 */
	@GetMapping("/listar-por-cor/{nomeAdministradora}/{cor}")
	public ResponseEntity<Object> listLinhaPorCor(@PathVariable String nomeAdministradora, @PathVariable String cor) {
		
		Optional<AdministradoraEntity> optionalAdminEntity = administradoraComponent.findByNomeAdministradora(nomeAdministradora);
		
		if (!optionalAdminEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		Optional<LinhaEntity> optionalLinhaEntity = linhaComponent.findLinhaPorCorByNomeAdministradora(optionalAdminEntity.get().getNomeAdministradora(), cor);
		
		if (!optionalLinhaEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma linha foi encontrada com esta cor.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(optionalLinhaEntity);
		
	}
	
}