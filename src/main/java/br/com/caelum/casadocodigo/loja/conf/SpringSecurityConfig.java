package br.com.caelum.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import br.com.caelum.casadocodigo.loja.service.UserService;
/*
 * Classe de Configuracoes do Spring Security
 * Por padrao ele bloqueia tudo
 * Possui form de login padrao
 */ 
@EnableWebMvcSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private UserService userService;
	/*
	 * Configura qual manager de autenticacao vai usar*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	/*libera/bloqueia aAs views, importante ter ROLE_ no banco como prefixo da Role*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
		.antMatchers("/products/form").hasRole("ADMIN")
		.antMatchers("/products/**").permitAll()
		.antMatchers("/shopping/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/login")
		.defaultSuccessUrl("/products").permitAll()
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/products")
		.permitAll()
		.and() 
		.exceptionHandling().accessDeniedPage("/WEB-INF/views/403.jsp");
	}
	/*libera os resources (css,js)*/
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
}
