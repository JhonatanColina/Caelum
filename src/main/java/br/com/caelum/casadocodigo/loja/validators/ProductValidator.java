package br.com.caelum.casadocodigo.loja.validators;

import java.util.Objects;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.caelum.casadocodigo.loja.model.Product;

public class ProductValidator implements Validator
{

	@Override
	public boolean supports(Class<?> arg0) {
		return Product.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		Product product = (Product) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "campo.obrigatorio");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "campo.obrigatorio");
	}
}
