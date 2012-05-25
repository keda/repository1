package com.msp.core.json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PojoMapper {
	
	public final static ObjectMapper defaultMapper = new ObjectMapper();
	private static JsonFactory js = new JsonFactory();
	
//	static {
//		
//	}
	
	private PojoMapper() {
		
	}
	
	public static <T> Object fromJson(String jsonAsString, Class<T> pojoClass) throws JsonParseException, JsonMappingException, IOException {
		return defaultMapper.readValue(jsonAsString, pojoClass);
	}
	
	public static <T> Object fromJson(FileReader fr, Class<T> pojoClass) throws JsonParseException, JsonMappingException, IOException {
		return defaultMapper.readValue(fr, pojoClass);
	}
	
	public static String toJson(Object pojo, boolean prettyPrint) throws JsonGenerationException, JsonMappingException, IOException {
		StringWriter sw = new StringWriter();
		
		JsonGenerator jg = js.createJsonGenerator(sw);
		if(prettyPrint) {
			jg.useDefaultPrettyPrinter();
		}
		
		try {
			defaultMapper.writeValue(jg, pojo);
		} finally {
			jg.close();
		}
		

		return sw.toString();
	}
	
	public static void toJson(Object pojo, FileWriter fw, boolean prettyPrint) throws JsonGenerationException, JsonMappingException, IOException {
		JsonGenerator jg = js.createJsonGenerator(fw);
		
		if(prettyPrint) {
			jg.useDefaultPrettyPrinter();
		}
		
		try {
			defaultMapper.writeValue(jg, pojo);
		} finally {
			jg.close();
		}
	}
}
