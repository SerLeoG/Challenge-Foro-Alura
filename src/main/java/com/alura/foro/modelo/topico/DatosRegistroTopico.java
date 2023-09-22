package com.alura.foro.modelo.topico;

import com.alura.foro.modelo.autor.DatosAutorTopico;
import com.alura.foro.modelo.curso.DatosCursoTopico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
		@NotBlank
		String titulo,
		@NotBlank
		String mensaje,
		@NotNull
		@Valid
		DatosAutorTopico autor,
		@NotNull
		@Valid
		DatosCursoTopico curso
		) {

	public DatosRegistroTopico(Topico topico) {
		this(
				topico.getTitulo(),
				topico.getMensaje(),
				new DatosAutorTopico(topico.getAutor().getNombre(), topico.getAutor().getEmail()),
				new DatosCursoTopico(topico.getCurso().getNombre(), topico.getCurso().getCategoria())
				);
	}

}
