package com.gestion.hotel.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.hotel.Entidades.Empleado;
import com.gestion.hotel.Repositorio.EmpleadoRepositorio;
import com.gestion.hotel.Excepciones.*;

@CrossOrigin(origins="http://localhost:4200", maxAge= 3600)
@RestController
@RequestMapping("/api/hotel/")
public class EmpleadoControlador {

	@Autowired
	private EmpleadoRepositorio repositorio;
	
	// get all employees
		@GetMapping("/employees")
		public List<Empleado> getAllEmployees(){
			return repositorio.findAll();
		}		
		
		// create employee rest api
		@PostMapping("/employees")
		public Empleado createEmployee(@RequestBody Empleado employee) {
			return repositorio.save(employee);
		}
		
		// get employee by id rest api
		@GetMapping("/employees/{id}")
		public ResponseEntity<Empleado> getEmployeeById(@PathVariable Long id) {
			Empleado employee = repositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			return ResponseEntity.ok(employee);
		}
		
		// update employee rest api
		
		@PutMapping("/employees/{id}")
		public ResponseEntity<Empleado> updateEmployee(@PathVariable Long id, @RequestBody Empleado employeeDetails){
			Empleado employee = repositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			employee.setNombre(employeeDetails.getNombre());
			employee.setApellido(employeeDetails.getApellido());
			employee.setEmail(employeeDetails.getEmail());
			
			Empleado updatedEmployee = repositorio.save(employee);
			return ResponseEntity.ok(updatedEmployee);
		}
		
		// delete employee rest api
		@DeleteMapping("/employees/{id}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
			Empleado employee = repositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
			
			repositorio.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
	
}
