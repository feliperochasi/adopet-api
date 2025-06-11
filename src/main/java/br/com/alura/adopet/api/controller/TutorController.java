package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AtualizaCadastroTutorDto;
import br.com.alura.adopet.api.dto.CadastroDeTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroDeTutorDto dto) {
        try {
            this.tutorService.cadastrar(dto);
            return ResponseEntity.ok("Tutor cadastrado com sucesso");
        } catch (ValidacaoException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid AtualizaCadastroTutorDto dto) {
        this.tutorService.atualizar(dto);
        return ResponseEntity.ok().build();
    }

}
