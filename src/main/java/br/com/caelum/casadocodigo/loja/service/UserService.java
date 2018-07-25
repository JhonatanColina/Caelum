package br.com.caelum.casadocodigo.loja.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.caelum.casadocodigo.loja.model.User;
import br.com.caelum.casadocodigo.loja.repository.UserDAO;
/*
 * Necessario para o spring security
 * */
@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = userDAO.findUser(username);
		
		if(Objects.isNull(u))
		{
			throw new UsernameNotFoundException("Usuario" + u + "Não existe");
		}
		return u;
		
	}
}
