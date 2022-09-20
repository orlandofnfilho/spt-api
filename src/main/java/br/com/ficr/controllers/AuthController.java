package br.com.ficr.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ficr.dto.auth.AuthDTO;
import br.com.ficr.dto.token.TokenDTO;

@RestController
@RequestMapping("spt/login")
public class AuthController {

	@PostMapping
	public ResponseEntity<TokenDTO> authenticate(@RequestBody AuthDTO dto) {
		String token = " ";
		return ResponseEntity.ok(new TokenDTO(token));
	}

}
