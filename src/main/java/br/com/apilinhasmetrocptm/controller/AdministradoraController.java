package br.com.apilinhasmetrocptm.controller;

import br.com.apilinhasmetrocptm.component.AdministradoraComponent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.apilinhasmetrocptm.entity.AdministradoraEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.apilinhasmetrocptm.model.ResponseMessages;
import br.com.apilinhasmetrocptm.dto.AdministradoraDTO;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Optional;
import java.util.List;

/**
 * Classe controller responsável pelo cadastro e pela consulta de administradoras.
 * 
 * @author Felipe Nascimento
 *
 */

@RestController
@RequestMapping("/v1/administradora")
public class AdministradoraController implements Serializable {
	private static final long serialVersionUID = 3199862102735651252L;
	private transient AdministradoraComponent administradoraComponent;
	
	/**
	 * Contrutor da classe.
	 * 
	 * @param administradoraComponent - {@link AdministradoraComponent} - serviço de administradoras
	 * 
	 */
	public AdministradoraController(AdministradoraComponent administradoraComponent) {
		this.administradoraComponent = administradoraComponent;
	}
	
	/**
	 * Método responsável pelo cadastramento das administradoras no banco de dados.
	 * 
	 * @param administradoraDTO - {@link RequestBody} / {@link Valid} / {@link AdministradoraDTO} - dados da administradora
	 * 
	 * @return dados da administradora em json em caso de sucesso
	 * 
	 */
	@PostMapping("/cadastrar-administradora")
	public ResponseEntity<Object> saveAdministradora(@RequestBody @Valid AdministradoraDTO administradoraDTO) {
		
		if (administradoraComponent.existsByNomeAdministradora(administradoraDTO.getNomeAdministradora())) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Já existe uma administradora cadastrada com este nome.", HttpStatus.CONFLICT, HttpStatus.CONFLICT.value()));
		}
		
		AdministradoraEntity administradoraEntity = new AdministradoraEntity();
		BeanUtils.copyProperties(administradoraDTO, administradoraEntity);
		administradoraComponent.save(administradoraEntity);
		
		return ResponseEntity.status(HttpStatus.OK)
			.body(new ResponseMessages("Administradora cadastrada com sucesso.", HttpStatus.CREATED, HttpStatus.CREATED.value()));
		
	}
	
	/**
	 * Método responsável pela busca de todas as administradoras no banco de dados.
	 * 
	 * @param pageable - {@link Pageable} - paginação da busca
	 * 
	 * @return dados de todas as administradoras em json
	 * 
	 */
	@GetMapping("/listar-administradoras")
	public ResponseEntity<Object> listAdministradoras(@PageableDefault(page = 0, size = 10, sort = "nomeAdministradora", direction = Sort.Direction.ASC) Pageable pageable) {
		
		List<AdministradoraEntity> listaAdministradoras = administradoraComponent.findAll(pageable).getContent();
		
		if (listaAdministradoras.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma administradora foi encontrada.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(listaAdministradoras);
		
	}
	
	/**
	 * Método responsável pela busca de administradoras por nome no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link PathVariable} / {@link String} - nome da administradora que será buscado
	 * 
	 * @return dados da dministradora em json
	 * 
	 */
	@GetMapping("/listar-por-nome/{nomeAdministradora}")
	public ResponseEntity<Object> listAdministradoraPorNome(@PathVariable String nomeAdministradora) {
		
		Optional<AdministradoraEntity> optionalAdminEntity = administradoraComponent.findByNomeAdministradora(nomeAdministradora);
		
		if (!optionalAdminEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("Nenhuma administradora foi encontrada com este nome.", HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value()));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(optionalAdminEntity.get());
		
	}
	
}