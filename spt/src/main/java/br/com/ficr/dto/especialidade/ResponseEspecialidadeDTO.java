package br.com.ficr.dto.especialidade;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEspecialidadeDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nome;
}
