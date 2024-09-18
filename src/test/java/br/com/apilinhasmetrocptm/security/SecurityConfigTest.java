package br.com.apilinhasmetrocptm.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import br.com.apilinhasmetrocptm.component.CustomAuthEntryPointComponent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import java.io.Serializable;
import org.mockito.Mockito;
import org.mockito.Mock;
import java.util.Base64;

/**
 * Classe test para implementação dos testes unitários dos métodos responsáveis
 * pelas configurações de segurança dos endpoints da API-LinhasMetroCPTM.
 * 
 * @author Felipe Nascimento
 * 
 */

class SecurityConfigTest implements Serializable {
	private static final long serialVersionUID = -8584436029071005000L;
	
	@Mock
    private CustomAuthEntryPointComponent customAuthEntryPointComponent;
	
	@InjectMocks
	private SecurityConfig securityConfig;
	
	private String apiUsername = null;
	
	/**
	 * Método responsável pela inicialização dos mocks.
	 */
	@BeforeEach
    void setUp() {
		apiUsername = new String(Base64.getDecoder().decode("TGluaGFzTWV0cm9DUFRNV0VCU2VydmljZUFwaQ=="));
        MockitoAnnotations.openMocks(this);
    }
	
	/**
	 * Método responsável pelo teste unitário do método {@link SecurityConfig#securityFilterChain(HttpSecurity httpSecurity)}.
	 */
	@Test
    void testSecurityFilterChain() throws Exception {
		HttpSecurity httpSecurity = Mockito.mock(HttpSecurity.class);
        SecurityFilterChain securityFilterChain = securityConfig.securityFilterChain(httpSecurity);

        verify(httpSecurity).sessionManagement(Mockito.any());
        verify(httpSecurity).authorizeHttpRequests(Mockito.any());
        verify(httpSecurity).httpBasic(Mockito.any());
        verify(httpSecurity).csrf(Mockito.any());
        verify(httpSecurity).exceptionHandling(Mockito.any());

        assertThat(securityFilterChain).isNotNull();
	}
	
	/**
	 * Método responsável pelo teste unitário do método {@link SecurityConfig#userDetailsService()}.
	 */
	@Test
    void testUserDetailsService() {
		InMemoryUserDetailsManager userDetailsManager = securityConfig.userDetailsService();
        UserDetails userDetails = userDetailsManager.loadUserByUsername(apiUsername);

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(apiUsername);
        assertThat(userDetails.getPassword()).isNotEmpty();
        assertThat(userDetails.getAuthorities()).hasSize(1);
	}
	
}