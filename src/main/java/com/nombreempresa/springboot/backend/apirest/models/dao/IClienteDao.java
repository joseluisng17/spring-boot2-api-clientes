package com.nombreempresa.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nombreempresa.springboot.backend.apirest.models.entity.Cliente;
import com.nombreempresa.springboot.backend.apirest.models.entity.Region;

// CrudRepository le pasamos el modelo entity Cliente, y el tipo de dato de la llave id
// en caso de usar más funcionalidades de respository como trabajar con una paginación agregamos JpaRepository en lugar de CrudRepository
public interface IClienteDao extends JpaRepository<Cliente, Long> {
	
	// como solo se va hace una consulta agregamos esta función en este IClientDao, pero si fuera más completo como un CRUD sería mejor hacerle su propio DAO a región 
	// la notación es para mapear este metodo a una consulta JPA y con eso podemos personalizar nuestra consulta
	@Query("from Region")
	public List<Region> findAllRegiones();

}
