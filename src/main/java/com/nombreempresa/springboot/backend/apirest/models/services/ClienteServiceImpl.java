package com.nombreempresa.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nombreempresa.springboot.backend.apirest.models.dao.IClienteDao;
import com.nombreempresa.springboot.backend.apirest.models.entity.Cliente;
import com.nombreempresa.springboot.backend.apirest.models.entity.Region;

// notación importante, decoramos y marcamos esta clase como un componente de servicio en spring y se va guardar en el contenedor de spring
// va quedar almacenado en el contexto y después podemos inyectar este objeto(este bean) en el controlador
@Service
public class ClienteServiceImpl implements IClienteService{

	// importamos inyección de dependencia en spring, una notación para inyectar en este caso el clienteDao
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	// nos permite manejar transacción en el metodo, el metodo de CrudRepository implementado en el clienteDao ya viene con transaccionabilidad
	// por lo tanto se puede omitir esa notación, pero lo anotamos para que sobre escriba la transacionalidad de la clase repository y tener control y hacelo de una form más esplicita.
	@Transactional(readOnly = true) 
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true) 
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDao.findAll(pageable);
	}
	
	
	@Override
	@Transactional(readOnly = true) 
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);	
	}

	@Override
	@Transactional(readOnly = true) 
	public List<Region> findAllRegiones() {
		return clienteDao.findAllRegiones();
	}

}
