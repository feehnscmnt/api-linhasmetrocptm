package br.com.apilinhasmetrocptm.entity;

import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.List;
import java.util.UUID;

/**
 * Classe entity responsável por gerar a tabela de administradoras no banco de dados.
 * 
 * @author Felipe Nascimento
 * 
 */

@Entity
@Table(name = "TBL_ADMINISTRADORAS")
public class AdministradoraEntity implements Comparator<AdministradoraEntity>, Serializable {
	private static final long serialVersionUID = -1441123734437739651L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@Id
	@Column(nullable = false, length = 100)
	private String nomeAdministradora;
	
	@OneToMany
	@JoinColumn(name = "nomeAdministradora")
	private List<LinhaEntity> linhas;
	
	/**
	 * Retorna o atributo id.
	 * 
	 * @return o id do administradora do tipo {@link UUID}.
	 * 
	 */
	public UUID getId() {
		return id;
	}
	
	/**
	 * Especifica o atributo id.
	 * .
	 * @param id {@link UUID} referente ao id do administradora que será setado.
	 * 
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	
	/**
	 * Retorna o atributo nomeAdministradora.
	 * 
	 * @return o nome da administradora da linha do tipo {@link String}.
	 * 
	 */
	public String getNomeAdministradora() {
		return nomeAdministradora;
	}
	
	/**
	 * Especifica o atributo nomeAdministradora.
	 * .
	 * @param nomeAdministradora {@link String} referente ao nome da administradora da linha que será setado.
	 * 
	 */
	public void setNomeAdministradora(String nomeAdministradora) {
		this.nomeAdministradora = nomeAdministradora;
	}
	
	/**
	 * Retorna o atributo linhas.
	 * 
	 * @return a lista de linhas da administradora do tipo {@lin List}.
	 * 
	 */
	public List<LinhaEntity> getLinhas() {
		return linhas;
	}
	
	/**
	 * Especifica o atributo linhas.
	 * .
	 * @param linhas {@link List} referente à lista de linhas da administradora que será setada.
	 * 
	 */
	public void setLinhas(List<LinhaEntity> linhas) {
		this.linhas = linhas;
	}
	
	/**
	 * Método comparador booleano.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		
		if (object == null || getClass() != object.getClass()) return false;
		
		AdministradoraEntity administradoraEntity = (AdministradoraEntity) object;
		
		return Objects.equals(id, administradoraEntity.id)
			&& Objects.equals(nomeAdministradora, administradoraEntity.nomeAdministradora)
			&& Objects.equals(linhas, administradoraEntity.linhas);
	}
	
	/**
	 * Método organizador dos atributos.
	 */
	@Override
    public int hashCode() {
		return Objects.hash(
			id,
			nomeAdministradora,
			linhas
		);
	}
	
	/**
	 * Método de representação textual da classe.
	 */
	@Override
	public String toString() {
		return String.valueOf(new StringBuilder()
			.append("Id: ").append(id)
			.append(", Nome da Administradora: ").append(nomeAdministradora)
			.append(", Linhas da Administradora: ").append(linhas));
	}
	
	/**
	 * Método comparador inteiro.
	 */
	@Override
	public int compare(AdministradoraEntity administradoraEntity1, AdministradoraEntity administradoraEntity2) {
		return administradoraEntity1.getNomeAdministradora().compareTo(administradoraEntity2.getNomeAdministradora());
	}
	
}