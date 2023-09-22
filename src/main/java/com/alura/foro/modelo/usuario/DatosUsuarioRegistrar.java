package com.alura.foro.modelo.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosUsuarioRegistrar(
		@NotBlank
		String nombre,	
		@NotBlank
		@Email
		String email,
		@NotBlank
		String contrasena
		) {

}
