package com.gestion.hotel.Controlador;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
			map.put("Xpage",10);
			response = serviciohabitacion.listarHabitacion(map);
		}catch(Exception e) {
			response.put("ERROR", e.getMessage());
			return ResponseEntity.status(500).body(response);
		}
		return ResponseEntity.ok().body(response);
	}
	
}
