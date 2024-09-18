package br.com.apilinhasmetrocptm.component;

import br.com.apilinhasmetrocptm.repository.EstacaoRepository;
import br.com.apilinhasmetrocptm.entity.EstacaoEntity;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Optional;
import java.util.List;

/**
 * Classe component responsável pela implementação dos métodos de cadastro,
 * validação e consulta de estações.
 * 
 * @author Felipe Nascimento
 * 
 */

@Component
public class EstacaoComponent implements Serializable {
	private static final long serialVersionUID = -6961827655182845972L;
	private transient EstacaoRepository estacaoRepository;
	
	/**
	 * Construtor da classe parametrizado para injeção dos repositories.
	 * 
	 * @param estacaoRepository - {@link EstacaoRepository} - repositório de estações
	 * 
	 */
	public EstacaoComponent(EstacaoRepository estacaoRepository) {
		this.estacaoRepository = estacaoRepository;
	}
	
	/**
	 * Método responsável por salvar as estações no banco de dados.
	 * 
	 * @param estacaoEntity - {@link EstacaoEntity} - dados das estações
	 * 
	 * @return dados das estações em json em caso de sucesso
	 * 
	 */
	@Transactional
	public EstacaoEntity save(EstacaoEntity estacaoEntity) {
		return estacaoRepository.save(estacaoEntity);
	}
	
	/**
	 * Método responsável pela busca de todas as estações atreladas a uma determinada linha no banco de dados.
	 * 
	 * @param numeroLinha - {@link String} - número da linha que a estação está atrelada
	 * 
	 * @return informações das linhas
	 * 
	 */
	public List<EstacaoEntity> findEstacoesByNumeroLinha(String numeroLinha) {
		return estacaoRepository.findEstacoesByNumeroLinha(numeroLinha);
	}
	
	/**
	 * Método responsável pela busca de uma estação pelo nome.
	 * 
	 * @param nomeEstacao - {@link String} - nome da estação que será buscada
	 * 
	 * @return informações da estação
	 * 
	 */
	public Optional<EstacaoEntity> findByNomeEstacao(String nomeEstacao) {
		return estacaoRepository.findByNomeEstacao(nomeEstacao); 
	}
	
	/**
	 * Método responsável pela busca de todas as estações no banco de dados.
	 * 
	 * @param pageable - {@link Pageable} - paginação da busca
	 * 
	 * @return informações das estações
	 * 
	 */
	public Page<EstacaoEntity> findAll(Pageable pageable) {
		return estacaoRepository.findAll(pageable);
	}
	
}