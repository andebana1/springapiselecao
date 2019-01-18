package br.com.anderson.SpringApi.jwt.filter;

import br.com.anderson.SpringApi.jwt.service.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFiltro extends AbstractAuthenticationProcessingFilter {
	public JWTLoginFiltro(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		// TODO Auto-generated method stub
		CredenciaisUsuario credenciaisUsuario = new ObjectMapper().readValue(req.getInputStream(), CredenciaisUsuario.class);
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
					credenciaisUsuario.getUsunome(), 
					credenciaisUsuario.getUsusenha(),
					Collections.<GrantedAuthority>emptyList()
				)
		);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		TokenAuthenticationService.addAuthentication(res, auth.getName());
	}
}
