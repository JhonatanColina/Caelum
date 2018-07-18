package br.com.caelum.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.casadocodigo.loja.model.BookType;
import br.com.caelum.casadocodigo.loja.model.Product;
import br.com.caelum.casadocodigo.loja.repository.ProductDAO;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping("form")
	public ModelAndView form()
	{
		ModelAndView view = new ModelAndView("product/cadastro");
		view.addObject("tiposLivros", BookType.values());
		return view;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public String save(Product product, RedirectAttributes redirectAttributes)
	{
		System.out.println(product);
		productDAO.save(product);
		redirectAttributes.addFlashAttribute("success",
				"Produto "+product.getTitle()+" salvo com sucesso!");
		return "redirect:products";
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
