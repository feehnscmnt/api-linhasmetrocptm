package br.com.apilinhasmetrocptm.component;

import br.com.apilinhasmetrocptm.repository.AdministradoraRepository;
import br.com.apilinhasmetrocptm.entity.AdministradoraEntity;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;

/**
 * Classe component responsável pela implementação dos métodos de cadastro,
 * validação e consulta de administradoras.
 * 
 * @author Felipe Nascimento
 * 
 */

@Component
public class AdministradoraComponent implements Serializable {
	private transient AdministradoraRepository administradoraRepository;
	private static final long serialVersionUID = 7582546076052867843L;
	
	/**
	 * Construtor da classe parametrizado para injeção dos repositories.
	 * 
	 * @param administradoraRepository - {@link AdministradoraRepository} - repositório de administradoras
	 * 
	 */
	public AdministradoraComponent(AdministradoraRepository administradoraRepository) {
		this.administradoraRepository = administradoraRepository;
	}
	
	/**
	 * Método responsável por verificar se a administradora a ser cadastrada já consta no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora a ser verificado
	 * 
	 * @return true se a administradora já estiver cadastrada / false se a administradora não estiver cadastrada
	 * 
	 */
	public boolean existsByNomeAdministradora(String nomeAdministradora) {
		return administradoraRepository.existsByNomeAdministradora(nomeAdministradora);
	}
	
	/**
	 * Método responsável por salvar as administradoras no banco de dados.
	 * 
	 * @param administradoraEntity - {@link AdministradoraEntity} - dados da administradora
	 * 
	 * @return dados da administradora em json em caso de sucesso
	 * 
	 */
	@Transactional
	public AdministradoraEntity save(AdministradoraEntity administradoraEntity) {
		return administradoraRepository.save(administradoraEntity);
	}
	
	/**
	 * Método responsável pela busca de administradoras por nome no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora que será buscada
	 * 
	 * @return informações da administradora
	 * 
	 */
	public Optional<AdministradoraEntity> findByNomeAdministradora(String nomeAdministradora) {
		return administradoraRepository.findByNomeAdministradora(nomeAdministradora);
	}
	
	/**
	 * Método responsável pela busca de todas as administradoras no banco de dados.
	 * 
	 * @param pageable - {@link Pageable} - paginação da busca
	 * 
	 * @return informações das administradoras
	 * 
	 */
	public Page<AdministradoraEntity> findAll(Pageable pageable) {
		return administradoraRepository.findAll(pageable);
	}
	
}