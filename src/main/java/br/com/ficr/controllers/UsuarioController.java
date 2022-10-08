package br.com.ficr.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ficr.dto.usuario.UsuarioMapper;
import br.com.ficr.dto.usuario.CreateUsuarioDTO;
import br.com.ficr.dto.usuario.ResponseUsuarioDTO;
import br.com.ficr.entities.Usuario;
import br.com.ficr.services.UsuarioService;

@RestController
@RequestMapping("spt/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	private static final String HAS_AUTHORITY_ADMIN = "hasAuthority('ADMIN')";
	private static final String HAS_ANY_AUTHORITY_ADMIN_USUARIO = "hasAnyAuthority('ADMIN','USUARIO')";
	private static final String HAS_ANY_AUTHORITY_ADMIN_PACIENTE_MEDICO = "hasAnyAuthority('ADMIN','PACIENTE', 'MEDICO')";

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<ResponseUsuarioDTO> create(@RequestBody CreateUsuarioDTO dto) {
		Usuario usuario = usuarioService.create(UsuarioMapper.fromDTO(dto));
		ResponseUsuarioDTO response = UsuarioMapper.fromEntity(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
				.toUri();
		return ResponseEntity.created(uri).body(response);
	}

	@GetMapping
	@PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_USUARIO)
	public ResponseEntity<List<ResponseUsuarioDTO>> findAll() {
		List<ResponseUsuarioDTO> response = usuarioService.findAll().stream().map(UsuarioMapper::fromEntity)
				.collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}

	@GetMapping("{id}")
	@PreAuthorize(HAS_AUTHORITY_ADMIN)
	public ResponseEntity<ResponseUsuarioDTO> findById(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		ResponseUsuarioDTO response = UsuarioMapper.fromEntity(usuario);
		return ResponseEntity.ok(response);
	}

	@PutMapping("{id}")
	@PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_PACIENTE_MEDICO)
	public ResponseEntity<ResponseUsuarioDTO> update(@RequestBody CreateUsuarioDTO dto, @PathVariable Long id) {
		Usuario usuario = usuarioService.update(UsuarioMapper.fromDTO(dto), id);
		ResponseUsuarioDTO response = UsuarioMapper.fromEntity(usuario);
		return ResponseEntity.ok().body(response);
	}

}
