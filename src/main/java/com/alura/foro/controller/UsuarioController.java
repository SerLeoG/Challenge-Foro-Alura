package com.alura.foro.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.foro.modelo.usuario.DatosMostrarUsuario;
import com.alura.foro.modelo.usuario.DatosUsuarioRegistrar;
import com.alura.foro.modelo.usuario.DatosUsuarioTopico;
import com.alura.foro.modelo.usuario.Usuario;
import com.alura.foro.modelo.usuario.UsuarioRepository;
import com.alura.foro.services.EncryptService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController implements EncryptService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<DatosMostrarUsuario> registrarUsuario(@RequestBody @Valid DatosUsuarioRegistrar datosUsuarioRegistrar) {
		String hasPassword = encryptPassword(datosUsuarioRegistrar.contrasena());
		Usuario usuario = usuarioRepository.save(new Usuario(datosUsuarioRegistrar, hasPassword));
		DatosMostrarUsuario datosMostrarUsuario = new DatosMostrarUsuario(usuario);
	
		URI url = UriComponentsBuilder.fromPath("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(url).body(datosMostrarUsuario);
	}
		
	@GetMapping
	public ResponseEntity<Page<DatosUsuarioTopico>> listadoMedicos(Pageable paginacion) {
		return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosUsuarioTopico::new));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DatosMostrarUsuario> retornarUsuario(@PathVariable Long id) {
		DatosMostrarUsuario usuario = new DatosMostrarUsuario(usuarioRepository.getReferenceById(id));
		
		return ResponseEntity.ok(usuario);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity actualizarUsuario(@RequestBody @Valid DatosUsuarioRegistrar datosUsuarioRegistrar, @PathVariable Long id) {
		Usuario usuario = usuarioRepository.getReferenceById(id);
		usuario.actualizarDatos(datosUsuarioRegistrar);
	
		return ResponseEntity.ok(new DatosMostrarUsuario(usuario));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity eliminarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioRepository.getReferenceById(id);
		usuario.desactivarUsuario();
		
		return ResponseEntity.noContent().build();
	}

	@Override
	public String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	@Override
	public boolean verifyPassword(String originalPassword, String hashPassword) {
		// TODO Auto-generated method stub
		return false;
	}
}
