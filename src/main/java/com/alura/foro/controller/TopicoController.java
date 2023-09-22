package com.alura.foro.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.modelo.topico.DatosMostrarTopico;
import com.alura.foro.modelo.topico.DatosRegistroTopico;
import com.alura.foro.modelo.topico.Topico;
import com.alura.foro.modelo.topico.TopicoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
	
@RestController	
@RequestMapping("/topicos")
public class TopicoController {

	@Autowired
	private TopicoRepository topicoRepository;
	
	@PostMapping
	public ResponseEntity<DatosRegistroTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosTopico) {
		Topico topico = topicoRepository.save(new Topico(datosTopico));
		DatosRegistroTopico datosRegistroTopico = new DatosRegistroTopico(topico);
		
		URI url = UriComponentsBuilder.fromPath("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(url).body(datosRegistroTopico);
	}
	
	@GetMapping
	public ResponseEntity<Page<DatosMostrarTopico>> listadoTopicos(Pageable paginacion) {
		return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosMostrarTopico::new));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosMostrarTopico> retornaTopico(@PathVariable Long id) {
		DatosMostrarTopico topico = new DatosMostrarTopico(topicoRepository.getReferenceById(id));

		return ResponseEntity.ok(topico);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity actualizarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, @PathVariable Long id) {		
		Topico topico = topicoRepository.getReferenceById(id);
		topico.actualizarDatos(datosRegistroTopico);
		
		return ResponseEntity.ok(new DatosRegistroTopico(topico));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminarTopico(@PathVariable Long id) {		
		Topico topico = topicoRepository.getReferenceById(id);
		topicoRepository.delete(topico);
		
		return ResponseEntity.noContent().build();
	}
}
