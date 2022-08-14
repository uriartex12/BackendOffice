package com.gestion.hotel.Servicio;

import java.util.Map;


public interface IserviceHabitacion {

	public Map listarHabitacion (Map params) throws Exception;
	public Map SaveHabitacion(Map params) throws Exception;
}
