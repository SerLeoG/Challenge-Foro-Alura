package com.alura.foro.modelo.usuario;

public record DatosUsuarioTopico(
		Long id,
		String nombre,		
		String email
		) {

	public DatosUsuarioTopico(Usuario usuario) {
		this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
	}
}
