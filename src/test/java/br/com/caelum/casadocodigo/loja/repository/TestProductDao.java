package br.com.caelum.casadocodigo.loja.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import br.com.caelum.casadocodigo.loja.conf.AppConfiguration;
import br.com.caelum.casadocodigo.loja.conf.DataSourceForTest;
import br.com.caelum.casadocodigo.loja.conf.JPAConfig;
import br.com.caelum.casadocodigo.loja.model.BookType;
import br.com.caelum.casadocodigo.loja.model.Price;
import br.com.caelum.casadocodigo.loja.model.Product;
import junit.framework.Assert;
/*
 * Com estas anotações o Spring cuida de rodar o Junit em suas classes
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class,JPAConfig.class,ProductDAO.class,DataSourceForTest.class})
@ActiveProfiles("teste")
public class TestProductDao 
{
	@Autowired
	private ProductDAO productDAO;
	
	/*Padrao Junit
	 * test+o mais descritivo possivel com os valores esperados
	 * */
	@Test
	@Transactional
	public void testDeveCadastrarUmLivro()
	{
		Price p = new Price();
		p.setPrice(new BigDecimal(1.5));
		p.setBooktype(BookType.PRINTED);
		List<Price> price = new ArrayList<>();
		price.add(p);
		
		Product product = new Product();
		product.setAuthor("Mark");
		product.setDescription("Descricao do livro");
		product.setTitle("Spring");
		product.setNumPage(14);
		product.setPrices(price);
		productDAO.save(product);
		Product productRetornado = productDAO.getById(product.getId());
		Assert.assertEquals(product.getNumPage(), productRetornado.getNumPage());
	}
}
