package br.com.ficr.dto.clinica;

import java.io.Serializable;

import br.com.ficr.entities.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseClinicaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String urlLoc;
    private Endereco endereco;

}
