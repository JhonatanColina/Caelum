package br.com.caelum.casadocodigo.loja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.casadocodigo.loja.model.BookType;
import br.com.caelum.casadocodigo.loja.model.Product;
import br.com.caelum.casadocodigo.loja.repository.ProductDAO;
import br.com.caelum.casadocodigo.loja.validators.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@InitBinder
	public void addValidators(WebDataBinder webDataBinder)
	{
		webDataBinder.addValidators(new ProductValidator());
	}
	
	@RequestMapping("form")
	public ModelAndView form(Product product)
	{
		ModelAndView view = new ModelAndView("product/cadastro");
		view.addObject("tiposLivros", BookType.values());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public ModelAndView save(@Valid Product product,BindingResult bindingResult, RedirectAttributes redirectAttributes)
	{
		System.out.println(product);
		if(bindingResult.hasErrors())
		{
			return form(product);
		}
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("success",
				"Produto "+product.getTitle()+" salvo com sucesso!");
		return new ModelAndView("redirect:products");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@Transactional
	public ModelAndView list()
	{
		ModelAndView view = new ModelAndView("product/list");
		view.addObject("books", productDAO.listAll());
		return view;
	}
}
