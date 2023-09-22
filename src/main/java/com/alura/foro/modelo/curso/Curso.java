package com.alura.foro.modelo.curso;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;

@Embeddable
public class Curso {

	@Column(name = "nombre_curso")
	private String nombre;
	
	private String categoria;

	public Curso(String nombre, String categoria) {
		this.nombre = nombre;
		this.categoria = categoria;
	}
	
	public Curso() {
	}

	public Curso(DatosCursoTopico curso) {
		this.nombre = curso.nombre();
		this.categoria = curso.categoria();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Curso actualizarDatos(@Valid DatosCursoTopico datosCursoTopico) {
		this.nombre = datosCursoTopico.nombre();
		this.categoria = datosCursoTopico.categoria();
		
		return this;
	}

}
