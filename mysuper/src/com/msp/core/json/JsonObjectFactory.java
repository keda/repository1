package com.msp.core.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Administrator
 *
 */
public class JsonObjectFactory {
	
	private static JsonFactory jsonFactory = new JsonFactory();
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private JsonObjectFactory() {
		
	}

	/**
	 * @return
	 */
	public static JsonFactory getJsonFactory() {
		return jsonFactory;
	}

	
	/**Tree Model provides a mutable in-memory tree representation of a JSON document.
	 * @return
	 */
	public static ObjectMapper getMapper() {
		return mapper;
	}
	
	
}
