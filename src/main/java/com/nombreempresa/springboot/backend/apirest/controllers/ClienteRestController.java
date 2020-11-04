package com.nombreempresa.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nombreempresa.springboot.backend.apirest.models.entity.Cliente;
import com.nombreempresa.springboot.backend.apirest.models.entity.Region;
import com.nombreempresa.springboot.backend.apirest.models.services.IClienteService;
import com.nombreempresa.springboot.backend.apirest.models.services.IUploadFileService;

// CrossOrigin es el cors para poder compartir recursos entre diferentes dominios
@CrossOrigin(origins = ("http://localhost:4200"))
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	//private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 4);
		return clienteService.findAll(pageable);
	}
	
	// con la notación @secured asignamos que usuario con que rol va poder acceder a la ruta
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/clientes/{id}")
	// Se utiliza Response entity para poder manejar tipos de respuesta, por ejemplo si manda un id poder responder un usuario no existe
	// Se agregar que tipo de dato retorna pero como pueden ser varias respuestas agregamos "?" para hacer una respuesta generica
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
		
		if(cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")
	//@ResponseStatus(HttpStatus.CREATED) comento este response porque se usa el ResponseEntity en el retorno de la función
	// @Valid es para validar las indicaciones que pusimos en la clase Cliente y hay que agregar BindingResult como parametro.
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		// la creación de la fecha también se puede hacer en el Objeto Cliente lo cual hago, por eso comento la siguiente linea que crea la fecha
		// cliente.setCreateAt(new Date());
		
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			/*List<String> errors = new ArrayList<>();
			
			for(FieldError err: result.getFieldErrors()) {
				errors.add("El campo '" + err.getField() + "' "+ err.getDefaultMessage());
			}*/
			// la siguiente forma hace lo mismo de arriba solo que la siguiente forma es programación funcional
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			clienteNew = clienteService.save(cliente);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido creado con éxito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	//@ResponseStatus(HttpStatus.CREATED)
	// para usar el validador de la clase Cliente es necesario colocar el BindingResult despues de "cliente" y antes de "id" en ese orden.
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdated = null; 
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(clienteActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: ".concat(id.toString().concat("no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			
			clienteUpdated = clienteService.save(clienteActual);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente ha sido actualizado con éxito");
		response.put("cliente", clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	//@ResponseStatus(HttpStatus.NO_CONTENT)
	public  ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			// antes de borrar un cliente comprobamos si tiene foto y si tiene borramos la foto
			Cliente cliente = clienteService.findById(id);
			String nombreFotoAnterior = cliente.getFoto();
			
			// utilizamos la Interfaz de upload service para que se elimine la imagen, nota: la logica esta en UploadFileServiceImpl
			uploadService.eliminar(nombreFotoAnterior);
			
			clienteService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente fue eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Cliente cliente = clienteService.findById(id);
		
		// si el archivo es diferente a vacio entra al if, es decir si hay un archivo disponible
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				// utilizamos la Interfaz de upload service para subir la imagen, nota: la logica esta en UploadFileServiceImpl
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del cliente ");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			// comprobamos si el cliente ya tiene una foto y de ser así remplazamos la foto existente por la nueva
			String nombreFotoAnterior = cliente.getFoto();
			
			// utilizamos la Interfaz de upload service para que se elimine la imagen, nota: la logica esta en UploadFileServiceImpl
			uploadService.eliminar(nombreFotoAnterior);
			
			// al cliente que encontramos por su id a su atributo foto le asignamos el nombre del archivo y lo guardamos en la base de datos
			cliente.setFoto(nombreArchivo);
			
			clienteService.save(cliente);
			
			response.put("cliente", cliente);
			response.put("mansaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	// Función para retornar una foto, al final es nombre:.+ para con el .+ indicar que despues del nombre vamos a pasar una extención como .jp .png
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename =\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
		
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}

}
