package br.com.caelum.casadocodigo.loja.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfig 
{
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf)
	{
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean LocalContainerEntityManagerFactoryBean(DataSource dataSource)
	{
		LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		managerFactoryBean.setDataSource(dataSource);
		managerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		managerFactoryBean.setJpaProperties(myJpaConfig());
		managerFactoryBean.setPackagesToScan(new String[]{"br.com.caelum.casadocodigo.loja.model"});
	
		return managerFactoryBean;
	}
	
	private Properties myJpaConfig()
	{
		Properties properties = new Properties();
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return properties;
	}
	
	@Bean
	@Profile("prod")
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo?createDatabaseIfNotExist=true");
		dataSource.setUsername("root");
		dataSource.setPassword("caelum");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
}
