package br.com.ficr.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ficr.entities.Especialidade;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

    List<Especialidade> findByNomeContainingIgnoreCase(String nome);

     Optional<Especialidade> findByNomeIgnoreCase(String nome);

}
