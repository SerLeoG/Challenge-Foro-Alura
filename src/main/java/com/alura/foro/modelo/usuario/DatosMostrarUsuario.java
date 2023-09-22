package com.alura.foro.modelo.usuario;

public record DatosMostrarUsuario(
		Long id,
		String nombre, 
		String email
		) {

	public DatosMostrarUsuario(Usuario usuario) {
		this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
	}

}
