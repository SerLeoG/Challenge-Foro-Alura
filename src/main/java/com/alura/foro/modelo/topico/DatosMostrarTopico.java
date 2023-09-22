package com.alura.foro.modelo.topico;

import java.time.LocalDateTime;

import com.alura.foro.modelo.autor.Autor;
import com.alura.foro.modelo.curso.Curso;

public record DatosMostrarTopico(
		Long id,
		String titulo,	
		String mensaje,		
		LocalDateTime fechaCreacion,
		String status,
		Autor autor,	
		Curso curso
		) {
	
	public DatosMostrarTopico(Topico topico) {
		this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getfechaCreacion(), 
				topico.getStatus().toString(), topico.getAutor(), topico.getCurso());
	}
	
}
