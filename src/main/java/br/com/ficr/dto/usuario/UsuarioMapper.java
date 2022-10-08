package br.com.ficr.dto.usuario;

import br.com.ficr.entities.Usuario;

public class UsuarioMapper {

	public static Usuario fromDTO(CreateUsuarioDTO dto) {
		return new Usuario(null, dto.getEmail(), dto.getSenha(), null);
	}

	public static ResponseUsuarioDTO fromEntity(Usuario obj) {
		return new ResponseUsuarioDTO(obj.getId(), obj.getEmail(), obj.getPerfil().getTipo());
	}

}
