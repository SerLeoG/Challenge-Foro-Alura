package com.alura.foro.modelo.autor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;

@Embeddable
public class Autor {
	@Column(name = "nombre_autor")
	private String nombre;
	private String email;
	
	public Autor() {
	}

	public Autor(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
	}

	public Autor(DatosAutorTopico autor) {
		this.nombre = autor.nombre();
		this.email = autor.email();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Autor actualizarDatos(@Valid DatosAutorTopico datosAutorTopico) {
		this.nombre = datosAutorTopico.nombre();
		this.email = datosAutorTopico.email();
		
		return this;
	}
	
	
}
