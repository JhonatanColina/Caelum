package br.com.caelum.casadocodigo.loja.conf;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
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
import br.com.caelum.casadocodigo.loja.Paymentfast.PaymentFastService;
import br.com.caelum.casadocodigo.loja.controllers.HomeController;

@EnableWebMvc
@ComponentScan(basePackageClasses = 
{
		HomeController.class,ProductDAO.class,
		ManagerFile.class,ShoppingCart.class,
		PaymentFastService.class
})
@EnableCaching
public class AppConfiguration extends WebMvcConfigurerAdapter{
	
	/*
	 * Gerenciador de Cache 
	 */
	@Bean
	public	CacheManager cacheManager()	{
					return	new	ConcurrentMapCacheManager();
	}
	
	/*Habilitar o Rest Template*/
	@Bean
	public RestTemplate RestTemplate()
	{
		return new RestTemplate();
	}
	
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
		//viewResolver.setExposeContextBeansAsAttributes(true); //libera todas as classes para chamar direto do server (NUNCA USAR)
		viewResolver.setExposedContextBeanNames("shoppingCart"); //libera o shopping cart para ser chamado da jsp
		// As classes seguem o modelo da primeira letra minuscula (camel case) // mesmo pocedimento do binding
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
