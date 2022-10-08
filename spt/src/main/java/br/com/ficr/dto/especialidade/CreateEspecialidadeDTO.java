package br.com.ficr.dto.especialidade;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEspecialidadeDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String nome;
}
