package br.com.apilinhasmetrocptm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import br.com.apilinhasmetrocptm.entity.EstacaoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

/**
 * Interface repository para implementação dos métodos de validação
 * do cadastro e dos métodos de consulta das estações.
 * 
 * @author Felipe Nascimento
 * 
 */

@Repository
public interface EstacaoRepository extends JpaRepository<EstacaoEntity, UUID> {
	
	/**
	 * Método responsável pela busca de todas as estações atreladas a uma determinada linha no banco de dados.
	 * 
	 * @param numeroLinha - {@link String} - número da linha que a estação está atrelada
	 * 
	 * @return informações das estações
	 * 
	 */
	@Query(value = "select * from tbl_estacoes inner join tbl_linhas on tbl_estacoes.numero_linha = tbl_linhas.numero_linha where tbl_linhas.numero_linha = :numeroLinha", nativeQuery = true)
	public List<EstacaoEntity> findEstacoesByNumeroLinha(@Param("numeroLinha") String numeroLinha);
	
	/**
	 * Método responsável pela busca de uma estação pelo nome.
	 * 
	 * @param nomeEstacao - {@link String} - nome da estação que será buscada
	 * 
	 * @return informações da estação
	 * 
	 */
	@Query(value = "select id, numero_linha, nome_estacao, cidade_estacao from tbl_estacoes inner join tbl_linhas on tbl_estacoes.numero_linha = tbl_linhas.numero_linha where tbl_estacoes.nome_estacao = :nomeEstacao", nativeQuery = true)
	public Optional<EstacaoEntity> findByNomeEstacao(@Param("nomeEstacao") String nomeEstacao);
	
}