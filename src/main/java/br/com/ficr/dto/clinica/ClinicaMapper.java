package br.com.ficr.dto.clinica;

import br.com.ficr.entities.Clinica;
import br.com.ficr.entities.Endereco;

public class ClinicaMapper {

    public static Clinica fromDTO(CreateClinicaDTO dto) {
        return new Clinica(null, dto.getNome(), dto.getUrlLoc(), new Endereco(null, dto.getCep(), dto.getLogradouro(),
                dto.getComplemento(), dto.getBairro(), dto.getLocalidade(), dto.getUf()));

    }

    public static ResponseClinicaDTO fromEntity(Clinica obj) {
        return new ResponseClinicaDTO(obj.getId(), obj.getNome(), obj.getUrlLoc(), obj.getEnderecoClinica());
    }
}