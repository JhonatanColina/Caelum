package br.com.caelum.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletDoSpring extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*Utilizado para o que sera usado antes da config classes rolar
	 * Primeiras entradas (Spring security) */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{SpringSecurityConfig.class,AppConfiguration.class, JPAConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
	
	/*Customiza a forma de registro do form*/
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	/*
	 * Metodo para setar o charset do Spring para toda a aplicação
	 * */
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		return new Filter[]{characterEncodingFilter};
	}

}
