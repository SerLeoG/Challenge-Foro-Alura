package com.alura.foro.modelo.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosCursoTopico(
		@NotBlank
		String nombre,
		@NotBlank
		String categoria
		) {

}
