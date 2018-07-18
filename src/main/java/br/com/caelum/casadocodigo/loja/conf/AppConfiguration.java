package br.com.caelum.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.caelum.casadocodigo.loja.controllers.ProductController;
import br.com.caelum.casadocodigo.loja.repository.ProductDAO;
import br.com.caelum.casadocodigo.loja.controllers.HomeController;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class,
		ProductDAO.class})
public class AppConfiguration extends WebMvcConfigurerAdapter{

	/*Permite que resources sejam colocados antes da web-inf*/
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
