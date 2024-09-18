package br.com.apilinhasmetrocptm.component;

import br.com.apilinhasmetrocptm.repository.LinhaRepository;
import br.com.apilinhasmetrocptm.entity.LinhaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;
import java.util.List;

/**
 * Classe component responsável pela implementação dos métodos de cadastro e consulta de linhas.
 * 
 * @author Felipe Nascimento
 * 
 */

@Component
public class LinhaComponent implements Serializable {
	private static final long serialVersionUID = 4737211385815000965L;
	private transient LinhaRepository linhaRepository;
	
	/**
	 * Construtor da classe parametrizado para injeção dos repositories.
	 * 
	 * @param linhaRepository - {@link LinhaRepository} - repositório de linhas
	 * 
	 */
	public LinhaComponent(LinhaRepository linhaRepository) {
		this.linhaRepository = linhaRepository;
	}
	
	/**
	 * Método responsável por verificar se a linha a ser cadastrada já consta no banco de dados.
	 * 
	 * @param numeroLinha - {@link String} - número da linha a ser verificado
	 * 
	 * @return true se a linha já estiver cadastrada / false se a linha não estiver cadastrada
	 * 
	 */
	public boolean existsByNumeroLinha(String numeroLinha) {
		return linhaRepository.existsByNumeroLinha(numeroLinha);
	}
	
	/**
	 * Método responsável por salvar as linhas no banco de dados.
	 * 
	 * @param linhaEntity - {@link LinhaEntity} - dados da linha
	 * 
	 * @return dados da linha em json em caso de sucesso
	 * 
	 */
	@Transactional
	public LinhaEntity save(LinhaEntity linhaEntity) {
		return linhaRepository.save(linhaEntity);
	}
	
	/**
	 * Método responsável pela busca de uma linha atrelada a uma determinada administradora no banco de dados.
	 * 
	 * @param numeroLinha - {@link String} - número da linha atrelado a administradora
	 * 
	 * @return informações da linha
	 * 
	 */
	public Optional<LinhaEntity> findByNumeroLinha(String numeroLinha) {
		return linhaRepository.findByNumeroLinha(numeroLinha);
	}
	
	/**
	 * Método responsável pela busca de todas as linhas atreladas a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora que as linhas estão atreladas
	 * 
	 * @return informações das linhas
	 * 
	 */
	public List<LinhaEntity> findLinhasByNomeAdministradora(String nomeAdministradora) {
		return linhaRepository.findLinhasByNomeAdministradora(nomeAdministradora);
	}
	
	/**
	 * Método responsável pela busca de uma linha por nome atrelada a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora que a linha está atrelada
	 * @param nome - {@link String} - nome da linha que será buscada
	 * 
	 * @return informação da linha
	 * 
	 */
	public Optional<LinhaEntity> findLinhaPorNomeByNomeAdministradora(String nomeAdministradora, String nome) {
		return linhaRepository.findLinhaPorNomeByNomeAdministradora(nomeAdministradora, nome);
	}
	
	/**
	 * Método responsável pela busca de uma linha por cor atrelada a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora que a linha está atrelada
	 * @param cor - {@link String} - cor da linha que será buscada
	 * 
	 * @return informação da linha
	 * 
	 */
	public Optional<LinhaEntity> findLinhaPorCorByNomeAdministradora(String nomeAdministradora, String cor) {
		return linhaRepository.findLinhaPorCorByNomeAdministradora(nomeAdministradora, cor);
	}
	
	/**
	 * Método responsável pela busca de todas as linhas no banco de dados.
	 * 
	 * @param pageable - {@link Pageable} - paginação da busca
	 * 
	 * @return informações das linhas
	 * 
	 */
	public Page<LinhaEntity> findAll(Pageable pageable) {
		return linhaRepository.findAll(pageable);
	}
	
}