package br.com.ficr.dto.clinica;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateClinicaDTO implements Serializable{
    private static final long serialVersionUID = 1L;

    private String nome;
    private String urlLoc;
    private String cep;
    private String logradouro;
    private String Complemento;
    private String bairro;
    private String localidade;
    private String uf;
    

}
