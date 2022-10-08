package br.com.ficr.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ficr.entities.Clinica;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Long> {

    List<Clinica> findByNomeContainingIgnoreCase(String nome);

    Optional<Clinica> findByNomeIgnoreCase(String nome);


}
