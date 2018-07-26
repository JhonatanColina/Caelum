package br.com.caelum.casadocodigo.loja.controllers;

import java.io.File;
import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.caelum.casadocodigo.loja.model.BookType;
import br.com.caelum.casadocodigo.loja.model.Product;
import br.com.caelum.casadocodigo.loja.model.User;
import br.com.caelum.casadocodigo.loja.repository.ProductDAO;
import br.com.caelum.casadocodigo.loja.utils.ManagerFile;
import br.com.caelum.casadocodigo.loja.validators.ProductValidator;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ManagerFile ManagerFile;
	
	@InitBinder
	public void addValidators(WebDataBinder webDataBinder)
	{
		webDataBinder.addValidators(new ProductValidator());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Product product)
	{
		ModelAndView view = new ModelAndView("product/cadastro");
		view.addObject("tiposLivros", BookType.values());
		return view;
	}

	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	@CacheEvict(value="livrosRecentes", allEntries=true)
	public ModelAndView save(@Valid Product product,BindingResult bindingResult, 
			RedirectAttributes redirectAttributes,MultipartFile summary)
	{
		System.out.println(product);
		/*VALIDAÇÃO DO ARQUIVO*/
		product.setSummaryPath(ManagerFile.save(summary));
		
		if(product.getSummaryPath() == null)
		{
			bindingResult.rejectValue("summaryPath", "campo.obrigatorio");
		}
		/*FIM DA VALIDACAO DO ARQUIVO*/
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
	@Cacheable(value = "livrosRecentes") // coloca como cache de memoria
	public ModelAndView list(@AuthenticationPrincipal User user)
	{
		ModelAndView view = new ModelAndView("product/list");
		view.addObject("books", productDAO.listAll());
		return view;
	}
	
	@RequestMapping("{id}")
	public ModelAndView product(@PathVariable("id") Long id)
	{
		ModelAndView view = new ModelAndView("product/show");
		view.addObject("product",productDAO.getById(id));
		return view;
	}
}
