package com.gestion.hotel.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Util {
	
	public static final int KG_MEASUREUNIT = 767;
	public static final int TRANSACTIONTYPE_EXPENSE_PRODUCTION = 5;
	public static final long ROOM_STATUS_AVAILABLE=1;
	
	
	public static int toInt(Object o) {
		return  Integer.parseInt(o.toString());
	}
	
	public static long toLong(Object o) {
		
		return Long.parseLong(o.toString());
	}
	
	public static int resolveLimitv2(Map map) {
		return (Integer.parseInt(map.get("page").toString())-1)*Integer.parseInt(map.get("xpage").toString());
	}
	public static int resolveLimit(Map map) {
		return (Integer.parseInt(map.get("pagina").toString())-1)*Integer.parseInt(map.get("xpagina").toString());
	}
	
	public static Map parseParameterMap(Map<String, String[]> parameterMap) {
		Map m = new HashMap();
		Set<String> keys= parameterMap.keySet();
		for( String key : keys ) {
			m.put(key, parameterMap.get(key)[0] );
		}
		return m;
	}
	
	public static List<Map> loadListFromRepository(List<Object[]> list, String[] fields) {
		List<Map> result=new ArrayList<Map>();
		for (Object[] item : list) {
			Map headerMap = Util.populateMap(fields, item);
			result.add(headerMap);
		}
		return result;
	}
	
	public static Map populateMap(String fields[],Object values[]){
		Map map= new HashMap();
		
		for(int i=0; i<fields.length; i++){
			map.put(fields[i], values[i]);
		}
		return map;
	}
	
	public static void responsePaginatedv2(Map response,int total, Map params) {
		response.put("total", total);
		response.put("xpage", Util.toInt(params.get("xpage")));
		response.put("page", Util.toInt(params.get("page")));
		//return response;
	}
	
}