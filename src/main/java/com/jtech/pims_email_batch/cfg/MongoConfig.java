package com.jtech.pims_email_batch.cfg;

import java.util.Arrays;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@Configuration
@MapperScan("com.jtech.pims_email_batch.dao")
@PropertySource("config.properties")
@EnableTransactionManagement
public class MongoConfig {

	@Value("${mongoUser}")
	String mongoUser;

	@Value("${databaseName}")
	String databaseName;

	@Value("${mongoPass}")
	String mongoPass;

	@Value("${mongoHost}")
	String mongoHost;

	@Value("${mongoPort}")
	String mongoPort;

	public @Bean MongoDbFactory mongoDbFactory() throws Exception {

		// Set credentials
		MongoCredential credential = MongoCredential.createCredential(mongoUser, databaseName, mongoPass.toCharArray());
		ServerAddress serverAddress = new ServerAddress(mongoHost + ":" + mongoPort);

		// Mongo Client
		MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(credential));

		// Mongo DB Factory
		SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClient, databaseName);

		return simpleMongoDbFactory;
	}

	public @Bean MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

}
