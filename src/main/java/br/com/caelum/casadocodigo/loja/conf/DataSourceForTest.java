package br.com.caelum.casadocodigo.loja.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Profile("teste")
public class DataSourceForTest 
{
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/casadocodigo_test?createDatabaseIfNotExist=true");
		dataSource.setUsername("root");
		dataSource.setPassword("caelum");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		return dataSource;
	}
}
