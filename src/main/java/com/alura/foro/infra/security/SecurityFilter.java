package com.alura.foro.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alura.foro.modelo.usuario.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		var authHeader = request.getHeader("Authorization"); 
		
		if (authHeader != null) {	
			var token = authHeader.replace("Bearer ", "");
			var nombreEmail = tokenService.getSubject(token);
			
			if (nombreEmail != null) {
				var usuario = usuarioRepository.findByEmail(nombreEmail);
				var authentication = new UsernamePasswordAuthenticationToken(usuario, 
						null, usuario.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request, response);	
	}

}
