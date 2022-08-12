package com.gestion.hotel.Repositorio;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class HabitacionRepositorio extends BaseRepositorio{ 
	
	public List<Object[]> listarHabitacion (Map params) throws Exception{
		this.checkSession();
		try {
			String sql="select h.id ,h.descripcion ,h.numero_habitacion ,h.precio ,h.estadohabitacion_id \r\n"
					+ ", h.nivelpiso_id , h.tipohabitacion_id  from habitacion h \r\n"
					+ "inner join tipohabitacion t on t.id =h.tipohabitacion_id\r\n"
					+ "inner join nivelhabitacion n on n.id = h.nivelpiso_id";
			
			return (List<Object[]>) this.session.createSQLQuery(sql).list();
			
		}catch(Exception e) {
			
			throw new Exception(e.getMessage());
		}
	}
	 
}

