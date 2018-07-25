package br.com.caelum.casadocodigo.loja.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.casadocodigo.loja.model.User;

@Repository
public class UserDAO 
{
	@PersistenceContext
	private EntityManager em;
	
	public User findUser(String user)
	{
		return em.createQuery("select u from User u where u.user=:user",User.class)
		.setParameter("user", user)
		.getSingleResult();
	}
}
