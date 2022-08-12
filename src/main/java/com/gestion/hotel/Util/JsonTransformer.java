package com.gestion.hotel.Util;

public interface JsonTransformer {
	
	String toJSON(Object data);
	Object fromJSON(String json, Class clazz);
}
