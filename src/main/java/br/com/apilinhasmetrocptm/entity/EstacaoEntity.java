package br.com.apilinhasmetrocptm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe entity responsável por gerar a tabela de estações no banco de dados.
 * 
 * @author Felipe Nascimento
 * 
 */

@Entity
@Table(name = "TBL_ESTACOES")
public class EstacaoEntity implements Comparator<EstacaoEntity>, Serializable {
	private static final long serialVersionUID = -8278604726357293413L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "numeroLinha", nullable = false)
	private LinhaEntity numeroLinha;
	
	@Column(nullable = false, length = 50)
	private String nomeEstacao;
	
	@Column(nullable = false, length = 50)
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
	 * Retorna o atributo numeroLinha.
	 * 
	 * @return o objeto da linha do tipo {@link LinhaEntity}.
	 * 
	 */
	public LinhaEntity getNumeroLinha() {
		return numeroLinha;
	}
	
	/**
	 * Especifica o atributo numeroLinha.
	 * .
	 * @param numeroLinha {@link LinhaEntity} referente ao objeto da linha que será setado.
	 * 
	 */
	public void setNumeroLinha(LinhaEntity numeroLinha) {
		this.numeroLinha = numeroLinha;
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
	 * @param nome {@link String} referente ao nome da estação que será setado.
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
	 * Especifica o atributo cidade.
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
		
		EstacaoEntity estacaoEntity = (EstacaoEntity) object;
		
		return Objects.equals(id, estacaoEntity.id)
			&& Objects.equals(numeroLinha.getNumeroLinha(), estacaoEntity.numeroLinha.getNumeroLinha())
			&& Objects.equals(nomeEstacao, estacaoEntity.nomeEstacao)
			&& Objects.equals(cidadeEstacao, estacaoEntity.cidadeEstacao);
	}
	
	/**
	 * Método organizador dos atributos.
	 */
	@Override
    public int hashCode() {
		return Objects.hash(
			id,
			numeroLinha.getNumeroLinha(),
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
			.append(", Número da Linha: ").append(numeroLinha.getNumeroLinha())
			.append(", Nome da Estação: ").append(nomeEstacao)
			.append(", Cidade da Estação: ").append(cidadeEstacao));
	}
	
	/**
	 * Método comparador inteiro.
	 */
	@Override
	public int compare(EstacaoEntity estacaoEntity1, EstacaoEntity estacaoEntity2) {
		return estacaoEntity1.getNomeEstacao().compareTo(estacaoEntity2.getNomeEstacao());
	}
	
}