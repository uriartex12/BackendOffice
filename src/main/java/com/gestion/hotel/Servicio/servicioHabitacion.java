package com.gestion.hotel.Servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestion.hotel.Repositorio.HabitacionRepositorio;
import com.gestion.hotel.Util.Util;

@Service
public class servicioHabitacion implements IserviceHabitacion {
	
	@Autowired
	HabitacionRepositorio habitacionRepositorio;

	@Override
	public Map listarHabitacion(Map params) throws Exception {
		Map response= new HashMap();
		try {
			List<Object[]> list=habitacionRepositorio.listarHabitacion(params);
			String fields[]= {"id","descripcion","numeroHabitacion","precio","estadoHabitacion","nivelId","tipoId"};
			response.put("List", Util.loadListFromRepository(list, fields));	
			
			if (params.containsKey("page")) {
				params.put("count", true);
				List<Object[]> total = habitacionRepositorio.listarHabitacion(params);
				Util.responsePaginatedv2(response, Integer.parseInt(total.get(0) + ""), params);
			}
			
			return response;
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
		}
	}
	
	public Map SaveHabitacion(Map params) throws Exception{
		Map response = new HashMap();
		try {
			response= habitacionRepositorio.SaveHabitacion(params);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			throw new Exception(e.getMessage());
		}
		return response;
	}

	
}
