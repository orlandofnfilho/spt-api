package br.com.ficr.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUsuarioDTO {

	private Long id;
	private String email;
	private String perfil;

}
