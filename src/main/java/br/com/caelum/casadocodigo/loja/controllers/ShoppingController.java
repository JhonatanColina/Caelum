package br.com.caelum.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.caelum.casadocodigo.loja.model.BookType;
import br.com.caelum.casadocodigo.loja.model.ShoppingCart;
import br.com.caelum.casadocodigo.loja.model.ShoppingItem;
import br.com.caelum.casadocodigo.loja.repository.ProductDAO;

@RequestMapping("/shopping")
@Controller
public class ShoppingController 
{
	@Autowired
	private ShoppingCart shoppingCart;
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(method = RequestMethod.POST)
	public void addItemCart(Long productId, BookType bookType)
	{
		shoppingCart.add(new ShoppingItem(productDAO.getById(productId), bookType));
	}

}
