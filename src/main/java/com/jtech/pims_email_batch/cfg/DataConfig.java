package com.jtech.pims_email_batch.cfg;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.jtech.pims_email_batch.dao")
@PropertySource("config.properties")
@EnableTransactionManagement
public class DataConfig {
	
	@Value("${mariaDB_url}")
	String mariaDB_url;
	
	@Value("${mariaDB_user}")
	String mariaDB_user;
	
	@Value("${mariaDB_passwd}")
	String mariaDB_passwd;

	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(org.jdbcdslog.DriverLoggingProxy.class);
		dataSource.setUrl(mariaDB_url);
		dataSource.setUsername(mariaDB_user);
		dataSource.setPassword(mariaDB_passwd);
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory
				.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return sessionFactory.getObject();
	}

}