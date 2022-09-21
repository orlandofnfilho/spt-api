package br.com.ficr.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ficr.entities.Perfil;
import br.com.ficr.entities.Usuario;
import br.com.ficr.exceptions.AlreadyExistsException;
import br.com.ficr.exceptions.ResourceNotFoundException;
import br.com.ficr.repositories.PerfilRepository;
import br.com.ficr.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PerfilRepository perfilRepository;

	public Usuario create(Usuario obj) {
		Optional<Perfil> p1 = perfilRepository.findById(2L);
		obj.setPerfil(p1.get());
		obj.setSenha(new BCryptPasswordEncoder().encode(obj.getSenha()));
		validEmail(obj.getEmail());
		return usuarioRepository.save(obj);
	}

	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isEmpty()) {
			throw new ResourceNotFoundException("Usuário não encontrado: " + id);
		}
		return optional.get();
	}

	@Transactional(readOnly = true)
	public void validEmail(String email) {
		Optional<Usuario> optional = usuarioRepository.findByEmail(email);
		if (optional.isPresent()) {
			throw new AlreadyExistsException("Usuário já cadastrado");
		}
	}

	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		Optional<Usuario> optional = usuarioRepository.findByEmail(email);
		if (optional.isEmpty()) {
			throw new UsernameNotFoundException("Usuário não encontrado: " + email);
		}
		return optional.get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByEmail(username);
	}

}
