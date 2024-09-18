package br.com.apilinhasmetrocptm.component;

import org.springframework.security.core.AuthenticationException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import br.com.apilinhasmetrocptm.model.ResponseMessages;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import javax.servlet.ServletException;
import org.mockito.ArgumentCaptor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import com.google.gson.Gson;
import java.io.Serializable;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Classe test para implementação do teste unitário do método de inicialização de um esquema de autenticação.
 * 
 * @author Felipe Nascimento
 * 
 */

class CustomAuthEntryPointComponentTest implements Serializable {
	private static final long serialVersionUID = -3741133055767837023L;
	
	@InjectMocks
	private CustomAuthEntryPointComponent customAuthEntryPointComponent;
	
	/**
	 * Método responsável pela inicialização dos mocks.
	 */
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
	/**
	 * Método responsável pelo teste unitário do metodo {@link CustomAuthEntryPointComponent#commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException)}.
	 */
	@Test
	void testCommence() throws IOException, ServletException {
		HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        AuthenticationException authException = mock(AuthenticationException.class);
        PrintWriter pw = mock(PrintWriter.class);
        
        when(resp.getWriter()).thenReturn(pw);
        
        customAuthEntryPointComponent.commence(req, resp, authException);
        
        verify(resp).setContentType("application/json;charset=UTF-8");
        verify(resp).setStatus(HttpStatus.UNAUTHORIZED.value());
        
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(pw).write(argumentCaptor.capture());
        
        ResponseMessages responseApi = new Gson().fromJson(argumentCaptor.getValue(), ResponseMessages.class);
        
        assertNotNull(responseApi);
        assertEquals("As credenciais de autenticação da API-LinhasMetroCPTM não foram informadas ou estão incorretas.", responseApi.getStatusMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, responseApi.getStatusRequest());
        assertEquals(HttpStatus.UNAUTHORIZED.value(), responseApi.getStatusCode());
	}
	
}