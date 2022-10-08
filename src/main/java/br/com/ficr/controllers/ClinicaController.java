package br.com.ficr.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.ficr.dto.clinica.ClinicaMapper;
import br.com.ficr.dto.clinica.CreateClinicaDTO;
import br.com.ficr.dto.clinica.ResponseClinicaDTO;
import br.com.ficr.entities.Clinica;
import br.com.ficr.services.ClinicaService;

@RestController
@RequestMapping("spt/clinicas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClinicaController {

    private static final String HAS_AUTHORITY_ADMIN = "hasAuthority('ADMIN')";
	private static final String HAS_ANY_AUTHORITY_ADMIN_MEDICO = "hasAnyAuthority('ADMIN','MEDICO')";

    @Autowired
    private ClinicaService clinicaService;

    @PostMapping
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<ResponseClinicaDTO> create(@RequestBody CreateClinicaDTO dto) {
        Clinica clinica = clinicaService.create(ClinicaMapper.fromDTO(dto));
        ResponseClinicaDTO response = ClinicaMapper.fromEntity(clinica);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<List<ResponseClinicaDTO>> findAll() {
        List<ResponseClinicaDTO> response = clinicaService.findAll().stream().map(ClinicaMapper::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<ResponseClinicaDTO> findById(@PathVariable Long id) {
        Clinica clinica = clinicaService.findById(id);
        ResponseClinicaDTO response = ClinicaMapper.fromEntity(clinica);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nome/{nome}")
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<List<ResponseClinicaDTO>> findAllByNome(@PathVariable String nome) {
        List<ResponseClinicaDTO> response = clinicaService.findAllByNome(nome).stream().map(ClinicaMapper::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<ResponseClinicaDTO> update(@RequestBody CreateClinicaDTO dto, @PathVariable Long id){
        Clinica clinica = clinicaService.update(ClinicaMapper.fromDTO(dto), id);
		ResponseClinicaDTO response = ClinicaMapper.fromEntity(clinica);
		return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{id}")
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<ResponseClinicaDTO> delete(@PathVariable Long id) {
        clinicaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
