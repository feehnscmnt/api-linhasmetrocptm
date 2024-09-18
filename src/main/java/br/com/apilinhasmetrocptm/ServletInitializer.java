package br.com.apilinhasmetrocptm;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Classe responsável pela inicialização da API-LinhasCPTM em um contêiner de Servlet.
 * 
 * @author Felipe Nascimento
 * 
 */

public class ServletInitializer extends SpringBootServletInitializer {
	
	/**
	 * Método responsável por garantir a inicialização da API-LinhasMetroCPTM com as configuração
	 * definidas na classe {@link ApiLinhasMetroCptmApplication}.
	 * 
	 * @param application - classe que configura a aplicação Spring Boot
	 * 
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiLinhasMetroCptmApplication.class);
	}

}