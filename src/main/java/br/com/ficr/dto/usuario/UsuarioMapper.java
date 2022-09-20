package br.com.ficr.dto.usuario;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ficr.entities.Usuario;

public class UsuarioMapper {

	public static Usuario fromDTO(UsuarioRequestDTO dto) {
		return new Usuario(null, dto.getEmail(), dto.getSenha(), null);
	}

	public static UsuarioResponseDTO fromEntity(Usuario obj) {
		List<String> perfis = obj.getPerfis().stream().map(x -> x.getTipo()).collect(Collectors.toList());
		return new UsuarioResponseDTO(obj.getId(), obj.getEmail(), perfis);
	}

}
