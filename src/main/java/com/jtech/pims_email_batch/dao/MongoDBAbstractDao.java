package com.jtech.pims_email_batch.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MongoDBAbstractDao implements MongoDBDao {

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Map<?, ?> selectOne(String collection, Map<?, ?> params) {

		DB mongoDb = mongoTemplate.getDb();
		DBCollection dbCol = mongoDb.getCollection(collection);

		DBObject dBObject = dbCol.findOne(objParameterPaser(params));

		return (Map<?, ?>) dBObject;
	}

	public BasicDBList listParameterPaser(List<?> list) {

		BasicDBList basicDBList = new BasicDBList();
		Map<?, ?> rowMap = new HashMap<String, Object>();

		for (int i = 0; i < list.size(); i++) {
			rowMap = (Map<?, ?>) list.get(i);
			basicDBList.add(objParameterPaser(rowMap));
		}

		return basicDBList;
	}

	@SuppressWarnings("rawtypes")
	public BasicDBObject objParameterPaser(Map<?, ?> params) {

		BasicDBObject basicDBObject = new BasicDBObject();

		Iterator iterator = params.entrySet().iterator();
		Entry entry = null;
		String keyName = "";

		while (iterator.hasNext()) {
			entry = (Entry) iterator.next();
			keyName = String.valueOf(entry.getKey());

			if ("M".equals(returnInstanceOfTarget(params.get(keyName)))) {
				basicDBObject.put(keyName, objParameterPaser((Map<?, ?>) params.get(keyName)));

			} else if ("L".equals(returnInstanceOfTarget(params.get(keyName)))) {
				basicDBObject.put(keyName, listParameterPaser((List<?>) params.get(keyName)));

			} else if ("S".equals(returnInstanceOfTarget(params.get(keyName)))) {
				basicDBObject.put(keyName, String.valueOf(params.get(keyName)));

			} else {
				basicDBObject.put(keyName, params.get(keyName));

			}

		}

		return basicDBObject;
	}

	public String returnInstanceOfTarget(Object obj) {
		if (obj instanceof Map) {
			return "M";
		} else if (obj instanceof List) {
			return "L";
		} else if (obj instanceof String) {
			return "S";
		} else if (obj instanceof Boolean) {
			return "B";

		} else {
			return "E";
		}
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<?> selectList(String collection, Map<?, ?> params) {

		List returnList = new ArrayList();
		Map rowMap = null;

		DB mongoDb = mongoTemplate.getDb();
		DBCollection dbCol = mongoDb.getCollection(collection);

		DBCursor dBCursor = dbCol.find(objParameterPaser(params));
		DBObject dBObject = null;

		while (dBCursor.hasNext()) {
			dBObject = dBCursor.next();
			rowMap = new HashMap<String, Object>();
			rowMap = (Map) dBObject;

			returnList.add(returnList.size(), rowMap);
		}

		return returnList;
	}

	@Override
	public Boolean insertOne(String collection, Map<?, ?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateOne(String collection, String keyField, String keyValue, Map<?, ?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteOne(String collection, String keyField, String keyValue, Map<?, ?> values) {
		// TODO Auto-generated method stub
		return null;
	}

}
