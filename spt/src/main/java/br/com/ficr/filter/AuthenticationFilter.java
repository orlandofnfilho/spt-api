package br.com.ficr.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.ficr.entities.Usuario;
import br.com.ficr.services.AuthService;
import br.com.ficr.services.UsuarioService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
	
	private final AuthService authService;
	private final UsuarioService usuarioService
	;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		String token = null;
		if (header != null && header.startsWith("Bearer ")) {
			token = header.substring(7, header.length());
		}

		if (authService.verifyToken(token)) {
			Long usuarioId = authService.getUsuarioId(token);
			Usuario usuario = usuarioService.findById(usuarioId);
			SecurityContextHolder.getContext()
					.setAuthentication(new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()));
		}

		filterChain.doFilter(request, response);

	}

}
