package br.com.ficr.dto.usuario;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioResponseDTO {

	private Long id;
	private String email;
	private List<String> perfil;

}
