package br.com.ficr.dto.usuario;

import br.com.ficr.entities.Usuario;

public class UsuarioMapper {

	public static Usuario fromDTO(UsuarioRequestDTO dto) {
		return new Usuario(null, dto.getEmail(), dto.getSenha(), null);
	}

	public static UsuarioResponseDTO fromEntity(Usuario obj) {
		return new UsuarioResponseDTO(obj.getId(), obj.getEmail(), obj.getPerfil().getTipo());
	}

}
