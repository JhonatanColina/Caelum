package br.com.caelum.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.caelum.casadocodigo.loja.controllers.ProductController;
import br.com.caelum.casadocodigo.loja.model.ShoppingCart;
import br.com.caelum.casadocodigo.loja.repository.ProductDAO;
import br.com.caelum.casadocodigo.loja.utils.ManagerFile;
import br.com.caelum.casadocodigo.loja.controllers.HomeController;

@EnableWebMvc
@ComponentScan(basePackageClasses = {HomeController.class,
		ProductDAO.class,ManagerFile.class,ShoppingCart.class})
public class AppConfiguration extends WebMvcConfigurerAdapter{
	
	
	/*Permite o Spring receber multipart form*/
	@Bean
	public MultipartResolver multipartResolver()
	{
		return new StandardServletMultipartResolver();
	}

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
	
	/*Interface para mensagens de validação*/
	@Bean
	public MessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename("WEB-INF/resources/messages");
		reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
		reloadableResourceBundleMessageSource.setCacheSeconds(1);
		return reloadableResourceBundleMessageSource;
		
	}
	
	/*Interface para converter as datas para o padrao Spring*/
	@Bean
	public FormattingConversionService mvcConversionService()
	{
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
		DateFormatterRegistrar formatterRegistrar = new DateFormatterRegistrar();
		formatterRegistrar.setFormatter(dateFormatter);
		formatterRegistrar.registerFormatters(conversionService);
		
		return conversionService;
	}
}
