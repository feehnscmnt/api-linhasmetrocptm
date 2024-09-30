package br.com.apilinhasmetrocptm.security;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.context.annotation.Configuration;
import java.io.Serializable;

/**
 * Classe responsável pelas configurações de permissão do CORS.
 * 
 * @author Felipe Nascimento
 * 
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer, Serializable {
	private static final long serialVersionUID = -4304347299892573726L;
	
	/**
	 * Método responsável por permitir que todos os endpoints da API-LinhasMetroCPTM
	 * recebam requisições de qualquer origem.
	 * 
	 * @param registry - {@link CorsRegistry} - permite definir as configurações de CORS da API-LinhasMetroCPTM
	 * 
	 */
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/v1/**").allowedOrigins("*").allowedMethods("GET", "POST");
    }
	
}