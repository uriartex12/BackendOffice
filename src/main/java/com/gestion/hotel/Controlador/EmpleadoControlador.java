package com.gestion.hotel.Controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.hotel.Entidades.Empleado;
import com.gestion.hotel.Repositorio.EmpleadoRepositorio;
@CrossOrigin(origins="http://localhost:4200", maxAge= 3600)
@RestController
@RequestMapping("/api/hotel/")
public class EmpleadoControlador {

	@Autowired
	private EmpleadoRepositorio repositorio;
	
	@GetMapping("/empleados")
	public List<Empleado> listarTodosLosEmpleados(){
		
		return repositorio.findAll();
	}
	
}
