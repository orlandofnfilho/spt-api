package br.com.ficr.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ficr.entities.Especialidade;
import br.com.ficr.exceptions.AlreadyExistsException;
import br.com.ficr.exceptions.ResourceNotFoundException;
import br.com.ficr.repositories.EspecialidadeRepository;

@Service
@Transactional
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public Especialidade create(Especialidade obj) {
        Optional<Especialidade> especialidadesCadastrada = especialidadeRepository.findByNomeIgnoreCase(obj.getNome());
        if (especialidadesCadastrada.isPresent()) {
            throw new AlreadyExistsException("Especialidade já cadastrada: " + obj.getNome());
        }
        return especialidadeRepository.save(obj);
    }

    public List<Especialidade> findAll() {
        return especialidadeRepository.findAll();
    }

    public List<Especialidade> findAllByNome(String nome) {
        return especialidadeRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Especialidade findById(Long id) {
        Optional<Especialidade> optional = especialidadeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("Clínica não encontrada: " + id);
        }
        return optional.get();
    }

    public Especialidade update(Especialidade obj, Long id) {
        Especialidade especialidadeCadastrada = this.findById(id);
        validUpdate(obj);
        especialidadeCadastrada.setNome(obj.getNome());
        return especialidadeCadastrada;
    }

    public void validUpdate(Especialidade obj) {
        Optional<Especialidade> especialidadeCadastrada = especialidadeRepository.findByNomeIgnoreCase(obj.getNome());
        if (especialidadeCadastrada.isPresent() && obj.getId() != especialidadeCadastrada.get().getId()) {
            throw new AlreadyExistsException("Clínica já cadastrada: " + obj.getNome());
        }
    }

    public void delete(Long id) {
        Especialidade especialidadeCadastrada = this.findById(id);
        especialidadeRepository.delete(especialidadeCadastrada);
    }

}
