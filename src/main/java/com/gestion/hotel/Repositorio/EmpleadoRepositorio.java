package com.gestion.hotel.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.hotel.Entidades.Empleado;

@Repository
public interface EmpleadoRepositorio extends JpaRepository<Empleado, Long> {

}
