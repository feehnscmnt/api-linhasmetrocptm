package br.com.apilinhasmetrocptm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * Classe entity responsável por gerar a tabela de linhas no banco de dados.
 * 
 * @author Felipe Nascimento
 * 
 */

@Entity
@Table(name = "TBL_LINHAS")
public class LinhaEntity implements Comparator<LinhaEntity>, Serializable {
	private static final long serialVersionUID = -3419591427147645729L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "nomeAdministradora", nullable = false)
	private AdministradoraEntity nomeAdministradora;
	
	@Id
	@Column(nullable = false, length = 3)
	private String numeroLinha;
	
	@Column(nullable = false, length = 50)
	private String nome;
	
	@Column(nullable = false, length = 50)
	private String nomeIngles;
	
	@Column(nullable = false, length = 15)
	private String cor;
	
	@Column(nullable = false, length = 15)
	private String corHexadecimal;
	
	@OneToMany
	@JoinColumn(name = "numeroLinha")
	private List<EstacaoEntity> estacoes;
	
	/**
	 * Retorna o atributo id.
	 * 
	 * @return o id do estabelecimento do tipo {@link UUID}.
	 * 
	 */
	public UUID getId() {
		return id;
	}
	
	/**
	 * Especifica o atributo id.
	 * .
	 * @param id {@link UUID} referente ao id do estabelecimento que será setado.
	 * 
	 */
	public void setId(UUID id) {
		this.id = id;
	}
	
	/**
	 * Retorna o atributo nomeAdministradora.
	 * 
	 * @return o objeto da administradora do tipo {@link AdministradoraEntity}.
	 * 
	 */
	public AdministradoraEntity getNomeAdministradora() {
		return nomeAdministradora;
	}
	
	/**
	 * Especifica o atributo nomeAdministradora.
	 * .
	 * @param nomeAdministradora {@link AdministradoraEntity} referente ao objeto da administradora que será setado.
	 * 
	 */
	public void setNomeAdministradora(AdministradoraEntity nomeAdministradora) {
		this.nomeAdministradora = nomeAdministradora;
	}
	
	/**
	 * Retorna o atributo numeroLinha.
	 * 
	 * @return o número da linha do tipo {@link String}.
	 * 
	 */
	public String getNumeroLinha() {
		return numeroLinha;
	}
	
	/**
	 * Especifica o atributo numeroLinha.
	 * .
	 * @param numeroLinha {@link String} referente ao número da linha que será setado.
	 * 
	 */
	public void setNumeroLinha(String numeroLinha) {
		this.numeroLinha = numeroLinha;
	}
	
	/**
	 * Retorna o atributo nome.
	 * 
	 * @return o nome da linha do tipo {@link String}.
	 * 
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Especifica o atributo nome.
	 * .
	 * @param nome {@link String} referente ao nome da linha que será setado.
	 * 
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Retorna o atributo nomeIngles.
	 * 
	 * @return o nome da linha em inglês do tipo {@link String}.
	 * 
	 */
	public String getNomeIngles() {
		return nomeIngles;
	}
	
	/**
	 * Especifica o atributo nomeIngles.
	 * .
	 * @param nomeIngles {@link String} referente ao nome da linha em inglês que será setado.
	 * 
	 */
	public void setNomeIngles(String nomeIngles) {
		this.nomeIngles = nomeIngles;
	}
	
	/**
	 * Retorna o atributo cor.
	 * 
	 * @return a cor da linha do tipo {@link String}.
	 * 
	 */
	public String getCor() {
		return cor;
	}
	
	/**
	 * Especifica o atributo cor.
	 * .
	 * @param cor {@link String} referente a cor da linha que será setada.
	 * 
	 */
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	/**
	 * Retorna o atributo corHexadecimal.
	 * 
	 * @return a cor hexadecimal da linha do tipo {@link String}.
	 * 
	 */
	public String getCorHexadecimal() {
		return corHexadecimal;
	}
	
	/**
	 * Especifica o atributo corHexadecimal.
	 * .
	 * @param cor {@link String} referente a cor hexadecimal da linha que será setada.
	 * 
	 */
	public void setCorHexadecimal(String corHexadecimal) {
		this.corHexadecimal = corHexadecimal;
	}
	
	/**
	 * Retorna o atributo estacoes.
	 * 
	 * @return a lista de estações da linha do tipo {@link List}.
	 * 
	 */
	public List<EstacaoEntity> getEstacoes() {
		return estacoes;
	}
	
	/**
	 * Especifica o atributo estacoes.
	 * .
	 * @param estacoes {@link List} referente à lista de estações da linha que será setada.
	 * 
	 */
	public void setEstacoes(List<EstacaoEntity> estacoes) {
		this.estacoes = estacoes;
	}
	
	/**
	 * Método comparador booleano.
	 */
	@Override
    public boolean equals(Object object) {
		if (this == object) return true;
		
		if (object == null || getClass() != object.getClass()) return false;
		
		LinhaEntity linhaEntity = (LinhaEntity) object;
		
		return Objects.equals(id, linhaEntity.id)
			&& Objects.equals(nomeAdministradora.getNomeAdministradora(), linhaEntity.nomeAdministradora.getNomeAdministradora())
			&& Objects.equals(numeroLinha, linhaEntity.numeroLinha)
			&& Objects.equals(nome, linhaEntity.nome)
			&& Objects.equals(nomeIngles, linhaEntity.nomeIngles)
			&& Objects.equals(cor, linhaEntity.cor)
			&& Objects.equals(corHexadecimal, linhaEntity.corHexadecimal)
			&& Objects.equals(estacoes, linhaEntity.estacoes);
	}
	
	/**
	 * Método organizador dos atributos.
	 */
	@Override
    public int hashCode() {
		return Objects.hash(
			id,
			nomeAdministradora.getNomeAdministradora(),
			numeroLinha,
			nome,
			nomeIngles,
			cor,
			corHexadecimal,
			estacoes
		);
	}
	
	/**
	 * Método de representação textual da classe.
	 */
	@Override
	public String toString() {
		return String.valueOf(new StringBuilder()
			.append("Id: ").append(id)
			.append(", Nome da Administradora: ").append(nomeAdministradora.getNomeAdministradora())
			.append(", Número da Linha: ").append(numeroLinha)
			.append(", Nome da Linha: ").append(nome)
			.append(", Nome da Linha em Inglês: ").append(nomeIngles)
			.append(", Cor da Linha: ").append(cor)
			.append(", Cor Hexadecimal da Linha: ").append(corHexadecimal)
			.append(", Estações: ").append(estacoes));
	}
	
	/**
	 * Método comparador inteiro.
	 */
	@Override
	public int compare(LinhaEntity linhaEntity1, LinhaEntity linhaEntity2) {
		return linhaEntity1.getNumeroLinha().compareTo(linhaEntity2.getNumeroLinha());
	}
	
}