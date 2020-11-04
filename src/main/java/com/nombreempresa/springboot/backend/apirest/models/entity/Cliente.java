package com.nombreempresa.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// indicamos que se trata de una clase Entity con el decorador.
@Entity
//este table se puede omitir si la tabla se va llamar igual que la clase, 
//pero en ese caso no se va llamar igual por eso lo agregamos.
@Table(name="clientes") 
public class Cliente implements Serializable {
	
	// indicamos que el atributo id corresponde a la llave primaria en la base de datos
	@Id
	// indicamos como se genera esta llave en la base de datos, usamos identity para que se genere de manera incremental.
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// @Column()  column solo se utiliza para cuando la columna se va llamar diferente al atributo
	// ene ste caso como queremos que el atributo se llame igual en la columna no agregamos el @Column()
	@NotEmpty(message = "no puede estar vacio")
	@Size(min=4, max=12, message = "el tamaño tiene que estar entre 4 y 12 caracteres")
	@Column(nullable=false)
	private String nombre;
	
	// nota: para poder agregar las validaciones @NotEmpty, @Size y @Email hay que agregar las dependencias en el pom.xml
	@NotEmpty(message = "no puede estar vacio")
	private String apellido;
	
	@NotEmpty(message = "no puede estar vacio")
	@Email(message = "la dirección de correo no es valida")
	@Column(nullable=false, unique=true)
	private String email;
	
	@NotNull(message = "no puede estar vacio")
	@Column(name="create_at")
	// temporal para indicar cual va ser el tipo en la base de datos.
	// por ejemplo paara tranformar una fecha de java a una fecha date de SQL
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	private String foto;
	
	// seria muchos a uno, por que muchos clientes pueden vivir en una región
	@NotNull(message = "la región no puede ser vacia")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="region_id")
	// para omitir los properties que genera el Lazy del fetch usamos la siguiente notación, si no ignoramos esos atributos va lanzar un error 
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Region region;
	
	// el siguiente evento es un prePersiste para antes de que se haga un Save o persist en al base de datos, que incluya la fecha,
	// es un evento de ciclo de vida de las clases entity.
	/*@PrePersist
	public void prePersist() {
		createAt = new Date();
	}*/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}





	/**
	 * Constante que se require cuando hacemos la importación de Serializable
	 */
	private static final long serialVersionUID = 1L;
	
}
