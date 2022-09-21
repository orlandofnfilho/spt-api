package br.com.ficr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ficr.dto.auth.AuthDTO;
import br.com.ficr.dto.token.TokenDTO;
import br.com.ficr.services.AuthService;

@RestController
@RequestMapping("/spt/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

	@Autowired
	AuthService authService;

	@PostMapping
	public ResponseEntity<TokenDTO> authenticate(@RequestBody AuthDTO dto) {
		try {
			return ResponseEntity.ok(authService.auth(dto));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

}
