package com.alura.foro.modelo.autor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutorTopico(
		@NotBlank
		String nombre,
		@NotBlank
		@Email
		String email
		) {

}
