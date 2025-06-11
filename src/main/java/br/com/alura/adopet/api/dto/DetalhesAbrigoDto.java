package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;

import java.util.List;

public record DetalhesAbrigoDto(
        String nome,
        String telefone,
        String email,
        List<Pet> pets) {
    public DetalhesAbrigoDto(Abrigo abrigo) {
        this(abrigo.getNome(), abrigo.getTelefone(), abrigo.getEmail(), abrigo.getPets());
    }
}
