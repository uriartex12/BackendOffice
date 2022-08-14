package com.gestion.hotel.Controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.hotel.Servicio.IserviceHabitacion;
import com.gestion.hotel.Util.JsonTransformer;
import com.gestion.hotel.Util.JsonTransformerImplJackson;
import com.gestion.hotel.Util.Util;

@CrossOrigin(origins="http://localhost:4200", maxAge= 3600)
@RestController
@RequestMapping("api/hotel")
public class HabitacionControlador {

	@Autowired
	private IserviceHabitacion serviciohabitacion;
	
	
	@RequestMapping(value="/ListarHabitacion", method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public ResponseEntity<?> listProductionExpense (HttpServletRequest request, HttpServletResponse httpServletResponse) throws IOException{
		
		JsonTransformer jsontransformer=new JsonTransformerImplJackson();
		Map response=new HashMap();
		try {
			Map map = Util.parseParameterMap(request.getParameterMap());
			map.put("count", false);
			map.put("page", map.containsKey("page") ? map.get("page").toString() : 1);
			map.put("xpage", map.containsKey("xpage")?map.get("xpage"):10);
			int limit = Util.resolveLimitv2(map);
			map.put("limit", limit);
			response = serviciohabitacion.listarHabitacion(map);
		}catch(Exception e) {
			response.put("ERROR", e.getMessage());
			return ResponseEntity.status(500).body(response);
		}
		return ResponseEntity.ok().body(response);
	}

	
	@RequestMapping(value="/SaveHabitacion", method=RequestMethod.POST, consumes="application/json", produces={"application/json; charset=UTF-8","*/*;charset=UTF-8"})
	public ResponseEntity<Map> SaveHabitacion(HttpServletRequest request, HttpServletResponse httpServletResponse, @RequestBody String jsonIn) throws IOException {
		Map response= new HashMap();
		JsonTransformer jsonTransformer = new JsonTransformerImplJackson();
		try {
			Map map = (Map) jsonTransformer.fromJSON(jsonIn, Map.class);
			map.put("employedid", map.containsKey("employedid") ? map.get("employedid") :1);
						response= serviciohabitacion.SaveHabitacion(map);
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			response.put("ERROR",e.getMessage());
			return ResponseEntity.status(500).body(response);
		}
	}
}
