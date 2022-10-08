package br.com.ficr.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ficr.entities.Clinica;
import br.com.ficr.exceptions.AlreadyExistsException;
import br.com.ficr.exceptions.ResourceNotFoundException;
import br.com.ficr.repositories.ClinicaRepository;
import br.com.ficr.repositories.EnderecoRepository;

@Service
@Transactional
public class ClinicaService {

    @Autowired
    private ClinicaRepository clinicaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Clinica create(Clinica obj) {
        Optional<Clinica> clinicasCadastrada = clinicaRepository.findByNomeIgnoreCase(obj.getNome());
        if (clinicasCadastrada.isPresent()) {
            throw new AlreadyExistsException("Clínica já cadastrada: " + obj.getNome());
        }
        return clinicaRepository.save(obj);
    }

    public List<Clinica> findAll() {
        return clinicaRepository.findAll();
    }

    public List<Clinica> findAllByNome(String nome) {
        return clinicaRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Clinica findById(Long id) {
        Optional<Clinica> optional = clinicaRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Clínica não encontrada: " + id);
        }
        return optional.get();
    }

    public Clinica update(Clinica obj, Long id) {
        Clinica clinicaCadastrada = this.findById(id);
        validUpdate(obj);
        updateClinica(obj, clinicaCadastrada);
        return clinicaCadastrada;
    }

    public void updateClinica(Clinica obj, Clinica clinicaCadastrada) {
        clinicaCadastrada.setNome(obj.getNome());
        clinicaCadastrada.setUrlLoc(obj.getUrlLoc());
        clinicaCadastrada.getEnderecoClinica().setCep(obj.getEnderecoClinica().getCep());
        clinicaCadastrada.getEnderecoClinica().setLogradouro(obj.getEnderecoClinica().getLogradouro());
        clinicaCadastrada.getEnderecoClinica().setComplemento(obj.getEnderecoClinica().getComplemento());
        clinicaCadastrada.getEnderecoClinica().setBairro(obj.getEnderecoClinica().getBairro());
        clinicaCadastrada.getEnderecoClinica().setLocalidade(obj.getEnderecoClinica().getLocalidade());
        clinicaCadastrada.getEnderecoClinica().setUf(obj.getEnderecoClinica().getUf());
    }

    public void validUpdate(Clinica obj) {
        Optional<Clinica> clinicaCadastrada = clinicaRepository.findByNomeIgnoreCase(obj.getNome());
        if (clinicaCadastrada.isPresent() && obj.getId() != clinicaCadastrada.get().getId()) {
            throw new AlreadyExistsException("Clínica já cadastrada: " + obj.getNome());
        }
    }

    public void delete(Long id) {
        Clinica clinicaCadastrada = this.findById(id);
        clinicaRepository.delete(clinicaCadastrada);
    }
}
