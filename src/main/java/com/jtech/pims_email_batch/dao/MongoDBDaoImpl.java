package com.jtech.pims_email_batch.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class MongoDBDaoImpl extends MongoDBAbstractDao {
	
	public String selectOneByGson(String collection, Map<?, ?> params) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(selectOne(collection, params));
	}
	
}
