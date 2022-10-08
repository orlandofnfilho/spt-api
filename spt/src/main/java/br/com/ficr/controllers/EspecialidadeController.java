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

import br.com.ficr.dto.especialidade.CreateEspecialidadeDTO;
import br.com.ficr.dto.especialidade.EspecialidadeMapper;
import br.com.ficr.dto.especialidade.ResponseEspecialidadeDTO;
import br.com.ficr.entities.Especialidade;
import br.com.ficr.services.EspecialidadeService;

@RestController
@RequestMapping("spt/especialidades")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EspecialidadeController {

    private static final String HAS_AUTHORITY_ADMIN = "hasAuthority('ADMIN')";
    private static final String HAS_ANY_AUTHORITY_ADMIN_MEDICO = "hasAnyAuthority('ADMIN','MEDICO')";

    @Autowired
    private EspecialidadeService especialidadeService;

    @PostMapping
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<ResponseEspecialidadeDTO> create(@RequestBody CreateEspecialidadeDTO dto) {
        Especialidade especialidade = especialidadeService.create(EspecialidadeMapper.fromDTO(dto));
        ResponseEspecialidadeDTO response = EspecialidadeMapper.fromEntity(especialidade);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId())
                .toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<List<ResponseEspecialidadeDTO>> findAll() {
        List<ResponseEspecialidadeDTO> response = especialidadeService.findAll().stream()
                .map(EspecialidadeMapper::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<ResponseEspecialidadeDTO> findById(@PathVariable Long id) {
        Especialidade especialidade = especialidadeService.findById(id);
        ResponseEspecialidadeDTO response = EspecialidadeMapper.fromEntity(especialidade);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/nome/{nome}")
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<List<ResponseEspecialidadeDTO>> findAllByNome(@PathVariable String nome) {
        List<ResponseEspecialidadeDTO> response = especialidadeService.findAllByNome(nome).stream().map(EspecialidadeMapper::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<ResponseEspecialidadeDTO> update(@RequestBody CreateEspecialidadeDTO dto, @PathVariable Long id){
        Especialidade especialidade = especialidadeService.update(EspecialidadeMapper.fromDTO(dto), id);
		ResponseEspecialidadeDTO response = EspecialidadeMapper.fromEntity(especialidade);
		return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("{id}")
    @PreAuthorize(HAS_ANY_AUTHORITY_ADMIN_MEDICO)
    public ResponseEntity<ResponseEspecialidadeDTO> delete(@PathVariable Long id) {
        especialidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
