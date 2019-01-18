package br.com.anderson.SpringApi.config;

import br.com.anderson.SpringApi.jwt.filter.JWTAutenticacaoFiltro;
import br.com.anderson.SpringApi.jwt.filter.JWTLoginFiltro;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// TODO Auto-generated method stub
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/home")
		.permitAll().antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().authenticated().and()
		// filtra requisições de login
		.addFilterBefore(new JWTLoginFiltro("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		// filtra outras requisições para verificar a presença do JWT no header
		.addFilterBefore(new JWTAutenticacaoFiltro(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// cria uma conta default
		
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}password")
			.roles("ADMIN");
	}
}
