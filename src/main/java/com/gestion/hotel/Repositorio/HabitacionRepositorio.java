package com.gestion.hotel.Repositorio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.gestion.hotel.Entidades.Estadohabitacion;
import com.gestion.hotel.Entidades.Habitacion;
import com.gestion.hotel.Entidades.Nivelhabitacion;
import com.gestion.hotel.Entidades.Tipohabitacion;
import com.gestion.hotel.Util.Util;

@Repository
public class HabitacionRepositorio extends BaseRepositorio{ 
	
	public List<Object[]> listarHabitacion (Map params) throws Exception{
		this.checkSession();
		try {
			String sql="select "+((boolean) params.get("count") ? "count(*)" 
					: " h.id ,h.descripcion ,h.numero_habitacion ,h.precio ,h.estadohabitacion_id,h.nivelpiso_id , h.tipohabitacion_id")
					+ " from habitacion h "
					+ " inner join tipohabitacion t on t.id =h.tipohabitacion_id"
					+ " inner join nivelhabitacion n on n.id = h.nivelpiso_id"
					+ (!(boolean)params.get("count")?" order by h.id desc offset "+params.get("limit")+ " limit "+ params.get("xpage"):"");
			
			return (List<Object[]>) this.session.createSQLQuery(sql).list();
			
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
		}
	}
	
	
	public Map SaveHabitacion (Map params) throws Exception{
		
		this.checkSession();
		Transaction tx = this.session.beginTransaction();
		this.session.clear();
		Map response = new HashMap<>();
		try {
			Habitacion habitacion= new Habitacion();
			Estadohabitacion estadohabitacion= new Estadohabitacion(Util.ROOM_STATUS_AVAILABLE);
			Nivelhabitacion nivel= new Nivelhabitacion(Util.toLong(params.get("idnivel").toString()));
			Tipohabitacion tipo= new Tipohabitacion(Util.toLong(params.get("idTipo").toString()));
			habitacion.setDescripcion(params.get("descripcion").toString());
			habitacion.setNumeroHabitacion(params.get("numero_habitacion").toString());
			habitacion.setPrecio(Float.parseFloat(params.get("precio").toString()));
			habitacion.setEstadohabitacion(estadohabitacion);
			habitacion.setNivelpiso(nivel);
			habitacion.setTipohabitacion(tipo);
			this.session.merge(habitacion);
			response.put("Descripcion", habitacion.getDescripcion());
			tx.commit();
			return response;
		} catch (Exception e) {
			tx.rollback();
			throw new Exception("R_ERRRO:" + e.getMessage());
		}
	}
	 
}

