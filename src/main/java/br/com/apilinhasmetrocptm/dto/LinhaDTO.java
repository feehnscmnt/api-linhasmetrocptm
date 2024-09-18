package br.com.apilinhasmetrocptm.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe DTO para o tráfego dos dados das linhas.
 * 
 * @author Felipe Nascimento
 * 
 */

public class LinhaDTO implements Comparator<LinhaDTO>, Serializable {
	private static final long serialVersionUID = 8029454056171709168L;

	@NotNull
	private UUID id;
	
	@NotBlank
	private String numeroLinha;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String nomeIngles;
	
	@NotBlank
	private String cor;
	
	@NotBlank
	private String corHexadecimal;
	
	/**
	 * Retorna o atributo id.
	 * 
	 * @return o id da linha do tipo {@link UUID}.
	 * 
	 */
	public UUID getId() {
		return id;
	}
	
	/**
	 * Especifica o atributo id.
	 * .
	 * @param id {@link UUID} referente ao id da linha que será setado.
	 * 
	 */
	public void setId(UUID id) {
		this.id = id;
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
	 * Método comparador booleano.
	 */
	@Override
    public boolean equals(Object object) {
		if (this == object) return true;
		
		if (object == null || getClass() != object.getClass()) return false;
		
		LinhaDTO linhaDTO = (LinhaDTO) object;
		
		return Objects.equals(id, linhaDTO.id)
			&& Objects.equals(numeroLinha, linhaDTO.numeroLinha)
			&& Objects.equals(nome, linhaDTO.nome)
			&& Objects.equals(nomeIngles, linhaDTO.nomeIngles)
			&& Objects.equals(cor, linhaDTO.cor)
			&& Objects.equals(corHexadecimal, linhaDTO.corHexadecimal);
	}
	
	/**
	 * Método organizador dos atributos.
	 */
	@Override
    public int hashCode() {
		return Objects.hash(
			id,
			numeroLinha,
			nome,
			nomeIngles,
			cor,
			corHexadecimal
		);
	}
	
	/**
	 * Método de representação textual da classe.
	 */
	@Override
	public String toString() {
		return String.valueOf(new StringBuilder()
			.append("Id: ").append(id)
			.append(", Número da Linha: ").append(numeroLinha)
			.append(", Nome da Linha: ").append(nome)
			.append(", Nome da Linha em Inglês: ").append(nomeIngles)
			.append(", Cor da Linha: ").append(cor)
			.append(", Cor Hexadecimal da Linha: ").append(corHexadecimal));
	}
	
	/**
	 * Método comparador inteiro.
	 */
	@Override
	public int compare(LinhaDTO linhaDTO1, LinhaDTO linhaDTO2) {
		return linhaDTO1.getNumeroLinha().compareTo(linhaDTO2.getNumeroLinha());
	}
	
}