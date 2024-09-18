package br.com.apilinhasmetrocptm.model;

import org.springframework.http.HttpStatus;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 * Classe model para tratamento das mensagens dos responses da API-FNAdega.
 * 
 * @author Felipe Nascimento
 *
 */

public class ResponseMessages implements Comparator<ResponseMessages>, Serializable {
	private static final long serialVersionUID = -3836044934160644567L;
	private String statusMessage;
	private HttpStatus statusRequest;
	private int statusCode;
	
	/**
	 * Construtor da classe parametrizado.
	 * 
	 * @param statusMessage - mensagem do status da requisição
	 * @param statusRequest - status da requisição
	 * @param statusCode - código do status da requisição
	 * 
	 */
	public ResponseMessages(String statusMessage, HttpStatus statusRequest, int statusCode) {
		this.statusMessage = statusMessage;
		this.statusRequest = statusRequest;
		this.statusCode = statusCode;
	}
	
	/**
	 * Retorna o atributo statusMessage.
	 * 
	 * @return o status da mensagem do tipo {@link String}.
	 * 
	 */
	public String getStatusMessage() {
		return statusMessage;
	}
	
	/**
	 * Especifica o atributo statusMessage.
	 * .
	 * @param statusMessage {@link String} referente ao status da mensagem que será setado.
	 * 
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/**
	 * Retorna o atributo statusRequest.
	 * 
	 * @return o status da requisição do tipo {@link HttpStatus}.
	 * 
	 */
	public HttpStatus getStatusRequest() {
		return statusRequest;
	}
	
	/**
	 * Especifica o atributo statusRequest.
	 * .
	 * @param statusRequest {@link HttpStatus} referente ao status da requisição que será setado.
	 * 
	 */
	public void setStatusRequest(HttpStatus statusRequest) {
		this.statusRequest = statusRequest;
	}
	
	/**
	 * Retorna o atributo statusCode.
	 * 
	 * @return o código do status da requisição do tipo int.
	 * 
	 */
	public int getStatusCode() {
		return statusCode;
	}
	
	/**
	 * Especifica o atributo statusCode.
	 * .
	 * @param statusCode int referente ao código do status da requisição que será setado.
	 * 
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * Método comparador booleano.
	 */
	@Override
	public boolean equals(Object object) {
		if (this == object) return true;
		
		if (object == null || getClass() != object.getClass()) return false;
		
		ResponseMessages responseMessages = (ResponseMessages) object;
		
		return Objects.equals(statusMessage, responseMessages.statusMessage)
			&& Objects.equals(statusRequest, responseMessages.statusRequest)
			&& Objects.equals(statusCode, responseMessages.statusCode);
	}
	
	/**
	 * Método organizador dos atributos.
	 */
	@Override
    public int hashCode() {
		return Objects.hash(
			statusMessage,
			statusRequest,
			statusCode
		);
	}
	
	/**
	 * Método de representação textual da classe.
	 */
	@Override
	public String toString() {
		return String.valueOf(new StringBuilder()
			.append("Mensagem da Requisição: ").append(statusMessage)
			.append(", Status da Requisição: ").append(statusRequest)
			.append(", Código da Requisição: ").append(statusCode));
	}
	
	/**
	 * Método comparador inteiro.
	 */
	@Override
	public int compare(ResponseMessages responseMessages1, ResponseMessages responseMessages2) {
		return responseMessages1.getStatusMessage().compareTo(responseMessages2.getStatusMessage());
	}
	
}