package br.com.apilinhasmetrocptm.repository;

import br.com.apilinhasmetrocptm.entity.AdministradoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Interface repository para implementação dos métodos de validação
 * do cadastro e do métodos de consulta de administradoras.
 * 
 * @author Felipe Nascimento
 * 
 */

@Repository
public interface AdministradoraRepository extends JpaRepository<AdministradoraEntity, String> {
	
	/**
	 * Método responsável por verificar se a administradora a ser cadastrada já consta no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora a ser verificado
	 * 
	 * @return true se a administradora já estiver cadastrada / false se a administradora não estiver cadastrada
	 * 
	 */
	public boolean existsByNomeAdministradora(String nomeAdministradora);
	
	/**
	 * Método responsável pela busca de administradoras por nome no banco de dados.
	 * 
	 * @param nomeAdministradora - {@link String} - nome da administradora que será buscada
	 * 
	 * @return informações da administradora
	 * 
	 */
	public Optional<AdministradoraEntity> findByNomeAdministradora(String nomeAdministradora);
	
}