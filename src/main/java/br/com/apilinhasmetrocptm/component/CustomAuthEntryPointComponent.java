package br.com.apilinhasmetrocptm.component;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import br.com.apilinhasmetrocptm.model.ResponseMessages;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import javax.servlet.ServletException;
import com.google.gson.Gson;
import java.io.Serializable;
import java.io.IOException;

/**
 * Classe component responsável por criar uma resposta de erro personalizada.
 * 
 * @author Felipe Nascimento
 *
 */

@Component
public class CustomAuthEntryPointComponent implements AuthenticationEntryPoint, Serializable {
	private static final long serialVersionUID = 8209195258916611489L;

	/**
	 * Método responsável pela inicialização de um esquema de autenticação.
	 * 
	 * @param req - {@link HttpServletRequest} - requisição realizada
	 * @param resp - {@link HttpServletResponse} - resposta para que o agente do usuário inicie a autenticação
	 * @param authException - {@link AuthenticationException} - causa da invocação
	 * 
	 */
	@Override
    public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException authException) throws IOException, ServletException {
		resp.setContentType("application/json;charset=UTF-8");
		resp.setStatus(HttpStatus.UNAUTHORIZED.value());
		resp.getWriter().write(new Gson().toJson(new ResponseMessages("As credenciais de autenticação da API-LinhasMetroCPTM não foram informadas ou estão incorretas.", HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value())));
	}
	
}