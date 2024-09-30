package br.com.apilinhasmetrocptm.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import static org.springframework.security.config.Customizer.withDefaults;
import br.com.apilinhasmetrocptm.component.CustomAuthEntryPointComponent;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import java.io.Serializable;
import java.util.Base64;

/**
 * Classe responsável pelas configurações de segurança dos endpoints da API-LinhasMetroCPTM.
 * 
 * @author Felipe Nascimento
 * 
 */

@Configuration
public class SecurityConfig implements Serializable {
	private String apiUsername = new String(Base64.getDecoder().decode("TGluaGFzTWV0cm9DUFRNV0VCU2VydmljZUFwaQ=="));
	private String apiPassword = new String(Base64.getDecoder().decode("V0VCU2VydmljZUFwaUxpbmhhc01ldHJvQ1BUTQ=="));
	private String apiRole = new String(Base64.getDecoder().decode("VVNFUg=="));
	private CustomAuthEntryPointComponent customAuthEntryPointComponent;
	private static final long serialVersionUID = -134864769602873564L;
	
	public SecurityConfig(CustomAuthEntryPointComponent customAuthEntryPointComponent) {
		this.customAuthEntryPointComponent = customAuthEntryPointComponent;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.cors().and().csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
			
				/**
				 * Endpoints de Administradoras.
				 */
				.antMatchers(HttpMethod.POST, "/v1/administradora/cadastrar-administradora/**").hasRole(apiRole)
				.antMatchers(HttpMethod.GET, "/v1/administradora/listar-administradoras").hasRole(apiRole)
				.antMatchers(HttpMethod.GET, "/v1/administradora/listar-por-nome/**").hasRole(apiRole)
				
				/**
				 * Endpoints de Linhas.
				 */
				.antMatchers(HttpMethod.POST, "/v1/linha/cadastrar-linha/**").hasRole(apiRole)
				.antMatchers(HttpMethod.GET, "/v1/linha/listar-por-nome/**").hasRole(apiRole)
				.antMatchers(HttpMethod.GET, "/v1/linha/listar-por-cor/**").hasRole(apiRole)
				.antMatchers(HttpMethod.GET, "/v1/linha/listar-linhas/**").hasRole(apiRole)
				
				/**
				 * Endpoints de Estações.
				 */
				.antMatchers(HttpMethod.POST, "/v1/estacao/cadastrar-estacao/**").hasRole(apiRole)
				.antMatchers(HttpMethod.GET, "/v1/estacao/listar-estacoes/**").hasRole(apiRole)
				.antMatchers(HttpMethod.GET, "/v1/estacao/listar-estacao/**").hasRole(apiRole)
				
			.anyRequest().authenticated()).httpBasic(withDefaults())
			.exceptionHandling(handling -> handling.authenticationEntryPoint(customAuthEntryPointComponent));
		
		return httpSecurity.build();
	}
	
	@Bean
    InMemoryUserDetailsManager userDetailsService() {
		return new InMemoryUserDetailsManager(User.withUsername(apiUsername)
			.password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(apiPassword)).roles(apiRole).build());
    }
	
}