package br.com.ficr.dto.especialidade;

import br.com.ficr.entities.Especialidade;

public class EspecialidadeMapper {

    public static Especialidade fromDTO(CreateEspecialidadeDTO dto) {
        return new Especialidade(null, dto.getNome());
    }

    public static ResponseEspecialidadeDTO fromEntity(Especialidade obj) {
        return new ResponseEspecialidadeDTO(obj.getId(), obj.getNome());
    }

}
