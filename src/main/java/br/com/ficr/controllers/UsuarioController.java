package br.com.ficr.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ficr.dto.usuario.UsuarioMapper;
import br.com.ficr.dto.usuario.UsuarioRequestDTO;
import br.com.ficr.dto.usuario.UsuarioResponseDTO;
import br.com.ficr.entities.Usuario;
import br.com.ficr.services.UsuarioService;

@RestController
@RequestMapping("spt/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<UsuarioResponseDTO> create(@RequestBody UsuarioRequestDTO dto) {
		Usuario usuario = usuarioService.create(UsuarioMapper.fromDTO(dto));
		UsuarioResponseDTO response = UsuarioMapper.fromEntity(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
				.toUri();
		return ResponseEntity.created(uri).body(response);
	}

	@GetMapping
	public ResponseEntity<List<UsuarioResponseDTO>> findAll() {
		List<UsuarioResponseDTO> response = usuarioService.findAll().stream().map(UsuarioMapper::fromEntity)
				.collect(Collectors.toList());
		return ResponseEntity.ok(response);
	}

	@GetMapping("{id}")
	public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
		Usuario usuario = usuarioService.findById(id);
		UsuarioResponseDTO response = UsuarioMapper.fromEntity(usuario);
		return ResponseEntity.ok(response);
	}

}
