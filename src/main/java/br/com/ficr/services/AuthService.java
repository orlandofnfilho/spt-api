package br.com.ficr.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.ficr.dto.auth.AuthDTO;
import br.com.ficr.dto.token.TokenDTO;
import br.com.ficr.entities.Usuario;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authManager;

	@Value("${spt-api.jwt.secret}")
	private String secret;
	@Value("${spt-api.jwt.expiration}")
	private String expiration;
	@Value("${spt-api.jwt.issuer}")
	private String issuer;

	public TokenDTO auth(AuthDTO authDTO) throws AuthenticationException {
		Authentication authenticate = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getEmail(), authDTO.getSenha()));

		String token = generateToken(authenticate);
		return new TokenDTO(token);

	}

	private Algorithm createAlgorithm() {
		return Algorithm.HMAC256(secret);
	}

	private String generateToken(Authentication authenticate) {
		Usuario principal = (Usuario) authenticate.getPrincipal();
		Date today = new Date();
		Date exp = new Date(today.getTime() + Long.parseLong(expiration));
		return JWT.create().withIssuer(issuer).withExpiresAt(exp).withSubject(principal.getId().toString())
				.withClaim("perfil", principal.getPerfil().getTipo()).sign(this.createAlgorithm());
	}

	public boolean verifyToken(String token) {
		try {
			if (token == null)
				return false;
			JWT.require(this.createAlgorithm()).withIssuer(issuer).build().verify(token);
			return true;
		} catch (JWTVerificationException e) {
			return false;
		}

	}

	public Long getUsuarioId(String token) {
		String subject = JWT.require(this.createAlgorithm()).withIssuer(issuer).build().verify(token).getSubject();
		return Long.parseLong(subject);
	}

}
