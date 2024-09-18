package br.com.apilinhasmetrocptm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import br.com.apilinhasmetrocptm.entity.LinhaEntity;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

/**
 * Interface repository para implementação dos métodos de validação
 * do cadastro e dos métodos de consulta de linhas.
 * 
 * @author Felipe Nascimento
 * 
 */

@Repository
public interface LinhaRepository extends JpaRepository<LinhaEntity, String> {
	
	/**
	 * Método responsável por verificar se a linha a ser cadastrada já consta no banco de dados.
	 * 
	 * @param numeroLinha - {@link String} - número da linha a ser verificado
	 * 
	 * @return true se a linha já estiver cadastrada / false se a linha não estiver cadastrada
	 * 
	 */
	public boolean existsByNumeroLinha(String numeroLinha);
	
	/**
	 * Método responsável pela busca de uma linha atrelada a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora que a linha está atrelada
	 * @param numeroLinha - {@link String} - número da linha atrelado a administradora
	 * 
	 * @return informações da linha
	 * 
	 */
	@Query(value = "select id, nome_administradora, numero_linha, nome, nome_ingles, cor, cor_hexadecimal from tbl_linhas inner join tbl_administradoras on tbl_linhas.nome_administradora = tbl_administradoras.nome_administradora where tbl_linhas.numero_linha = :numeroLinha", nativeQuery = true)
	public Optional<LinhaEntity> findByNumeroLinha(@Param("numeroLinha") String numeroLinha);
	
	/**
	 * Método responsável pela busca de todas as linhas atreladas a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora que as linhas estão atreladas
	 * 
	 * @return informações das linhas
	 * 
	 */
	@Query(value = "select * from tbl_linhas inner join tbl_administradoras on tbl_linhas.nome_administradora = tbl_administradoras.nome_administradora where tbl_administradoras.nome_administradora = :nomeAdministradora", nativeQuery = true)
	public List<LinhaEntity> findLinhasByNomeAdministradora(@Param("nomeAdministradora") String nomeAdministradora);
	
	/**
	 * Método responsável pela busca de uma linha por nome atrelada a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora que a linha está atrelada
	 * @param nome - {@link String} - nome da linha que será buscada
	 * 
	 * @return informação da linha
	 * 
	 */
	@Query(value = "select id, nome_administradora, numero_linha, nome, nome_ingles, cor, cor_hexadecimal from tbl_linhas inner join tbl_administradoras on tbl_linhas.nome_administradora = tbl_administradoras.nome_administradora where tbl_administradoras.nome_administradora = :nomeAdministradora and tbl_linhas.nome = :nome", nativeQuery = true)
	public Optional<LinhaEntity> findLinhaPorNomeByNomeAdministradora(@Param("nomeAdministradora") String nomeAdministradora, @Param("nome") String nome);
	
	/**
	 * Método responsável pela busca de uma linha por cor atrelada a uma determinada administradora no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora que a linha está atrelada
	 * @param cor - {@link String} - cor da linha que será buscada
	 * 
	 * @return informação da linha
	 * 
	 */
	@Query(value = "select id, nome_administradora, numero_linha, nome, nome_ingles, cor, cor_hexadecimal from tbl_linhas inner join tbl_administradoras on tbl_linhas.nome_administradora = tbl_administradoras.nome_administradora where tbl_administradoras.nome_administradora = :nomeAdministradora and tbl_linhas.cor = :cor", nativeQuery = true)
	public Optional<LinhaEntity> findLinhaPorCorByNomeAdministradora(@Param("nomeAdministradora") String nomeAdministradora, @Param("cor") String cor);
	
}