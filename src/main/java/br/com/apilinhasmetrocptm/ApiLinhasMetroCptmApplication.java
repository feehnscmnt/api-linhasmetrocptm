package br.com.apilinhasmetrocptm;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import java.io.Serializable;

/**
 * Classe responsável pela inicialização da API-LinhasMetroCPTM.
 * 
 * @author Felipe Nascimento
 * 
 */

@SpringBootApplication
@EntityScan("br.com.apilinhasmetrocptm.entity")
@EnableJpaRepositories("br.com.apilinhasmetrocptm.repository")
@ComponentScan({ "br.com.apilinhasmetrocptm.component", "br.com.apilinhasmetrocptm.controller", "br.com.apilinhasmetrocptm.security" })
public class ApiLinhasMetroCptmApplication implements Serializable {
	private static final long serialVersionUID = -6140733597744698109L;

	public static void main(String[] args) {
		SpringApplication.run(ApiLinhasMetroCptmApplication.class, args);
	}

}