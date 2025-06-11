package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CriacaoAbrigoDto;
import br.com.alura.adopet.api.dto.CriacaoPetDto;
import br.com.alura.adopet.api.dto.DetalhesPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<Abrigo>> listar() {
        return ResponseEntity.ok(abrigoService.listar());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CriacaoAbrigoDto dto) {
        try {
            this.abrigoService.cadastrar(dto);
            return ResponseEntity.ok("Abrigo cadastrado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<DetalhesPetDto>> listarPets(@PathVariable String idOuNome) {
        try {
            return ResponseEntity.ok().body(this.abrigoService.listarPets(idOuNome));
        } catch (EntityNotFoundException enfe){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CriacaoPetDto dto) {
        try {
            this.abrigoService.cadastrarPet(idOuNome, dto);
            return ResponseEntity.ok("Pet cadastrado com sucesso");
        } catch (EntityNotFoundException enfe) {
            return ResponseEntity.notFound().build();
        }
    }

}
