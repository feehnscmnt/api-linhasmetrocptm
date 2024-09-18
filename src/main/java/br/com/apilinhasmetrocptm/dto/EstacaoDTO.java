package br.com.apilinhasmetrocptm.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe DTO para o tráfego dos dados das estações.
 * 
 * @author Felipe Nascimento
 * 
 */

public class EstacaoDTO implements Comparator<EstacaoDTO>, Serializable {
	private static final long serialVersionUID = -7870385423638144894L;

	@NotNull
	private UUID id;
	
	@NotBlank
	private String nomeEstacao;
	
	@NotBlank
	private String cidadeEstacao;
	
	/**
	 * Retorna o atributo id.
	 * 
	 * @return o id da estação do tipo {@link UUID}.
	 * 
	 */
	public UUID getId() {
		return id;
	}
	
	/**
	 * Especifica o atributo id.
	 * .
	 * @param id {@link UUID} referente ao id da estação que será setado.
	 * 
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	
	/**
	 * Retorna o atributo nomeEstacao.
	 * 
	 * @return o nome da estação do tipo {@link String}.
	 * 
	 */
	public String getNomeEstacao() {
		return nomeEstacao;
	}
	
	/**
	 * Especifica o atributo nomeEstacao.
	 * .
	 * @param nomeEstacao {@link String} referente ao nome da estação que será setado.
	 * 
	 */
	public void setNomeEstacao(String nomeEstacao) {
		this.nomeEstacao = nomeEstacao;
	}
	
	/**
	 * Retorna o atributo cidadeEstacao.
	 * 
	 * @return a cidade da estação do tipo {@link String}.
	 * 
	 */
	public String getCidadeEstacao() {
		return cidadeEstacao;
	}
	
	/**
	 * Especifica o atributo cidadeEstacao.
	 * .
	 * @param cidadeEstacao {@link String} referente a cidade da estação que será setada.
	 * 
	 */
	public void setCidadeEstacao(String cidadeEstacao) {
		this.cidadeEstacao = cidadeEstacao;
	}
	
	/**
	 * Método comparador booleano.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		
		if (object == null || getClass() != object.getClass()) return false;
		
		EstacaoDTO estacaoDTO = (EstacaoDTO) object;
		
		return Objects.equals(id, estacaoDTO.id)
			&& Objects.equals(nomeEstacao, estacaoDTO.nomeEstacao)
			&& Objects.equals(cidadeEstacao, estacaoDTO.cidadeEstacao);
	}
	
	/**
	 * Método organizador dos atributos.
	 */
	@Override
    public int hashCode() {
		return Objects.hash(
			id,
			nomeEstacao,
			cidadeEstacao
		);
	}
	
	/**
	 * Método de representação textual da classe.
	 */
	@Override
	public String toString() {
		return String.valueOf(new StringBuilder()
			.append("Id: ").append(id)
			.append(", Nome da Estação: ").append(nomeEstacao)
			.append(", Cidade da Estação: ").append(cidadeEstacao));
	}
	
	/**
	 * Método comparador inteiro.
	 */
	@Override
	public int compare(EstacaoDTO estacaoDTO1, EstacaoDTO estacaoDTO2) {
		return estacaoDTO1.getNomeEstacao().compareTo(estacaoDTO2.getNomeEstacao());
	}
	
}