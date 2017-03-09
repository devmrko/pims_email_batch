package com.jtech.pims_email_batch.dao;

import java.util.List;
import java.util.Map;

public interface MongoDBDao {
	
	Map<?, ?> selectOne(String collection, Map<?, ?> params);

	List<?> selectList(String collection, Map<?, ?> params);

	Boolean insertOne(String collection, Map<?, ?> values);

	Boolean updateOne(String collection, String keyField, String keyValue, Map<?, ?> values);

	Boolean deleteOne(String collection, String keyField, String keyValue, Map<?, ?> values);

}
