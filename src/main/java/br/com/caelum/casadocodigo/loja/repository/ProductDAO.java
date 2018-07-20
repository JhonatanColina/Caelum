package br.com.caelum.casadocodigo.loja.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.casadocodigo.loja.model.Product;

@Repository
public class ProductDAO 
{
	@PersistenceContext
	private EntityManager em;
	
	public void save(Product product)
	{
		em.persist(product);
	}
	
	public List<Product> listAll()
	{
		return em.createQuery("select distinct(p) from Product p "
				+ "join fetch p.prices",Product.class)
				.getResultList();
	}
	
	public Product getById(Long id)
	{
		return em.createQuery("select p from Product p "
				+ "join fetch p.prices where p.id = :id",Product.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
