package br.com.caelum.casadocodigo.loja.viewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

public class XmlViewResolver implements ViewResolver
{

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MappingJackson2XmlView mappingJackson2XmlView = new MappingJackson2XmlView();
		return mappingJackson2XmlView;
	}

}
