package br.com.apilinhasmetrocptm.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe DTO para o tráfego dos dados das administradoras.
 * 
 * @author Felipe Nascimento
 * 
 */

public class AdministradoraDTO implements Comparator<AdministradoraDTO>, Serializable {
	private static final long serialVersionUID = 3113495509207198250L;

	@NotNull
	private UUID id;
	
	@NotBlank
	private String nomeAdministradora;
	
	/**
	 * Retorna o atributo id.
	 * 
	 * @return o id da administradora do tipo {@link UUID}.
	 * 
	 */
	public UUID getId() {
		return id;
	}
	
	/**
	 * Especifica o atributo id.
	 * .
	 * @param id {@link UUID} referente ao id da administradora que será setado.
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
	 * Método comparador booleano.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		
		if (object == null || getClass() != object.getClass()) return false;
		
		AdministradoraDTO administradoraDTO = (AdministradoraDTO) object;
		
		return Objects.equals(id, administradoraDTO.id)
			&& Objects.equals(nomeAdministradora, administradoraDTO.nomeAdministradora);
	}
	
	/**
	 * Método organizador dos atributos.
	 */
	@Override
    public int hashCode() {
		return Objects.hash(
			id,
			nomeAdministradora
		);
	}
	
	/**
	 * Método de representação textual da classe.
	 */
	@Override
	public String toString() {
		return String.valueOf(new StringBuilder()
			.append("Id: ").append(id)
			.append(", Nome da Administradora: ").append(nomeAdministradora));
	}
	
	/**
	 * Método comparador inteiro.
	 */
	@Override
	public int compare(AdministradoraDTO administradoraDTO1, AdministradoraDTO administradoraDTO2) {
		return administradoraDTO1.getNomeAdministradora().compareTo(administradoraDTO2.getNomeAdministradora());
	}
	
}