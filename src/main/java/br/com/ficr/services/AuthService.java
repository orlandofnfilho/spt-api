package br.com.ficr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import br.com.ficr.dto.auth.AuthDTO;
import br.com.ficr.dto.token.TokenDTO;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authManager;

	public TokenDTO auth(AuthDTO authDTO) throws AuthenticationException {
		Authentication authenticate = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getSenha()));

		String token = generateToken(authenticate);

	}

	private String generateToken(Authentication authenticate) {
		// TODO Auto-generated method stub
		return null;
	}

}
