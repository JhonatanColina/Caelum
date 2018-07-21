package br.com.caelum.casadocodigo.loja.controllers;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.caelum.casadocodigo.loja.Paymentfast.PaymentFastService;
import br.com.caelum.casadocodigo.loja.model.BookType;
import br.com.caelum.casadocodigo.loja.model.Product;
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
	@Autowired
	private PaymentFastService paymentFastService;
	
	@RequestMapping(method = RequestMethod.POST)
	public String addItemCart(Long productId, BookType booktype)
	{
		shoppingCart.add(new ShoppingItem(productDAO.getById(productId), booktype));
		return "redirect:/products";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String items()	
	{
		return "shoppingCart/items";
	}
	
	/*Callable para ele ser assincrono*/
	@RequestMapping(value="/checkout",	method=RequestMethod.POST)
	public Callable<String> checkout(RedirectAttributes redirectAttributes)	
	{
		return () ->
		{
			if(paymentFastService.finishPayment(shoppingCart.getTotal()) == HttpStatus.OK)
			{
				redirectAttributes.addFlashAttribute("success","Pagamento Efetuado com êxito!");
				shoppingCart.getList().clear();
			}
			else
				redirectAttributes.addFlashAttribute("success","Erro no Pagamento, tente novamente!");
			return "redirect:/products";
		};
	}

}